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
import exception.InvalidCommandArgumentException;
import shell.ReturnObject;
import shell.State;

/**
 * The exit command
 */
public class ExitCommand extends Command {

  /*
  _________________________________________
  |   ______    __    __   _    ________   |
  |  |  ____|   \ \  / /  | |  |___  ___|  |
  |  | |____     \ \/ /   | |     |  |     |
  |  |  ____|     |  |    | |     |  |     |
  |  | |____     / /\ \   | |     |  |     |
  |  |______|   /_/  \_\  |_|     |__|     |
  |________________________________________|

   */

  /**
   * Creates the man command with the appropriate documentation
   */
  public ExitCommand() {
    // The name of the exit command
    name = "exit";

    // The description of the exit command
    description = "Will close the current shell";

  }

  /**
   * Closes the JShell of the given State
   *
   * @param state - The State of a JShell
   * @return A message indicating the Shell is closed
   */
  public void run(State state) throws IllegalNumberOfArgumentsException,
      InvalidCommandArgumentException {
    // Ensure the input does not contain extra arguments
    validateParameters(state.getParameters());
    // Set the given state's exit to true
    if (state.getRedirectionType() == null) {
      state.setExit(true);
      state.addMessage("Shell closed");
    } else {
      throw new
          InvalidCommandArgumentException("exit does not support redirection");
    }
  }

  /**
   * Validates a given user input and throws an appropriate error if incorrect
   *
   * @param pars - The array string of parameters
   */
  private void validateParameters(String[] pars)
      throws IllegalNumberOfArgumentsException {
    if (pars.length != 0) {
      // If incorrect number of arguments, throw appropriate error
      throw new IllegalNumberOfArgumentsException(pars.length, 0);
    }
  }
}
