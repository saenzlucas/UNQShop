package Pagos;

public interface BilleteraVirtualAPI {

	boolean checkBalance ();
	
	void blockBalance ();
	 
	void accredit ();
	
	void inform ();
	
}
