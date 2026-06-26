package Pedido;

import Catalogo.Item;

public class Borrador extends Estado {

	public Borrador (Pedido order) {
		super(order);
	}
	
	@Override
	public void addItem(Item item) {
		order.getItems().add(item);
	}

	@Override
	public void removeItem(Item item) {
		order.getItems().remove(item);
	}
	
	@Override
	public Estado newState () {
		return new Confirmado (order);
	}
	
	@Override
	public Estado cancelled () {
		return new Cancelado (order);
	}
}
