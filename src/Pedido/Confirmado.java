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
		order.getItems().forEach((item, cantidad) -> order.getBranch().increaseStock(item, cantidad));
		return new Cancelado (order);
	}
}
