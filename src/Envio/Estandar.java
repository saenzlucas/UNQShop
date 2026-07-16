package Envio;

import Misc.CorreoArgentina;
import Pedido.Pedido;

public class Estandar implements Envio {
	
	private CorreoArgentina shipping;
	
	public Estandar(CorreoArgentina shipping) {
		this.shipping = shipping;
	}
	
	@Override
	public void shippingTime (Pedido order) {
		System.out.println ("La entrega se garantiza entre 5 y 7 dias habiles");
	}

	@Override
	public float calculateCost (Pedido order) {
		return shipping.estimarEnvio((float) order.getWeight(), order.getAddress());
	}
}
