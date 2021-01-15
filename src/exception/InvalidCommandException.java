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
package exception;

public class InvalidCommandException extends Exception {

  /**
   * Creates the InvalidCommandArgument, which is to be used when the user
   * enters an invalid command
   */
  public InvalidCommandException() {
    // Call the superclass with a general message for all commands
    super("The command given is not valid");
  }

  /**
   * Creates the InvalidCommandArgumentException with a more detailed message
   * using the given command inputted
   *
   * @param command The command the user attempted to use
   */
  public InvalidCommandException(String command) {
    super(command + " is not a valid command");
  }
}
