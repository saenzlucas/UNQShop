package Pedido;

import Catalogo.Item;

public abstract class Estado {
	
	public Pedido pedido;

	public Estado(Pedido pedido) {
		this.pedido = pedido;
	}
	
	// Falta hacer que la excepcion sea propia del programa y no generica
	
	public void addItem(Item item) {
		throw new UnsupportedOperationException("No se puede agregar el item");
	}

	public void removeItem(Item item) {
		throw new UnsupportedOperationException("No se puede remover el item");
	}
	
	public Estado nuevoEstado() {
		throw new UnsupportedOperationException("No se puede actualizar el estado");
	}
	
	public Estado cancelado () {
		throw new UnsupportedOperationException("No se puede cancelar el pedido");
	}
}
