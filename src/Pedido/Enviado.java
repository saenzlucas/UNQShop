package Pedido;

public class Enviado extends Estado {

	public Enviado(Pedido pedido) {
		super(pedido);
	}
	
	@Override
	public Estado nuevoEstado () {
		return new Entregado (pedido);
	}
	
	@Override
	public Estado cancelado () {
		return new Cancelado (pedido);
	}
}
