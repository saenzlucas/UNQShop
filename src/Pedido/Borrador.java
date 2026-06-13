package Pedido;

import Catalogo.Item;

public class Borrador extends Estado {

	public Borrador(Pedido pedido) {
		super(pedido);
	}
	
	// Pensar si existe alguna forma mas optima de implementar esto (como agregar items) 
	@Override
	public void addItem(Item item) {
		pedido.items.add(item);
	}

	// Pensar si existe alguna forma mas optima de implementar esto (como remover items) 
	@Override
	public void removeItem(Item item) {
		pedido.items.remove(item);
	}
	
	@Override
	public Estado nuevoEstado () {
		return new Confirmado (pedido);
	}
	
	@Override
	public Estado cancelado () {
		return new Cancelado (pedido);
	}
}
