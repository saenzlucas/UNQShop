package Catalogo;

import java.util.List;

public class Paquete extends Item {

private List<Item> items;
	
	public Paquete (String name, String description, double price, double discount, int weight, List<Item> items) {
		super(name, description, price, discount, weight);
		this.items = items;
	}
	
	@Override
	public double getFinalPrice () {
		double total = items.stream().mapToDouble(Item::getFinalPrice).sum();
		return (total * (1-getDiscount()));
	}
	
	@Override
    public boolean validateProduct () {
        boolean validItems = items.stream().allMatch(item -> item.validateProduct());                            
        return validateItem() && validItems;
    }
	
	@Override 
	public boolean isCategory (String category) {
		return items.stream().anyMatch(item -> item.isCategory(category));
	}
}
