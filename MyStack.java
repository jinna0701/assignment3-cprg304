package utilities;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * The `MyStack` class represents a generic stack data structure implemented using a singly linked list.
 *
 * @param <E> The type of elements stored in the stack.
 */
public class MyStack<E> implements StackADT<E> {

	private SLLNode<E> top;
	private int size;

	/**
	 * Constructs an empty stack.
	 */
	public MyStack() {
		top = null;
		size = 0;
	}

	/**
	 * Returns the number of elements in the stack.
	 *
	 * @return The number of elements in the stack.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Removes all of the elements from the stack.
	 */
	@Override
	public void clear() {
		top = null;
		size = 0;
	}

	/**
	 * Pushes an element onto the top of this stack.
	 *
	 * @param toAdd The element to add to the stack.
	 * @return {@code true} if the element was successfully added, {@code false} otherwise.
	 * @throws NullPointerException if the specified element is null.
	 */
	@Override
	public boolean push(E toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot add a null element to the stack.");
		}

		SLLNode<E> newNode = new SLLNode<>(toAdd, top);
		top = newNode;
		size++;
		return true;
	}

	/**
	 * Searches for the specified element in the stack.
	 *
	 * @param toFind The element to search for.
	 * @return The 1-based position of the element from the top of the stack, or -1 if the element is not found.
	 * @throws NullPointerException if the specified element is null.
	 */
	@Override
	public int search(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Cannot search for a null element in the stack.");
		}

		SLLNode<E> current = top;
		int index = 1;
		while (current != null) {
			if (current.getElement().equals(toFind)) {
				return index;
			}
			current = current.getNext();
			index++;
		}
		return -1; // Element not found
	}

	/**
	 * Looks at the object at the top of this stack without removing it from the stack.
	 *
	 * @return The object at the top of the stack.
	 * @throws EmptyStackException if the stack is empty.
	 */
	@Override
	public E peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return top.getElement();
	}

	/**
	 * Removes the object at the top of this stack and returns that object.
	 *
	 * @return The object removed from the top of the stack.
	 * @throws EmptyStackException if the stack is empty.
	 */
	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		E element = top.getElement();
		top = top.getNext();
		size--;
		return element;
	}

	/**
	 * Tests if this stack is empty.
	 *
	 * @return {@code true} if and only if this stack contains no items; {@code false} otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns an iterator over the elements in this stack.
	 *
	 * @return An iterator over the elements in this stack.
	 */
	@Override
	public Iterator<E> iterator() {
		return new MyStackIterator();
	}

	/**
	 * The `MyStackIterator` class represents an iterator over a stack.
	 */
	private class MyStackIterator implements Iterator<E> {
		private SLLNode<E> current;

		/**
		 * Constructs a new iterator.
		 */
		public MyStackIterator() {
			current = top;
		}

		/**
		 * Returns {@code true} if the iteration has more elements.
		 *
		 * @return {@code true} if the iteration has more elements, {@code false} otherwise.
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @return The next element in the iteration.
		 * @throws NoSuchElementException if the iteration has no more elements.
		 */
		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException("No more elements in the iterator.");
			}
			E element = current.getElement();
			current = current.getNext();
			return element;
		}
	}
}
