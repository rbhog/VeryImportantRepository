/**
 * * Corresponds to one node in a singly linked list that we are building up *
 * ourselves. * @author Ishan Khetarpal * @param <E>
 */
public class ListNode<E> {
	private E data;
	private ListNode<E> next;

	/**
	 * * Construct a new list node with given data and no next node. * @param data -
	 * data held by this node
	 */
	public ListNode(E data) {
		this(data, null);
	}

	/**
	 * * Construct a new list node with given data and reference to next node.
	 * * @param data - data held by this node * @param next - reference to next node
	 */
	public ListNode(E data, ListNode<E> next) {
		setData(data);
		setNext(next);
	}

	/** * @return the data held in this node */
	public E getData() {
		return data;
	}

	/**
	 * * Sets/updates the value of data held in this list node. * @param data - the
	 * data to be held in this node
	 */
	public void setData(E data) {
		this.data = data;
	}

	/** * @return reference the next node in the list */
	public ListNode<E> getNext() {
		return next;
	}

	/**
	 * * Sets the node to point at the next node. * @param next - reference to next
	 * node
	 */
	public void setNext(ListNode<E> next) {
		this.next = next;
	}
}
