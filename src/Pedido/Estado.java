package Pedido;

import Catalogo.Item;
import Exceptions.InvalidStateActionException;

public abstract class Estado {
	
	private Pedido order;

	public Estado (Pedido order) {
		this.order = order;
	}
	
	public Pedido getOrder() {
		return order;
	}

	public void addItem (Item item) {
		throw new InvalidStateActionException();
	}

	public void removeItem (Item item) {
		throw new InvalidStateActionException();
	}
	
	public Estado newState () {
		throw new InvalidStateActionException();
	}
	
	public Estado cancelled () {
		throw new InvalidStateActionException();
	}
	
	public void shotout(Estado oldState, Estado newState) {
		getOrder().getNotifications().forEach(notification -> notification.shoutout(getOrder(), oldState, newState));
	}
}
