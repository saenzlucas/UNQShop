package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class Nombre implements Criterio {

	private String name;
	
	public Nombre(String name) {
		this.name = name;
	}

	@Override
	public List<Item> filter(List<Item> catalog) {
		 //se pasan ambos strings a minusculas asi no se tienen en cuenta mayusculas y minusculas.
    	// return item.getname().toLowerCase().contains(this.textoABuscar.toLowerCase()); [Tiene que devolver la lista, no un booleano]
		return new ArrayList <> ();
	}
	
}
