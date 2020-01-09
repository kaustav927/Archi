public class Node<E> {

	private Node<E> next;
	private E current;
	
	//creates the list
	public Node(){
		next = null;
		current = null;
	}
	
	public Node(E element) {
		next = null;
		current = element;
	}
	
	
	//get the next element
	public Node<E> getNext(){
		return next;
	}
	
	//add a new node to the list
	public void setNext(Node<E> newNode){
		next = newNode;
	}
	
	//method which returns the currentElement element inside the list
	public E getCurrent(){
		return current;
	}
	
	
	
}