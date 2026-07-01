package Busqueda;

import java.util.Map;
import java.util.stream.Collectors;

import Catalogo.Item;

public class Precio implements Criterio {

	private double maxPrice;
	
	public Precio(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Override
	public Map<Item, Integer> filter(Map<Item, Integer> catalog) {
		return catalog.entrySet().stream().filter(item -> item.getKey().getPrice() <= maxPrice).collect(Collectors.toMap(item -> item.getKey(), item -> item.getValue()));
	}
	
}
