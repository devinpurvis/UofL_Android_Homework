package doublylinkedlist;

import junit.framework.Assert;

import org.junit.Test;


public class DoublyLinkedListTest {
	
	@Test
	public void test() {
	MyDoublyLinkedList list = new MyDoublyLinkedList();
	
	list.insertLast("wes");
	list.insertLast("kim");
	list.insertLast("justin");
	list.insertLast("leanne");
	
	list.displayBackwards();

	}
}
