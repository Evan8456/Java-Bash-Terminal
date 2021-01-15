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

import shell.Directory;
import shell.Navigate;
import shell.State;
import exception.IllegalNumberOfArgumentsException;
import exception.InvalidPathException;

/**
 * The pushd command
 */
public class PushdCommand extends Command {

  /*
        _____           _________________________
       /     \         |                         |
       |    o|         |                         |
       |     |         |                         |
       |   \_|         |                         |
       |__  _|        _|                         |
         |  |________| |         Command         |
         |   __________|                         |
         |  |          |                         |
         |  |          |                         |
         /  |          |                         |
        | |\ \         |                         |
        |_| \_\        |_________________________|
   */


  /**
   * Creates the pushd command with the appropriate documentation
   */
  public PushdCommand() {
    // The name of the pushd command
    name = "pushd";

    // The description of the pushd command
    description = "Will push the current directory onto the directory stack "
        + "then change to the given directory.";

    // The parameters for the pushd command pushd their description
    parameters.put("DIR", "The directory to change to");
  }

  /**
   * Given a directory path, will push the current working directory onto the
   * directory stack then change the directory to the given directory
   *
   * @param state The state of the JShell
   * @throws IllegalNumberOfArgumentsException if more or less than 1 argument
   * is given
   * @throws InvalidPathException if the given directory is not valid
   */
  public void run(State state)
      throws IllegalNumberOfArgumentsException, InvalidPathException {
    // Get the parameters of the command
    String[] stateParams = state.getParameters();

    if (stateParams.length != 1) {
      throw new IllegalNumberOfArgumentsException(stateParams.length, 1);
    }

    // Get the new directory
    Directory directory = Navigate.navigateToDirectory(state, stateParams[0]);

    // Push the current directory onto the directory stack
    state.pushDirectory(state.getWorkingDirectory());

    // Change to the given directory, not using cd to avoid adding cd to
    // history
    state.setWorkingDirectory(directory);
  }
}
