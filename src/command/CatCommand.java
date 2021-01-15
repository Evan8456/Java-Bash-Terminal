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
package command;

import exception.IllegalNumberOfArgumentsException;
import exception.InvalidPathException;
import shell.*;

/**
 * The cat command
 */
public class CatCommand extends Command {

  /**
   * Creates the cat command with the appropriate documentation
   */
  public CatCommand() {

    /*  ,     ,
        |\."./|
       / _   _ \   meow 
      / {|} {|} \  _
      \==  Y  ==/ ( \
       ;-._^_.-;   ) )
      /   \_/   \ / /
     _|   (*)   |/ /
    / | /|   |  | (
    | |  |   |  | |
     \|__|___|__|/
      '""'   '""'
    */

    // The name of the cat command
    name = "cat";

    // The description of the cat command
    description = "Displays the contents of the files listed seperated by 3"
        + " newlines";

    // The parameters for the cat command and their description
    parameters.put("File1 (File2, File3)", "The File to output");

  }

  /**
   * Given a File or list of Files, will return the file contents separated by 3
   * newlines
   *
   * @param state - The State of a JShell
   * @throws IllegalNumberOfArgumentsException if invalid number of arguments
   */
  public void run(State state) throws IllegalNumberOfArgumentsException {
    validateParameters(state.getParameters());
    // Create a boolean so that the line breaks only appear between separate
    // File contents
    boolean first = true;
    for (String filePath : state.getParameters()) {

      // Get the File from the user inputed path and append the contents to
      // result
      try {
        File file = Navigate.navigateToFile(state, filePath);
        if (!first) {
          state.addResult("\n\n\n");
        }
        state.addResult(file.getContents());
      } catch (InvalidPathException e) {
        if (!first) {
          state.addMessage("\n\n\n");
        }
        state.addMessage("Could not get contents of file at \"" + (filePath) +
            "\" because it does not exist\n");
      }
      first = false;
    }
  }

  /**
   * Validates a given user input and throws an appropriate error if incorrect
   *
   * @param pars - The array string of parameters
   * @throws IllegalNumberOfArgumentsException if invalid number of arguments
   */
  private void validateParameters(String[] pars)
      throws IllegalNumberOfArgumentsException {
    if (pars.length < 1) {
      // If incorrect number of arguments, throw appropriate error
      throw new IllegalNumberOfArgumentsException();
    }
  }


}
