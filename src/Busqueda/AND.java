package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class AND implements Criterio {
	
	private List<Criterio> criteria;

	public AND (List<Criterio> criteria) {
		this.criteria = criteria;
	}

	@Override
	public List<Item> filter (List<Item> catalog) {
		//return this.criteria.stream().allMatch(criterio -> criterio.satisface(item));  [Tiene que devolver la lista, no un booleano]
		return new ArrayList <> ();
	}
}
