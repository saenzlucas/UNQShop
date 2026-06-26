package Busqueda;

import java.util.List;
import java.util.stream.Collectors;

import Catalogo.Item;

public class OR implements Criterio {
	
	private List<Criterio> criteria;
	
	public OR (List<Criterio> criteria) {
		this.criteria = criteria;
	}

	@Override
	public List<Item> filter (List<Item> catalog) {
		return criteria.stream().flatMap(criterio -> criterio.filter(catalog).stream()).distinct().collect(Collectors.toList());
	}
}
