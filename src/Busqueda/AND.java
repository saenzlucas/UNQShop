package Busqueda;

import java.util.List;

import Catalogo.Item;

public class AND implements Criterio {
	
	private List<Criterio> criteria;

	public AND (List<Criterio> criteria) {
		this.criteria = criteria;
	}

	@Override
	public List<Item> filter (List<Item> catalog) {
		List<Item> newCatalog = catalog;
		for (Criterio criterion : criteria) {
            newCatalog = criterion.filter(newCatalog);
        }
		return newCatalog;
	}
}
