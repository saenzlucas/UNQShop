package Catalogo;

public class Producto extends Item {

	private int sku;
	private int stock;
	private double finalPrice;
	private String category;

	public Producto (String name, String description, double price, double discount, int stock, int weight, int sku, String category) {
		super(name, description, price, discount, weight);
		this.sku = sku;
		this.stock = stock;
		this.finalPrice = price * (1-discount);
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}

	@Override
	public double getFinalPrice () {
		return finalPrice;
	}
	
	@Override
    public boolean validateProduct () {
		boolean validSku = sku != 0;
		return validateItem() && validSku;
    }
	
	@Override 
	public boolean isCategory (String category) {
		return getCategory().equalsIgnoreCase(category);
	}
	
	@Override
	public boolean inStock() {
		return getStock () > 0;
	}
	
	@Override
	public int getStock() {
		return stock;
	}
	
	@Override
	public void increaseStock (int amount) {
		setStock(getStock()+amount);
	}
	
	@Override
	public void reduceStock (int amount) {
		setStock(getStock()-amount);
	}
	
	@Override
	public void setStock(int stock) {
		this.stock = stock;
	}
}
