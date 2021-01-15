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

import java.util.ArrayList;
import exception.InvalidCommandArgumentException;
import exception.InvalidPathException;
import shell.Directory;
import shell.File;
import shell.Navigate;
import shell.State;

public class FindCommand extends Command {

  /*
     1       __/\__
     2  |\  /      \
     3   \\/      o \
     4   //\        /
     5  |/  \__  __/
     6         \/
     7
     8
     9
     10
     11          __
     .          (  )
     .          \||/
     .           ||
     20,000     /  \
   */

  public FindCommand() {
    // Set the name
    name = "find";

    // Set the description
    description = "Finds all the files or directories that match a name that"
        + " provided";

    // Add the parameters for the description
    parameters.put("path...", "A variable number of directories to search in");
    parameters.put("-type [f|d]",
        "The type path to search for, f for file, d" + "for directory");
    parameters.put("name", "The name of the file or directory to search for");
  }

  /**
   * Searches in the given directories for all the paths that match the type and
   * name and adds the result to the state
   *
   * @param state The state of the JShell
   * @param parentDirectories The directories to search in
   * @param type The type of path to look for, f for file, d for directory
   * @param name The name to search for
   */
  public void findByName(State state, Directory[] parentDirectories,
      String type, String name) {
    // Loop through the directories
    for (Directory parent : parentDirectories) {
      // Get all the child directories
      Directory[] childDirs = parent.getChildren();

      // Find the child by the given name
      if (type.equals("f")) {
        File child = parent.getChildFile(name);
        if (child != null) {
          state.addResult(parent.getPath() + "\n");
        }
      } else {
        // Loop through all children directories
        for (Directory childDir : childDirs) {
          // Check if the name is the one we are looking for
          if (childDir.getName().equals(name)) {
            state.addResult(parent.getPath() + "\n");
          }
        }
      }

      // Look through all the child directories for the name
      findByName(state, childDirs, type, name);
    }
  }

  /**
   * Finds all the files or directories that match a given name.
   *
   * @param state The state of the JShell
   * @throws InvalidPathException If a directory given is invalid
   * @throws InvalidCommandArgumentException If the type or name is not
   * specified or if a directory was not given
   */
  public void run(State state)
      throws InvalidPathException, InvalidCommandArgumentException {
    // Get the parameters
    String[] parameters = state.getParameters();

    // Declare the directories to search in, the type and the name
    ArrayList<Directory> parentDirectories = new ArrayList<>();
    String type = "";
    String name = "";

    // Loop through all the parameters to get the values
    for (int i = 0; i < parameters.length; i++) {
      // Check for the type and name
      if ((parameters[i].equals("-name") || parameters[i].equals("-type"))
          && i != parameters.length - 1) {
        // Add the type if the current parameter is the type flag
        if (parameters[i].equals("-type")) {
          type = parameters[i + 1];
        }
        // Add the name if the current parameter is the name flag
        else if (parameters[i].equals("-name")) {
          name = parameters[i + 1];
        }
        // Increment the index twice this iteration since the type/name was
        // retrieved
        i++;
      }
      // Add the directory otherwise
      else {
        parentDirectories
            .add(Navigate.navigateToDirectory(state, parameters[i]));
      }
    }

    // Make the type and name were given
    if (type.length() == 0 || name.length() == 0) {
      throw new InvalidCommandArgumentException(
          "One or both of the type and name were not specified");
    }
    // Make sure the type is valid
    if (!type.equals("d") && !type.equals("f")) {
      throw new InvalidCommandArgumentException(
          "The path type \"" + type + "\" is invalid");
    }
    // Make sure at least one directory to search in was given
    if (parentDirectories.size() == 0) {
      throw new InvalidCommandArgumentException(
          "No directories to search in were given");
    }

    // Convert the parentDirectories to an array
    Directory[] searchDirectories =
        parentDirectories.toArray(new Directory[parentDirectories.size()]);

    // Make sure to add a slash to the end of the name if there isn't one
    // already for directories
    if (type.equals("d") && !name.endsWith("/")) {
      name = name + "/";
    }

    // Search if all the parameters are good
    findByName(state, searchDirectories, type, name);
  }
}
