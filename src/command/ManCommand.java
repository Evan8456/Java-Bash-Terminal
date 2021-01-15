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
import exception.InvalidCommandException;
import shell.State;

/**
 * The man command
 */
public class ManCommand extends Command {

  /*
          ___
         /   \
         \___/
         \ | /
          \|/
           |
          / \
         /   \
   */

  /**
   * Creates the man command with the appropriate documentation
   */
  public ManCommand() {
    // The name of the man command
    name = "man";

    // The description of the man command
    description = "Will print the documentation for a given command.";

    // The parameters for the man command and their description
    parameters.put("CMD", "The command to return the documentation for");
  }

  /**
   * Given a command, will return the documentation for that string
   *
   * @param state The state of the JShell
   * @throws IllegalNumberOfArgumentsException when more or less than 1
   * @throws InvalidCommandException if the given command is invalid
   */
  public void run(State state)
      throws IllegalNumberOfArgumentsException, InvalidCommandException {
    // Get the parameters
    String[] commandParams = state.getParameters();

    // Make sure the length is only 1
    if (commandParams.length != 1) {
      throw new IllegalNumberOfArgumentsException(commandParams.length, 1);
    }

    state.addResult(Command.getCommand(commandParams[0]).toString());
  }
}
