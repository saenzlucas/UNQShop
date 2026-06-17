package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class Categoria implements Criterio {
	
	private String categoria;
	
	public Categoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public List<Item> filtrar(List<Item> catalogo) {
		/* if (item instanceof Producto) {
            Producto p = (Producto) item; // por ser java de tipado estatico, hay que avisarle que este es un producto en este caso. Si no, item no sabe que es getCategoria y explota tutto
            return p.validar() && p.getCategoria().equalsIgnoreCase(this.categoria); // "equalsIgnoreCase" sirve para no tener en cuenta mayusculas y minusculas.
        }
        return false; // si es un paquete, como los paquetes no tienen categoria, debe dar false. */
		
		//[Tiene que devolver la lista, no un booleano]
		return new ArrayList <> ();
	}
	
}
