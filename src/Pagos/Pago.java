package Pagos;

public abstract class Pago {

	public void processPayment () {
		validateData ();
		reserveFunds ();
		executeTransaction ();
		reportResult ();
	}
	
	public abstract void validateData ();
	
	public abstract void reserveFunds ();
	
	public abstract void executeTransaction ();
	
	public abstract void reportResult ();
	
}
