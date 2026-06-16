package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class Precio extends Simple {

	private double precioMax;
	
	public Precio(double precioMax) {
		this.precioMax = precioMax;
	}

	@Override
	public List<Item> filtrar(List<Item> catalogo) {
		return new ArrayList<>();
	}
	
}
