package Busqueda;

import java.util.Map;
import java.util.stream.Collectors;

import Catalogo.Item;

public class Disponibilidad implements Criterio {
	@Override
	public Map<Item, Integer> filter(Map<Item, Integer> catalog) {
		return catalog.entrySet().stream().filter(item -> item.getValue() > 0).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
}
