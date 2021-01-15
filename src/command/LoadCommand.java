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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import exception.IllegalNumberOfArgumentsException;
import shell.State;

/**
 * The load command
 */
public class LoadCommand extends Command {


  /**
   * Creates the load command with the appropriate documentation
   */
  public LoadCommand() {

    /*
        ______
        |LOAD|
            |\
            | \
            |  \
            |  _\
            |/\\
               \\
    */

    // The name of the load command
    name = "load";

    // The description of the load command
    description = "Loads the state of the shell saved at a file";

    // The parameters for the load command and their description
    parameters.put("fileName", "The file name of the state to load");

  }

  /**
   * Given a file name, loads the state at the file to the current running
   * shell
   *
   * @param state - The State of a JShell
   */
  public void run(State state) throws IllegalNumberOfArgumentsException {
    String[] pars = state.getParameters();
    validateParameters(pars);

    // If not the first command in this history
    if (state.getCommandHistory().size() != 1) {
      state.addMessage("Cannot load a state after a command has been entered");
      return;
    }

    try {
      // read object from file
      FileInputStream fis = new FileInputStream(
          "src/savedStates/" + pars[0] + ".ser");
      ObjectInputStream ois = new ObjectInputStream(fis);
      State newState = (State) ois.readObject();
      // Add the current command to the history of the new state
      newState.addCommandToHistory("load " + pars[0]);
      // Overwrite the old state to the new state
      state.mutateState(newState);
      ois.close();

    } catch (ClassNotFoundException | IOException e) {
      // If the state has not been saved under the given name
      state.addMessage("Unable to find the given state at " + pars[0]);
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
