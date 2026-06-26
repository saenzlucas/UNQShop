package Pagos;

import Misc.CuponDePago;

public interface TarjetaAPI { // Testear con mockito

	boolean verifyData ();
	
	void requestAuthorization ();
	
	void transfer ();
	
	default void generateCoupon () {
		CuponDePago coupon = new CuponDePago ();
		coupon.register();
	}
	
}
