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

public class TreeCommand extends Command {

  public TreeCommand() {
    // set the name
    name = "tree";

    // set the description
    description =
        "This returns a String representation of the current file system tree.";
  }

  /**
   * This method calls a recursive helper to generate a tree representation the
   * the current status of the file system.
   *
   * @param state is the current status of the file system
   */
  public void run(State state) {
    state
        .addResult(recHelp(state.getRoot().getData(), state.getRoot(), "\n\t"));

  }

  /**
   * This is a recursive helper function that iterates all child directories of
   * the current directory, and recursively goes through all their
   * sub-directories and adds their names to the current tree representation of
   * the file system.
   *
   * @param output is the output of the function
   * @param d is the current directory the method is accessing
   * @param adder is the string that is being added to the output to make the
   * output as intended
   * @return a tree representation of the file system as a string
   */
  private String recHelp(String output, Directory d, String adder) {
    // checks to see if the current directory has an child directories
    if (d.getChildren() != null) {
      Directory[] dirArr = d.getChildren();
      // loops through all the child directories
      for (Directory child : dirArr) {
        output += adder
            + child.getName().substring(0, child.getName().length() - 1);
        // Recursively finds any sub-directories of the children
        output = recHelp(output, child, adder + "\t");
      }

    }
    return output;
  }
}
