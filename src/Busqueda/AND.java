package Busqueda;

import java.util.List;
import java.util.Map;

import Catalogo.Item;

public class AND implements Criterio {
	
	private List<Criterio> criteria;

	public AND (List<Criterio> criteria) {
		this.criteria = criteria;
	}

	@Override
	public Map<Item, Integer> filter (Map<Item, Integer> catalog) {
		return criteria.stream().reduce(catalog, (currentCatalog, criterion) -> criterion.filter(currentCatalog), (oldValue, newValue) -> oldValue);
	}
}
