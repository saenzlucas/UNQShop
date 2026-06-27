package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
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

	private List<Item> food;
	private List<Item> catalog;

	@BeforeEach
	void setUp() {
		alfajor = new Producto("Havanna", "Playa Grande", 2000, 0, 5, 90, 1, "Alimento");
		snack = new Producto("Doritos", "Sabor Queso", 3500, 0.05, 10, 45, 2, "Alimento");
		gaseosa = new Producto("Coca-Cola", "Original", 1500, 0, 3, 500, 3, "Alimento");

		celular = new Producto("Samsung A36", "5G 6/128GB", 100000, 0.25, 2, 195, 4, "Electronica");
		silla = new Producto("Silla de Pino", "Estilo Nordico", 25000, 0, 0, 4000, 5, "Hogar");
		soldadora = new Producto("Soldadora", "TIG", 150000, 0.10, 1, 4500, 6, "Herramienta");

		food = new ArrayList<>();
		food.add(alfajor);
		food.add(snack);
		food.add(gaseosa);

		bajon = new Paquete("Combo Bajon", "Alfajor + Snack + Bebida", 7000, 0.15, 635, food);

		catalog = new ArrayList<>();
		catalog.add(celular);
		catalog.add(silla);
		catalog.add(soldadora);
		catalog.add(alfajor);
		catalog.add(snack);
		catalog.add(gaseosa);
		catalog.add(bajon);

		BilleteraVirtualAPI api = mock(BilleteraVirtualAPI.class);
		payment = new BilleteraVirtual(api);
		
		shipment = new Presencial();
		branch = new Sucursal(catalog);

		email = new Email();

		order = new Pedido(payment, shipment, branch);
	}

	@Test
	void exposedAttributes() {
		// Se puede obtener el metodo de pago //
		assertEquals(order.getPayment(), payment);

		// Se puede obtener el metodo de envio //
		assertEquals(order.getShipment(), shipment);

		// Se puede obtener la sucursal //
		assertEquals(order.getBranch(), branch);
	}

	@Test
	void items() {
		// Se pueden agregar items al pedido //
		order.addItem(silla);
		order.addItem(bajon);
		assertEquals(order.getItems(), Map.of(silla, 1, bajon, 1));

		// Se pueden remover items del pedido //
		order.addItem(bajon);
		order.removeItem(silla);
		assertEquals(order.getItems(), Map.of(bajon, 2));
	}

	@Test
	void price() {
		// Se pueden calcular el precio total del pedido //
		order.addItem(celular);
		order.addItem(silla);
		assertEquals(order.getTotalPrice(), 100000);
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
