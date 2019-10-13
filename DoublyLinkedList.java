/**
 * DoublyLinkedList
 * A LinkedList that implements both a front and end node
 * Each ListNode is doubly linked, containing both a previous + next
 * pointer. Also provides methods for getting, setting, inserting, and
 * removing ListNodes.
 */

import java.util.*;

public class DoublyLinkedList<E> {
	private ListNode front;
	private ListNode end;
	private int size;

	// Verifies that index is within bounds [0, size)
	private void checkIndex (int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index out of bounds");
	}

	// Returns ListNode at index
	private ListNode getNodeByIndex (int index) {
		ListNode current = null;

		// If index is in upper half of size, iterate from the end
		// rather than the front
		if (index < size / 2) {
			current = front;
			for (int i = 0; i < index; i++)
				current = current.next;
		} else {
			current = end;
			for (int i = 0; i < size - index - 1; i++)
				current = current.previous;
		}

		return current;
	}

	public boolean isEmpty () {
		return size == 0;
	}

	public int size () {
		return size;
	}

	// Returns the data at index
	public E get (int index) {
		checkIndex(index);
		return getNodeByIndex(index).data;
	}

	// Sets the element at index to value, and returns original value
	public E set (int index, E value) {
		checkIndex(index);

		ListNode current = getNodeByIndex(index);
		E toRet = current.data;
		current.data = value;
		return toRet;
	}

	// Adds element data after index
	public void addAfter (int index, E data) {
		checkIndex(index);

		if (index == size - 1) {
			addLast(data);
			return;
		}

		ListNode current = getNodeByIndex(index);
		ListNode toAdd = new ListNode(current, data, current.next);
		current.next.previous = toAdd;
		current.next = toAdd;

		size++;
	}

	// Adds element data to the front of the list
	public void addFront (E data) {
		front = new ListNode(null, data, front);

		if (front.next != null)
			front.next.previous = front;
		else
			end = front;

		size++;
	}

	// Adds element to the end of the list
	public void addLast (E data) {
		if (isEmpty())
			addFront(data);
		else {
			end = new ListNode(end, data, null);
			end.previous.next = end;
			size++;
		}
	}

	// Remove element data if found
	public void remove (E data) {
		ListNode current = front;

		while (current != null) {
			if (current.data.equals(data)) {
				// Updates front if current was front
				if (current.previous == null)
					front = current.next;
				else
					current.previous.next = current.next;

				// Updates end if current was end
				if (current.next == null)
					end = current.previous;
				else
					current.next.previous = current.previous;

				current.next = null;
				current.previous = null;

				size--;
				return;
			}

			current = current.next;
		}

		throw new NoSuchElementException("Element not found");
	}

	// Removes first element
	public void removeFirst () {
		if (isEmpty())
			throw new NoSuchElementException("No elements in list");

		front = front.next;
		size--;

		// Clear end if list is now empty
		if (!isEmpty()) {
			front.previous.next = null;
			front.previous = null;
		} else {
			end = null;
		}
	}

	// Removes last element
	public void removeLast () {
		if (isEmpty())
			throw new NoSuchElementException("No elements in list");

		end = end.previous;
		size--;

		// Clear front if list is now empty
		if (!isEmpty()) {
			end.next.previous = null;
			end.next = null;
		} else {
			front = null;
		}
	}

	// Represents a List Node in the LinkedList
	private class ListNode {
		private ListNode next;
		private ListNode previous;
		private E data;

		public ListNode (ListNode p, E d, ListNode n) {
			previous = p;
			next = n;
			data = d;
		}
	}

	public String toString () {
		String res = "[";

		ListNode current = front;

		while (current != null) {
			res += current.data + ", ";
			current = current.next;
		}

		return res + "]";
	}

	public void debug () {
		ListNode next = front;
		int i = 0;

		while (next != null) {
			System.out.println("[" + i + "]: " + next.data);
			System.out.println("PREV: " + next.previous);
			System.out.println("CURRENT: " + next);
			System.out.println("NEXT: " + next.next);
			System.out.println();
			next = next.next;
			i++;
		}

		System.out.println("SIZE: " + size);
		System.out.println();
	}

	public Iterator<E> iterator () {
		return new Iterator<E> () {
			private ListNode iterLoc = front;
			private boolean removeOk = false;

			public boolean hasNext () {
				return iterLoc != null;
			}

			public E next () {
				if (!hasNext())
					throw new NoSuchElementException();

				E toRet = iterLoc.data;
				iterLoc = iterLoc.next;

				removeOk = true;

				return toRet;
			}

			public void remove () {
				if (!removeOk)
					throw new IllegalStateException();

				ListNode toRem;

				if (iterLoc != null)
					toRem = iterLoc.previous;
				else
					toRem = end;

				if (toRem.previous == null) {
					front = toRem.next;
				} else {
					toRem.previous.next = toRem.next;
					toRem.previous = null;
				}

				if (toRem.next == null) {
					end = toRem.previous;
				} else {
					toRem.next.previous = toRem.previous;
					toRem.next = null;
				}

				removeOk = false;
				size--;
			}
		};
	}
}
