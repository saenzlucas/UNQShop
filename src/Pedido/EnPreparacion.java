package Pedido;

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
		return new Cancelado (order);
	}
}
