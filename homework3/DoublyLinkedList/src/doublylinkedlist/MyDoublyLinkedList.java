package doublylinkedlist;


public class MyDoublyLinkedList {
	private Node first;
	private Node last;
	
	public MyDoublyLinkedList()
	{
		first = null;
		last = null;
	}
	
	public boolean isEmpty()
	{
	    return first == null;
	}

	public void insertLast(String value)
	{
		Node newNode = new Node(value); 
	 	if (isEmpty()) 
	 		first = newNode; 
	 	else {
	 		last.next = newNode; 
	 		newNode.previous = last; 
	 		 }
	 	last = newNode; 
	}


	public void displayBackwards() 
	{
		System.out.print("List backwards: ");
		Node current = last;
		while (current != null)
		{
			current.displayNode();
		    current = current.previous;
		}
	    System.out.println("");
	}

}
