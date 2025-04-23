
//from demo
package utilities;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;
import utilities.SLLNode;


public class SLL<E> implements ListADT<E>
{
	// Constants
	private static final long serialVersionUID = -420356452346832256L;

	// Attributes
	private SLLNode<E> head, tail;
	private int size;

	// Constructors
	/**
	 * Constructs an empty SLL.
	 */
	public SLL()
	{
		head = tail = null;
		size = 0;
	}

	/**
	 * Gets the current number of elements in the list, if list is empty returns a 0
	 * (zero).
	 *
	 * @return <code>int</code>, an integer greater or equal to zero
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Clears the list, all elements are lost.
	 */
	public void clear()
	{
		head = tail = null;
		size = 0;
	}

	/**
	 * Method to add an element to a specific index in a list. If the index is an
	 * index outside of the bounds of the list an exception is thrown
	 *
	 * @param toAdd element to be added to the list.
	 * @param index position that the element should be placed at.
	 * @return true if the element is added successfully.
	 * @throws IndexOutOfBoundsException when the index is
	 *                                   <code>(index < 0 || index > size)</code>
	 */
	public boolean add( int index, E toAdd ) throws NullPointerException, IndexOutOfBoundsException
	{
		boolean status = false;
		if( toAdd == null )
		{
			throw new NullPointerException( "Cannot store an null" );
		}
		if( index < 0 || index > size )
		{
			throw new IndexOutOfBoundsException( "Index is outside current list boundary '" + index + "'" );
		}

		if( !isEmpty() )
		{
			if( size == 1 && index == 0 )
			{
				head = new SLLNode<E>( toAdd, tail );
				size++;
				status = true;
			}
			else if( index == 0 )
			{
				SLLNode<E> next = head;
				head = new SLLNode<E>( toAdd, next );
				size++;
				status = true;
			}
			else if( index == size )
			{
				SLLNode<E> before = tail;
				tail = new SLLNode<E>( toAdd, null );
				before.setNext( tail );
				size++;
				status = true;
			}
			else
			{
				SLLNode<E> before = head;
				SLLNode<E> after = head.getNext();

				for( int i = 0; i < ( index - 1 ); i++ )
				{
					before = before.getNext();
					after = after.getNext();
				}

				SLLNode<E> insert = new SLLNode<E>( toAdd, after );
				before.setNext( insert );
				size++;
				status = true;
			}
		}
		else
		{
			head = new SLLNode<E>( toAdd, null );
			tail = head;
			size++;
			status = true;
		}
		return status;
	}

	/**
	 * Method to add an element to the end of a List. If the list is empty the
	 * element will be added to the first position.
	 *
	 * @param toAdd - element to be added to the list
	 * @return <code>true</code> if the element is appended successfully.
	 */
	public boolean add( E toAdd )
	{
		return add( size, toAdd );
	}

	/**
	 * Appends all of the elements of the list passed into the method to the end of
	 * this list, in the order that they are according to the logical position of
	 * the list being appended.
	 *
	 * @param toAdd list to be added to the end of the current list.
	 * @return true if the new list is appended successful.
	 * @throws NullPointerException if the list to append is null.
	 */
	public boolean addAll( ListADT<? extends E> toAdd ) throws NullPointerException
	{
		if( toAdd == null )
		{
			throw new NullPointerException( "Cannot add a null list" );
		}

		for( int i = 0; i < toAdd.size(); i++ )
		{
			this.add( toAdd.get( i ) );
		}
		return true;
	}

	/**
	 * Gets a reference to the element at the specified position. If the list is
	 * empty returns a <code>null</code> reference.
	 *
	 * @param index the position within the list of the desired element
	 * @return element of the node at that position
	 * @throws IndexOutOfBoundsException when the index is
	 *                                   <code>(index < 0 || index > size())</code>
	 */
	public E get( int index ) throws IndexOutOfBoundsException
	{
		if( index < 0 || index > size() )
		{
			throw new IndexOutOfBoundsException(
					"The index is outside the bounds of the current list : index = " + index );
		}

		if( !isEmpty() )
		{
			SLLNode<E> curr = head;
			for( int i = 0; i < index; i++ )
			{
				curr = curr.getNext();
			}
			return curr.getElement();
		}
		else
		{
			throw new IndexOutOfBoundsException( "list is empty." );
		}
	}

