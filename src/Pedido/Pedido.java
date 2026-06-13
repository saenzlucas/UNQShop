
package Pedido;

import java.util.List;

import Catalogo.Item;

public class Pedido {
	private Estado estado;
	public List<Item> items;
	
	public Pedido(List<Item> items) {
		this.estado = new Borrador (this);
		this.items = items;
	}
	
	// Pensar si existe alguna forma mas optima de implementar esto (como agregar items) 
	public void addItem (Item item) {
		estado.addItem(item);
	}
	
	// Pensar si existe alguna forma mas optima de implementar esto (como remover items) 
	public void removeItem (Item item) {
		estado.addItem(item);
	}
	
	public void actualizarEstado () {
		estado = estado.nuevoEstado();
	}
	
	public void cancelarPedido () {
		estado = estado.cancelado();
	}
}
