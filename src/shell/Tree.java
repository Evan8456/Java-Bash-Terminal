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
import java.util.Iterator;
import java.util.Stack;

/**
 * A generic Tree class. Every tree can have multiple tree children and one tree
 * parent. Every tree contains a data object of a given type
 */
public class Tree<E> implements Iterable<E>, Serializable {

  //default serialVersion id
  protected static final long serialVersionUID = 1L;
  protected E data;
  protected Tree<E> parent;
  protected ArrayList<Tree<E>> children;

  /**
   * Create a root of a tree
   *
   * @param rootData the data stored in the root
   */
  public Tree(E rootData) {
    this.data = rootData;
    parent = null;
    children = new ArrayList<>();
  }

  /**
   * Checks if this tree has no parent
   *
   * @return true iff this is the root of a tree system
   */
  public boolean isRoot() {
    return parent == null;
  }

  /**
   * Retrieves the data object stored in this tree
   *
   * @return this tree's data object
   */
  public E getData() {
    return data;
  }

  /**
   * Retrieves the parent of this tree
   *
   * @return this tree's parent tree
   */
  public Tree<E> getParent() {
    return parent;
  }

  /**
   * Update this tree's parent tree
   *
   * @param parent the new parent
   */
  public void setParent(Tree<E> parent) {
    this.parent = parent;
  }

  /**
   * Add a child tree to this tree
   *
   * @param d the data of the new child tree
   * @return the new child tree
   * @throws IllegalNameException if d is an invalid data
   */
  public Tree<E> addChild(E d) throws IllegalNameException {
    Tree<E> newTree = new Tree<>(d);
    newTree.setParent(this);
    children.add(newTree);
    return newTree;
  }

  /**
   * Get the child of this tree with a given data
   *
   * @param data the data of the target child
   * @return the target child or null if no such child exists
   */
  public Tree<E> getChild(E data) {
    for (Tree<E> child : children) {
      if (child.data.equals(data)) {
        return child;
      }
    }
    return null;
  }

  /**
   * Returns whether or not a given object is equal to this Tree. Two trees are
   * equal iff they have the same data, and either the same parent or are both
   * roots.
   *
   * @param obj An object to compare to
   * @return true iff the object is equal to this tree
   */
  @Override
  public boolean equals(Object obj) {
    // check for null
    if (obj == null) {
      return false;
    }
    // check that these objects are trees of the same type
    if (getClass() != obj.getClass()) {
      return false;
    }
    // cast to tree
    Tree<E> other = (Tree<E>) obj;
    // check if these have different data
    if (!data.equals(other.getData())) {
      return false;
    }
    // check if these are both roots of trees
    if (isRoot() && (other.isRoot())) {
      return true;
    }
    // check if these tree's parents are equal
    return (parent.equals(other.parent));
  }

  /**
   * Returns an Iterator for this tree
   *
   * @return an Iterator for this tree
   */
  @Override
  public Iterator<E> iterator() {
    return new PreOrderIterator<>(this);
  }

  /**
   * An Pre-Order Iterator object for this tree
   */
  private static class PreOrderIterator<E> implements Iterator<E> {

    Stack<Tree<E>> stack;

    /**
     * Create a new Iterator
     *
     * @param root the root of the tree to iterate through
     */
    public PreOrderIterator(Tree<E> root) {
      stack = new Stack<>();
      stack.push(root);
    }

    /**
     * Check if the iteration is complete
     *
     * @return true if there is another subtree to iterate through
     */
    @Override
    public boolean hasNext() {
      return !stack.empty();
    }

    /**
     * Get the next subtree according to pre-order traversal
     *
     * @return the next subtree
     */
    @Override
    public E next() {
      if (hasNext()) {
        Tree<E> node = stack.pop();
        for (Tree<E> child : node.children) {
          stack.push(child);
        }
        return node.data;
      }
      return null;
    }
  }
}