package Pedido;

import java.time.LocalDate;

import Misc.NotaDeCredito;

public class Enviado extends Estado {

	public Enviado (Pedido order) {
		super(order);
	}
	
	@Override
	public Estado newState () {
		Estado newState = new Entregado (getOrder());
		getOrder().getBranch().newSale(getOrder().getItems(), LocalDate.now());
		shotout (this, newState);
		return newState;
	}
	
	@Override
	public Estado cancelled () {
		Estado newState = new Cancelado (getOrder());
		NotaDeCredito creditNote = new NotaDeCredito ("Lucas Saenz (46282416)", getOrder().getPrice(), 0);
		creditNote.register();
		shotout (this, newState);
		return newState;
	}
}
