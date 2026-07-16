package Catalogo;

import java.util.List;

public class Paquete extends Item {

private List<Item> items;
	
	public Paquete (int sku, String name, String description, String category, double discount, List<Item> items) {
		super(sku, name, description, category, discount);
		this.items = items;
	}
	
	@Override
	public boolean validateItem () {
		boolean validSku = getSku() != 0;
		boolean validName = getName() != null;
		boolean validDinamics =  getDinamics().values().stream().allMatch(attribute -> attribute != null);
		return items.stream().allMatch(Item::validateItem) && validSku && validName && validDinamics;
	}
	
	@Override
	public int getWeight() {
		return items.stream().mapToInt(Item::getWeight).sum();
	}
	
	@Override
	public double getPrice() {
		return items.stream().mapToDouble(Item::getPrice).sum();
	}
	
	@Override
	public double getFinalPrice () {
		return ((items.stream().mapToDouble(Item::getFinalPrice).sum()) * (1-getDiscount()));
	}
}
