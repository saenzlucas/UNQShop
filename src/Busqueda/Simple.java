package Busqueda;

import java.util.List;

import Catalogo.Item;

public abstract class Simple implements Criterio {
	
	public abstract List<Item> filtrar (List<Item> catalogo);
	
}
