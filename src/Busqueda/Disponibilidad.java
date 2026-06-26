package Busqueda;

import java.util.List;
import java.util.stream.Collectors;

import Catalogo.Item;

public class Disponibilidad implements Criterio {

	@Override
	public List<Item> filter(List<Item> catalog) {
		return catalog.stream().filter(item -> item.inStock()).collect(Collectors.toList());
	}
	
}
