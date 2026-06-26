package Pagos;

import java.util.Date;

public class Tarjeta extends Pago {
	
	private TarjetaAPI api;
	private String issuer;
	private double number;
	private int cvv;
	private Date expiration;
	
	public Tarjeta(double funds, String issuer, double number, int cvv, Date expiration) {
		super(funds);
		this.issuer = issuer;
		this.number = number;
		this.cvv = cvv;
		this.expiration = expiration;
	}

	public void processPayment() {
		api.verifyData();
		api.requestAuthorization();;
		api.transfer();;
		api.generateCoupon();
	}

}
