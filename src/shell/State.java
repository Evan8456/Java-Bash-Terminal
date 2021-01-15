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

import java.util.Arrays;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

/*
            _____
          _/     -----__                                                     _
       _ /               \ __                                               / \
     /  /_                   \                                             |  |
   |       -                  \                                           /   |
  /          \___   _          |                             ___________/| ME |
  |              |_| \          \                       __---  |     |   |   /
   \                 /           |                     /       | *  /    | * |
     \     WI       /            |            *____---/        |VT | NH  | _/
      |             |     MI      |           _\               |   |  *  |/
      |      *      |             |        __/        NY       |___|____/
       \____________|     *      /     ___/ |                  |  MA    |
        \          _|__________/ \__--/  |  |_____________   * |_______*|
        _|         |       |             |                |    |  *  | \ |
       |           |       |             |      PA         \   | CT  | *||
       _|   IL     |  IN   |     OH      |                  /\ |__--_|-\ /
     _/            |       |             |          *      |  /_____/   \
    |     *        |   *   |     *      /|________________/* /_          RI
     \             |       |          /      | ___  MD   | |NJ |
      \__          |       |         |       |/   |__  * | |_  /
        /          |       |       /    WV       /  |    | * |/
        \         /      /   \   /  *        /|/    |    |___------ DE
         \      /__ --/   *    - \          /        --______/
          |   /                    ---___--/                |
           |--        KY         __/          VA       *    |
           |     ______________--____________________________\
           |_____|                  /                        |
           /          *           |              *           |
         /        TN            /      _____         NC       |
       /______________________/_____---     |____           /
      /          |        |        /               \      /
    /            |         \       \                 \__/
   /             |         |          \      *         /
  |              |          \   *        \     SC    /
  |      MS      |           |              \      /
   \             |    AL     |                \__/
    |   *        |          |                   |
   /             |     *    |       GA          /
  /              |          |                  |
 |_______        |    ______|               __/
        /        |    \      \__________   |
        \        |    |        *        \_/ \
         \_______|____/___    ___            \
                          \__/   \           |
                                   \   FL    \
                                    \__       |
                                       |      \
                                      |        |
                                      \         \
                                       \        |
                                         \       \
                                           \      |
                                             \    |
                                              \__/
 */


/**
 * This is a data class that holds information about the current state of the
 * jShell
 */
public class State implements Serializable {

  // default serialVersion id
  private static final long serialVersionUID = 1L;
  // a string that matches with this regex contains illegal characters
  public static final String ILLEGAL_CHARACTERS = ".*[. !@#$%^&*(){}~|<>?].*";
  // the root is a tree of paths to directories and files
  private Directory root;
  // the current working directory of the jShell
  private Directory workingDirectory;
  // first word entered in the user input - indicates which command should run
  private String command;
  // array of all other words entered in the user input
  private String[] parameters;
  // exit is true iff the user has told the jShell to close
  private boolean exit;
  private Stack<Directory> directoryStack;
  // Contains > or >> depending on the redirection type of the next command
  private String redirectionType;
  // Contains the return object to be returned
  private ReturnObject returnObject;

  // The command history
  private ArrayList<String> commandHistory;

  /**
   * Creates the state of a newly created jShell given the root directory
   */
  public State() {
    this.root = Directory.createRoot();
    this.setExit(false);
    this.directoryStack = new Stack<>();
    this.returnObject = new ReturnObject();
    workingDirectory = this.root;

    // Create a new ArrayList for the command history
    commandHistory = new ArrayList<>();
  }

  /**
   * Get the root
   *
   * @return the root of the jShell's directory tree
   */
  public Directory getRoot() {
    return root;
  }

  /**
   * Get the current working directory
   *
   * @return the jShell's current working directory
   */
  public Directory getWorkingDirectory() {
    return workingDirectory;
  }

  /**
   * Change the jShell's working directory
   *
   * @param workingDirectory the new working directory
   */
  public void setWorkingDirectory(Directory workingDirectory) {
    this.workingDirectory = workingDirectory;
  }

  /**
   * Get cleaned user input data. Set command and parameter instance variables
   *
   * @param inputData an array of Strings of the space separated user input
   */
  public void setInputData(String[] inputData) {
    // separate input into command and parameters
    String command = inputData[0];
    String[] parameters = Arrays.copyOfRange(inputData, 1, inputData.length);
    this.command = command;
    this.parameters = parameters;
  }

  /**
   * Add a new command to the command history
   *
   * @param command The users string input into the JShell for a command
   */
  public void addCommandToHistory(String command) {
    commandHistory.add(command);
  }

  /**
   * Pushes the given directory onto the directory stack
   *
   * @param directory The directory to be pushed on the stack
   */
  public void pushDirectory(Directory directory) {
    directoryStack.push(directory);
  }

  /**
   * Get the command entered by the user
   *
   * @return the command entered by the user
   */
  public String getCommand() {
    return command;
  }

  /**
   * Get an array of parameters entered by the user
   *
   * @return the parameters entered by the user
   */
  public String[] getParameters() {
    return parameters;
  }

