package Envio;

import Misc.Direccion;

public class Estandar implements Envio {
	
	private Direccion address;
	private float weight;
	
	public Estandar(Direccion address, float weight) {
		this.address = address;
		this.weight = weight;
	}

	@Override
	public float calculateCost () {
		return 0;
		//return CorreoArgentina.estimarEnvio(weight, address);
	}
}
