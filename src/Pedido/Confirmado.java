package Pedido;

public class Confirmado extends Estado {

	public Confirmado(Pedido pedido) {
		super(pedido);
	}
	
	@Override
	public Estado nuevoEstado () {
		return new EnPreparacion (pedido);
	}
	
	@Override
	public Estado cancelado () {
		return new Cancelado (pedido);
	}
}
