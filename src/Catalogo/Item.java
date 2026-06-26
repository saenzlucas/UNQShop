package Catalogo;

import java.util.HashMap;
import java.util.Map;

public abstract class Item {

	private String name;
	private String description;
	private double price;
	private double discount;
	private int weight;
	private Map<String, Object> dinamics;
	
	public Item (String name, String description, double price, double discount, int weight) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.weight = weight;
		this.dinamics = new HashMap<>();
	}
	
	public String getName () {
		return name;
	}

	public String getDescription () {
		return description;
	}

	public double getPrice () {
		return price;
	}

	public double getDiscount () {
		return discount;
	}
	
	public Map<String, Object> getDinamics () {
		return dinamics;
	}

	public void addAttribute (String attribute, Object data) {
		dinamics.put(attribute, data);
	}
	
	public Object getAttribute (String attribute) {
		return dinamics.get(attribute);
	}
	
	public boolean validateItem () {
		boolean validName = getName () != null;
		boolean validDinamics =  getDinamics().values().stream().allMatch(value -> value != null);
		return validName && validDinamics;
	}
	
	public abstract boolean validateProduct ();
	
	public abstract double getFinalPrice ();	
}
