
package Pedido;

import java.util.List;

import Catalogo.Item;
import Notificaciones.Notificacion;

public class Pedido {
	private Estado state;
	private List<Item> items;
	private List<Notificacion> notifications;
	
	public Pedido(List<Item> items) {
		this.state = new Borrador (this);
		this.items = items;
	}
	
	public List<Item> getItems() {
		return items;
	}

	// Pensar si existe alguna forma mas optima de implementar esto (como agregar items) 
	public void addItem (Item item) {
		state.addItem(item);
	}
	
	// Pensar si existe alguna forma mas optima de implementar esto (como remover items) 
	public void removeItem (Item item) {
		state.removeItem(item);
	}
	
	public void addNotification (Notificacion notification) {
		notifications.add(notification);
	}
	
	public void removeNotification (Notificacion notification) {
		notifications.remove(notification);
	}
	
	public void updateState () {
		state = state.newState();
	}
	
	public void cancelOrder () {
		state = state.cancelled();
	}
}
