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
import shell.Directory;
import shell.Navigate;
import shell.ReturnObject;
import shell.State;

/**
 * This is the CdCommand class
 */
public class CdCommand extends Command {

  /*
               _____
             /   __   \
            |   /  \   |
            |   \__/   |
             \ ______ /
   */

  /**
   * Default constructor for CdCommand
   */
  public CdCommand() {
    name = "cd";
    description = "Will change the current directory to the specified path";
  }

  /**
   * This function runs the cdCommand and changes directory to the given path
   * from state.
   *
   * @param state is the current state of the terminal
   * @throws IllegalNumberOfArgumentsException if user input to many arguments
   * @throws InvalidPathException if user inputted an nonexistent path.
   */
  public void run(State state)
      throws InvalidPathException, IllegalNumberOfArgumentsException {
    if (state.getParameters().length != 1) {
      throw new IllegalNumberOfArgumentsException();
    }
    // gets the current working directory from state
    Directory current = state.getWorkingDirectory();
    // try catch block to find if user inputted an invalid path
    try {
      // method call to change current directory
      String path = state.getParameters()[0];
      path = State.cleanDirectoryPath(path);
      state.setWorkingDirectory(Navigate.navigateToDirectory(state, path));
      state.addMessage("Changed directory to " + state.getWorkingDirectory()
          .getData());
    } catch (NullPointerException e) {
      // throws InvalidPathException if a invalid path was given
      state.setWorkingDirectory(current);
      throw new InvalidPathException(state.getParameters()[0]);
    } catch (IndexOutOfBoundsException e) {
      throw new InvalidPathException(state.getParameters()[0]);
    }
  }
}
