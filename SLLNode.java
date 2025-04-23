//from demo

package utilities;

import java.io.Serializable;


public class SLLNode<E> implements Serializable
{
	// Constants
	private static final long serialVersionUID = -2472546079381363389L;

	// Attributes
	private E element;
	private SLLNode<E> next;

	// Constructors
	/**
	 * Constructs a SLL node with an element and a successor.
	 *
	 * @param element element to be added.
	 * @param next    SLLNode to be set to next element.
	 */
	public SLLNode( E element, SLLNode<E> next )
	{
		this.element = element;
		this.next = next;
	}

	/**
	 * Constructs a SLL node with an element and no successor.
	 *
	 * @param element element of the new node.
	 */
	public SLLNode( E element )
	{
		this.element = element;
		this.next = null;
	}

	// Accessor and Modifier Methods
	/**
	 * Method to return the value of element.
	 *
	 * @return the element of the node.
	 */
	public E getElement()
	{
		return element;
	}

	/**
	 * Method to set the of element.
	 *
	 * @param element the new element of the node.
	 */
	public void setElement( E element )
	{
		this.element = element;
	}

	/**
	 * Method to return the value of next.
	 *
	 * @return reference to the next node, null if there's no next node.
	 */
	public SLLNode<E> getNext()
	{
		return next;
	}

	/**
	 * Method to set the value of next.
	 *
	 * @param next reference to the next node to set.
	 */
	public void setNext( SLLNode<E> next )
	{
		this.next = next;
	}

	/**
	 * Method to set the value of next.
	 */
	public String toString()
	{
		return "Node element: " + this.element + ", next: " + this.next;
	}
}