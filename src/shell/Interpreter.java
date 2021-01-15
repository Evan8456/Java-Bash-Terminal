// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: bryanch3
// UT Student #: 1004015683
// Author: Chanzo Bryan
//
// Student2:
// UTORID user_name: gaylejoe
// UT Student #: 1004029268
// Author: Joey Lakerdas-Gayle
//
// Student3:
// UTORID user_name: appleb16
// UT Student #: 1004432205
// Author: Sean Applebaum
//
// Student4:
// UTORID user_name: ngevan1
// UT Student #:1004166662
// Author: Evan Kar Long Ng
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package shell;

import command.*;
import exception.IllegalNameException;
import exception.InvalidCommandException;
import exception.InvalidPathException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class contains functions that read the user input and decide which
 * command class should be called to correctly run the user input.
 */
public abstract class Interpreter {

  /*
  __IN__                 __OUT_
  \_  _/                 \_  _/
    \ \  _______________  / /
     \ \|               |/ /
      \_  Interpreting   _/
        |   Machine     |
        |               |
        |_______________|
*/

  /**
   * Runs the command entered by the user. Calls the correct command class.
   *
   * @param state contains information on the current state of the jshell
   * @param userInput the line entered by the user
   * @return A string describing what has happened. This is an error string if
   * the user input is invalid, or the String output from the command class for
   * this command.
   */
  public static String run(State state, String userInput) {
    // make sure the return object is empty
    state.clearReturnObject();

    // parse input data
    String[] inputData = parseUserInput(userInput);
    // recognize redirection if input
    String[] redirectedInputData = recognizeRedirection(state, inputData);
    if (redirectedInputData.length == 0) {
      // user entered nothing or spaces
      return "";
    }
    // update history
    state.addCommandToHistory(userInput);
    // send input data to State
    state.setInputData(redirectedInputData);

    Command cmd;
    try {
      // get command referenced by first word in input
      cmd = Command.getCommand(state.getCommand());
    } catch (InvalidCommandException e) {
      // Set redirection type to null after finishing executing the command
      state.setRedirectionType(null);
      return state.getCommand() + " is not a valid command";
    }
    // Run the command and catch any errors
    try {
      cmd.run(state);
      // If the command is to be redirected
      if (state.getRedirectionType() != null) {
        performRedirection(state, inputData[inputData.length - 1],
            state.getResultsWithoutMessages());
      }
      // Set redirection type to null after finishing executing the command
      state.setRedirectionType(null);
      return state.getResults();
    } catch (Exception e) {
      //e.printStackTrace();
      // Set redirection type to null after finishing executing the command
      state.setRedirectionType(null);
      // Return the exception message that was caught
      return cmd.getName() + ": " + e.getMessage();
    }
  }

  /**
   * Given a split user input, recognizes redirection if it exists, and sends
   * information to the state as to how to redirect.
   *
   * @param state contains information on the current state of the jshell
   * @param inputData the line entered by the user
   * @return an array of the command and the parameters not including the
   * redirection
   */
  private static String[] recognizeRedirection(State state,
      String[] inputData) {

    if (inputData.length > 2) {
      // If to be redirected and overwritten/created
      if (inputData[inputData.length - 2].equals(">")) {
        state.setRedirectionType(">");
        return Arrays.copyOfRange(inputData, 0, inputData.length - 2);
      }
      // If to be redirected and appended/created
      else if (inputData[inputData.length - 2].equals(">>")) {
        state.setRedirectionType(">>");
        return Arrays.copyOfRange(inputData, 0, inputData.length - 2);
      }
    }
    // If no redirection, return the original user input
    return inputData;
  }


  /**
   * Given a state, file path, and file contents, performs redirection based on
   * the user input from the state
   *
   * @param state contains information on the current state of the jshell
   * @param filePath the path of the file to redirect to
   * @param contents of the file to redirect to
   */
  private static void performRedirection(State state, String filePath,
      String contents) throws IllegalNameException, InvalidPathException {

    // get the separated file info
    String[] fileInf = state.separatePathName(filePath);
    // get parent directory
    Directory parentDirectory = Navigate.navigateToDirectory(state, fileInf[1]);
    // get the file in the parent directory
    File file = parentDirectory.getChildFile(fileInf[0]);
    // If there is a child file with the given name
    if (file != null) {
      // Edit the file based on user input
      if (state.getRedirectionType().equals(">")) {
        // Overwrite file
        file.setContents(contents);
        state.setRedirectionType(null);
        //result = "Overwrote contents of " + file.getName();
      } else if (state.getRedirectionType().equals(">>")) {
        // Append file
        file.setContents(file.getContents() + "\n" + contents);
        state.setRedirectionType(null);
        //result = "Appended contents of " + file.getName();
      }
    } else {
      // create the file with the given contents if it does not exist
      file = parentDirectory.addFile(fileInf[0], contents);
    }
    //return result;
  }

  /**
   * Splits the user input into an array of the command and parameters. Splits
   * the input at spaces, strings in quotations (",") are not split.
   *
   * @param input a String of user input
   * @return an array of the command and the parameters
   */
  private static String[] parseUserInput(String input) {
    // trim input and add a single trailing space to recognize end of last word
    input = input.trim();
    input += " ";
    ArrayList<String> words = new ArrayList<>();
    // find if the substring from index start to end (start:end) is a word
    int start = 0, end = 1;
    // inString is true iff end is an index between quotation marks
    boolean inString = input.charAt(0) == '"';
    // navigate through the user input to find words
    for (; end < input.length(); end++) {
      // test if start:end is a word
      if (inString && input.charAt(end) == '"'
          || !inString && input.charAt(end) == ' ') {
        if (inString) {
          // end is a closing quotation mark - no longer in a string
          inString = false;
          words.add(input.substring(start, end + 1));
        } else {
          // end is the space after a word
          words.add(input.substring(start, end));
        }
        // update start and end to after all consecutive spaces
        start = passSpaces(input, end);
        end = start;
        // if this is a quotation mark, this is the start of a string
        if (start < input.length() && input.charAt(start) == '"') {
          inString = true;
        }
      }
    }
    // add additional word
    if (start < end - 1) {
      words.add(input.substring(start, end));
    }
    return words.toArray(new String[0]);
  }

  /**
   * Returns the index of the next non-space character in input after the given
   * index, or the length of the String if there are no more non-space
   * characters.
   *
   * @param input the string
   * @param fromIndex the index to start searching from
   * @return the first non-space index after fromIndex
   */
  private static int passSpaces(String input, int fromIndex) {
    fromIndex++;
    try {
      // loop through the String until fromIndex is a non-space character
      while (input.charAt(fromIndex) == ' ') {
        fromIndex++;
      }
    } catch (IndexOutOfBoundsException e) {
      // got to end of String
      return input.length();
    }
    return fromIndex;
  }
}
