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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import command.Command;
import command.GetCommand;
import driver.JShell;
import exception.IllegalNameException;
import exception.IllegalNumberOfArgumentsException;
import exception.InvalidCommandException;
import exception.InvalidURLException;
import shell.Interpreter;
import shell.State;

public class GetCommandTest {

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  private class MockURL {

    private String urlPath;
    private String contents;

    public MockURL(String urlPath, String contents) {
      this.urlPath = urlPath;
      this.contents = contents;
    }

    public InputStream openStream() throws UnsupportedEncodingException {
      return new ByteArrayInputStream(this.contents.getBytes("UTF-8"));
    }

    public String getPath() {
      return urlPath;
    }
  }

  @Test
  public void test01GetName() {
    GetCommand get = new GetCommand();

    String actual = "get";
    String expected = get.getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test02GetDescription() {
    GetCommand get = new GetCommand();

    String actual = "Will retrieve the file on a server at a given web address";
    String expected = get.getDescription();

    assertEquals(actual, expected);
  }

  @Test
  public void test03saveURLToFileTextFile() throws InvalidCommandException,
      IOException, IllegalNameException, InvalidURLException {
    GetCommand get = (GetCommand) Command.getCommand("get");
    JShell jShell = new JShell();
    State state = jShell.getState();

    String actual = "This is a text file";
    String filePath = "file://home/user/text.txt";

    MockURL url = new MockURL(filePath, actual);
    InputStream in = url.openStream();
    String urlPath = url.getPath();

    get.saveURLToFile(state, in, urlPath);
    String expected = Interpreter.run(state, "cat text").trim();

    assertEquals(actual, expected);
  }

  @Test
  public void test04saveURLToFileHTMLFile() throws InvalidCommandException,
      IOException, IllegalNameException, InvalidURLException {
    GetCommand get = (GetCommand) Command.getCommand("get");
    JShell jShell = new JShell();
    State state = jShell.getState();

    String actual = "<html><body>GetTest!</body></html>";
    String filePath = "https://htmlfile.com/htmlFile.html";

    MockURL url = new MockURL(filePath, actual);
    InputStream in = url.openStream();
    String urlPath = url.getPath();

    get.saveURLToFile(state, in, urlPath);
    String expected = Interpreter.run(state, "cat htmlFile").trim();

    assertEquals(actual, expected);
  }

  @Test
  public void test05saveURLToFileJavaFile() throws InvalidCommandException,
      IOException, IllegalNameException, InvalidURLException {
    GetCommand get = (GetCommand) Command.getCommand("get");
    JShell jShell = new JShell();
    State state = jShell.getState();

    String actual = "public class JavaFile { public JavaFile() { // File } }";
    String filePath = "http://whereisthis.com/long/website/url/important.java";

    MockURL url = new MockURL(filePath, actual);
    InputStream in = url.openStream();
    String urlPath = url.getPath();

    get.saveURLToFile(state, in, urlPath);
    String expected = Interpreter.run(state, "cat important").trim();

    assertEquals(actual, expected);
  }

  @Test
  public void test06saveURLToFileEmptyFile() throws InvalidCommandException,
      IOException, IllegalNameException, InvalidURLException {
    GetCommand get = (GetCommand) Command.getCommand("get");
    JShell jShell = new JShell();
    State state = jShell.getState();

    String actual = "";
    String filePath = "https://empty.com/file/at/this/url/empty.o";

    MockURL url = new MockURL(filePath, actual);
    InputStream in = url.openStream();
    String urlPath = url.getPath();

    get.saveURLToFile(state, in, urlPath);
    String expected = Interpreter.run(state, "cat empty").trim();

    assertEquals(actual, expected);
  }

  @Test
  public void test07saveURLToFileFileNoExt() throws InvalidCommandException,
      IOException, IllegalNameException, InvalidURLException {
    GetCommand get = (GetCommand) Command.getCommand("get");
    JShell jShell = new JShell();
    State state = jShell.getState();

    String actual = "This file has no extension";
    String filePath = "http://no.ca/ext/in/this/noext";

    MockURL url = new MockURL(filePath, actual);
    InputStream in = url.openStream();
    String urlPath = url.getPath();

    get.saveURLToFile(state, in, urlPath);
    String expected = Interpreter.run(state, "cat noext").trim();

    assertEquals(actual, expected);
  }

  @Test
  public void test08RunNoArgs() throws Exception {
    Command get = (GetCommand) Command.getCommand("get");
    JShell jShell = new JShell();
    State state = jShell.getState();

    state.setInputData(new String[]{"get"});
    exception.expect(IllegalNumberOfArgumentsException.class);

    get.run(jShell.getState());
  }

  @Test
  public void test09saveURLToFileEmptyURL() throws InvalidCommandException,
      IOException, IllegalNameException, InvalidURLException {
    GetCommand get = (GetCommand) Command.getCommand("get");
    JShell jShell = new JShell();
    State state = jShell.getState();

    exception.expect(InvalidURLException.class);

    String actual = "";
    String filePath = "";

    MockURL url = new MockURL(filePath, actual);
    InputStream in = url.openStream();
    String urlPath = url.getPath();

    get.saveURLToFile(state, in, urlPath);
  }

  @Test
  public void test10saveURLToFileIllegalName() throws InvalidCommandException,
      IOException, IllegalNameException, InvalidURLException {
    GetCommand get = (GetCommand) Command.getCommand("get");
    JShell jShell = new JShell();
    State state = jShell.getState();
    exception.expect(IllegalNameException.class);

    String actual = "Contents";
    String filePath = "/";

    MockURL url = new MockURL(filePath, actual);
    InputStream in = url.openStream();
    String urlPath = url.getPath();

    get.saveURLToFile(state, in, urlPath);
  }

  @Test
  public void test11RunInvalidURL() throws Exception {
    Command get = (GetCommand) Command.getCommand("get");
    JShell jShell = new JShell();
    State state = jShell.getState();

    state.setInputData(new String[]{"get", "asfasgasgas/asgasgasg"});
    exception.expect(IOException.class);

    get.run(jShell.getState());
  }
}
