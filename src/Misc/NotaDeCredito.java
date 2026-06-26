package Misc;

public class NotaDeCredito {

	private String client;
	private double productsRefunds;
	private double shipmentRefunds;
	
	public NotaDeCredito(String client, double productsRefunds, double shipmentRefunds) {
		this.client = client;
		this.productsRefunds = productsRefunds;
		this.shipmentRefunds = shipmentRefunds;
	}

	public void register () {
		System.out.println ("Nota de credito registrada exitosamente");
	}
	
}
