
package Pedido;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Catalogo.Item;
import Envio.Envio;
import Notificaciones.Email;
import Notificaciones.Factura;
import Notificaciones.Fidelizacion;
import Notificaciones.Notificacion;
import Pagos.Pago;

public class Pedido {
	private Estado state;
	private Estado oldState;
	private Envio shipment;
	private Pago payment;
	private Map<Item, Integer> items;
	private List<Notificacion> notifications;
	
	public Pedido(Pago payment, Envio shipment) {
		this.payment = payment;
		this.shipment = shipment;
		this.items = new HashMap<>();
		this.state = new Borrador (this);
		this.notifications = List.of(new Email(), new Factura(), new Fidelizacion());
	}
	
	public Map<Item, Integer> getItems() {
		return items;
	}

	public Pago getPayment() {
		return payment;
	}
	
	public Envio getShipment() {
		return shipment;
	}

	public double getTotalPrice () {
		return items.entrySet().stream().mapToDouble(entry -> entry.getKey().getFinalPrice() * entry.getValue()).sum();
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
		oldState = state;
		state = state.newState();
		notifications.forEach(notification -> notification.shoutout(this, oldState, state)); // Ver si se puede mejorar
		
	}
	
	public void cancel () {
		oldState = state;
		state = state.cancelled();
		notifications.forEach(notification -> notification.shoutout(this, oldState, state)); // Ver si se puede mejorar
	}
}
