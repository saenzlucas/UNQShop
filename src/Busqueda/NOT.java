package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class NOT implements Criterio {
	
	private Criterio criterion;

	public NOT (Criterio criterion) {
		this.criterion = criterion;
	}

	@Override
	public List<Item> filter (List<Item> catalog) {
		 //return !this.criterioEnvuelto.satisface(item); [Tiene que devolver la lista, no un booleano]
		return new ArrayList <> ();
	}
}
