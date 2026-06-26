package Busqueda;

import java.util.List;
import java.util.stream.Collectors;

import Catalogo.Item;

public class Precio implements Criterio {

	private double maxPrice;
	
	public Precio(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Override
	public List<Item> filter(List<Item> catalog) {
		return catalog.stream().filter(item -> item.getPrice() <= maxPrice).collect(Collectors.toList());
	}
	
}
