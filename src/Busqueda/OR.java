package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class OR extends Complejo {
	
	public OR (List<Criterio> criterios) {
		super(criterios);
	}

	@Override
	public List<Item> filtrar (List<Item> catalogo) {
		return new ArrayList<>();
	}
}
