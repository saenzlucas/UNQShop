package Pedido;

public class EnPreparacion extends Estado {

	public EnPreparacion(Pedido pedido) {
		super(pedido);
	}	
	
	@Override
	public Estado nuevoEstado () {
		return new Enviado (pedido);
	}
	
	@Override
	public Estado cancelado () {
		return new Cancelado (pedido);
	}
}
