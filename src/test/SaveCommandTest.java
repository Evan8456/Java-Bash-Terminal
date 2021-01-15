package test;

import static org.junit.Assert.*;

import exception.IllegalNameException;
import java.io.File;
import org.junit.Test;
import command.SaveCommand;
import driver.JShell;
import shell.Interpreter;

public class SaveCommandTest {

  @Test
  public void test01GetName() {
    SaveCommand save = new SaveCommand();

    String actual = "save";
    String expected = save.getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test02GetDescription() {
    SaveCommand save = new SaveCommand();

    String actual = "Saves the current state of the shell";
    String expected = save.getDescription();

    assertEquals(actual, expected);
  }

  @Test
  public void test03SaveStateExists() throws IllegalNameException {
    JShell shell = new JShell();
    shell.getState().getRoot().addFile("file1");

    Interpreter.run(shell.getState(), "save stateTest1");
    File file = new File("Assignment2/src/savedStates/stateTest1.ser");
    boolean fileExists = file.getAbsoluteFile().exists();

    assertTrue(fileExists);

  }
}
