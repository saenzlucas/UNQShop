package Pagos;

public class BilleteraVirtual extends Pago {
	
	private BilleteraVirtualAPI api;

	public void processPayment() {
		api.checkBalance();
		api.blockBalance();;
		api.accredit();;
		api.inform();;
	}

}
