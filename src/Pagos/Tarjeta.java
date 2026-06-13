package Pagos;

import java.util.Date;

public class Tarjeta extends Pago {
	
	private String emisor;
	private double numero;
	private int cvv;
	private Date vencimiento;
	
	public Tarjeta(double fondos, String emisor, double numero, int cvv, Date vencimiento) {
		super(fondos);
		this.emisor = emisor;
		this.numero = numero;
		this.cvv = cvv;
		this.vencimiento = vencimiento;
	}

	public void validarDatos() {

	}

	public void reservarFondos() {

	}

	public void ejecutarTransferencia() {

	}

	public void notificarResultado() {

	}

}
