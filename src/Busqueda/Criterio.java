package Busqueda;

import java.util.List;

import Catalogo.Item;

public interface Criterio {
	
	List<Item> filtrar (List<Item> catalogo);
	
}
