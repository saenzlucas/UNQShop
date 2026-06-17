package Envio;

public class Express extends Envio {

	private float precio;
	
	public Express(float precio) {
		this.precio = precio;
	}

	@Override
	public float calcularCosto () {
		return EnvioExpress.calcularCosto(precio);
	}
}
