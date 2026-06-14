package Pagos;

public interface TarjetaAPI {

	boolean verificarDatos ();
	
	void pedirAutorizacion ();
	
	void transferir ();
	
	void generarCupon ();
	
}
