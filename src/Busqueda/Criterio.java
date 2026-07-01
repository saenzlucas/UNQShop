package Busqueda;

import java.util.Map;

import Catalogo.Item;

public interface Criterio {
	Map<Item, Integer> filter (Map<Item, Integer> catalog);
}
