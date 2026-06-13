package Pedido;

public abstract class Estado {
	private Pedido pedido;

	public Estado(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public abstract void addItem();
	
	public abstract void removeItem();
	
	public abstract Estado nuevoEstado();
}
