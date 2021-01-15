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
import java.util.HashMap;
import java.util.Stack;
import command.PushdCommand;
import driver.JShell;
import exception.IllegalNumberOfArgumentsException;
import exception.InvalidCommandException;
import exception.InvalidPathException;
import shell.Directory;
import shell.Interpreter;

public class PushdCommandTest {

  private ExpectedException exception = ExpectedException.none();

  @Test
  public void test01GetName() {
    PushdCommand pushd = new PushdCommand();

    String actual = "pushd";
    String expected = pushd.getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test02GetDescription() {
    PushdCommand pushd = new PushdCommand();

    String actual = "Will push the current directory onto the directory stack "
        + "then change to the given directory.";
    String expected = pushd.getDescription();

    assertEquals(actual, expected);
  }

  @Test
  public void test03GetParameters() {
    PushdCommand pushd = new PushdCommand();

    HashMap<String, String> actual = new HashMap<>();
    actual.put("DIR", "The directory to change to");

    HashMap<String, String> expected = pushd.getParameters();

    assertEquals(actual, expected);
  }

  @Test
  public void test04RunRoot() throws InvalidCommandException {
    JShell jShell = new JShell();

    Stack<Directory> actualStack = new Stack<>();

    Interpreter.run(jShell.getState(), "pushd /");

    String actualPath = "/";

    actualStack.push(jShell.getState().getWorkingDirectory());
    String expectedPath = jShell.getState().getWorkingDirectory().getData();

    Stack<Directory> expectedStack = jShell.getState().getDirectoryStack();

    assertEquals(actualPath, expectedPath);
    assertEquals(actualStack, expectedStack);
  }

  @Test
  public void test05RunAbsolutePath() throws InvalidCommandException {
    JShell jShell = new JShell();

    Stack<Directory> actualStack = new Stack<>();

    Interpreter.run(jShell.getState(), "mkdir home");

    actualStack.push(jShell.getState().getWorkingDirectory());
    Interpreter.run(jShell.getState(), "pushd /home");

    String actualPath = "/home/";
    String expectedPath = jShell.getState().getWorkingDirectory().getData();

    Stack<Directory> expectedStack = jShell.getState().getDirectoryStack();

    assertEquals(actualPath, expectedPath);
    assertEquals(actualStack, expectedStack);
  }

  @Test
  public void test06RunRelativePath() throws InvalidCommandException {
    JShell jShell = new JShell();

    Stack<Directory> actualStack = new Stack<>();

    Interpreter.run(jShell.getState(), "mkdir home");

    actualStack.push(jShell.getState().getWorkingDirectory());
    Interpreter.run(jShell.getState(), "pushd home");

    String actualPath = "/home/";
    String expectedPath = jShell.getState().getWorkingDirectory().getData();

    Stack<Directory> expectedStack = jShell.getState().getDirectoryStack();

    assertEquals(actualPath, expectedPath);
    assertEquals(actualStack, expectedStack);
  }

  @Test
  public void test07MultipleOnStack() throws InvalidCommandException {
    JShell jShell = new JShell();

    Stack<Directory> actualStack = new Stack<>();

    Interpreter.run(jShell.getState(), "mkdir home");
    Interpreter.run(jShell.getState(), "mkdir home/dir");

    actualStack.push(jShell.getState().getWorkingDirectory());
    Interpreter.run(jShell.getState(), "pushd home");

    actualStack.push(jShell.getState().getWorkingDirectory());
    Interpreter.run(jShell.getState(), "pushd dir");

    actualStack.push(jShell.getState().getWorkingDirectory());
    Interpreter.run(jShell.getState(), "pushd /");

    String actualPath = "/";
    String expectedPath = jShell.getState().getWorkingDirectory().getData();

    Stack<Directory> expectedStack = jShell.getState().getDirectoryStack();

    assertEquals(actualPath, expectedPath);
    assertEquals(actualStack, expectedStack);
  }

  @Test
  public void test08TooFewArguments()
      throws InvalidCommandException {
    JShell jShell = new JShell();
    exception.expect(IllegalNumberOfArgumentsException.class);

    Interpreter.run(jShell.getState(), "pushd");
  }

  @Test
  public void test09TooManyArguments()
      throws InvalidCommandException {
    JShell jShell = new JShell();
    exception.expect(IllegalNumberOfArgumentsException.class);

    Interpreter.run(jShell.getState(), "pushd home dir");
  }

  @Test
  public void test10InvalidAbsolutePath()
      throws InvalidCommandException {
    JShell jShell = new JShell();
    exception.expect(InvalidPathException.class);

    Interpreter.run(jShell.getState(), "pushd /home/dir");
  }

  @Test
  public void test11InvalidRelativePath()
      throws InvalidCommandException {
    JShell jShell = new JShell();
    exception.expect(InvalidPathException.class);

    Interpreter.run(jShell.getState(), "pushd home");
  }
}
