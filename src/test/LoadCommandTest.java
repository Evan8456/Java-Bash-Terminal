package test;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;
import command.LoadCommand;
import driver.JShell;
import shell.Interpreter;

public class LoadCommandTest {


  @Test
  public void test01GetName() {
    LoadCommand load = new LoadCommand();

    String actual = "load";
    String expected = load.getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test02GetDescription() {
    LoadCommand load = new LoadCommand();

    String actual = "Loads the state of the shell saved at a file";
    String expected = load.getDescription();

    assertEquals(actual, expected);
  }

  @Test
  public void test03SimpleLoadStateWithHistory() throws IOException {
    JShell shell1 = new JShell();
    JShell shell2 = new JShell();

    Interpreter.run(shell1.getState(), "history");
    Interpreter.run(shell1.getState(), "mkdir a");
    Interpreter.run(shell1.getState(), "cd a");
    Interpreter.run(shell1.getState(), "echo \"b\" > b");
    Interpreter.run(shell1.getState(), "save stateTest2");
    Interpreter.run(shell2.getState(), "load stateTest2");
    shell1.getState().addCommandToHistory("load stateTest2");
    shell1.getState().setWorkingDirectory(shell1.getState().getRoot());
    assertEquals(shell1.getState(), shell2.getState());

  }
}
