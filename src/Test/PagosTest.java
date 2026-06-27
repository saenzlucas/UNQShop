package Test;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import Pagos.BilleteraVirtual;
import Pagos.BilleteraVirtualAPI;
import Pagos.Pago;
import Pagos.Tarjeta;
import Pagos.TarjetaAPI;
import Pagos.Transferencia;
import Pagos.TransferenciaAPI;

class PagosTest {
	
	private Pago billeteraVirtual;
	private Pago transferencia;
	private Pago tarjeta;

	@Test
	void billeteraVirtual() {
		// Mock //
		BilleteraVirtualAPI api = mock(BilleteraVirtualAPI.class);
		billeteraVirtual = new BilleteraVirtual (api);
		
		// Ejecucion //
		billeteraVirtual.processPayment();
	}

	@Test
	void transferencia() {
		// Mock //
		TransferenciaAPI api = mock(TransferenciaAPI.class);
		transferencia = new Transferencia ("auto.rueda.motor", 346723464, api);
		
		// Ejecucion //
		transferencia.processPayment();
	}
	
	@Test
	void tarjeta() {
		// Mock //
		TarjetaAPI api = mock(TarjetaAPI.class);
		tarjeta = new Tarjeta ("Banco Provincia", 43682475, 566, LocalDate.of(2030, 9, 5), api);
		
		// Ejecucion //
		tarjeta.processPayment();
	}
}
