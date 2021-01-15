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

import java.util.EmptyStackException;
import exception.IllegalNumberOfArgumentsException;
import exception.NullStackException;
import shell.State;

/**
 * The popd command
 */
public class PopdCommand extends Command {

  /**
   * Creates the popd command with the appropriate documentation
   */
  public PopdCommand() {

    /*
                     \                    /
                      \      Command     /        *POP!*
                       \                /
                        \              /
                   ______\____________/________
                  |                /\          |
                  |\              /  \         |      __
                  | \            /    \        |     (  )
                  |  \          /      \       |     / /
                  |   \        /        \      |____/ /
                  |    \      /          \     |_____/
                  |     \    /            \    |
                  |      \  /              \   |
                  |_______\/________________\__|

     */

    // The name of the popd command
    name = "popd";

    // The description of the popd command
    description = "Will remove the first directory on the stack then change "
        + "the current work directory to it";
  }

  /**
   * Given a directory path, will push the current working directory onto the
   * directory stack then change the directory to the given directory
   *
   * @param state The state of the JShell
   * @throws IllegalNumberOfArgumentsException if parameters are given
   * @throws NullStackException if the directory stack is empty
   */
  public void run(State state)
      throws IllegalNumberOfArgumentsException, NullStackException {
    // Get the parameters of the command
    String[] stateParams = state.getParameters();

    if (stateParams.length > 0) {
      // Throw error for the number of arguments
      throw new IllegalNumberOfArgumentsException(stateParams.length, 0);
    }

    try {
      // Change to the popped directory
      state.setWorkingDirectory(state.popDirectory());
    } catch (EmptyStackException e) {
      throw new NullStackException("There are no directories on the stack");
    }
  }
}
