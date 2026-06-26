package Pedido;

import Misc.NotaDeCredito;

public class Enviado extends Estado {

	public Enviado (Pedido order) {
		super(order);
	}
	
	@Override
	public Estado newState () {
		return new Entregado (order);
	}
	
	@Override
	public Estado cancelled () {
		NotaDeCredito creditNote = new NotaDeCredito ("Lucas Saenz (46282416)", order.getTotalPrice(), 0);
		creditNote.register();
		return new Cancelado (order);
	}
}
