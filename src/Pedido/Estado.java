package Pedido;

import Catalogo.Item;
import Exceptions.InvalidStateActionException;

public abstract class Estado {
	
	public Pedido order;

	public Estado (Pedido order) {
		this.order = order;
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
}
