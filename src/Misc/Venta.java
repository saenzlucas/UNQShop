package Misc;

import java.time.LocalDate;
import java.util.Map;

import Catalogo.Item;

public class Venta {
	
	private Map<Item, Integer> items;
	private LocalDate date;
	
	public Venta(Map<Item, Integer> items, LocalDate date) {
		this.items = items;
		this.date = date;
	}
	
	public Map<Item, Integer> getItems() {
		return items;
	}

	public LocalDate getDate() {
		return date;
	}
	
}
