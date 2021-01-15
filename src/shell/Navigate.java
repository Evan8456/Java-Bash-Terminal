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
package shell;

import exception.InvalidPathException;

/**
 * This class contains functions to help navigate through a Directory tree
 */
public abstract class Navigate {

  /*
        _________________________________
       |                       ____      |
       |    * <-yer here      |    |     |      __       __
       |     \                 \  /      |     (  )     (  )
       |      |                 ||       |       \\     //
       |       \                ||       |        \\___//
       |        \___                     |        | o o |
       |            \                    |        | ___ |
       |             \      ___          |        //   \\
       |\             \    /   \         |      _//     \\_
       |  \            |__/     \__      |     (__)     (__)
       |    \__                    \     |
       |        \                   X    |
       |_________\_______________________|
   */

  /**
   * Splits a path into a String array of names in the path in order
   *
   * @param path the path to split
   * @return an array of the names in the path
   */
  public static String[] splitPath(String path) {
    // if path is the root
    if (path.equals("/")) {
      String[] root = new String[1];
      root[0] = "";
      return root;
    }
    // get path as array of String names
    return path.split("(?<=/)");
  }

  /**
   * Navigate to a Directory defined by a path. The path can either be relative
   * to the root directory, or to the shell's working directory. Throws
   * InvalidPathException if no such Directory exists.
   *
   * @param state contains information on the current state of the jshell
   * @param path the path to a Directory
   * @return the Directory object for the Directory defined by path
   * @throws InvalidPathException if the given path does not exist or does not
   * point to a Directory
   */
  public static Directory navigateToDirectory(State state, String path)
      throws InvalidPathException {
    if (path.equals("/")) {
      return state.getRoot();
    }
    path = State.cleanDirectoryPath(path);
    String[] names = splitPath(path);
    Directory currDir;
    // depth is the next name in the path for working directory to visit
    int depth = 0;
    if (names[0].equals("/")) {
      // path is absolute, the root is the first name in the path
      currDir = state.getRoot();
      depth = 1;
    } else {
      // path is a relative path
      currDir = state.getWorkingDirectory();
    }
    // iterate through levels of the path
    for (; depth < names.length; depth++) {
      String currentPath = currDir.getData() + names[depth];
      // check if this this name is ".."
      if (names[depth].equals("../")) {
        // go up 1 level unless this is the root
        if (currDir.isRoot()) {
          throw new InvalidPathException(path);
        }
        currDir = (Directory) currDir.getParent();
      } else if (!names[depth].equals("./")) {
        // navigate down 1 level
        // get name of current path
        Directory child = (Directory) currDir.getChild(currentPath);
        // return null if no child with this name exists
        if (child == null) {
          throw new InvalidPathException(path);
        }
        currDir = child;
      }
    }
    return currDir;
  }

  /**
   * Given a path to a file, returns the File object.
   *
   * @param state contains information on the current state of the jshell
   * @param path the path to a File
   * @return the File object for the File defined by path
   * @throws InvalidPathException if the given path does not exist or does not
   * point to a File
   */
  public static File navigateToFile(State state, String path)
      throws InvalidPathException {
    // get parent directory and name of file
    String[] separatedPath = state.separatePathName(path);
    String parentPath = separatedPath[1], name = separatedPath[0];
    Directory parent = navigateToDirectory(state, parentPath);
    // get file
    File file = parent.getChildFile(name);
    if (file == null) {
      // the parent directory does not contain a file with this name
      throw new InvalidPathException();
    }
    return file;
  }

}
