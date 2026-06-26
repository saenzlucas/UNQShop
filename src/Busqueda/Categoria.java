package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class Categoria implements Criterio {
	
	private String category;
	
	public Categoria(String category) {
		this.category = category;
	}

	@Override
	public List<Item> filter(List<Item> catalog) {
		/* if (item instanceof Producto) {
            Producto p = (Producto) item; // por ser java de tipado estatico, hay que avisarle que este es un producto en este caso. Si no, item no sabe que es getcategory y explota tutto
            return p.validar() && p.getcategory().equalsIgnoreCase(this.category); // "equalsIgnoreCase" sirve para no tener en cuenta mayusculas y minusculas.
        }
        return false; // si es un paquete, como los paquetes no tienen category, debe dar false. */
		
		//[Tiene que devolver la lista, no un booleano]
		return new ArrayList <> ();
	}
	
}
