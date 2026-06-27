package Pagos;

import java.time.LocalDate;

import Misc.CuponDePago;

public class Tarjeta extends Pago {

	private TarjetaAPI api;
	private String issuer;
	private double number;
	private int cvv;
	private LocalDate expiration;

	public Tarjeta(String issuer, double number, int cvv, LocalDate expiration, TarjetaAPI api) {
		this.issuer = issuer;
		this.number = number;
		this.cvv = cvv;
		this.expiration = expiration;
		this.api = api;
	}

	@Override
	public void validateData() {
		api.verifyData();
	}

	@Override
	public void reserveFunds() {
		api.requestAuthorization();
	}

	@Override
	public void executeTransaction() {
		api.transfer();
	}

	@Override
	public void reportResult() {
		CuponDePago coupon = new CuponDePago();
		coupon.register();
	}

}
