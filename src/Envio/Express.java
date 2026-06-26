package Envio;

public class Express implements Envio {

	private float price;
	
	public Express(float price) {
		this.price = price;
	}

	@Override
	public float calculateCost () {
		return 0;
		//return EnvioExpress.calcularCosto(price);
	}
}
