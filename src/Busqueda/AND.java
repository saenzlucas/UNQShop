package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class AND implements Criterio {
	
	private List<Criterio> criterios;

	public AND (List<Criterio> criterios) {
		this.criterios = criterios;
	}

	@Override
	public List<Item> filtrar (List<Item> catalogo) {
		//return this.criterios.stream().allMatch(criterio -> criterio.satisface(item));  [Tiene que devolver la lista, no un booleano]
		return new ArrayList <> ();
	}
}
