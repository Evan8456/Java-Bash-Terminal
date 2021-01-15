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

import shell.*;
import exception.IllegalNumberOfArgumentsException;
import exception.InvalidPathException;
/*
__
(  }  ______          1            2
  \\/______\     __________   __________
  |    o  o |   |        v | |        v |
  |    \_/  |   |        x | |        x |
   \_______/    |        v | |        v |
                |       ...| |       ...|
                |__________| |__________|
*/

/**
 * The LsCommand Class
 */
public class LsCommand extends Command {

  /**
   * Default constructor for LsCommand
   */

  public LsCommand() {
    name = "ls";
    description = "This returns the contents of given path";
  }

  /**
   * This function takes in an array of directories that the user put in and
   * returns a string representation of all directories separated by a new line
   *
   * @param directories is an array of all directories the user inputed
   * @return a string representation of result
   */
  private String childDirToString(State state, Directory[] directories) {
    StringBuilder result = new StringBuilder();
    for (Directory d : directories) {
      // get the name of the file
      String[] separatedData = state.separatePathName(d.getData());
      result.append(separatedData[0]);
      result.append("\n");
    }
    return result.toString();
  }

  /**
   * @param childFiles if an array of files that the user put in and returns a
   * string representation of all files separated by a new line
   * @return a string representation of result
   */
  private String getChildFiles(State state, File[] childFiles) {
    StringBuilder result = new StringBuilder();
    for (File f : childFiles) {
      // get the name of the file
      String[] separatedData = state.separatePathName(f.getName());
      result.append(separatedData[0]);
      result.append("\n");
    }
    return result.toString();
  }

  /**
   * This function returns all children directories/files of the given directory
   * as a String
   *
   * @param dir is the given directory we wish to find
   * @return a string containing all children directories and files
   */
  private String printChildren(State state, Directory dir) {
    return childDirToString(state, dir.getChildren())
        + getChildFiles(state, dir.getChildFiles());
  }

  /**
   * This function runs the LsCommand and throws and InvalidPathException if the
   * given path is not valid.
   *
   * @param state is the state of the current directory
   */
  public void run(State state) {
    String[] params = state.getParameters();
    if (params.length == 0) {
      state.addResult(printChildren(state, state.getWorkingDirectory()));
    } else if (params[0].equals("-R")) {
      String[] params2 = new String[params.length - 1];
      System.arraycopy(params, 1, params2, 0, params.length - 1);
      if (params2.length == 0) {
        params2 = new String[]{"/"};
      }
      state.addResult(getOutput(state, params2, true));
    } else {
      state.addResult(getOutput(state, params, false));
    }
  }

  /**
   * This function returns the expected output needed when ls is called. It
   * loops through all of the root children and lists out all the contents, and
   * if -R is present, recursively list out all sub-directory contents as well.
   *
   * @param state is the current status of the file system
   * @param params is the array of parameters the user inputed
   * @param isR boolean to see if the user inputed -R
   * @return the expected output when ls is called
   */
  private String getOutput(State state, String[] params, boolean isR) {
    // if user input no parameters, return contents of working directory
    if (params.length == 0) {
      return "";
    } else {
      StringBuilder result = new StringBuilder();
      // if user input parameters, loop through the array of parameters
      for (String path : params) {
        try {
          // try to get the path as a file
          String pathClone = State.cleanDirectoryPath(path);
          Directory dir = Navigate.navigateToDirectory(state, pathClone);
          result.append(dir.getPath());
          result.append(": \n");
          result.append(printChildren(state, dir));
          result.append("\n");
          if (isR) {
            result.append(getOutput(state, dir.getChildData(), true));
          }

        } catch (InvalidPathException e) {
          // try to get a directory described by this path
          try {
            File file = Navigate.navigateToFile(state, path);
            result.append(file.getPath().substring(1)).append("\n");
          } catch (InvalidPathException e1) {
            state.addMessage("The path: " + path
                + " is not valid or doesn't exist\n");
          }
        }
      }
      return result.toString();
    }
  }
}


