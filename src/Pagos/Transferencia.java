package Pagos;

public class Transferencia extends Pago {
	
	private TransferenciaAPI api;
	private String alias;
	private double cbu;
	
	public Transferencia(String alias, double cbu) {
		this.alias = alias;
		this.cbu = cbu;
	}

	public void processPayment() {
		api.verifyIdentifier();
		api.transfer();;
		api.generateReceipt(cbu);
	}

}
