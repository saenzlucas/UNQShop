package Test;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Catalogo.Item;
import Catalogo.Paquete;
import Catalogo.Producto;
import Envio.Presencial;
import Misc.Direccion;
import Misc.Sucursal;
import Pagos.BilleteraVirtual;
import Pagos.BilleteraVirtualAPI;
import Pagos.Tarjeta;
import Pagos.TarjetaAPI;
import Pagos.Transferencia;
import Pagos.TransferenciaAPI;
import Pedido.Pedido;
import Reportes.CSV;
import Reportes.HTML;
import Reportes.TXT;

class ReportesTest {
	
	private Direccion address;

	private Producto alfajor;
	private Producto snack;
	private Producto gaseosa;
	private Producto salchicha;
	private Producto celular;
	private Producto pendrive;
	private Producto silla;
	private Producto soldadora;
	
	private Paquete bajon;
	private Paquete vocero;
	
	private Map<Item, Integer> catalog;
	
	private Sucursal unqshop;
	
	private Pedido orderOne;
	private TarjetaAPI apiCard;
	private Pedido orderTwo;
	private TransferenciaAPI apiTransfer;
	private Pedido orderThree;
	private BilleteraVirtualAPI apiWallet;

	@BeforeEach
	void setUp() {
		// Direccion //
		address = new Direccion(1875, 6100, "Av Mitre", "Wilde, Avellaneda");
		
		// Productos //
		alfajor = new Producto(1, "Havanna", "Playa Grande", "Alimento", 0, 2000, 90);
		snack = new Producto(2, "Doritos", "Sabor Queso", "Alimento", 0.05, 3500, 45); 
		gaseosa = new Producto(3, "Coca-Cola", "Original", "Alimento", 0, 1500, 500); 
		salchicha = new Producto (4, "Salchichas Swift", "12u", "Alimento", 0, 5000, 450); 
		celular = new Producto(5, "Samsung A36", "5G 6/128GB", "Electronica", 0.25, 100000, 195); 
		pendrive = new Producto (6, "Pendrive Kingston", "32GB", "Electronica", 0.15, 15000, 10); 
		silla = new Producto(7, "Silla de Pino", "Estilo Nordico", "Hogar", 0, 25000, 4000); 
		soldadora = new Producto(8, "Soldadora", "TIG", "Herramienta", 0.10, 150000, 4500); 
			
		// Paquetes //
		
		bajon = new Paquete (9, "Combo Bajon", "Alfajor + Snack + Bebida", "Alimento", 0.15, List.of(alfajor, snack, gaseosa));			
		vocero = new Paquete (10, "Combo Vocero", "Salchichas + Pendrive", "Gubernamental", 0.50, List.of(salchicha, pendrive));	
		
		// Catalogo //
		catalog = new HashMap<>();
		catalog.put(alfajor, 5);
		catalog.put(snack, 10);
		catalog.put(gaseosa, 3);
		catalog.put(salchicha, 8);
		catalog.put(celular, 2);
		catalog.put(pendrive, 4);
		catalog.put(silla, 4);
		catalog.put(soldadora, 1);
		catalog.put(bajon, 3);
		catalog.put(vocero, 4);
		
		// Sucursal //
		unqshop = new Sucursal (catalog);

		// Primera compra //
		apiCard = mock(TarjetaAPI.class);
		orderOne = new Pedido (new Tarjeta ("Banco Provincia", 43682475, 566, LocalDate.of(2030, 9, 5), apiCard), new Presencial (), address, unqshop);
		
		orderOne.addItem(alfajor);
		orderOne.addItem(salchicha);
		orderOne.addItem(celular);
		orderOne.addItem(vocero);
		orderOne.addItem(snack);
		
		orderOne.updateState(); // Confirmado
		orderOne.updateState(); // En Preparacion
		orderOne.updateState(); // Enviado
		orderOne.updateState(); // Entregado [Registra nueva venta]
		
		// Segunda compra //
		apiTransfer = mock(TransferenciaAPI.class);
		orderTwo = new Pedido (new Transferencia ("auto.rueda.motor", 346723464, apiTransfer), new Presencial (), address, unqshop);
		
		orderTwo.addItem(silla);
		orderTwo.addItem(vocero);
		orderTwo.addItem(bajon);
		orderTwo.addItem(soldadora);
		
		orderTwo.updateState(); // Confirmado
		orderTwo.updateState(); // En Preparacion
		orderTwo.updateState(); // Enviado
		orderTwo.updateState(); // Entregado [Registra nueva venta]
		
		// Tercer compra //
		apiWallet = mock(BilleteraVirtualAPI.class);
		orderThree = new Pedido (new BilleteraVirtual(apiWallet), new Presencial (), address, unqshop);
		
		orderThree.addItem(pendrive);
		orderThree.addItem(gaseosa);
		orderThree.addItem(vocero);
		orderThree.addItem(salchicha);
		
		orderThree.updateState(); // Confirmado
		orderThree.updateState(); // En Preparacion
		orderThree.updateState(); // Enviado
		orderThree.updateState(); // Entregado [Registra nueva venta]
	}
	
	@Test
	void test () throws IOException {
		unqshop.newReport(new TXT(), LocalDate.of(2025, 11, 24), LocalDate.of(2026, 11, 24));
		unqshop.newReport(new HTML(), LocalDate.of(2025, 11, 24), LocalDate.of(2026, 11, 24));
		unqshop.newReport(new CSV(), LocalDate.of(2025, 11, 24), LocalDate.of(2026, 11, 24));
	}
}
