package Pagos;

public interface TransferenciaAPI {
	
	boolean verifyIdentifier ();
	
	void transfer ();
	
	void generateReceipt ();

}
