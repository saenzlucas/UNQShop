package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class Precio implements Criterio {

	private double precioMax;
	
	public Precio(double precioMax) {
		this.precioMax = precioMax;
	}

	@Override
	public List<Item> filtrar(List<Item> catalogo) {
		// return item.getPrecioBase() <= this.precioMax; [Tiene que devolver la lista, no un booleano]
		return new ArrayList <> ();
	}
	
}
