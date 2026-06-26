package Pedido;

import Catalogo.Item;

public class Borrador extends Estado {

	public Borrador (Pedido order) {
		super(order);
	}
	
	// Pensar si existe alguna forma mas optima de implementar esto (como agregar items) 
	@Override
	public void addItem(Item item) {
		order.getItems().add(item);
	}

	// Pensar si existe alguna forma mas optima de implementar esto (como remover items) 
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
