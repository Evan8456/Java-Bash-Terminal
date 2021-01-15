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

import command.MkdirCommand;
import driver.JShell;
import exception.InvalidCommandException;
import org.junit.Test;
import shell.Interpreter;

public class MkdirCommandTest {

  private MkdirCommand mkdir = new MkdirCommand();

  @Test
  public void test01GetNameTest() {
    assertEquals(mkdir.getName(), "mkdir");
  }

  @Test
  public void test02GetDescriptionTest() {
    assertEquals(mkdir.getDescription(), "Creates a directory ar the "
        + "given path. The given path can be a full path or a path relative "
        + "to the working directory");
  }

  @Test
  public void test03CreateOneAbsoluteDirectoryTest() {
    JShell shell = new JShell();
    String actual = Interpreter
        .run(shell.getState(), "mkdir /folder_1");
    String expected = "Created directory: /folder_1/\n";
    assertEquals(expected, actual);
  }

  @Test
  public void test04CreateOneRelativeDirectoryTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir folder_1");
    String actual = Interpreter
        .run(shell.getState(), "mkdir folder_1/folder_2");
    String expected = "Created directory: /folder_1/folder_2/\n";
    assertEquals(expected, actual);
  }

  @Test
  public void test05CreateMultipleAbsoluteDirectoriesTest() {
    JShell shell = new JShell();
    String actual = Interpreter
        .run(shell.getState(), "mkdir /a /s /d");
    String expected = "Created directory: /a/\n"
        + "Created directory: /s/\n"
        + "Created directory: /d/\n";
    assertEquals(expected, actual);
  }

  @Test
  public void test06CreateMultipleRelativeDirectoriesTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /a /s /d");
    String actual = Interpreter
        .run(shell.getState(), "mkdir a/q s/w d/e");
    String expected = "Created directory: /a/q/\n"
        + "Created directory: /s/w/\n"
        + "Created directory: /d/e/\n";
    assertEquals(expected, actual);
  }

  @Test
  public void test07InvalidPathTest() {
    JShell shell = new JShell();
    String actual = Interpreter
        .run(shell.getState(), "mkdir /a/b/c/d");
    String expected =
        "Could not add directory /a/b/c/d/ because /a/b/c/ does not exist.\n";
    assertEquals(expected, actual);
  }

  @Test
  public void test08DirectoryAlreadyExistsTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /a");
    String actual = Interpreter.run(shell.getState(), "mkdir a");
    String expected = "/a/ already exists\n";
    assertEquals(expected, actual);
  }
}
