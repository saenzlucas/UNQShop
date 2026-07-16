package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

	@BeforeEach
	void setUp() {
		alfajor = new Producto(1, "Havanna", "Playa Grande", "Alimento", 0, 2000, 90);
		snack = new Producto(2, "Doritos", "Sabor Queso", "Alimento", 0.05, 3500, 45); 
		gaseosa = new Producto(3, "Coca-Cola", "Original", "Alimento", 0, 1500, 500); 

		celular = new Producto(4, "Samsung A36", "5G 6/128GB", "Electronica", 0.25, 100000, 195); 
		silla = new Producto(5, "Silla de Pino", "Estilo Nordico", "Hogar", 0, 25000, 4000); 
		soldadora = new Producto(6, "Soldadora", "TIG", "Herramienta", 0.10, 150000, 4500); 
		
		bajon = new Paquete(7, "Combo Bajon", "Alfajor + Snack + Bebida", "Alimento", 0.15, List.of(alfajor, snack, gaseosa));
	}

	@Test
	void exposedAttributes() {
		// Se puede obtener el SKU //
		assertEquals (4, celular.getSku());
		
		// Se puede obtener el nombre //
		assertEquals ("Samsung A36", celular.getName());
		
		// Se puede obtener la descripcion //
		assertEquals ("5G 6/128GB", celular.getDescription());
		
		// Se puede obtener la categoria // 
		assertEquals ("Electronica", celular.getCategory());
		
		// Se puede obtener el porcentaje de descuento //
		assertEquals (0.25, celular.getDiscount());
		
		// Se puede obtener el precio //
		assertEquals (100000, celular.getPrice());
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
		assertTrue (soldadora.validateItem());
		
		// Paquete es valido //
		assertTrue (bajon.validateItem());
	}
	
	@Test
	void productIsInvalid () {
		// Producto es invalido //
		soldadora = new Producto(1, null, "TIG", "Herramienta", 0.10, 150000, 4500);
		assertFalse (soldadora.validateItem());
		
		// Paquete es invalido //
		bajon = new Paquete(2, null, "Alfajor + Snack + Bebida", "Alimento", 0.15, List.of(alfajor, snack, gaseosa));
		assertFalse (bajon.validateItem());
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
	
	@Test
	void weight () {
		// Se puede obtener el peso de un producto //
		assertEquals (4000, silla.getWeight());
					
		// Se puede obtener el peso de un paquete //
		assertEquals (635, bajon.getWeight());
	}
}
