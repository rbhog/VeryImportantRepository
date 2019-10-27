import java.util.*;

public class ListStack<E>{
	
	private ListNode top;
	
	//Adds in new data to the top 
	public void push(E item) {
		
		//Creates the top if list is empty 
		if(isEmpty()) {
			top = new ListNode(item, null);
			return;
		}
		top = new ListNode(item, top);
	}
	
	//Removes the top of the stack and returns the data 
	public E pop() {
		
		if(isEmpty()) {
			throw new NoSuchElementException("No Elements");
		}
		
		//Retrieves the data from the top and removes the node
		E toReturn = top.data;
		ListNode toRem = top;
		top = top.next;
		toRem.next = null;
		return toReturn;
	}
	
	//Returns the top data
	public E peek() {
		
		if(isEmpty()) {
			throw new NoSuchElementException("No Elements");
		}
		return top.data;
	}
	
	public boolean isEmpty() {
		
		return top == null;
	}
	
	public class ListNode{

		private E data;
		private ListNode next;

		public ListNode(E d, ListNode n){
			data = d;
			next = n;
		}
	}
	
}
