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
package test;

import static org.junit.Assert.assertEquals;

import driver.JShell;
import exception.InvalidCommandException;
import org.junit.Test;
import shell.Interpreter;
import shell.State;

public class InterpreterTest {

  @Test
  public void test01RunInvalidCommandTest() throws InvalidCommandException {
    JShell shell = new JShell();
    State state = shell.getState();
    String actual = Interpreter.run(state, "NA asdf qwer");
    assertEquals("NA is not a valid command", actual);
  }

  @Test
  public void test02RunNoInputTest() throws InvalidCommandException {
    JShell shell = new JShell();
    State state = shell.getState();
    String actual = Interpreter.run(state, "");
    assertEquals("", actual);
  }

  @Test
  public void test03RunValidCommandTest() throws InvalidCommandException {
    JShell shell = new JShell();
    State state = shell.getState();
    String actual = Interpreter.run(state, "echo \"abcd\"");
    assertEquals("abcd", actual);
  }

  @Test
  public void test04RunValidCommandWithErrorTest()
      throws InvalidCommandException {
    JShell shell = new JShell();
    State state = shell.getState();
    String actual = Interpreter.run(state, "mkdir");
    assertEquals(
        "mkdir: Mkdir requires a path to a directory to create.", actual);
  }

}
