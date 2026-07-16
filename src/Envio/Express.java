package Envio;

import Misc.EnvioExpress;
import Pedido.Pedido;

public class Express implements Envio {

	private EnvioExpress shipping;
	
	public Express(EnvioExpress shipping) {
		this.shipping = shipping;
	}
	
	@Override
	public void shippingTime (Pedido order) {
		System.out.println ("La entrega se garantiza en 1 dia habil");
	}

	@Override
	public float calculateCost (Pedido order) {
		return shipping.calcularCosto((float) order.getPrice());
	}
}
