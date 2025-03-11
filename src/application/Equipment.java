package application;

public class Equipment {
	String name;
	String img;
	double price;

	
	public Equipment(String name, String img, double price) {
		super();
		this.name = name;
		this.img = img;
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
