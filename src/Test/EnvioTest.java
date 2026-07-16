package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
		
		branch = new Sucursal(catalog);
		
		order = new Pedido(payment, presencial, address, branch);
	}

	@Test
	void estandar() {
		// Mock //
		CorreoArgentina correoMock = mock(CorreoArgentina.class);
		when(correoMock.estimarEnvio(4000, address)).thenReturn(7500.0f);
		
		estandar = new Estandar (correoMock);
		when(estandar.calculateCost(order)).thenReturn(7500.0f);

		// Test //
		assertEquals(7500.0f, estandar.calculateCost(order));
		
		// Tiempo de envio //
		estandar.shippingTime(order);
	}

	@Test
	void express() {
		// Mock //
		EnvioExpress correoMock = mock(EnvioExpress.class);
		when(correoMock.calcularCosto(75000)).thenReturn(5000.0f);
		
		express = new Express(correoMock);
		when(express.calculateCost(order)).thenReturn(5000.0f);
		
		// Test //
		assertEquals(5000.0f, express.calculateCost(order));

		// Tiempo de envio //
		express.shippingTime(order);
	}

	@Test
	void presencial() {
		presencial = new Presencial();
		assertEquals(0, presencial.calculateCost(order));
		
		// Tiempo de envio con stock en la sucursal //
		order.addItem(soldadora);
		presencial.shippingTime(order);
		
		// Tiempo de envio sin stock en la sucursal //
		branch.reduceStock(soldadora, 1);
		presencial.shippingTime(order);
	}	
}
