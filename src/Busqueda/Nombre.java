package Busqueda;

import java.util.List;
import java.util.stream.Collectors;

import Catalogo.Item;

public class Nombre implements Criterio {

	private String name;
	
	public Nombre(String name) {
		this.name = name;
	}

	@Override
	public List<Item> filter(List<Item> catalog) {
		return catalog.stream().filter(item -> item.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
	}

}
