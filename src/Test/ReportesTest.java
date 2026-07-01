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
		// Productos //
		alfajor = new Producto ("Havanna", "Playa Grande", 2000, 0, 90, 1, "Alimento");
		snack = new Producto ("Doritos", "Sabor Queso", 3500, 0.05, 45, 2, "Alimento"); 
		gaseosa = new Producto ("Coca-Cola", "Original", 1500, 0, 500, 3, "Alimento"); 
		salchicha = new Producto ("Salchichas Swift", "12u", 5000, 0, 4500, 4, "Alimento"); 
		celular = new Producto ("Samsung A36", "5G 6/128GB", 100000, 0.25, 195, 5, "Electronica");
		pendrive = new Producto ("Pendrive Kingston", "32GB", 150000, 0.15, 15000, 6, "Electronica"); 
		silla = new Producto ("Silla de Pino", "Estilo Nordico", 25000, 0, 4000, 7, "Hogar"); 
		soldadora = new Producto ("Soldadora", "TIG", 150000, 0.10, 4500, 8, "Herramienta"); 
			
		// Paquetes //
		bajon = new Paquete ("Combo Bajon", "Alfajor + Snack + Bebida", 7000, 0.15, 635, List.of(alfajor, snack, gaseosa));			
		vocero = new Paquete ("Combo Vocero", "Salchichas + Pendrive", 20000, 0.50, 500, List.of(salchicha, pendrive));	
		
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
		orderOne = new Pedido (new Tarjeta ("Banco Provincia", 43682475, 566, LocalDate.of(2030, 9, 5), apiCard), new Presencial (), unqshop);
		
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
		orderTwo = new Pedido (new Transferencia ("auto.rueda.motor", 346723464, apiTransfer), new Presencial (), unqshop);
		
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
		orderThree = new Pedido (new BilleteraVirtual(apiWallet), new Presencial (), unqshop);
		
		orderThree.addItem(pendrive);
		orderThree.addItem(gaseosa);
		orderThree.addItem(gaseosa);
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
