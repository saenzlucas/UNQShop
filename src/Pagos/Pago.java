package Pagos;

public abstract class Pago {
	
	private double funds;
	
	public Pago(double funds) {
		this.funds = funds;
	}
	
	public void accredit (double amount) {
		funds = funds + amount;
	}
	
	public void debit (double amount) {
		funds = funds - amount;
	}

	public abstract void processPayment ();
	
}
