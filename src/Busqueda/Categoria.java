package Busqueda;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;

public class Categoria extends Simple {
	
	private String categoria;
	
	public Categoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public List<Item> filtrar(List<Item> catalogo) {
		return new ArrayList<>();
	}
	
}
