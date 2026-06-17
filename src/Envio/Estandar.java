package Envio;

public class Estandar extends Envio {
	
	private Direccion direccion;
	private float peso;
	
	public Estandar(Direccion direccion, float peso) {
		this.direccion = direccion;
		this.peso = peso;
	}

	@Override
	public float calcularCosto () {
		return CorreoArgentina.estimarEnvio(peso, direccion);
	}
}
