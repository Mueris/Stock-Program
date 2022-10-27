
import java.awt.Color;




public class MLL {
	private ColumnNode head;
	//TextAttributes color = new TextAttributes(Color.WHITE,Color.BLACK);
	
	public MLL() {
		head = null;
	}
	
	public ColumnNode getHead() {
		return head;
	}
	public void addColumn(Product product) { // adds a new column
		ColumnNode newNode = new ColumnNode(product.getCategory());
		if(head == null) {
			head = newNode;
		}
		else{
			ColumnNode temp = head;
			while(temp.getDown()!=null) {
				temp = temp.getDown();
			}
			temp.setDown(newNode);
			
		}
		
	}
	
	
	public void addElement(String columnName , Product product) { //This function has been improved so now you dont need to add category
		//if there is no such category, function adds it Automaticly.
		// Adds an element to the column entered as a parameter
		
		if(head == null) {
			addColumn(product);
			addElement(product.getCategory(),product);
		}
		else {
			ElementNode newNode = new ElementNode(product);
			ColumnNode temp = head;
			ColumnNode tempPre= temp;
			while(temp!=null && !columnName.equals(temp.getData())) {
				tempPre = temp;
				temp = temp.getDown();
			}
			if(temp!=null) {
				if(temp.getRight()==null) {
					temp.setRight(newNode);
				}
				else {
					ElementNode tempE = temp.getRight();
					while(tempE.getNext()!=null) {
						tempE = tempE.getNext();
					}
					tempE.setNext(newNode);
				}
			}
			else {
				addColumn(product);
				tempPre=tempPre.getDown();
				tempPre.setRight(newNode);
				
			}
		}		
	}
	
	
	
	public void display() {  // prints columns and heights
		
		if(head == null) {
			System.out.println("there is no data to display");
		}
		else {
			ColumnNode temp= head;
			while(temp!=null ) {
				System.out.println(temp.getData());
				temp=temp.getDown();
			}
		}
	}
	
	
	public int columnSize() {  // return the number of columns
		if(head == null) {
			System.out.println("there is no column");
			return 0;
		}
		else {
			ColumnNode temp = head;
			int count = 0;
			while(temp!=null) {
				count++;
				temp = temp.getDown();
			}
			return count;
		}
	}
	
	
	public int elementSize(String column) { // returns the number of elements on column that entered parameter
		if(head == null) {
			System.out.println("there is no column");
			return 0;
		}
		else {
			int count = 0;
			ColumnNode temp = head;
			while(temp!=null && (String)temp.getData()!=column) {
				temp = temp.getDown();
			}
			if(temp!=null && temp.getRight()!=null) {
				
				ElementNode tempE = temp.getRight();
				count=1;
				while(tempE!=null && tempE.getNext()!=null) {
					count++;
					tempE = tempE.getNext();
				}
				temp = temp.getDown();
			}
			else {
				System.out.println("there is no column like : "+ column);
				
			}
			return count;
		}
		
	}
	public void deleteAllElements() {
		head = null;
		
	}
	public ColumnNode newHead(ColumnNode tempC) {
		ColumnNode temp = head;
		tempC.setDown(temp);
		return tempC;
	}
	public void headChanger(ColumnNode newHead) {
		ColumnNode temp =head;
		newHead.setDown(temp.getDown());
		newHead.setRight(newHead.getRight());
		newHead.setData(newHead.getData());
		head = newHead;
		
	}
	public void remove(Product pro) {
		ColumnNode temp =head;
		ElementNode tempE= null;
		ElementNode tempEPre=null;
		
		while(temp.getDown() !=  null && !pro.getCategory().equals(temp.getData())) {//Category based searching.
			temp=temp.getDown();
		}
		if(pro.getCategory().equals(temp.getData())) {//Category has been found.
			tempE=temp.getRight();
			
		}
		else {
			//no Category found
		}
		if(temp.getDown()==null &&tempE.getNext()==null && pro.getName().equals(((Product) tempE.getData()).getName()) && pro.getBarcode() == ((Product) tempE.getData()).getBarcode()) {
			//NULL PO�NTER EXCEPT�ON SEBEB OLUYOR TEK �TEM KKALIRSA S�L�NMES�N ��MD�L�K!
		}
		
		while(tempE.getNext()!=null && !pro.getName().equals(((Product) tempE.getData()).getName()) && pro.getBarcode() != ((Product) tempE.getData()).getBarcode()) {
			//product based searcihng
			tempEPre=tempE;
			tempE=tempE.getNext();
		}
		if(tempEPre==null) {
			temp.setRight(tempE.getNext());
		}
		else if(tempE.getNext()!=null && pro.getName().equals(((Product) tempE.getData()).getName()) && pro.getBarcode() == ((Product) tempE.getData()).getBarcode()){
			tempEPre.setNext(tempE.getNext());
			//remove(pro);//calling the function again so if there is a exact  CANCELED NO NEED!
		}
		else if(pro.getName().equals(((Product) tempE.getData()).getName()) && pro.getBarcode() == ((Product) tempE.getData()).getBarcode()) {
			tempEPre.setNext(null);
		}
		else {
			//No product found so continue with function.
			//Swing data = new Swing();
			//data.function(list, listShwon, pro.getCategory());��MD�L�K KALDIRILDI HANG� ARAY�ZDEN S�L�NECE��N� B�LM�YORUZ
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	
}
