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
import java.util.Stack;
import command.PopdCommand;
import driver.JShell;
import exception.IllegalNumberOfArgumentsException;
import exception.InvalidCommandException;
import shell.Directory;
import shell.Interpreter;

public class PopdCommandTest {

  private ExpectedException exception = ExpectedException.none();

  @Test
  public void test01GetName() {
    PopdCommand popd = new PopdCommand();

    String actual = "popd";
    String expected = popd.getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test02GetDescription() {
    PopdCommand popd = new PopdCommand();

    String actual = "Will remove the first directory on the stack then change "
        + "the current work directory to it";

    String expected = popd.getDescription();

    assertEquals(actual, expected);
  }

  @Test
  public void test03RunRoot() throws InvalidCommandException {
    JShell jShell = new JShell();

    Stack<Directory> actualStack = new Stack<>();

    Interpreter.run(jShell.getState(), "mkdir home");
    Interpreter.run(jShell.getState(), "pushd home");
    Interpreter.run(jShell.getState(), "popd");

    String actualPath = "/";
    String expectedPath = jShell.getState().getWorkingDirectory().getData();

    Stack<Directory> expectedStack = jShell.getState().getDirectoryStack();

    assertEquals(actualPath, expectedPath);
    assertEquals(actualStack, expectedStack);
  }

  @Test
  public void test04RunAbsolutePath() throws InvalidCommandException {
    JShell jShell = new JShell();

    Stack<Directory> actualStack = new Stack<>();

    Interpreter.run(jShell.getState(), "mkdir /home /dir");
    Interpreter.run(jShell.getState(), "cd /home");
    Interpreter.run(jShell.getState(), "pushd /dir");
    Interpreter.run(jShell.getState(), "popd");

    String actualPath = "/home/";
    String expectedPath = jShell.getState().getWorkingDirectory().getData();

    Stack<Directory> expectedStack = jShell.getState().getDirectoryStack();

    assertEquals(actualPath, expectedPath);
    assertEquals(actualStack, expectedStack);
  }

  @Test
  public void test05RunRelativePath() throws InvalidCommandException {
    JShell jShell = new JShell();

    Stack<Directory> actualStack = new Stack<>();

    Interpreter.run(jShell.getState(), "mkdir home dir");
    Interpreter.run(jShell.getState(), "cd home");
    Interpreter.run(jShell.getState(), "pushd dir");
    Interpreter.run(jShell.getState(), "popd");

    String actualPath = "/home/";
    String expectedPath = jShell.getState().getWorkingDirectory().getData();

    Stack<Directory> expectedStack = jShell.getState().getDirectoryStack();

    assertEquals(actualPath, expectedPath);
    assertEquals(actualStack, expectedStack);
  }

  @Test
  public void test06MultipleOnStack() throws InvalidCommandException {
    JShell jShell = new JShell();

    Stack<Directory> actualStack = new Stack<>();

    Interpreter.run(jShell.getState(), "mkdir home");
    Interpreter.run(jShell.getState(), "mkdir home/dir");

    actualStack.push(jShell.getState().getWorkingDirectory());
    Interpreter.run(jShell.getState(), "pushd home");

    actualStack.push(jShell.getState().getWorkingDirectory());
    Interpreter.run(jShell.getState(), "pushd dir");

    Interpreter.run(jShell.getState(), "pushd /");
    Interpreter.run(jShell.getState(), "popd");

    String actualPath = "/home/dir/";
    String expectedPath = jShell.getState().getWorkingDirectory().getData();

    Stack<Directory> expectedStack = jShell.getState().getDirectoryStack();

    assertEquals(actualPath, expectedPath);
    assertEquals(actualStack, expectedStack);
  }

  @Test
  public void test07TooManyArguments()
      throws InvalidCommandException {
    JShell jShell = new JShell();
    exception.expect(IllegalNumberOfArgumentsException.class);

    Interpreter.run(jShell.getState(), "popd home");
  }

  @Test
  public void test08NullStack()
      throws InvalidCommandException {
    JShell jShell = new JShell();
    exception.expect(IllegalNumberOfArgumentsException.class);

    Interpreter.run(jShell.getState(), "popd");
  }
}
