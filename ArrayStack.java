import java.util.*;

public class ArrayStack<E> {
	
	private E[] data = (E[]) new Object[10];
	private int topLoc = -1;
	
	//Adds new data to the top 
	public void push(E item) {
		
		//When max capacity is reached resizes
		if(topLoc+1 == data.length) {
			resize();
		}		
		topLoc++;
		data[topLoc] = item;
	}
	
	//Removes the top and returns the data at top
	public E pop() {
		
		if(isEmpty()) {
			throw new NoSuchElementException("No Element");
		}
		//Retrieves the data from the top and moves topLoc backwards
		E toReturn = data[topLoc];
		topLoc--;
		return toReturn;
	}
	
	//Returns the top data
	public E peek() {
		
		if(isEmpty()) {
			throw new NoSuchElementException("No Element");
		}
		return data[topLoc];
	}
	
	public boolean isEmpty() {
		return topLoc == -1;
	}

	//Doubles the size of the array when capacity is reached
	private void resize() {

		E[] temp = (E[]) new Object[data.length*2];
		for(int i =0 ; i < data.length ; i++) {
			temp[i] = data[i];
		}
		data = temp;
	}
}
