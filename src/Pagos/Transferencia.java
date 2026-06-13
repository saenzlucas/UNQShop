package Pagos;

public class Transferencia extends Pago {
	
	private String alias;
	private double cbu;
	
	public Transferencia(double fondos, String alias, double cbu) {
		super(fondos);
		this.alias = alias;
		this.cbu = cbu;
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
