package Pagos;

public interface BilleteraVirtualAPI {

	boolean verificarSaldo ();
	
	void bloquearSaldo ();
	 
	void acreditar ();
	
	void notificar ();
	
}
