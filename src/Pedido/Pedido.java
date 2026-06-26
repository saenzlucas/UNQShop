
package Pedido;

import java.util.List;

import Catalogo.Item;
import Notificaciones.Email;
import Notificaciones.Factura;
import Notificaciones.Fidelizacion;
import Notificaciones.Notificacion;
import Pagos.Pago;

public class Pedido {
	private Estado state;
	private Pago payment;
	private List<Item> items;
	private List<Notificacion> notifications;
	
	public Pedido(List<Item> items, Pago payment) {
		this.items = items;
		this.payment = payment;
		this.state = new Borrador (this);
		this.notifications = List.of(new Email(), new Factura(), new Fidelizacion());
	}
	
	public List<Item> getItems() { // Capaz no hace falta esto
		return items;
	}

	public Pago getPayment() {
		return payment;
	}
	
	public double getTotalPrice () {
		return this.items.stream().mapToDouble(Item::getFinalPrice).sum();
	}

	public void addItem (Item item) {
		state.addItem(item);
	}

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
		notifications.forEach(notification -> notification.shoutout(this, state, state.newState())); // Ver si se puede mejorar
		state = state.newState();
	}
	
	public void cancel () {
		notifications.forEach(notification -> notification.shoutout(this, state, state.cancelled())); // Ver si se puede mejorar
		state = state.cancelled();
	}
}
