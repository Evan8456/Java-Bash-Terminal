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
import shell.State;

/**
 * The pwd command
 */
public class PwdCommand extends Command {

  /*
         ____________
        /  Working  /
       | Directory |
     __|___________|__
    |                 |
    |   |__________|  |
    |_________________|
   */

  /**
   * Creates the pwd command with the appropriate documentation
   */
  public PwdCommand() {
    // The name of the pwd command
    name = "pwd";

    // The description of the pwd command
    description = "Will return the current working directory.";
  }

  /**
   * Will return the user's current working directory
   *
   * @param state The state of the JShell
   * @throws IllegalNumberOfArgumentsException if parameters are given
   */
  public void run(State state) throws IllegalNumberOfArgumentsException {
    // Get the parameters, which should be an empty list
    String[] stateParams = state.getParameters();

    // Throw IllegalNumberOfArgumentsException when parameters are given
    if (stateParams.length > 0) {
      throw new IllegalNumberOfArgumentsException(stateParams.length, 0);
    }

    state.addResult(state.getWorkingDirectory().getData());
  }
}
