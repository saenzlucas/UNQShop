package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Envio.Envio;
import Envio.Estandar;
import Envio.Express;
import Envio.Presencial;
import Misc.CorreoArgentina;
import Misc.Direccion;
import Misc.EnvioExpress;

class EnvioTest {

	private Envio estandar;
	private Envio express;
	private Envio presencial;

	private Direccion address;

	@BeforeEach
	void setUp() {
		address = new Direccion(1875, 6100, "Av Mitre", "Wilde, Avellaneda");
	}

	@Test
	void estandar() {
		// Mock //
		CorreoArgentina correoMock = mock(CorreoArgentina.class);
		when(correoMock.estimarEnvio(4000, address)).thenReturn(7500.0f);

		// Test //
		estandar = new Estandar(address, 4000, correoMock);
		assertEquals(estandar.calculateCost(), 7500.0f);
	}

	@Test
	void express() {
		// Mock //
		EnvioExpress correoMock = mock(EnvioExpress.class);
		when(correoMock.calcularCosto(75000)).thenReturn(5000.0f);

		// Test //
		express = new Express(75000, correoMock);
		assertEquals(express.calculateCost(), 5000.0f);
	}

	@Test
	void presencial() {
		presencial = new Presencial();
		assertEquals(presencial.calculateCost(), 0);
	}
	
}
