package Pedido;

import java.util.List;

import Catalogo.Item;

public class Pedido {
	private Estado estado;
	private List<Item> items;
	
	public Pedido(List<Item> items) {
		this.estado = new Borrador (this);
		this.items = items;
	}
	
	public void addItem () {
		
	}
	
	public void removeItem () {
		
	}
	
	public void actualizarEstado () {
		estado = estado.nuevoEstado();
	}
	
	public void cancelarPedido () {
		estado = new Cancelado (this); // Pensar una forma mejor
	}
}
