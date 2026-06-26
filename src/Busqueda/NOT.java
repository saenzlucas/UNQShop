package Busqueda;

import java.util.List;
import java.util.stream.Collectors;

import Catalogo.Item;

public class NOT implements Criterio {
	
	private Criterio criterion;

	public NOT (Criterio criterion) {
		this.criterion = criterion;
	}

	@Override
	public List<Item> filter (List<Item> catalog) {
		return catalog.stream().filter(item -> !criterion.filter(catalog).contains(item)).collect(Collectors.toList());
	}
}
