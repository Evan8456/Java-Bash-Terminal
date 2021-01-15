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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import exception.IllegalNameException;
import exception.IllegalNumberOfArgumentsException;
import shell.State;

/**
 * The save command
 */
public class SaveCommand extends Command {


  /**
   * Creates the save command with the appropriate documentation
   */
  public SaveCommand() {

    /*
        ______
        |SAVE|
            |\
            | \
            |  \
            |  _\
            |/\\
               \\
    */

    // The name of the save command
    name = "save";

    // The description of the save command
    description = "Saves the current state of the shell";

    // The parameters for the save command and their description
    parameters.put("fileName", "The file name to save the state to");

  }

  /**
   * Given a file name, saves the current state to a file at the given name
   *
   * @param state - The State of a JShell
   * @throws IllegalNumberOfArgumentsException when invalid number of args
   * @throws IllegalNameException when file name is invalid
   */
  public void run(State state) throws IllegalNumberOfArgumentsException,
      IllegalNameException {

    String[] pars = state.getParameters();

    validateParameters(pars);

    if (pars[0].matches(State.ILLEGAL_CHARACTERS) || name.contains("/")) {
      throw new IllegalNameException(pars[0]);
    }

    try {
      // write object to file
      FileOutputStream fos = new FileOutputStream(
          "Assignment2/src/savedStates/" + pars[0] + ".ser");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(state);
      oos.close();

    } catch (IOException e) {
      // If there is a problem accessing the relative directory src/savedStates
      state.addMessage("Unable to save the given state at " + pars[0]);
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
    if (pars.length != 1) {
      // If incorrect number of arguments, throw appropriate error
      throw new IllegalNumberOfArgumentsException(pars.length, 1);
    }
  }
}
