package Pedido;

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
		order.getPayment().accredit(order.getTotalPrice());
		return new Cancelado (order);
	}
}
