package Pedido;

import Misc.NotaDeCredito;

public class EnPreparacion extends Estado {

	public EnPreparacion (Pedido order) {
		super(order);
	}	
	
	@Override
	public Estado newState () {
		return new Enviado (order);
	}
	
	@Override
	public Estado cancelled () {
		NotaDeCredito creditNote = new NotaDeCredito ("Lucas Saenz (46282416)", order.getTotalPrice(), order.getShipment().calculateCost());
		creditNote.register();
		order.getItems().forEach((item, cantidad) -> item.increaseStock(cantidad));
		return new Cancelado (order);
	}
}
