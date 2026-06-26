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
		order.getPayment().accredit(order.getTotalPrice());
		//order.getPayment().accredit(costoEnvio); Reembolso del envio
		order.getItems().forEach((item, cantidad) -> item.increaseStock(cantidad));
		return new Cancelado (order);
	}
}
