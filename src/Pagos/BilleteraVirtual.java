package Pagos;

public class BilleteraVirtual extends Pago {
	
	private BilleteraVirtualAPI api;

	public BilleteraVirtual(double fondos) {
		super(fondos);
	}

	public void procesarPago() {
		api.verificarSaldo();
		api.bloquearSaldo();
		api.acreditar();
		api.notificar();
	}

}
