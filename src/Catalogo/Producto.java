package Catalogo;

public class Producto extends Item {

	private int sku;
	private double finalPrice;
	private String category;

	public Producto (String name, String description, double price, double discount, int weight, int sku, String category) {
		super(name, description, price, discount, weight);
		this.sku = sku;
		this.finalPrice = price * (1-discount);
		this.category = category;
	}
	
	public double getFinalPrice () {
		return finalPrice;
	}
	
	@Override
    public boolean validateProduct () {
		boolean validSku = sku != 0;
		return validateItem() && validSku;
    }
}
