package Envio;

import Misc.EnvioExpress;
import Pedido.Pedido;

public class Express implements Envio {

	private float price;
	private EnvioExpress shipping;
	
	public Express(float price, EnvioExpress shipping) {
		this.price = price;
		this.shipping = shipping;
	}
	
	@Override
	public void shippingTime (Pedido order) {
		System.out.println ("La entrega se garantiza en 1 dia habil");
	}

	@Override
	public float calculateCost () {
		return shipping.calcularCosto(price);
	}
}
