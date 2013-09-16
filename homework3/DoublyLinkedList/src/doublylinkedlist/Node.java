package doublylinkedlist;

public class Node {

	public Node next;
	public Node previous;
	public String name;
	

	public Node(String nm)
	{
		name = nm;
	}
	
	public void displayNode()
	{
		System.out.println(name);
	}

}
