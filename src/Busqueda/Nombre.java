package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class Nombre implements Criterio {

	private String nombre;
	
	public Nombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public List<Item> filtrar(List<Item> catalogo) {
		 //se pasan ambos strings a minusculas asi no se tienen en cuenta mayusculas y minusculas.
    	// return item.getNombre().toLowerCase().contains(this.textoABuscar.toLowerCase()); [Tiene que devolver la lista, no un booleano]
		return new ArrayList <> ();
	}
	
}
