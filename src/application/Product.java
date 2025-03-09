package application;

public class Product {
	String name, img, amount, doseunit;
	int dose;
	double price;
	
	public Product(String name, String img, String amount, String doseunit, int dose, double price) {
		super();
		this.name = name;
		this.img = img;
		this.amount = amount;
		this.doseunit = doseunit;
		this.dose = dose;
		this.price = price;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getDoseUnit() {
		return doseunit;
	}
	
	public void setDoseUnit(String doseunit) {
		this.doseunit = doseunit;
	}
	
	public int getDose() {
		return dose;
	}
	
	public void setDose(int dose) {
		this.dose = dose;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
