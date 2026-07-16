package Pedido;

public class Confirmado extends Estado {

	public Confirmado (Pedido pedido) {
		super(pedido);
	}
	
	@Override
	public Estado newState () {
		Estado newState = new EnPreparacion (getOrder());
		shotout (this, newState);
		return newState;
	}
	
	@Override
	public Estado cancelled () {
		Estado newState = new Cancelado (getOrder());
		getOrder().getItems().forEach((item, cantidad) -> getOrder().getBranch().increaseStock(item, cantidad));
		shotout (this, newState);
		return newState;
	}
}
