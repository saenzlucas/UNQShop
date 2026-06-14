package Pagos;

public class Transferencia extends Pago {
	
	private TransferenciaAPI api;
	private String alias;
	private double cbu;
	
	public Transferencia(double fondos, String alias, double cbu) {
		super(fondos);
		this.alias = alias;
		this.cbu = cbu;
	}

	public void procesarPago() {
		api.verificarIdentificador();
		api.transferir();
		api.generarComprobante();
	}

}
