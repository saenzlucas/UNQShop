package Pagos;

public interface BilleteraVirtualAPI { // Testear con mockito

	boolean checkBalance ();
	
	void blockBalance ();
	 
	void accredit ();
	
	void inform (); 
	
}
