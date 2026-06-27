package Test;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Envio.Presencial;
import Misc.Sucursal;
import Notificaciones.Email;
import Notificaciones.Factura;
import Notificaciones.Fidelizacion;
import Notificaciones.Notificacion;
import Pagos.BilleteraVirtual;
import Pagos.BilleteraVirtualAPI;
import Pedido.Borrador;
import Pedido.Cancelado;
import Pedido.Confirmado;
import Pedido.Entregado;
import Pedido.Enviado;
import Pedido.Pedido;

class NotificacionesTest {
	
	private Notificacion email;
	private Notificacion factura;
	private Notificacion fidelizacion;
	
	private Pedido order;

	@BeforeEach
	void setUp() {
		email = new Email ();
		factura = new Factura ();
		fidelizacion = new Fidelizacion ();
		
		BilleteraVirtualAPI api = mock(BilleteraVirtualAPI.class);
		
		order = new Pedido (new BilleteraVirtual(api), new Presencial (), new Sucursal (new ArrayList<>()));
	}

	@Test
	void email() {
		email.shoutout(order, new Borrador (order), new Confirmado(order));
	}
	
	@Test
	void factura() {
		factura.shoutout(order, new Enviado (order), new Entregado(order));
	}
	
	@Test
	void fidelizacion() {
		fidelizacion.shoutout(order, new Borrador (order), new Cancelado(order));
	}

}
