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
import command.PwdCommand;
import driver.JShell;
import exception.IllegalNumberOfArgumentsException;
import exception.InvalidCommandException;
import shell.Interpreter;

public class PwdCommandTest {

  private ExpectedException exception = ExpectedException.none();

  @Test
  public void test01GetName() {
    PwdCommand pwd = new PwdCommand();

    String actual = "pwd";
    String expected = pwd.getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test02GetDescription() {
    PwdCommand pwd = new PwdCommand();

    String actual = "Will return the current working directory.";

    String expected = pwd.getDescription();

    assertEquals(actual, expected);
  }

  @Test
  public void test03RunRoot() throws InvalidCommandException {
    JShell jShell = new JShell();

    String actual = "/";
    String expected = Interpreter.run(jShell.getState(), "pwd");

    assertEquals(actual, expected);
  }

  @Test
  public void test04RunAbsolutePath() throws InvalidCommandException {
    JShell jShell = new JShell();

    Interpreter.run(jShell.getState(), "mkdir /home");
    Interpreter.run(jShell.getState(), "cd /home");

    String actual = "/home/";
    String expected = Interpreter.run(jShell.getState(), "pwd");

    assertEquals(actual, expected);
  }

  @Test
  public void test05RunRelativePath() throws InvalidCommandException {
    JShell jShell = new JShell();

    Interpreter.run(jShell.getState(), "mkdir home");
    Interpreter.run(jShell.getState(), "cd home");

    String actual = "/home/";
    String expected = Interpreter.run(jShell.getState(), "pwd");

    assertEquals(actual, expected);
  }

  @Test
  public void test06RunNestedPath() throws InvalidCommandException {
    JShell jShell = new JShell();

    Interpreter.run(jShell.getState(), "mkdir /home");
    Interpreter.run(jShell.getState(), "mkdir /home/dir");
    Interpreter.run(jShell.getState(), "mkdir /home/dir/test");
    Interpreter.run(jShell.getState(), "cd home/dir/test");

    String actual = "/home/dir/test/";
    String expected = Interpreter.run(jShell.getState(), "pwd");

    assertEquals(actual, expected);
  }

  @Test
  public void test07RunMultipleDirectories() throws InvalidCommandException {
    JShell jShell = new JShell();

    Interpreter.run(jShell.getState(), "mkdir home dir test1 test2 docs pics");
    Interpreter.run(jShell.getState(), "cd test2");

    String actual = "/test2/";
    String expected = Interpreter.run(jShell.getState(), "pwd");

    assertEquals(actual, expected);
  }

  @Test
  public void test08IllegalNumberOfArguments()
      throws InvalidCommandException {
    JShell jShell = new JShell();
    exception.expect(IllegalNumberOfArgumentsException.class);

    Interpreter.run(jShell.getState(), "pwd home");
  }
}
