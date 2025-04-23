package utilities;

import java.util.NoSuchElementException;

/**
 * The `myBSTree` class represents a binary search tree implementation. It stores elements of type E
 * and provides methods for adding, removing, searching, and traversing elements within the tree.
 *
 * @param <E> The type of elements stored in the binary search tree.
 */
public class myBSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {
	private BSTreeNode<E> root;
	private int size;

	/**
	 * Constructs an empty binary search tree.
	 */
	public myBSTree() {
		this.root = null;
		this.size = 0;
	}

	/**
	 * Returns the root node of the binary search tree.
	 *
	 * @return The root node of the binary search tree.
	 */
	@Override
	public BSTreeNode<E> getRoot() {
		return root;
	}

	/**
	 * Returns the height of the binary search tree.
	 *
	 * @return The height of the binary search tree.
	 */
	@Override
	public int getHeight() {
		if (root == null)
			return 0;
		return root.getHeight();
	}

	/**
	 * Returns the number of elements in the binary search tree.
	 *
	 * @return The number of elements in the binary search tree.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Checks if the binary search tree is empty.
	 *
	 * @return true if the binary search tree is empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Clears the binary search tree, removing all elements.
	 */
	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Checks if the binary search tree contains the specified element.
	 *
	 * @param entry The element to search for in the binary search tree.
	 * @return true if the binary search tree contains the element, false otherwise.
	 * @throws NullPointerException if the specified element is null.
	 */
	@Override
	public boolean contains(E entry) throws NullPointerException {
		return search(entry) != null;
	}

	/**
	 * Searches for the specified element in the binary search tree.
	 *
	 * @param entry The element to search for in the binary search tree.
	 * @return The node containing the specified element if found, null otherwise.
	 * @throws NullPointerException if the specified element is null.
	 */
	@Override
	public BSTreeNode<E> search(E entry) throws NullPointerException {
		return searchRecursively(root, entry);
	}

	private BSTreeNode<E> searchRecursively(BSTreeNode<E> node, E entry) {
		if (node == null || node.getElement().equals(entry))
			return node;
		int compare = entry.compareTo(node.getElement());
		if (compare < 0)
			return searchRecursively(node.getLeft(), entry);
		else
			return searchRecursively(node.getRight(), entry);
	}

	/**
	 * Adds the specified element to the binary search tree.
	 *
	 * @param newEntry The element to add to the binary search tree.
	 * @return true if the element is added successfully, false if it already exists in the tree.
	 * @throws NullPointerException if the specified element is null.
	 */
	@Override
	public boolean add(E newEntry) throws NullPointerException {
		if (newEntry == null)
			throw new NullPointerException("Cannot add null entry to the tree");
		if (root == null) {
			root = new BSTreeNode<>(newEntry, null, null);
			size++;
			return true;
		}
		return addRecursively(root, newEntry);
	}

	private boolean addRecursively(BSTreeNode<E> node, E newEntry) {
		int compare = newEntry.compareTo(node.getElement());
		if (compare < 0) {
			if (node.getLeft() == null) {
				node.setLeft(new BSTreeNode<>(newEntry, null, null));
				size++;
				return true;
			} else {
				return addRecursively(node.getLeft(), newEntry);
			}
		} else if (compare > 0) {
			if (node.getRight() == null) {
				node.setRight(new BSTreeNode<>(newEntry, null, null));
				size++;
				return true;
			} else {
				return addRecursively(node.getRight(), newEntry);
			}
		}
		return false; // Duplicate entry, not added
	}

	/**
	 * Removes and returns the node with the smallest element from the binary search tree.
	 *
	 * @return The node with the smallest element if found, null if the tree is empty.
	 */
	@Override
	public BSTreeNode<E> removeMin() {
		if (root == null)
			return null;
		BSTreeNode<E> parent = null;
		BSTreeNode<E> current = root;
		while (current.getLeft() != null) {
			parent = current;
			current = current.getLeft();
		}
		if (parent == null)
			root = current.getRight();
		else
			parent.setLeft(current.getRight());
		size--;
		return current;
	}

	/**
	 * Removes and returns the node with the largest element from the binary search tree.
	 *
	 * @return The node with the largest element if found, null if the tree is empty.
	 */
	@Override
	public BSTreeNode<E> removeMax() {
		if (root == null)
			return null;
		BSTreeNode<E> parent = null;
		BSTreeNode<E> current = root;
		while (current.getRight() != null) {
			parent = current;
			current = current.getRight();
		}
		if (parent == null)
			root = current.getLeft();
		else
			parent.setRight(current.getLeft());
		size--;
		return current;
	}

	/**
	 * Returns an iterator for traversing the binary search tree in inorder.
	 *
	 * @return An iterator for inorder traversal of the binary search tree.
	 */
	@Override
	public Iterator<E> inorderIterator() {
		return new InorderIterator(root);
	}

	/**
	 * Returns an iterator for traversing the binary search tree in preorder.
	 *
	 * @return An iterator for preorder traversal of the binary search tree.
	 */
	@Override
	public Iterator<E> preorderIterator() {
		return new PreorderIterator(root);
	}

	/**
	 * Returns an iterator for traversing the binary search tree in postorder.
	 *
	 * @return An iterator for postorder traversal of the binary search tree.
	 */
	@Override
	public Iterator<E> postorderIterator() {
		return new PostorderIterator(root);
	}

	// Inner classes for iterators
	private class InorderIterator implements Iterator<E> {
		private MyStack<BSTreeNode<E>> stack;

		public InorderIterator(BSTreeNode<E> root) {
			stack = new MyStack<>();
			pushLeft(root);
		}

		private void pushLeft(BSTreeNode<E> node) {
			while (node != null) {
				stack.push(node);
				node = node.getLeft();
			}
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext())
				throw new NoSuchElementException("No more elements in the iterator.");
			BSTreeNode<E> current = stack.pop();
			pushLeft(current.getRight());
			return current.getElement();
		}
	}

	private class PreorderIterator implements Iterator<E> {
		private MyStack<BSTreeNode<E>> stack;

		public PreorderIterator(BSTreeNode<E> root) {
			stack = new MyStack<>();
			if (root != null)
				stack.push(root);
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext())
				throw new NoSuchElementException("No more elements in the iterator.");
			BSTreeNode<E> current = stack.pop();
			if (current.getRight() != null)
				stack.push(current.getRight());
			if (current.getLeft() != null)
				stack.push(current.getLeft());
			return current.getElement();
		}
	}

	private class PostorderIterator implements Iterator<E> {
		private MyStack<BSTreeNode<E>> stack;
		private BSTreeNode<E> lastVisited;

		public PostorderIterator(BSTreeNode<E> root) {
			stack = new MyStack<>();
			lastVisited = null;
			pushLeftmostPath(root);
		}

		private void pushLeftmostPath(BSTreeNode<E> node) {
			while (node != null) {
				stack.push(node);
				node = node.getLeft();
			}
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext())
				throw new NoSuchElementException("No more elements in the iterator.");

			while (true) {
				BSTreeNode<E> current = stack.peek();
				if (current.getRight() == null || current.getRight() == lastVisited) {
					lastVisited = stack.pop();
					return lastVisited.getElement();
				} else {
					pushLeftmostPath(current.getRight());
				}
			}
		}
	}
}
