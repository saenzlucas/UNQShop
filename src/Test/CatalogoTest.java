package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Catalogo.Item;
import Catalogo.Paquete;
import Catalogo.Producto;
import Exceptions.StockUnchangeableException;

class CatalogoTest {

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
		silla = new Producto("Silla de Pino", "Estilo Nordico", 25000, 0, 4, 4000, 5, "Hogar");
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
		catalog.add(bajon);
	}

	@Test
	void exposedAttributes() {
		// Se puede obtener el nombre //
		assertEquals (celular.getName(), "Samsung A36");
		
		// Se puede obtener la descripcion //
		assertEquals (celular.getDescription(), "5G 6/128GB");
		
		// Se puede obtener el precio //
		assertEquals (celular.getPrice(), 100000);
		
		// Se puede obtener el porcentaje de descuento //
		assertEquals (celular.getDiscount(), 0.25);
	}
	
	@Test
	void dinamicsAttributes() {
		// Producto puede tener atributos dinamicos //
		celular.addAttribute("compañia", "Movistar");
		assertEquals (celular.getAttribute("compañia"), "Movistar");
		
		// Paquete puede tener atributos dinamicos //
		bajon.addAttribute("kcal", 800);
		assertEquals (bajon.getAttribute("kcal"), 800);
	}
	
	@Test
	void productIsValid () {
		// Producto es valido //
		assertTrue (soldadora.validateProduct());
		
		// Paquete es valido //
		assertTrue (bajon.validateProduct());
	}
	
	@Test
	void productIsInvalid () {
		// Producto es invalido //
		soldadora = new Producto(null, "TIG", 150000, 0.10, 1, 4500, 0, "Herramienta");
		assertFalse (soldadora.validateProduct());
		
		// Paquete es invalido //
		bajon = new Paquete(null, "Alfajor + Snack + Bebida", 7000, 0.15, 635, food);
		assertFalse (bajon.validateProduct());
	}
	
	@Test
	void stock () {
		// Hay stock de Producto //
		snack.reduceStock(1);
		assertEquals (snack.getStock(), 9);
		assertTrue (snack.inStock());
		snack.increaseStock(1);
		assertEquals (snack.getStock(), 10);
		
		// Hay stock de Paquete //
		bajon.reduceStock(2);
		assertEquals (bajon.getStock(), 1);
		assertTrue (bajon.inStock());
		bajon.increaseStock(2);
		assertEquals (bajon.getStock(), 3);
		
		// No hay stock de Producto //
		gaseosa.reduceStock(3);
		assertFalse (gaseosa.inStock());
		
		// No hay stock de Paquete //
		bajon.reduceStock(3);
		assertFalse (bajon.inStock());
		
		// Paquete no puede setear stock //
		assertThrows (StockUnchangeableException.class, () -> {
	        bajon.setStock(1);
	    });
	}
	
	@Test
	void isCategory () {
		// La categoria del Producto coincide //
		assertTrue (celular.isCategory("Electronica"));
		
		// La categoria del Paquete coincide //
		assertTrue (bajon.isCategory("Alimento"));
	}
	
	@Test
	void notIsCategory () {
		// La categoria del Producto no coincide //
		assertFalse (soldadora.isCategory("Alimento"));
		
		// La categoria del Paquete no coincide //
		assertFalse (bajon.isCategory("Hogar"));
	}
	
	@Test
	void finalPrice () {
		// Se calcula el precio final con descuento aplicado de Producto //
		assertEquals (celular.getFinalPrice(), 75000);
		
		// Se calcula el precio final con descuento aplicado de Paquete //
		assertEquals (bajon.getFinalPrice(), 5801.25);
	}
}
