package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Catalogo.Item;
import Catalogo.Paquete;
import Catalogo.Producto;
import Envio.Envio;
import Envio.Estandar;
import Envio.Express;
import Envio.Presencial;
import Misc.CorreoArgentina;
import Misc.Direccion;
import Misc.EnvioExpress;
import Misc.Sucursal;
import Pagos.BilleteraVirtual;
import Pagos.BilleteraVirtualAPI;
import Pagos.Pago;
import Pedido.Pedido;

class EnvioTest {

	private Envio estandar;
	private Envio express;
	private Envio presencial;

	private Direccion address;
	
	private Pago payment;
	private Sucursal branch;
	
	private Pedido order;

	private Producto alfajor;
	private Producto snack;
	private Producto gaseosa;

	private Producto celular;
	private Producto silla;
	private Producto soldadora;

	private Paquete bajon;

	private List<Item> food;
	private Map<Item, Integer> catalog;

	@BeforeEach
	void setUp() {
		address = new Direccion(1875, 6100, "Av Mitre", "Wilde, Avellaneda");
		
		alfajor = new Producto("Havanna", "Playa Grande", 2000, 0, 90, 1, "Alimento"); 
		snack = new Producto("Doritos", "Sabor Queso", 3500, 0.05, 45, 2, "Alimento"); 
		gaseosa = new Producto("Coca-Cola", "Original", 1500, 0, 500, 3, "Alimento"); 

		celular = new Producto("Samsung A36", "5G 6/128GB", 100000, 0.25, 195, 4, "Electronica"); 
		silla = new Producto("Silla de Pino", "Estilo Nordico", 25000, 0, 4000, 5, "Hogar"); 
		soldadora = new Producto("Soldadora", "TIG", 150000, 0.10, 4500, 6, "Herramienta"); 

		food = new ArrayList<>();
		food.add(alfajor);
		food.add(snack);
		food.add(gaseosa);

		bajon = new Paquete("Combo Bajon", "Alfajor + Snack + Bebida", 7000, 0.15, 635, food);

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
		
		branch = new Sucursal(catalog);
	}

	@Test
	void estandar() {
		// Mock //
		CorreoArgentina correoMock = mock(CorreoArgentina.class);
		when(correoMock.estimarEnvio(4000, address)).thenReturn(7500.0f);

		// Test //
		estandar = new Estandar(address, 4000, correoMock);
		assertEquals(7500.0f, estandar.calculateCost());
		
		// Tiempo de envio //
		order = new Pedido(payment, presencial, branch);
		estandar.shippingTime(order);
	}

	@Test
	void express() {
		// Mock //
		EnvioExpress correoMock = mock(EnvioExpress.class);
		when(correoMock.calcularCosto(75000)).thenReturn(5000.0f);

		// Test //
		express = new Express(75000, correoMock);
		assertEquals(5000.0f, express.calculateCost());

		// Tiempo de envio //
		order = new Pedido(payment, presencial, branch);
		express.shippingTime(order);
	}

	@Test
	void presencial() {
		presencial = new Presencial();
		assertEquals(0, presencial.calculateCost());
		
		// Tiempo de envio con stock en la sucursal //
		order = new Pedido(payment, presencial, branch);
		order.addItem(soldadora);
		presencial.shippingTime(order);
		
		// Tiempo de envio sin stock en la sucursal //
		branch.reduceStock(soldadora, 1);
		presencial.shippingTime(order);
	}
	
}
