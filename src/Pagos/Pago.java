package Pagos;

public abstract class Pago {
	
	public void procesarPago () {
		validarDatos ();
		reservarFondos ();
		ejecutarTransferencia ();
		notificarResultado ();
	}	
	
	public abstract void validarDatos ();	
	
	public abstract void reservarFondos ();	
	
	public abstract void ejecutarTransferencia ();	
	
	public abstract void notificarResultado ();
	
}
