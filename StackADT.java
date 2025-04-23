package utilities;

import java.io.Serializable;
import java.util.EmptyStackException;

/**
 * The StackADT interface defines the methods that a stack data structure should implement.
 *
 * @param <E> The type of elements this stack holds.
 */
public interface StackADT<E> extends Serializable
{

	/**
	 * Returns the number of elements in the stack.
	 *
	 * @return The number of elements in the stack.
	 * Preconditions: A valid StackADT object exists.
	 * Postconditions: StackADT size is returned.
	 */
	public int size();


	/**
	 * Removes all the elements in the stack.
	 * Precondition: None
	 * Postcondition: The stack is empty.
	 */
	public void clear();

	/**
	 * Adds the specific element at the top of the stack.
	 *
	 * @param element The element that is to be added on top of the stack.
	 * @return true if element is appended successfully.
	 * @throws NullPointerException
	 * 			If the specified element is <code>null</code> and the list
	 * 			implementation does not support having <code>null</code>
	 * 			elements.
	 * Precondition: None.
	 * Postcondition: The stack contains the element at the top.
	 */
	public boolean push(E toAdd)throws NullPointerException;

	/**
	 * Returns index of the specified element in this stack.
	 *
	 * @param toFind The element whose presence in this list is to be found.
	 * @return Integer of the index of the specified element in this stack.
	 * @throws NullPointerException
	 * 			If the specified element is <code>null</code> and the
	 * 			stack implementation does not support having
	 * 			<code>null</code> elements.
	 * Precondition: None.
	 * Postcondition: The stack remains unchanged.
	 */
	public int search(E toFind) throws NullPointerException;

	/**
	 * Returns the element at the top of the stack without removing it.
	 *
	 * @return The element at the top of the stack.
	 * @throws java.util.NoSuchElementException if the stack is empty.
	 * Precondition: The stack is not empty.
	 * Postcondition: The stack remains unchanged or if empty, throws EmptyStackException.
	 */
	public E peek()throws EmptyStackException;

	/**
	 * Removes and returns the element at the top of the stack.
	 *
	 * @return The element at the top of the stack.
	 * @throws java.util.NoSuchElementException if the stack is empty.
	 * @post The size of the stack decreases by 1.
	 * Precondition: The stack is not empty.
	 * Postcondition: The stack no longer contains the element returned or EmptyStackException.
	 */
	public E pop() throws EmptyStackException;



	/**
	 * Checks if the stack is empty.
	 *
	 * @return true if the stack is empty, false otherwise.
	 * @post The stack remains unchanged.
	 * Precondition: None.
	 * Postcondition: The stack remains unchanged.
	 */
	public boolean isEmpty();

	/**
	 * Returns an iterator over the elements in this stack, in proper sequence.
	 *
	 * @return An iterator over the elements in this stack, in proper sequence.
	 * 			NB: The return is of type
	 * 			<code>linearUtilities.Iterator<E></code>,
	 * 			not <code>java.util.Iterator</code>.
	 */
	public Iterator<E> iterator();
}