package Pagos;

import java.time.LocalDate;

public class Tarjeta extends Pago {
	
	private TarjetaAPI api;
	private String issuer;
	private double number;
	private int cvv;
	private LocalDate expiration;
	
	public Tarjeta(String issuer, double number, int cvv, LocalDate expiration) {
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
