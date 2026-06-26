package Busqueda;

import java.util.List;
import java.util.stream.Collectors;

import Catalogo.Item;

public class Categoria implements Criterio {
	
	private String category;
	
	public Categoria(String category) {
		this.category = category;
	}

	@Override
	public List<Item> filter(List<Item> catalog) {		
		return catalog.stream().filter(item -> item.isCategory(category)).collect(Collectors.toList());
	}
	
}
