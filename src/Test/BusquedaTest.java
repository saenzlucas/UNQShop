package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Busqueda.AND;
import Busqueda.Categoria;
import Busqueda.Criterio;
import Busqueda.Disponibilidad;
import Busqueda.NOT;
import Busqueda.Nombre;
import Busqueda.OR;
import Busqueda.Precio;
import Catalogo.Item;
import Catalogo.Paquete;
import Catalogo.Producto;
import Misc.Sucursal;

class BusquedaTest {
	
	private Producto alfajor;
	private Producto snack;
	private Producto gaseosa;

	private Producto celular;
	private Producto silla;
	private Producto soldadora;

	private Paquete bajon;
	
	private Sucursal branch;

	private Map<Item, Integer> catalog;

	@BeforeEach
	void setUp() {
		alfajor = new Producto(1, "Havanna", "Playa Grande", "Alimento", 0, 2000, 90);
		snack = new Producto(2, "Doritos", "Sabor Queso", "Alimento", 0.05, 3500, 45); 
		gaseosa = new Producto(3, "Coca-Cola", "Original", "Alimento", 0, 1500, 500); 

		celular = new Producto(4, "Samsung A36", "5G 6/128GB", "Electronica", 0.25, 100000, 195); 
		silla = new Producto(5, "Silla de Pino", "Estilo Nordico", "Hogar", 0, 25000, 4000); 
		soldadora = new Producto(6, "Soldadora", "TIG", "Herramienta", 0.10, 150000, 4500); 

		bajon = new Paquete(7, "Combo Bajon", "Alfajor + Snack + Bebida", "Alimento", 0.15, List.of(alfajor, snack, gaseosa));

		catalog = new HashMap<>();
		catalog.put(alfajor, 5);
		catalog.put(snack, 10);
		catalog.put(gaseosa, 3);
		catalog.put(celular, 2);
		catalog.put(silla, 0);
		catalog.put(soldadora, 0);
		catalog.put(bajon, 3);
		
		branch = new Sucursal (catalog);
		branch.increaseStock(soldadora, 1);
	}

	@Test
	void byName () {
		Criterio name = new Nombre ("Silla de Pino");
		assertEquals (Map.of(silla, 0), name.filter(branch.getCatalog()));
	}
	
	@Test
	void byCategory () {
		Criterio category = new Categoria ("Alimento");
		assertEquals (Map.of(alfajor, 5, snack, 10, gaseosa, 3, bajon, 3), category.filter(branch.getCatalog()));
	}
	
	@Test
	void byPrice () {
		Criterio price = new Precio (5000);
		assertEquals (Map.of(alfajor, 5, snack, 10, gaseosa, 3), price.filter(branch.getCatalog()));
	}
	
	@Test
	void byAvailability () {
		Criterio availability = new Disponibilidad ();
		assertEquals (Map.of(celular, 2, soldadora, 1, alfajor, 5, snack, 10, gaseosa, 3, bajon, 3), availability.filter(branch.getCatalog()));
	}
	
	@Test
	void or () {
		Criterio name = new Nombre ("Soldadora");
		Criterio category = new Categoria ("Alimento");
		Criterio price = new Precio (5000);
		Criterio or = new OR (List.of(name, category, price));
		assertEquals (Map.of(soldadora, 1, alfajor, 5, snack, 10, gaseosa, 3, bajon, 3), or.filter(branch.getCatalog()));
	}
	
	@Test
	void and () {
		Criterio category = new Categoria ("Alimento");
		Criterio price = new Precio (3000);
		Criterio availability = new Disponibilidad ();
		Criterio and = new AND (List.of(category, price, availability));
		assertEquals (Map.of(alfajor, 5, gaseosa, 3), and.filter(branch.getCatalog()));
	}
	
	@Test
	void not () {
		Criterio availability = new Disponibilidad ();
		Criterio not = new NOT (availability);
		assertEquals (Map.of(silla, 0), not.filter(catalog));
	}
}
