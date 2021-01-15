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

public class IllegalNumberOfArgumentsException extends Exception {

  /**
   * Creates the IllegalNumberOfArgumentsException, which will provide the error
   * string to be returned when thrown.
   */
  public IllegalNumberOfArgumentsException() {
    // Call the superclass with a generic error statement
    super("An illegal number of arguments were given");
  }

  /**
   * Creates the IllegalNumberOfArgumentsException with the error message
   * provided
   *
   * @param message The error message to be returned when thrown
   */
  public IllegalNumberOfArgumentsException(String message) {
    super(message);
  }

  /**
   * Creates the IllegalNumberOfArgumentsException with the message to be use
   * when the exception is thrown. Provides a more detailed string that lets the
   * user know how many arguments were provided and what was expected
   *
   * @param numGiven The number of arguments that were providedre
   * @param numExpected The number of expected arguments
   * @param numWanted The other possible number of arguments in the case of
   * commands that can take different arguments
   */
  public IllegalNumberOfArgumentsException(int numGiven, int numExpected,
      int... numWanted) {
    // Call the superclass with the detailed message
    super(createDetailedMessage(numGiven, numExpected, numWanted));
  }

  /**
   * @param numGiven The number of arguments that were provided
   * @param numExpected The number of expected arguments
   * @param extraArgs The other possible number of arguments in the case of
   * commands that can take different arguments
   * @return A detailed string telling the user how many arguments were provided
   * and all the possible number of arguments that are expected
   */
  private static String createDetailedMessage(int numGiven, int numExpected,
      int... extraArgs) {
    String message = numGiven
        + " arguments was/were given, but " + numExpected;

    /*
     * Loop through the all the number of wanted arguments, but leave the last
     * one to add an or before the last argument, and s to arguments
     */
    for (int i = 0; i < extraArgs.length; i++) {
      // Add the number of arguments
      message += ", " + extraArgs[i];
    }

    // Add given to end the sentence
    message += " was/were expected";

    return message;
  }
}
