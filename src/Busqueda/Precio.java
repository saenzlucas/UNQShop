package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class Precio implements Criterio {

	private double maxPrice;
	
	public Precio(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Override
	public List<Item> filter(List<Item> catalog) {
		// return item.getPrecioBase() <= this.maxPrice; [Tiene que devolver la lista, no un booleano]
		return new ArrayList <> ();
	}
	
}
