package Envio;

import Misc.EnvioExpress;

public class Express implements Envio {

	private float price;
	private EnvioExpress shipping;
	
	public Express(float price, EnvioExpress shipping) {
		this.price = price;
		this.shipping = shipping;
	}

	@Override
	public float calculateCost () {
		return shipping.calcularCosto(price);
	}
}
