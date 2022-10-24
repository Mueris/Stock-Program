
import java.util.Random;

public class SingleLinkedList {

	private ElementNode head;	

	//For adding dices
	public void deleteAllElements() {
		head=null;
	}
	public void add(Product dataToAdd) {
		if(head == null) {
			ElementNode newNode = new ElementNode(dataToAdd);
			head = newNode;
		}
		else {
			ElementNode temp = head;
			while(temp.getNext() != null)
				temp = temp.getNext();
			ElementNode newNode = new ElementNode(dataToAdd);
			temp.setNext(newNode);
		}
	}
	public ElementNode getHead() {
        if(head != null)
            return head;
        else
            return null;
    }
	
	public int size() {
		if(head == null)
			return 0;
		else {
			int count = 0;
			ElementNode temp = head;
			while(temp != null) {
				temp = temp.getNext();
				count++;
			}
			return count;
		}
	}

	
	
	
	
	
	
	public void remove(Object dataToDelete) {
		if(head == null)
			System.out.println("List is empty");
		else {
			int count = 0;
			while((Integer) head.getData() == (Integer) dataToDelete) {
				head = head.getNext();
				count++;
			}
			ElementNode temp = head;
			ElementNode previous = null;
			while(temp != null) {
				if((Integer) temp.getData() == (Integer) dataToDelete) {
					previous.setNext(temp.getNext());
					temp = previous;
					count++;
				}
				previous = temp;
				temp = temp.getNext();
				if(count == 1)
					break;
			}
		}
	}
	
	
	
	
	public boolean isEmpty() {
		if(head == null) {
			return true;
		}
		else
			return false;
	}
	
	
}
