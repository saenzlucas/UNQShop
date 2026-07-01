package Busqueda;

import java.util.Map;
import java.util.stream.Collectors;

import Catalogo.Item;

public class NOT implements Criterio {
	
	private Criterio criterion;

	public NOT (Criterio criterion) {
		this.criterion = criterion;
	}

	@Override
	public Map<Item, Integer> filter (Map<Item, Integer> catalog) {
		return catalog.entrySet().stream().filter(item -> !criterion.filter(catalog).containsKey(item.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
}
