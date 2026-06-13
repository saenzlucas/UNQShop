package Pagos;

public abstract class Pago {
	
	private double fondos;
	
	public Pago(double fondos) {
		this.fondos = fondos;
	}
	
	public void acreditar (double cantidad) {
		fondos = fondos + cantidad;
	}
	
	public void debitar (double cantidad) {
		fondos = fondos - cantidad;
	}

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
