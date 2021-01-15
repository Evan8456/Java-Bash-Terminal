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

/**
 * NullStackException, to be thrown when a stack is empty. Can take in an error
 * string to create a message (since EmptyStackException cannot).
 */
public class NullStackException extends Exception {

  /**
   * Creates the NullStackException
   */
  public NullStackException() {
    super();
  }

  /**
   * Creates the NullStackException with the error message given
   *
   * @param message The message to be returned when the error this thrown
   */
  public NullStackException(String message) {
    super(message);
  }
}
