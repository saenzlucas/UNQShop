package Pagos;

public class BilleteraVirtual extends Pago {
	
	private BilleteraVirtualAPI api;

	public BilleteraVirtual(double funds) {
		super(funds);
	}

	public void processPayment() {
		api.checkBalance();
		api.blockBalance();;
		api.accredit();;
		api.inform();;
	}

}
