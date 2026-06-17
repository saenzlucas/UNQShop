package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class OR implements Criterio {
	
	private List<Criterio> criterios;
	
	public OR (List<Criterio> criterios) {
		this.criterios = criterios;
	}

	@Override
	public List<Item> filtrar (List<Item> catalogo) {
		//return this.criterios.stream().anyMatch(criterio -> criterio.satisface(item));
		return new ArrayList <> ();
	}
}
