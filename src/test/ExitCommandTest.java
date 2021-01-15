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

import org.junit.Test;
import org.junit.rules.ExpectedException;
import command.ExitCommand;
import driver.JShell;
import exception.IllegalNumberOfArgumentsException;
import exception.InvalidCommandException;
import shell.Interpreter;

public class ExitCommandTest {

  private ExpectedException exception = ExpectedException.none();

  @Test
  public void test01GetName() {
    ExitCommand exit = new ExitCommand();

    String actual = "exit";
    String expected = exit.getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test02GetDescription() {
    ExitCommand exit = new ExitCommand();

    String actual = "Will close the current shell";

    String expected = exit.getDescription();

    assertEquals(actual, expected);
  }

  @Test
  public void test03EnsureJShellIsClosed() throws InvalidCommandException {
    JShell jShell = new JShell();

    Interpreter.run(jShell.getState(), "exit");

    Boolean actual = true;
    Boolean expected = jShell.getState().getExit();

    assertEquals(actual, expected);
  }


  @Test
  public void test04IllegalNumberOfArgument()
      throws InvalidCommandException {
    JShell jShell = new JShell();

    exception.expect(IllegalNumberOfArgumentsException.class);
    Interpreter.run(jShell.getState(), "exit 1");
  }

  @Test
  public void test05IllegalNumberOfStringArgument()
      throws InvalidCommandException {
    JShell jShell = new JShell();

    exception.expect(IllegalNumberOfArgumentsException.class);
    Interpreter.run(jShell.getState(), "exit \"string\"");
  }

  @Test
  public void test06IllegalNumberOfArguments()
      throws InvalidCommandException {
    JShell jShell = new JShell();

    exception.expect(IllegalNumberOfArgumentsException.class);
    Interpreter.run(jShell.getState(), "exit \"string\" 1 2 3");
  }
}
