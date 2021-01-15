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

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import command.EchoCommand;
import driver.JShell;
import exception.IllegalNumberOfArgumentsException;
import exception.InvalidCommandException;
import shell.Interpreter;

public class EchoCommandTest {

  private ExpectedException exception = ExpectedException.none();

  @Test
  public void test01GetName() {
    EchoCommand echo = new EchoCommand();

    String actual = "echo";
    String expected = echo.getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test02GetDescription() {
    EchoCommand echo = new EchoCommand();

    String actual = "Given a string in quotes, returns that string back";

    String expected = echo.getDescription();

    assertEquals(actual, expected);
  }

  @Test
  public void test03InvalidStringFormat1() throws InvalidCommandException {
    JShell jShell = new JShell();

    String actual = "echo: String must be surrounded by double quotes";
    String expected = Interpreter.run(jShell.getState(), "echo 1\" > /1");

    assertEquals(actual, expected);
  }

  @Test
  public void test04InvalidStringFormat2() throws InvalidCommandException {
    JShell jShell = new JShell();

    String actual = "echo: String must be surrounded by double quotes";
    String expected = Interpreter.run(jShell.getState(), "echo \"1 > /1");

    assertEquals(actual, expected);
  }

  @Test
  public void test05InvalidStringFormat3() throws InvalidCommandException {
    JShell jShell = new JShell();

    String actual = "echo: String must be surrounded by double quotes";
    String expected = Interpreter.run(jShell.getState(), "echo 1 > /1");

    assertEquals(actual, expected);
  }

  @Test
  public void test06EnsureFileIsCreated() throws InvalidCommandException {
    JShell jShell = new JShell();

    Interpreter.run(jShell.getState(), "echo \"1\" > 1");

    String actual = "1";
    String expected = Interpreter.run(jShell.getState(), "cat 1");

    assertEquals(actual, expected);
  }

  @Test
  public void test07EnsureStringIsAppended() throws InvalidCommandException {
    JShell jShell = new JShell();

    Interpreter.run(jShell.getState(), "echo \"1\" > 1");
    Interpreter.run(jShell.getState(), "echo \"2\" >> 1");

    String actual = "1\n2";
    String expected = Interpreter.run(jShell.getState(), "cat 1");

    assertEquals(actual, expected);
  }

  @Test
  public void test8EnsureFileIsOverwritten() throws InvalidCommandException {
    JShell jShell = new JShell();

    Interpreter.run(jShell.getState(), "echo \"1\" > 1");
    Interpreter.run(jShell.getState(), "echo \"2\" > 1");

    String actual = "2";
    String expected = Interpreter.run(jShell.getState(), "cat 1");

    assertEquals(actual, expected);
  }

  @Test
  public void test9IllegalNumberOfArgumentsEmpty()
      throws InvalidCommandException {
    JShell jShell = new JShell();

    exception.expect(IllegalNumberOfArgumentsException.class);
    Interpreter.run(jShell.getState(), "echo");
  }

  @Test
  public void test10IllegalNumberOfArgumentsTwo()
      throws InvalidCommandException {
    JShell jShell = new JShell();

    exception.expect(IllegalNumberOfArgumentsException.class);
    Interpreter.run(jShell.getState(), "echo \"string\" >");
  }

  @Test
  public void test11IllegalNumberOfArgumentsExtra()
      throws InvalidCommandException {
    JShell jShell = new JShell();

    exception.expect(IllegalNumberOfArgumentsException.class);
    Interpreter
        .run(jShell.getState(), "echo \"string\" > file_name extraparam");
  }
}
