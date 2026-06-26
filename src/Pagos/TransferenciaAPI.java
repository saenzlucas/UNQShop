package Pagos;

import Misc.ComprobanteTransferencia;

public interface TransferenciaAPI { // Testear con mockito
	
	boolean verifyIdentifier ();
	
	void transfer ();
	
	default void generateReceipt (double cbu) { 
		ComprobanteTransferencia receipt = new ComprobanteTransferencia (cbu, (Math.random() * 10000));
		receipt.register();
	}

}
