package Envio;

import Misc.CorreoArgentina;
import Misc.Direccion;

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
	public float calculateCost () {
		return shipping.estimarEnvio(weight, address);
	}
}
