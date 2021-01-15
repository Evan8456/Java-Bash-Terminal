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

public class InvalidCommandArgumentException extends Exception {

  /**
   * Creates the InvalidCommandArgument, which is to be used when the user
   * passes an invalid argument type to a command
   */
  public InvalidCommandArgumentException() {
    // Call the superclass with a general message for all commands
    super("One or more arguments are not valid types");
  }

  /**
   * Creates the InvalidCommandArgumentException with the given message
   *
   * @param message The message to be returned when the exception is thrown
   */
  public InvalidCommandArgumentException(String message) {
    super(message);
  }
}
