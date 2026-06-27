
package Pedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Catalogo.Item;
import Envio.Envio;
import Misc.Sucursal;
import Notificaciones.Notificacion;
import Pagos.Pago;

public class Pedido {
	private Estado state;
	private Estado oldState;
	private Envio shipment;
	private Sucursal branch;
	private Pago payment;
	private Map<Item, Integer> items;
	private List<Notificacion> notifications;

	public Pedido(Pago payment, Envio shipment, Sucursal branch) {
		this.payment = payment;
		this.shipment = shipment;
		this.branch = branch;
		this.items = new HashMap<>();
		this.state = new Borrador(this);
		this.notifications = new ArrayList<>();
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

	public Sucursal getBranch() {
		return branch;
	}

	public Estado getState() {
		return state;
	}

	public List<Notificacion> getNotifications() {
		return notifications;
	}

	public double getTotalPrice() {
		return items.entrySet().stream().mapToDouble(entry -> entry.getKey().getFinalPrice() * entry.getValue()).sum();
	}

	public void addItem(Item item) {
		state.addItem(item);
	}

	public void removeItem(Item item) {
		state.removeItem(item);
	}

	public void addNotification(Notificacion notification) {
		notifications.add(notification);
	}

	public void removeNotification(Notificacion notification) {
		notifications.remove(notification);
	}

	public void updateState() {
		oldState = state;
		state = state.newState();
		shotout();
	}

	public void cancel() {
		oldState = state;
		state = state.cancelled();
		shotout();
	}

	public void shotout() {
		notifications.forEach(notification -> notification.shoutout(this, oldState, state));
	}
}
