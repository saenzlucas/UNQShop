package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class Disponibilidad implements Criterio {

	@Override
	public List<Item> filter(List<Item> catalog) {
		 //return this.depositos.stream().anyMatch(deposito -> deposito.tieneStock(item)); [Tiene que devolver la lista, no un booleano]
		return new ArrayList <> ();
	}
	
}
