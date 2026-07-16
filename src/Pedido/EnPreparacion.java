package Pedido;

import Misc.NotaDeCredito;

public class EnPreparacion extends Estado {

	public EnPreparacion (Pedido order) {
		super(order);
	}	
	
	@Override
	public Estado newState () {
		Estado newState = new Enviado (getOrder());
		shotout (this, newState);
		return newState;
	}
	
	@Override
	public Estado cancelled () {
		Estado newState = new Cancelado (getOrder());
		NotaDeCredito creditNote = new NotaDeCredito ("Lucas Saenz (46282416)", getOrder().getPrice(), getOrder().getShipment().calculateCost(getOrder()));
		creditNote.register();
		getOrder().getItems().forEach((item, cantidad) -> getOrder().getBranch().increaseStock(item, cantidad));
		shotout (this, newState);
		return newState;
	}
}
