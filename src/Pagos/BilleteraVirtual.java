package Pagos;

public class BilleteraVirtual extends Pago {

	private BilleteraVirtualAPI api;

	public BilleteraVirtual(BilleteraVirtualAPI api) {
		this.api = api;
	}

	@Override
	public void validateData() {
		api.checkBalance();
	}

	@Override
	public void reserveFunds() {
		api.blockBalance();
	}

	@Override
	public void executeTransaction() {
		api.accredit();
	}

	@Override
	public void reportResult() {
		api.inform();
	}

}
