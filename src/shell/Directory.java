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
package shell;

import exception.IllegalNameException;
import exception.InvalidPathException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Directory is a tree of Directory's where each Directory has a String data
 * which is the direct path to a specific directory
 */
public class Directory extends Tree<String> implements Serializable, Path {

  // default serialVersion id
  private static final long serialVersionUID = 1L;
  // a list of files stored in this Directory
  private ArrayList<File> childFiles;
  // the name of this Directory
  private String name;

  /**
   * Creates the root Directory
   */
  public static Directory createRoot() {
    return new Directory("/");
  }

  /**
   * Creates a Directory with a specific name
   *
   * @param name the given data for the new Directory
   */
  private Directory(String name) {
    super(name);
    childFiles = new ArrayList<>();
  }

  /**
   * Retrieves the path of this Directory
   *
   * @return this Directory's path
   */
  @Override
  public String getPath() {
    return getData();
  }

  /**
   * Retrieves the parent Directory of this Directory
   *
   * @return this Directory's parent Directory
   */
  @Override
  public Directory getParentDirectory() {
    return (Directory) getParent();
  }

  /**
   * Retrieves the name of this Directory
   *
   * @return this Directory's name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Change the name of this Directory
   *
   * @param name the new name
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get a list of the Files in this Directory
   *
   * @return an array of the Files in this Directory
   */
  public File[] getChildFiles() {
    return childFiles.toArray(new File[childFiles.size()]);
  }

  /**
   * Checks if this Directory contains a Directory with a specific name
   *
   * @param name a given path to search for
   * @return true iff the child exists
   */
  public boolean hasChildDirectory(String name) {
    for (Directory child : getChildren()) {
      if (child.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if this Directory contains a File with a specific name
   *
   * @param name a given path to search for
   * @return true iff the child exists
   */
  public boolean hasChildFile(String name) {
    for (File child : childFiles) {
      if (child.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if this Directory is an ancestor of another Directory. Ie, if the
   * other directory is located in the subtree of this Directory
   *
   * @param other another Directory
   * @return true iff this Directory is an ancestor of the other Directory
   */
  public boolean isAncestorOf(Directory other) {
    return other.getPath().startsWith(this.getPath());
  }

  /**
   * Add a new Directory as a child of this Directory
   *
   * @param name the name of the new tree
   * @return the new tree
   * @throws IllegalNameException if name is invalid
   */
  @Override
  public Directory addChild(String name) throws IllegalNameException {
    // check that the name is valid
    if (name.matches(State.ILLEGAL_CHARACTERS)
        || hasChildDirectory(State.cleanDirectoryPath(name))
        || hasChildFile(State.cleanFilePath(name))) {
      throw new IllegalNameException(
          name + " already exists in " + getPath() + " this");
    }
    Directory newTree = new Directory(getData() + name);
    newTree.setName(name);
    children.add(newTree);
    newTree.setParent(this);
    return newTree;
  }


  /**
   * Add an existing Directory as a child of this Directory
   *
   * @param child the existing Directory
   * @return the added Directory
   * @throws IllegalNameException if the new Directory's name is invalid
   */
  public Directory addChild(Directory child) throws IllegalNameException {
    String name = child.getName();
    // check that the name is valid
    if (name.matches(State.ILLEGAL_CHARACTERS)
        || hasChildDirectory(State.cleanDirectoryPath(name))
        || hasChildFile(State.cleanFilePath(name))) {
      throw new IllegalNameException(
          name + " already exists in " + getPath() + " this");
    }
    children.add(child);
    child.setParent(this);
    return child;
  }

  /**
   * Create a new child directory with the same attributes as an existing
   * Directory
   *
   * @param original an existing Directory
   * @return the new Directory
   */
  public Directory addDuplicate(Directory original)
      throws IllegalNameException {
    Directory newChild = addChild(original.name);
    newChild.childFiles = original.childFiles;
    // recursively add Directory children
    for (Directory child : original.getChildren()) {
      newChild.addDuplicate(child);
    }
    return newChild;
  }

  /**
   * Create a File with contents
   *
   * @param name the name of the file
   * @param contents the contents of the file
   * @return the new file
   * @throws IllegalNameException if name is invalid
   */
  public File addFile(String name, String contents)
      throws IllegalNameException {
    // check that the name is valid
    if (name.matches(State.ILLEGAL_CHARACTERS)
        || hasChildDirectory(State.cleanDirectoryPath(name))
        || hasChildFile(State.cleanFilePath(name))) {
      throw new IllegalNameException(
          name + " already exists in " + getPath() + " this");
    }
    File newFile = new File(this, name, contents);
    childFiles.add(newFile);
    return newFile;
  }

  /**
   * Create a File without contents
   *
   * @param name the name of the file
   * @return the new file
   * @throws IllegalNameException if name is invalid
   */
  public File addFile(String name) throws IllegalNameException {
    // check that the name is valid
    if (name.matches(State.ILLEGAL_CHARACTERS)
        || hasChildDirectory(State.cleanDirectoryPath(name))
        || hasChildFile(State.cleanFilePath(name))) {
      throw new IllegalNameException(
          name + " already exists in " + getPath() + " this");
    }
    File newFile = new File(this, name);
    childFiles.add(newFile);
    return newFile;
  }

  /**
   * Add an existing File object to this Directory
   *
   * @param child an existing File
   * @return the new file
   */
  public File addFile(File child) throws IllegalNameException {
    String name = child.getName();
    // check that the name is valid
    if (name.matches(State.ILLEGAL_CHARACTERS)
        || hasChildDirectory(State.cleanDirectoryPath(name))
        || hasChildFile(State.cleanFilePath(name))) {
      throw new IllegalNameException(
          name + " already exists in " + getPath() + " this");
    }
    childFiles.add(child);
    return child;
  }

  /**
   * Get a child file by its name
   *
   * @param name the name of a child file
   * @return the child file with this name, or null if no such child exists
   */
  public File getChildFile(String name) {
    for (File file : childFiles) {
      if (file.getName().equals(name)) {
        return file;
      }
    }
    return null;
  }

  /**
   * Removes a file with a given name from this Directory
   *
   * @param name a given File name
   * @return the removed File, or null if no File with this name exists in this
   * Directory
   */
  public File removeChildFile(String name) {
    for (File child : childFiles) {
      if (child.getName().equals(name)) {
        childFiles.remove(child);
        return child;
      }
    }
    return null;
  }

  /**
   * Get an array of the Directory children of this directory
   *
   * @return the array of children
   */
  public Directory[] getChildren() {
    return children.toArray(new Directory[children.size()]);
  }

  /**
   * Get an array of the data (paths) of the children of this directory
   *
   * @return the array of paths
   */
  public String[] getChildData() {
    ArrayList<String> data = new ArrayList<>();
    for (Tree<String> child : children) {
      data.add(child.getData());
    }
    return data.toArray(new String[children.size()]);
  }

  /**
   * Change the parent of this Directory. Update all other affected Directory's
   *
   * @param newParent the new parent Directory
   */
  private void setParent(Directory newParent) {
    // remove from current parent
    if (parent != null) {
      parent.children.remove(this);
    }
    // add to new parent
    parent = newParent;
    // recursively update all children paths
    updatePaths();
  }

  /**
   * Recursively update the paths of this Directory and all of its children
   */
  private void updatePaths() {
    data = parent.data + name;
    for (Directory child : getChildren()) {
      child.updatePaths();
    }
  }

}
