package Pagos;

public class Transferencia extends Pago {
	
	private TransferenciaAPI api;
	private String alias;
	private double cbu;
	
	public Transferencia(double funds, String alias, double cbu) {
		super(funds);
		this.alias = alias;
		this.cbu = cbu;
	}

	public void processPayment() {
		api.verifyIdentifier();
		api.transfer();;
		api.generateReceipt();;
	}

}
