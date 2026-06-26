package Misc;

public class ComprobanteTransferencia {
	
	private double cbu;
	private double operationCode;
	
	public ComprobanteTransferencia(double cbu, double operationCode) {
		this.cbu = cbu;
		this.operationCode = operationCode;
	}

	public void register () {
		System.out.println ("Comprobante de transferencia registrado exitosamente");
	}

}
