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
import command.Command;
import exception.InvalidCommandException;

public class CommandTest {

  @Test
  public void test01GetExit() throws InvalidCommandException {
    String actual = "exit";
    String expected = Command.getCommand("exit").getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test02GetMkdir() throws InvalidCommandException {
    String actual = "mkdir";
    String expected = Command.getCommand("mkdir").getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test03GetCd() throws InvalidCommandException {
    String actual = "cd";
    String expected = Command.getCommand("cd").getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test04GetLs() throws InvalidCommandException {
    String actual = "ls";
    String expected = Command.getCommand("ls").getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test05GetPwd() throws InvalidCommandException {
    String actual = "pwd";
    String expected = Command.getCommand("pwd").getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test06GetPushd() throws InvalidCommandException {
    String actual = "pushd";
    String expected = Command.getCommand("pushd").getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test07GetPopd() throws InvalidCommandException {
    String actual = "popd";
    String expected = Command.getCommand("popd").getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test08GetHistory() throws InvalidCommandException {
    String actual = "history";
    String expected = Command.getCommand("history").getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test09GetCat() throws InvalidCommandException {
    String actual = "cat";
    String expected = Command.getCommand("cat").getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test10GetEcho() throws InvalidCommandException {
    String actual = "echo";
    String expected = Command.getCommand("echo").getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test11GetMan() throws InvalidCommandException {
    String actual = "man";
    String expected = Command.getCommand("man").getName();

    assertEquals(actual, expected);
  }
}
