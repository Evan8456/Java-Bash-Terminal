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

import exception.IllegalNameException;
import exception.IllegalNumberOfArgumentsException;
import exception.InvalidCommandArgumentException;
import exception.InvalidPathException;
import shell.*;

/**
 * The echo command
 */
public class EchoCommand extends Command {
  /*                                                                     ______
        _____                                                           |
       /     \                                                          |
      |     o|                                                          |
      |      |     \                                             /      |  To
      |      /    \ \                                           / /     | inf
      |     /    \ \ \                                         / / /    | and
      |     \    / / /                                         \ \ \    | beynd
      |      \    / /                                           \ \     |
      |       |    /                                             \      |
      \_______/                                                         |______
   */

  /**
   * Creates the echo command with the appropriate documentation
   */
  public EchoCommand() {
    // The name of the echo command
    name = "echo";

    // The description of the echo command
    description = "Given a string in quotes, returns that string back";

    // The parameters for the echo command and their description
    parameters.put("STRING", "The string to be returned");
  }

  /**
   * Given a string in quotes, returns that string back
   *
   * @param state the State of a JShell
   * @return the input string provided
   */
  public void run(State state)
      throws InvalidPathException, IllegalNumberOfArgumentsException,
      InvalidCommandArgumentException {
    // Get the parameters from the JShell state
    String[] pars = state.getParameters();
    // Ensure the arguments are valid
    validateParameters(pars);
    // If there is a parameter, return the string inputed by the user
    if (pars.length == 1) {
      state.addResult(getParsedString(pars[0]));
    }
  }


  /**
   * Given a string array of parameters, throw appropriate exception if they are
   * of an invalid form.
   *
   * @param pars string array of parameters
   */
  private void validateParameters(String[] pars)
      throws IllegalNumberOfArgumentsException,
      InvalidCommandArgumentException {
    if (pars.length > 1 || pars.length == 0) {
      // If incorrect number of arguments, throw appropriate error
      throw new IllegalNumberOfArgumentsException(pars.length, 1);
    } else {
      // Ensure the string starts and ends with double quotes, throw
      // appropriate error otherwise
      if (!(pars[0].charAt(0) == '\"'
          && pars[0].charAt(pars[0].length() - 1) == '\"')) {
        throw new InvalidCommandArgumentException(
            "String must be surrounded" + " by double quotes");
      }
    }
  }

  /**
   * Given a string surrounded by quotes, returns the String contained by them
   *
   * @param string surrounded by double quotes
   */
  private String getParsedString(String string) {
    return string.substring(1, string.length() - 1);
  }


}


