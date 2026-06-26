package Pedido;

public class Confirmado extends Estado {

	public Confirmado (Pedido pedido) {
		super(pedido);
	}
	
	@Override
	public Estado newState () {
		return new EnPreparacion (order);
	}
	
	@Override
	public Estado cancelled () {
		return new Cancelado (order);
	}
}
