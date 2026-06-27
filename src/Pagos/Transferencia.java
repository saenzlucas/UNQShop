package Pagos;

import Misc.ComprobanteTransferencia;

public class Transferencia extends Pago {

	private TransferenciaAPI api;
	private String alias;
	private double cbu;

	public Transferencia(String alias, double cbu) {
		this.alias = alias;
		this.cbu = cbu;
	}

	@Override
	public void validateData() {
		api.verifyIdentifier();
	}

	@Override
	public void reserveFunds() {

	}

	@Override
	public void executeTransaction() {
		api.transfer();
	}

	@Override
	public void reportResult() {
		ComprobanteTransferencia receipt = new ComprobanteTransferencia(cbu, (Math.random() * 10000));
		receipt.register();
	}
}
