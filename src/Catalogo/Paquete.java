package Catalogo;

import java.util.List;

import Exceptions.StockUnchangeableException;

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
	
	
	@Override
	public boolean inStock() {
		return getStock() > 0;
	}
	
	@Override
	public int getStock() {
		return items.stream().mapToInt(Item::getStock).min().orElse(0);
	}
	
	@Override
	public void increaseStock (int amount) {
		items.forEach(item -> item.increaseStock(amount));
	}
	
	@Override
	public void reduceStock (int amount) {
		items.forEach(item -> item.reduceStock(amount));
	}
	
	@Override
	public void setStock(int stock) {
		throw new StockUnchangeableException ();
	}
}