	/**
	 * Removes the element at the provided index, if the index is outside the bounds
	 * of the the list an exception is thrown
	 *
	 * @param index the position of the element in the list to be removed
	 * @return element being removed from the list
	 * @throws IndexOutOfBoundsException when the index is
	 *                                   <code>index < 0 || index >= size()</code>
	 */
	public E remove( int index ) throws IndexOutOfBoundsException
	{
		if( index < 0 || index >= size() )
		{
			throw new IndexOutOfBoundsException(
					"The index is outside the bounds of the current list : index = " + index );
		}

		if( !isEmpty() )
		{
			if( index == 0 )
			{
				SLLNode<E> del = head;
				head = head.getNext();
				size--;
				return del.getElement();
			}
			else
			{
				SLLNode<E> del = head.getNext();
				SLLNode<E> prev = head;
				for( int i = 1; i < index; i++ )
				{
					del = del.getNext();
					prev = prev.getNext();
				}
				E deleted = del.getElement();
				prev.setNext( del.getNext() );
				size--;
				return deleted;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * // TODO Write some docs here!
	 */
	public E remove( E toRemove ) throws NullPointerException
	{
		// Check if the list is empty
		if (head == null)
			return null;

		// If the head node itself holds the element to be deleted
		if (head.getElement().equals(toRemove)) {
			E deletedElement = head.getElement();
			head = head.getNext();
			return deletedElement;
		}

		// Search for the element to be deleted, and keep track of the previous node
		SLLNode<E> current = head;
		SLLNode<E> prev = null;
		while (current != null && !current.getElement().equals(toRemove)) {
			prev = current;
			current = current.getNext();
		}

		// If the element is not present in the list
		if (current == null)
			return null;

		// Unlink the node from the linked list
		prev.setNext(current.getNext());
		return current.getElement();

	}

	/**
	 * Replaces the element at the specified index in this list with the specified
	 * element.
	 *
	 * @param toChange - element to be stored at the specified index
	 * @param index    - index of the element to replace
	 * @return reference to the element previously stored at the specified index
	 * @throws IndexOutOfBoundsException when the index is
	 *                                   <code>(index < 0 || index >= size())</code>
	 */
	public E set( int index, E toChange ) throws NullPointerException, IndexOutOfBoundsException
	{
		if( toChange == null )
		{
			throw new NullPointerException( "Cannot store an null" );
		}
		if( index < 0 || index >= size() )
		{
			throw new IndexOutOfBoundsException(
					"The index is outside the bounds of the current list : index = " + index );
		}
		E old = null;

		if( !isEmpty() )
		{
			if( index == 0 )
			{
				old = head.getElement();
				head.setElement( toChange );
			}
			else if( index == size - 1 )
			{
				old = tail.getElement();
				tail.setElement( toChange );
			}
			else
			{
				int i;
				SLLNode<E> set;

				for( i = 0, set = head; i < index; set = set.getNext(), i++ )
					;

				old = set.getElement();
				set.setElement( toChange );
			}
		}
		else
		{
			old = null;
		}
		return old;
	}

	/**
	 * Returns the current status of the list.
	 *
	 * @return <code>false</code> if the list contains at least one element.
	 */
	public boolean isEmpty()
	{
		return head == null;
	}

	/**
	 * Checks the list and determines if the object exists in the list, if the
	 * object is present in the list, the method returns true.
	 *
	 * @param toFind element to be searched in this list.
	 * @return <code>true</code> if object is in list, otherwise false.
	 */
	public boolean contains( E toFind )
	{
		SLLNode<E> curr = head;
		while( curr.getNext() != null )
		{
			if( toFind.equals( curr.getElement() ) )
			{
				return true;
			}
			curr = curr.getNext();
		}
		return false;
	}

	/**
	 * // TODO Write some docs here!
	 */
	public E[] toArray( E[] toHold ) throws NullPointerException
	{
		// TODO intentionally left for you to complete!
		return toHold;
	}

	/**
	 * Converts the current list into an array.
	 *
	 * @return the array containing all the elements of the list in the proper
	 *         order. (first element at index 0, last element at index n-1)
	 */
	public Object[] toArray()
	{
		Object[] toReturn = new Object[this.size];

		SLLNode<E> curr = head;
		for( int i = 0; i < this.size; i++ )
		{
			toReturn[i] = curr.getElement();
			curr = curr.getNext();
		}
		return toReturn;
	}

	/**
	 * Returns a new instance of an iterator for this list in proper order. (from
	 * first element to last element)
	 */
	public Iterator<E> iterator()
	{
		return new SLLIterator();
	}

	/*******************************
	 * INNER CLASSES
	 *********************************/
	private class SLLIterator implements Iterator<E>
	{
		// Attributes
		private E[] copyOfElements;
		private int pos;

		// Constructors
		@SuppressWarnings( "unchecked" )
		public SLLIterator()
		{
			copyOfElements = (E[]) ( new Object[size()] );
			SLLNode<E> curr = head;

			for( int i = 0; i < size; i++ )
			{
				copyOfElements[i] = curr.getElement();
				curr = curr.getNext();
			}
		}

		/**
		 * Returns <code>true</code> if the iteration has more elements. (In other
		 * words, returns <code>true</code> if <code>next()</code> would return an
		 * element rather than throwing an exception.)
		 *
		 * @return <code>true</code> if the iterator has more elements.
		 */
		@Override
		public boolean hasNext()
		{
			return pos < copyOfElements.length;
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @return The next element in the iteration.
		 * @throws NoSuchElementException If the iteration has no more elements.
		 */
		@Override
		public E next() throws NoSuchElementException
		{
			E toReturn = copyOfElements[pos];
			pos++;
			return toReturn;
		}
	}

}