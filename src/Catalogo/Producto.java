package Catalogo;

public class Producto extends Item {
	
	private double price;
	private int weight;

	public Producto (int sku, String name, String description, String category, double discount, double price, int weight) {
		super(sku, name, description, category, discount);
		this.price = price;
		this.weight = weight;
	}
	
	@Override
	public boolean validateItem () {
		boolean validSku = getSku() != 0;
		boolean validName = getName() != null;
		boolean validDinamics =  getDinamics().values().stream().allMatch(attribute -> attribute != null);
		return validSku && validName && validDinamics;
	}
	
	@Override
	public int getWeight() {
		return weight;
	}
	
	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public double getFinalPrice () {
		return (price * (1-getDiscount()));
	}
}
