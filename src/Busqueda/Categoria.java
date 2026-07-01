package Busqueda;

import java.util.Map;
import java.util.stream.Collectors;

import Catalogo.Item;

public class Categoria implements Criterio {

	private String category;

	public Categoria(String category) {
		this.category = category;
	}

	@Override
	public Map<Item, Integer> filter(Map<Item, Integer> catalog) {
		return catalog.entrySet().stream().filter(item -> item.getKey().isCategory(category)).collect(Collectors.toMap(item -> item.getKey(), item -> item.getValue()));
	}
}
