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
import shell.Directory;
import shell.File;
import shell.Navigate;
import shell.State;

/**
 * The move command
 */
public class MvCommand extends Command {

  /**
   * Creates the move command with the appropriate documentation
   */
  public MvCommand() {
    // The name of the move command
    name = "mv";

    // The description of the move command
    description = "Moves a Directory or File to a new location";

    // The parameters for the move command and their description
    parameters.put("OLDPATH", "The location of the original directory.");
    parameters.put("NEWPATH", "The location to place the directory.");
  }

  /**
   * Moves a directory from one path to another. Removes the original file. The
   * root cannot be moved.
   *
   * @param state The state of the JShell
   * @throws IllegalNumberOfArgumentsException iff there aren't exactly 2
   * parameters
   * @throws InvalidPathException if either of the given paths don't point to
   * valid locations
   */
  public void run(State state)
      throws IllegalNumberOfArgumentsException, InvalidPathException, IllegalNameException {
    String[] parameters = state.getParameters();
    if (parameters.length != 2) {
      throw new IllegalNumberOfArgumentsException("Copy requires a path to the"
          + " original directory and a path to the new directory.");
    }
    // move target to destination
    String targetPath = parameters[0];
    String destinationPath = State.cleanDirectoryPath(parameters[1]);
    // get required path objects
    String[] separatedFromPath;
    try {
      separatedFromPath = state.separatePathName(
          Navigate.navigateToDirectory(state, targetPath).getPath());
    } catch (InvalidPathException e) {
      separatedFromPath = state.separatePathName(
          Navigate.navigateToFile(state, targetPath).getPath());
    }
    String targetName = separatedFromPath[0];
    String targetParentPath = separatedFromPath[1];
    Directory destinationParent =
        Navigate.navigateToDirectory(state, destinationPath);
    Directory targetParent =
        Navigate.navigateToDirectory(state, targetParentPath);
    // try to get the child as a file and as a directory
    File targetFile = targetParent.getChildFile(targetName);
    if (targetFile == null) {
      // target must be a directory
      Directory targetDir = (Directory) targetParent
          .getChild(targetParent.getData() + targetName);
      if (targetDir == null || targetDir.isAncestorOf(destinationParent)) {
        throw new InvalidPathException(targetPath);
      }
      destinationParent.addChild(targetDir);
    } else {
      // target is a file
      targetFile.setParent(destinationParent);
    }
    state.addMessage("Moved " + targetPath + " to " + destinationPath);
  }
}
