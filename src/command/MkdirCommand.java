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
import exception.InvalidPathException;
import shell.*;

/**
 * The mkdir command
 */
public class MkdirCommand extends Command {

  /*
    \\                    ______    //    *Poof!*
        _________________/      \
       |                        |
       |                        |
       |                        |
       |                        |
       |________________________|

     //                             \\
   */

  /**
   * Creates the mkdir command with the appropriate documentation
   */
  public MkdirCommand() {
    // The name of the mkdir command
    name = "mkdir";

    // The description of the mkdir command
    description = "Creates a directory ar the given path. The given path can"
        + " be a full path or a path relative to the working directory";

    // The parameters for the mkdir command and their description
    parameters.put("DIR", "The path that points to where the new directory"
        + " should be created.");
  }

  /**
   * Creates a new Directory defined by a path. The path can either be relative
   * to the root directory, or to the parameter currDir.
   *
   * @param state contains information on the current state of the jshell
   * @throws IllegalNumberOfArgumentsException if no arguments are given
   */
  public void run(State state) throws IllegalNumberOfArgumentsException,
      InvalidPathException {
    String[] parameters = state.getParameters();
    if (parameters.length == 0) {
      throw new IllegalNumberOfArgumentsException(
          "Mkdir requires a path to a directory to create.");
    }
    // make a new directory for each path parameter
    for (String path : parameters) {
      path = State.cleanDirectoryPath(path);
      // get the name of the new directory and the path to its parent
      String[] separatedPath = state.separatePathName(path);
      String name = separatedPath[0];
      String parentPath = separatedPath[1];
      if (name.matches("/*")) {
        throw new InvalidPathException(path);
      }
      try {
        // check that the name and parent's path are valid
        if (name.matches(State.ILLEGAL_CHARACTERS) || name
            .substring(0, name.length() - 1).contains("/")) {
          throw new IllegalNameException(name);
        }
        // get the parent directory
        Directory parentDir = Navigate.navigateToDirectory(state, parentPath);
        //System.out.println("parent data: " + parentDir.getData());
        //System.out.println("name: " + name);
        String newPath = parentDir.getData() + name;
        // check that this file does not already exist
        if (parentDir.getChild(newPath) == null) {
          // create the new directory
          parentDir.addChild(name);
          state.addMessage("Created directory: " + newPath + "\n");
        } else {
          state.addMessage(newPath + " already exists\n");
        }
      } catch (InvalidPathException e) {
        state.addMessage("Could not add directory " + path + " because "
            + parentPath + " does not exist.\n");
      } catch (IllegalNameException e) {
        state.addMessage("Could not add directory " + path
            + " because it contains illegal characters.\n");
      }
    }
  }
}
