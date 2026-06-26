package Pagos;

public interface TarjetaAPI {

	boolean verifyData ();
	
	void requestAuthorization ();
	
	void transfer ();
	
	void generateCoupon ();
	
}
