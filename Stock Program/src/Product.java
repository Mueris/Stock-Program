
public class Product   {
	private String category;
	private String name;
	private int barcode;
	private int stock;
	//basic properties.. Next step sells will be added.



public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getBarcode() {
	return barcode;
}

public void setBarcode(int barcode) {
	this.barcode = barcode;
}

public int getStock() {
	return stock;
}

public void setStock(int stock) {
	this.stock = stock;
}
public void addProduct(MLL list, Product product) {
	list.addElement(product.category, product);
	
}
}
