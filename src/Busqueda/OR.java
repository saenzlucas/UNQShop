package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class OR implements Criterio {
	
	private List<Criterio> criteria;
	
	public OR (List<Criterio> criteria) {
		this.criteria = criteria;
	}

	@Override
	public List<Item> filter (List<Item> catalog) {
		//return this.criteria.stream().anyMatch(criterio -> criterio.satisface(item));
		return new ArrayList <> ();
	}
}
