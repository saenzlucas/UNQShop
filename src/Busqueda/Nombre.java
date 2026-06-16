package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class Nombre extends Simple {

	private String nombre;
	
	public Nombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public List<Item> filtrar(List<Item> catalogo) {
		return new ArrayList<>();
	}
	
}
