package Catalogo;

import java.util.HashMap;
import java.util.Map;

public abstract class Item {

	private int sku;
	private String name;
	private String description;
	private String category;
	private double discount;
	private Map<String, Object> dinamics;
	
	public Item (int sku, String name, String description, String category, double discount) {
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.category = category;
		this.discount = discount;
		this.dinamics = new HashMap<>();
	}
	
	public int getSku() {
		return sku;
	}

	public String getName () {
		return name;
	}

	public String getDescription () {
		return description;
	}

	public String getCategory() {
		return category;
	}
	
	public double getDiscount () {
		return discount;
	}
	
	public Map<String, Object> getDinamics () {
		return dinamics;
	}

	public Object getAttribute (String attribute) {
		return dinamics.get(attribute);
	}
	
	public void addAttribute (String attribute, Object data) {
		dinamics.put(attribute, data);
	}
	
	public boolean isCategory (String category) {
		return getCategory().equalsIgnoreCase(category);
	}
	
	public abstract boolean validateItem ();
	
	public abstract int getWeight ();
	
	public abstract double getPrice ();
	
	public abstract double getFinalPrice ();
}
