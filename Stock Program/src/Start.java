import java.util.Scanner;

public class Start {

	public static void main(String[] args) {
		Swing sw = new Swing();
		MLL list = new MLL();
		Product product = new Product();
		product.setBarcode(0);
		product.setCategory("yCategory");
		product.setName("pro");
		product.setStock(3);
		list.addElement(product.getCategory(), product);
		Product product2 = new Product();
		product2.setBarcode(0);
		product2.setCategory("xCategory");
		product2.setName("elma");
		product2.setStock(3);
		list.addElement(product2.getCategory(), product2);
		Product product3 = new Product();
		product3.setBarcode(0);
		product3.setCategory("xCategory");
		product3.setName("YAR");
		product3.setStock(3);
		list.addElement(product3.getCategory(), product3);
		
		Scanner s= new Scanner(System.in);
		sw.menu(list);

	}

}
