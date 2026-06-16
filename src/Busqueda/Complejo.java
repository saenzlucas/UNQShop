package Busqueda;

import java.util.List;

import Catalogo.Item;

public abstract class Complejo implements Criterio {
	
	private List<Criterio> criterios;
	
	public Complejo (List<Criterio> criterios) {
		this.criterios = criterios;
	}

	public abstract List<Item> filtrar (List<Item> catalogo);
	
}
