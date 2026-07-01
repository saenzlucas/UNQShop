package Busqueda;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Catalogo.Item;

public class OR implements Criterio {
	
	private List<Criterio> criteria;
	
	public OR (List<Criterio> criteria) {
		this.criteria = criteria;
	}

	@Override
	public Map<Item, Integer> filter (Map<Item, Integer> catalog) {
		return criteria.stream().flatMap(criteria -> criteria.filter(catalog).entrySet().stream()).distinct().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue));
	}
}
