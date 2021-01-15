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
import command.CatCommand;
import driver.JShell;
import exception.IllegalNumberOfArgumentsException;
import exception.InvalidCommandException;
import shell.Interpreter;

public class CatCommandTest {

  private ExpectedException exception = ExpectedException.none();

  @Test
  public void test01GetName() {
    CatCommand cat = new CatCommand();

    String actual = "cat";
    String expected = cat.getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test02GetDescription() {
    CatCommand cat = new CatCommand();

    String actual = "Displays the contents of the files listed seperated by "
        + "3 newlines";

    String expected = cat.getDescription();

    assertEquals(actual, expected);
  }

  @Test
  public void test03GetFileInRootFromRoot() throws InvalidCommandException {
    JShell jShell = new JShell();

    // Create the file
    Interpreter.run(jShell.getState(), "echo \"hello world\" > /hello_world");

    String actual = "hello world";
    String expected = Interpreter.run(jShell.getState(), "cat hello_world");

    assertEquals(actual, expected);
  }

  @Test
  public void test04GetFileInRootFromNotRoot() throws InvalidCommandException {
    JShell jShell = new JShell();

    // Create the file
    Interpreter.run(jShell.getState(), "mkdir test");
    Interpreter.run(jShell.getState(), "cd test");
    Interpreter.run(jShell.getState(), "echo \"hello world\" > /hello_world");

    String actual = "hello world";
    String expected =
        Interpreter.run(jShell.getState(), "cat ./../hello_world");

    assertEquals(actual, expected);
  }

  @Test
  public void test05GetFileInRelativePath() throws InvalidCommandException {
    JShell jShell = new JShell();

    // Create the file
    Interpreter.run(jShell.getState(), "mkdir test");
    Interpreter.run(jShell.getState(), "echo \"abc\" > /test/abc");

    String actual = "abc";
    String expected =
        Interpreter.run(jShell.getState(), "cat test/abc");

    assertEquals(actual, expected);
  }

  @Test
  public void test06GetFileInRelativePathFromAnotherRelativePath()
      throws InvalidCommandException {
    JShell jShell = new JShell();

    // Create the file
    Interpreter.run(jShell.getState(), "mkdir test test2");
    Interpreter.run(jShell.getState(), "cd test");
    Interpreter.run(jShell.getState(), "echo \"abc\" > /test2/abc");

    String actual = "abc";
    String expected =
        Interpreter.run(jShell.getState(), "cat ../test2/abc");

    assertEquals(actual, expected);
  }

  @Test
  public void test07MultipleFiles() throws InvalidCommandException {
    JShell jShell = new JShell();

    // Create the files
    Interpreter.run(jShell.getState(), "echo \"1\" > /1");
    Interpreter.run(jShell.getState(), "echo \"2\" > /2");
    Interpreter.run(jShell.getState(), "echo \"3\" > /3");
    Interpreter.run(jShell.getState(), "echo \"4\" > /4");

    String actual = "1\n\n\n2\n\n\n3\n\n\n4";
    String expected =
        Interpreter.run(jShell.getState(), "cat 1 2 3 4");

    assertEquals(actual, expected);
  }

  @Test
  public void test08IllegalNumberOfArguments()
      throws InvalidCommandException {
    JShell jShell = new JShell();

    exception.expect(IllegalNumberOfArgumentsException.class);
    Interpreter.run(jShell.getState(), "cat");
  }

}
