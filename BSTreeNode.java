/**
 * BSTreeADT
 */
package utilities;

public class BSTreeNode<E> {
	private E element;
	private BSTreeNode<E> left, right;

	/**
	 * Constructs a new node with the specified element and children.
	 *
	 * @param elem  The element stored in the node.
	 * @param left  The left child node (less than or equal to).
	 * @param right The right child node (greater than).
	 */
	public BSTreeNode(E elem, BSTreeNode<E> left, BSTreeNode<E> right) {
		this.element = elem;
		this.left = left;
		this.right = right;
	}

	/**
	 * Returns the element stored in the node.
	 *
	 * @return The element stored in the node.
	 */
	public E getElement() {
		return this.element;
	}

	/**
	 * Sets the element stored in the node.
	 *
	 * @param element The element to set.
	 */
	public void setElement(E element) {
		this.element = element;
	}

	/**
	 * Returns the left child node.
	 *
	 * @return The left child node.
	 */
	public BSTreeNode<E> getLeft() {
		return this.left;
	}

	/**
	 * Returns the right child node.
	 *
	 * @return The right child node.
	 */
	public BSTreeNode<E> getRight() {
		return this.right;
	}

	/**
	 * Sets the left child node.
	 *
	 * @param left The left child node to set.
	 */
	public void setLeft(BSTreeNode<E> left) {
		this.left = left;
	}

	/**
	 * Sets the right child node.
	 *
	 * @param right The right child node to set.
	 */
	public void setRight(BSTreeNode<E> right) {
		this.right = right;
	}

	/**
	 * Checks if the node has a left child.
	 *
	 * @return {@code true} if the node has a left child, {@code false} otherwise.
	 */
	public boolean hasLeftChild() {
		return (this.left != null);
	}

	/**
	 * Checks if the node has a right child.
	 *
	 * @return {@code true} if the node has a right child, {@code false} otherwise.
	 */
	public boolean hasRightChild() {
		return (this.right != null);
	}

	/**
	 * Checks if the node is a leaf node (i.e., has no children).
	 *
	 * @return {@code true} if the node is a leaf node, {@code false} otherwise.
	 */
	public boolean isLeaf() {
		return (left == null && right == null);
	}

	/**
	 * Returns the number of nodes in the subtree rooted at this node.
	 *
	 * @return The number of nodes in the subtree.
	 */
	public int getNumberNodes() {
		// Base case: if the current node is a leaf node, return 1
		if (isLeaf())
			return 1;
		// Recursively count nodes in left and right subtrees
		int leftCount = (this.left == null) ? 0 : this.left.getNumberNodes();
		int rightCount = (this.right == null) ? 0 : this.right.getNumberNodes();
		// Return total count including the current node
		return 1 + leftCount + rightCount;
	}

	/**
	 * Returns the height of the subtree rooted at this node.
	 *
	 * @return The height of the subtree.
	 */
	public int getHeight() {
		// Base case: if the current node is a leaf node, return 0
		if (isLeaf())
			return 0;
		// Recursively calculate height of left and right subtrees
		int leftHeight = (this.left == null) ? 0 : this.left.getHeight();
		int rightHeight = (this.right == null) ? 0 : this.right.getHeight();
		// Return the maximum height of left or right subtree, plus 1 for the current node
		return 1 + Math.max(leftHeight, rightHeight);
	}
}