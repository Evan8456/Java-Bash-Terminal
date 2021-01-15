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
import static org.junit.Assert.assertTrue;

public class StateTest {
/*TODO: Redo this test file
  @Test
  public void test01GetRootTest() throws IllegalNameException {
    Directory root = Directory.createDirectory(null, "/");
    State state = new State(root);
    assertEquals(state.getRoot(), root);
  }

  @Test
  public void test02GetWorkingDirectoryTest() throws IllegalNameException {
    Directory root = Directory.createDirectory(null, "/");
    State state = new State(root);
    assertEquals(state.getWorkingDirectory(), root);
  }

  @Test
  public void test03SetWorkingDirectoryTest() throws IllegalNameException {
    JShell shell = new JShell();
    State state = shell.getState();
    Directory dir1 = Directory.createDirectory(state.getRoot(), "folder");
    state.setWorkingDirectory(dir1);
    assertEquals(state.getWorkingDirectory(), dir1);
  }

  @Test
  public void test04SetInputDataTest() {
    JShell shell = new JShell();
    State state = shell.getState();
    state.setInputData(new String[]{"Command", "param_1", "param_2"});
    assertEquals("Command", state.getCommand());
    assertEquals("param_1", state.getParameters()[0]);
    assertEquals("param_2", state.getParameters()[1]);
  }

  @Test
  public void test05AddCommandTest() {
    JShell shell = new JShell();
    State state = shell.getState();
    state.addCommandToHistory("new command");
    assertTrue(state.getCommandHistory().contains("new command"));
  }

  @Test
  public void test06PushPopDirectoriesTest() throws IllegalNameException {
    JShell shell = new JShell();
    State state = shell.getState();
    Directory dir = Directory.createDirectory(state.getRoot(), "folder");
    state.pushDirectory(dir);
    assertEquals(state.popDirectory(), dir);
  }

  @Test
  public void test07ExitTest() {
    JShell shell = new JShell();
    State state = shell.getState();
    assertTrue(!state.getExit());
    state.setExit(true);
    assertTrue(state.getExit());
  }

  @Test
  public void test08SeparatePathNameTest() {
    JShell shell = new JShell();
    State state = shell.getState();
    String[] separated = state.separatePathName("folder1/folder2/file");
    assertEquals("file", separated[0]);
    assertEquals("folder1/folder2", separated[1]);
  }
*/
}
