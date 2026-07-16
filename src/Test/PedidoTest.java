package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Catalogo.Item;
import Catalogo.Paquete;
import Catalogo.Producto;
import Envio.Envio;
import Envio.Presencial;
import Exceptions.InvalidStateActionException;
import Exceptions.StockEmptyException;
import Misc.Direccion;
import Misc.Sucursal;
import Notificaciones.Email;
import Notificaciones.Notificacion;
import Pagos.BilleteraVirtual;
import Pagos.BilleteraVirtualAPI;
import Pagos.Pago;
import Pedido.Borrador;
import Pedido.Cancelado;
import Pedido.Confirmado;
import Pedido.EnPreparacion;
import Pedido.Entregado;
import Pedido.Enviado;
import Pedido.Estado;
import Pedido.Pedido;

class PedidoTest {

	private Pago payment;
	private Envio shipment;
	private Direccion address;
	private Sucursal branch;

	private Notificacion email;

	private Pedido order;

	private Producto alfajor;
	private Producto snack;
	private Producto gaseosa;

	private Producto celular;
	private Producto silla;
	private Producto soldadora;

	private Paquete bajon;

	private Map<Item, Integer> catalog;

	@BeforeEach
	void setUp() {		
		address = new Direccion(1875, 6100, "Av Mitre", "Wilde, Avellaneda");
		
		alfajor = new Producto(1, "Havanna", "Playa Grande", "Alimento", 0, 2000, 90);
		snack = new Producto(2, "Doritos", "Sabor Queso", "Alimento", 0.05, 3500, 45); 
		gaseosa = new Producto(3, "Coca-Cola", "Original", "Alimento", 0, 1500, 500); 

		celular = new Producto(4, "Samsung A36", "5G 6/128GB", "Electronica", 0.25, 100000, 195); 
		silla = new Producto(5, "Silla de Pino", "Estilo Nordico", "Hogar", 0, 25000, 4000); 
		soldadora = new Producto(6, "Soldadora", "TIG", "Herramienta", 0.10, 150000, 4500); 

		bajon = new Paquete(7, "Combo Bajon", "Alfajor + Snack + Bebida", "Alimento", 0.15, List.of(alfajor, snack, gaseosa));

		catalog = new HashMap<>();
		catalog.put(celular, 2);
		catalog.put(silla, 0);
		catalog.put(soldadora, 1);
		catalog.put(alfajor, 5);
		catalog.put(snack, 10);
		catalog.put(gaseosa, 3);
		catalog.put(bajon, 3);

		BilleteraVirtualAPI api = mock(BilleteraVirtualAPI.class);
		payment = new BilleteraVirtual(api);
		
		shipment = new Presencial();
		branch = new Sucursal(catalog);

		email = new Email();

		order = new Pedido(payment, shipment, address, branch);
	}

	@Test
	void exposedAttributes() {
		// Se puede obtener el metodo de pago //
		assertEquals(payment, order.getPayment());

		// Se puede obtener el metodo de envio //
		assertEquals(shipment, order.getShipment());
		
		// Se puede obtener el domicilio //
		assertEquals(address, order.getAddress());

		// Se puede obtener la sucursal //
		assertEquals(branch, order.getBranch());
	}

	@Test
	void items() {
		// Se pueden agregar items al pedido //
		order.addItem(silla);
		order.addItem(bajon);
		assertEquals(Map.of(silla, 1, bajon, 1), order.getItems());

		// Se pueden remover items del pedido //
		order.addItem(bajon);
		order.removeItem(silla);
		assertEquals(Map.of(bajon, 2), order.getItems());
	}

	@Test
	void price() {
		// Se puede calcula el precio del pedido //
		order.addItem(celular);
		order.addItem(silla);
		assertEquals(100000, order.getPrice());
	}
	
	@Test
	void weight() {
		// Se puede calcular el peso del pedido //
		order.addItem(celular);
		order.addItem(silla);
		assertEquals(4195, order.getWeight());
	}


	@Test
	void orderState() {
		// Se puede actualizar el estado del pedido //
		order.updateState();
		assertTrue(order.getState() instanceof Confirmado);

		// Se puede cancelar el pedido //
		order.cancel();
		assertTrue(order.getState() instanceof Cancelado);
	}

	@Test
	void notifications() {
		// Se puede suscribir diferentes notificaciones //
		order.addNotification(email);
		assertTrue(order.getNotifications().contains(email));

		// Se puede desuscribir a diferentes notificaciones //
		order.removeNotification(email);
		assertFalse(order.getNotifications().contains(email));
	}

	@Test
	void borrador() {
		Estado borrador = new Borrador(order);
		assertTrue(borrador.newState() instanceof Confirmado);
		assertTrue(borrador.cancelled() instanceof Cancelado);

		// No hay stock del producto //
		assertThrows(StockEmptyException.class, () -> {
			borrador.addItem(silla);
			borrador.newState();
		});
	}

	@Test
	void confirmado() {
		Estado confirmado = new Confirmado(order);

		assertTrue(confirmado.newState() instanceof EnPreparacion);
		assertTrue(confirmado.cancelled() instanceof Cancelado);

		// El estado no permite las siguientes acciones //
		assertThrows(InvalidStateActionException.class, () -> {
			confirmado.addItem(soldadora);
		});

		assertThrows(InvalidStateActionException.class, () -> {
			confirmado.removeItem(soldadora);
		});
	}

	@Test
	void enpreparacion() {
		Estado enpreparacion = new EnPreparacion(order);

		assertTrue(enpreparacion.newState() instanceof Enviado);
		assertTrue(enpreparacion.cancelled() instanceof Cancelado);

		// El estado no permite las siguientes acciones //
		assertThrows(InvalidStateActionException.class, () -> {
			enpreparacion.addItem(snack);
		});

		assertThrows(InvalidStateActionException.class, () -> {
			enpreparacion.removeItem(snack);
		});
	}

	@Test
	void enviado() {
		Estado enviado = new Enviado(order);
		assertTrue(enviado.newState() instanceof Entregado);
		assertTrue(enviado.cancelled() instanceof Cancelado);

		// El estado no permite las siguientes acciones //
		assertThrows(InvalidStateActionException.class, () -> {
			enviado.addItem(gaseosa);
		});

		assertThrows(InvalidStateActionException.class, () -> {
			enviado.removeItem(gaseosa);
		});
	}

	@Test
	void entregado() {
		Estado entregado = new Entregado(order);

		// El estado no permite las siguientes acciones //
		assertThrows(InvalidStateActionException.class, () -> {
			entregado.addItem(silla);
		});

		assertThrows(InvalidStateActionException.class, () -> {
			entregado.removeItem(silla);
		});

		assertThrows(InvalidStateActionException.class, () -> {
			entregado.newState();
		});

		assertThrows(InvalidStateActionException.class, () -> {
			entregado.cancelled();
		});
	}

	@Test
	void cancelado() {
		Estado cancelado = new Cancelado(order);

		// El estado no permite las siguientes acciones //
		assertThrows(InvalidStateActionException.class, () -> {
			cancelado.addItem(alfajor);
		});

		assertThrows(InvalidStateActionException.class, () -> {
			cancelado.removeItem(alfajor);
		});

		assertThrows(InvalidStateActionException.class, () -> {
			cancelado.newState();
		});

		assertThrows(InvalidStateActionException.class, () -> {
			cancelado.cancelled();
		});
	}
}
