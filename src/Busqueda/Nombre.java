package Busqueda;

import java.util.Map;
import java.util.stream.Collectors;

import Catalogo.Item;

public class Nombre implements Criterio {

	private String name;
	
	public Nombre(String name) {
		this.name = name;
	}

	@Override
	public Map<Item, Integer> filter(Map<Item, Integer> catalog) {
		return catalog.entrySet().stream().filter(item -> item.getKey().getName().equalsIgnoreCase(name)).collect(Collectors.toMap(item -> item.getKey(), item -> item.getValue()));
	}
}
