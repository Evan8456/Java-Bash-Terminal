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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import command.Command;
import command.FindCommand;
import driver.JShell;
import exception.InvalidCommandArgumentException;
import exception.InvalidCommandException;
import exception.InvalidPathException;
import shell.Interpreter;
import shell.State;


public class FindCommandTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void test01FindName() {
    FindCommand find = new FindCommand();

    String actual = "find";
    String expected = find.getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test02FindDescription() {
    FindCommand find = new FindCommand();

    String actual = "Finds all the files or directories that match a name that"
        + " provided";
    String expected = find.getDescription();

    assertEquals(actual, expected);
  }

  @Test
  public void test03RunDirectory() {
    JShell jShell = new JShell();
    Interpreter.run(jShell.getState(), "mkdir test");

    String actual = "/";

    String expected =
        Interpreter.run(jShell.getState(), "find / -type d -name test").trim();

    assertEquals(actual, expected);
  }

  @Test
  public void test04RunFile() {
    JShell jShell = new JShell();
    Interpreter.run(jShell.getState(), "echo \"\" > test");

    String actual = "/";

    String expected =
        Interpreter.run(jShell.getState(), "find / -type f -name test").trim();

    assertEquals(actual, expected);
  }

  @Test
  public void test05RunNotRoot() {
    JShell jShell = new JShell();
    Interpreter.run(jShell.getState(), "mkdir test");
    Interpreter.run(jShell.getState(), "echo \"\" > test/file1");

    String actual = "/test/";

    String expected = Interpreter
        .run(jShell.getState(), "find test -type f -name file1").trim();

    assertEquals(actual, expected);
  }

  @Test
  public void test06RunAbsolutePath() {
    JShell jShell = new JShell();
    Interpreter.run(jShell.getState(), "mkdir test");
    Interpreter.run(jShell.getState(), "echo \"\" > test/file1");

    String actual = "/test/";

    String expected = Interpreter
        .run(jShell.getState(), "find /test -type f -name file1").trim();

    assertEquals(actual, expected);
  }

  @Test
  public void test07RunMultipleDirectories() {
    JShell jShell = new JShell();
    Interpreter.run(jShell.getState(), "mkdir dir1 dir2 dir3");
    Interpreter.run(jShell.getState(), "mkdir dir2/inner");
    Interpreter.run(jShell.getState(), "mkdir dir1/low dir2/inner/low");

    String actual = "/dir1/\n/dir2/inner/";

    String expected = Interpreter
        .run(jShell.getState(), "find dir1 dir2/inner -type d -name low")
        .trim();

    assertEquals(actual, expected);
  }

  @Test
  public void test08RunInnerDirectories() {
    JShell jShell = new JShell();
    Interpreter.run(jShell.getState(), "mkdir dir1 dir2 dir3");
    Interpreter.run(jShell.getState(), "mkdir dir2/inner");
    Interpreter.run(jShell.getState(), "mkdir dir1/low dir2/inner/low");

    String actual = "/dir1/\n/dir2/inner/";

    String expected = Interpreter
        .run(jShell.getState(), "find / -type d -name low")
        .trim();

    assertEquals(actual, expected);
  }

  @Test
  public void test09RunNoMatches() {
    JShell jShell = new JShell();
    Interpreter.run(jShell.getState(), "mkdir test");

    String actual = "";

    String expected = Interpreter
        .run(jShell.getState(), "find / -type d -name \"dir\"").trim();

    assertEquals(actual, expected);
  }

  @Test
  public void test10RunInvalidPath() throws InvalidCommandException,
      InvalidPathException, InvalidCommandArgumentException {
    FindCommand find = (FindCommand) Command.getCommand("find");
    JShell jShell = new JShell();
    State state = jShell.getState();

    state.setInputData(
        new String[]{"find", "/as.ss", "-type", "d", "-name", "test"});
    exception.expect(InvalidPathException.class);

    find.run(state);
  }

  @Test
  public void test11RunInvalidType() throws InvalidCommandException,
      InvalidCommandArgumentException, InvalidPathException {
    FindCommand find = (FindCommand) Command.getCommand("find");
    JShell jShell = new JShell();
    State state = jShell.getState();

    Interpreter.run(state, "mkdir /as");
    state.setInputData(
        new String[]{"find", "/as", "-type", "e", "-name", "test"});
    exception.expect(InvalidCommandArgumentException.class);

    find.run(state);
  }
}
