package Catalogo;

import java.util.List;

public class Paquete extends Item {

private List<Item> items;
	
	public Paquete(String name, String description, double price, double discount, int weight, List<Item> items) {
		super(name, description, price, discount, weight);
		this.items = items;
	}
	
	// OJO la recursion, no es sobre producto sino sobre item
	public double getFinalPrice () {
		double total = items.stream().mapToDouble(item -> ((Producto) item).getFinalPrice()).sum();
		return (total * (1-getDiscount()));
	}
	
	@Override
    public boolean validateProduct() {
		boolean nombreValido = getName () != null;        
        boolean itemsValidos = this.items.stream().allMatch(item -> item.validateProduct());                            
        //boolean dinamicosValidos = !this.getAttributes().containsValue(null);
        //return nombreValido && itemsValidos && dinamicosValidos;
        return nombreValido && itemsValidos;
    }
	
}
