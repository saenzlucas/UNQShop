package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class NOT implements Criterio {
	
	private Criterio criterio;

	public NOT (Criterio criterio) {
		this.criterio = criterio;
	}

	@Override
	public List<Item> filtrar (List<Item> catalogo) {
		 //return !this.criterioEnvuelto.satisface(item); [Tiene que devolver la lista, no un booleano]
		return new ArrayList <> ();
	}
}
