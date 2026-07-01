package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Catalogo.Item;
import Catalogo.Paquete;
import Catalogo.Producto;

class CatalogoTest {

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
		catalog.put(silla, 4);
		catalog.put(soldadora, 1 );
		catalog.put(bajon, 3);
	}

	@Test
	void exposedAttributes() {
		// Se puede obtener el nombre //
		assertEquals ("Samsung A36", celular.getName());
		
		// Se puede obtener la descripcion //
		assertEquals ("5G 6/128GB", celular.getDescription());
		
		// Se puede obtener el precio //
		assertEquals (100000, celular.getPrice());
		
		// Se puede obtener el porcentaje de descuento //
		assertEquals (0.25, celular.getDiscount());
	}
	
	@Test
	void dinamicsAttributes() {
		// Producto puede tener atributos dinamicos //
		celular.addAttribute("compañia", "Movistar");
		assertEquals ("Movistar", celular.getAttribute("compañia"));
		
		// Paquete puede tener atributos dinamicos //
		bajon.addAttribute("kcal", 800);
		assertEquals (800, bajon.getAttribute("kcal"));
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
		soldadora = new Producto(null, "TIG", 150000, 0.10, 4500, 0, "Herramienta");
		assertFalse (soldadora.validateProduct());
		
		// Paquete es invalido //
		bajon = new Paquete(null, "Alfajor + Snack + Bebida", 7000, 0.15, 635, food);
		assertFalse (bajon.validateProduct());
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
		assertEquals (75000, celular.getFinalPrice());
		
		// Se calcula el precio final con descuento aplicado de Paquete //
		assertEquals (5801.25, bajon.getFinalPrice());
	}
}