  /**
   * Get the current exit state
   *
   * @return true iff the user has told the jShell to close
   */
  public boolean getExit() {
    return exit;
  }

  /**
   * Returns the command history
   *
   * @return The command history
   */
  public ArrayList<String> getCommandHistory() {
    return commandHistory;
  }

  /**
   * Returns the redirection type of the command to be run and null otherwise
   *
   * @return the redirection type of the command to be run and null otherwise
   */
  public String getRedirectionType() {
    return redirectionType;
  }

  /**
   * Set the redirection type for the command to be run
   *
   * @param redirectionType for the command to be run
   */
  public void setRedirectionType(String redirectionType) {
    this.redirectionType = redirectionType;
  }

  /**
   * Returns the last directory that was added to the directory stack
   *
   * @return The last directory added to the directory stack
   */
  public Directory popDirectory() {
    return directoryStack.pop();
  }

  /**
   * Returns the directory stack
   *
   * @return The directory stack
   */
  public Stack<Directory> getDirectoryStack() {
    return directoryStack;
  }

  /**
   * Tell the jShell whether or not to close
   *
   * @param exit true iff the user has told the jShell to close
   */
  public void setExit(boolean exit) {
    this.exit = exit;
  }

  /**
   * Separate the path of a location into the absolute path to the location's
   * parent, and a relative path from the parent to the location
   *
   * @param path a given path
   * @return an array of the relative path from the parent and the path of the
   * parent. The name and path will be properly formatted
   */
  public String[] separatePathName(String path) {
    // check if this is the path to the root
    if (path.equals("/")) {
      return new String[]{"/", ""};
    }
    String name, parentPath;
    int lastSlash = path.lastIndexOf('/');
    if (lastSlash == path.length() - 1) {
      lastSlash = path.lastIndexOf('/', lastSlash - 1);
    }
    if (lastSlash == -1) {
      // path is relative
      name = path;
      parentPath = workingDirectory.getData();
    } else if (path.charAt(0) == '/') {
      // path is absolute
      name = path.substring(lastSlash + 1);
      parentPath = path.substring(0, lastSlash + 1);
    } else {
      name = path.substring(lastSlash + 1);
      parentPath =
          workingDirectory.getData() + path.substring(0, lastSlash + 1);
    }
    return new String[]{name, parentPath};
  }

  /**
   * Converts a given path to the proper form of a Directory path. Only use this
   * if the path is definitely for a Directory
   *
   * @param path the given path
   * @return the cleaned path
   */
  public static String cleanDirectoryPath(String path) {
    if (!path.endsWith("/")) {
      path += "/";
    }
    return path;
  }

  /**
   * Converts a given path to the proper form of a File path. Only use this if
   * the path is definitely for a File
   *
   * @param path the given path
   * @return the cleaned path
   */
  public static String cleanFilePath(String path) {
    if (path.endsWith("/")) {
      path = path.substring(0, path.length() - 1);
    }
    return path;
  }

  /**
   * Mutate the State to a new State
   *
   * @param state - the new state to mutate to
   */
  public void mutateState(State state) {
    this.root = state.getRoot();
    this.setExit(state.getExit());
    this.directoryStack = state.directoryStack;
    this.workingDirectory = state.getWorkingDirectory();
    this.commandHistory = state.commandHistory;
  }

  /**
   * Returns whether or not a given object is equal to this State
   *
   * @param obj An object to compare to
   * @return true iff the object is equal to this State
   */
  @Override
  public boolean equals(Object obj) {
    // check for null
    if (obj == null) {
      System.out.println("State.equals() null fail");
      return false;
    }
    // check that these objects are both States
    if (getClass() != obj.getClass()) {
      System.out.println("State.equals() class fail");
      return false;
    }
    // cast to State
    State other = (State) obj;
    // check if these have the same root stored
    if (!this.root.equals(other.root)) {
      System.out.println("State.equals() root fail");
      return false;
    }
    // check if these have the same exit state
    if (this.getExit() != (other.getExit())) {
      System.out.println("State.equals() exit fail");
      return false;
    }
    // check if these have the same directory stack
    if (!this.directoryStack.equals(other.directoryStack)) {
      System.out.println("State.equals() dirStack fail");
      return false;
    }
    // check if these have the same working directory
    return this.getWorkingDirectory().equals(other.getWorkingDirectory());
  }

  /**
   * Get the command's return String
   *
   * @return text returned by the command
   */
  public String getResults() {
    return returnObject.getResults();
  }

  /**
   * Get the command's return String without any error text
   *
   * @return non-error text returned by the command
   */
  public String getResultsWithoutMessages() {
    return returnObject.getResultsWithoutMessages();
  }

  /**
   * Add a text message to the command's output
   *
   * @param message new error message
   */
  public void addMessage(String message) {
    returnObject.addMessage(message);
  }

  /**
   * Add a result to the command's output
   *
   * @param message new non-error message
   */
  public void addResult(String message) {
    returnObject.addResult(message);
  }

  /**
   * Delete all return text
   */
  public void clearReturnObject() {
    returnObject.clear();
  }
}
