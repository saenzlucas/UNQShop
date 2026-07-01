package Envio;

import Misc.CorreoArgentina;
import Misc.Direccion;
import Pedido.Pedido;

public class Estandar implements Envio {
	
	private Direccion address;
	private float weight;
	private CorreoArgentina shipping;
	
	public Estandar(Direccion address, float weight, CorreoArgentina shipping) {
		this.address = address;
		this.weight = weight;
		this.shipping = shipping;
	}
	
	@Override
	public void shippingTime (Pedido order) {
		System.out.println ("La entrega se garantiza entre 5 y 7 dias habiles");
	}

	@Override
	public float calculateCost () {
		return shipping.estimarEnvio(weight, address);
	}
}
