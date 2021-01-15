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

import java.util.HashMap;
import org.junit.Test;
import driver.JShell;
import exception.InvalidCommandException;
import shell.Interpreter;
import command.EchoCommand;
import command.ManCommand;
import command.PwdCommand;
import command.PushdCommand;

public class ManCommandTest {

  @Test
  public void test01GetName() {
    ManCommand man = new ManCommand();

    String actual = "man";
    String expected = man.getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test02GetDescription() {
    ManCommand man = new ManCommand();

    String actual = "Will print the documentation for a given command.";

    String expected = man.getDescription();

    assertEquals(actual, expected);
  }

  @Test
  public void test03GetParameters() {
    ManCommand man = new ManCommand();

    HashMap<String, String> actual = new HashMap<>();
    actual.put("CMD", "The command to return the documentation for");

    HashMap<String, String> expected = man.getParameters();

    assertEquals(actual, expected);
  }

  @Test
  public void test04RunPwd() throws InvalidCommandException {
    JShell jShell = new JShell();
    PwdCommand pwd = new PwdCommand();

    String actual = "\nNAME\n\n" + pwd.getName() + "\n\nDESCRIPTION\n\n"
        + pwd.getDescription() + "\n\nPARAMETERS";

    String expected = Interpreter.run(jShell.getState(), "man pwd");

    assertEquals(actual, expected);
  }

  @Test
  public void test05RunPushd() throws InvalidCommandException {
    JShell jShell = new JShell();
    PushdCommand pushd = new PushdCommand();

    String actual = "\nNAME\n\n" + pushd.getName() + "\n\nDESCRIPTION\n\n"
        + pushd.getDescription()
        + "\n\nPARAMETERS\n\nDIR - The directory to change to";

    String expected = Interpreter.run(jShell.getState(), "man pushd");

    assertEquals(actual, expected);
  }

  @Test
  public void test05RunEcho() throws InvalidCommandException {
    JShell jShell = new JShell();
    EchoCommand echo = new EchoCommand();

    String actual = "\nNAME\n\n" + echo.getName() + "\n\nDESCRIPTION\n\n"
        + echo.getDescription() + "\n\nPARAMETERS\n\nSTRING - The string to be "
        + "returned";

    String expected = Interpreter.run(jShell.getState(), "man echo");

    assertEquals(actual, expected);
  }
}
