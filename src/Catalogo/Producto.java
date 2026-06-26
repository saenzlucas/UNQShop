package Catalogo;

public class Producto extends Item {

	private int SKU;
	private double finalPrice;
	private String category;

	public Producto(String name, String description, double price, double discount, int weight, int SKU, String category) {
		super(name, description, price, discount, weight);
		this.SKU = SKU;
		this.finalPrice = price * (1-discount);
		this.category = category;
	}
	
	public double getFinalPrice () {
		return finalPrice;
	}
	
	
	@Override
    public boolean validateProduct() {
		boolean nameValido = getName () != null;
		boolean skuValido = SKU > 0;
       // boolean dinamicosValidos = !getAttributes().containsValue(null);
		//return nameValido && skuValido && dinamicosValidos;
		return nameValido && skuValido;
    }
	
}
