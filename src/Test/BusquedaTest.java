package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
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

class BusquedaTest {
	
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
		catalog.put(alfajor, 5);
		catalog.put(snack, 10);
		catalog.put(gaseosa, 3);
		catalog.put(celular, 2);
		catalog.put(silla, 0);
		catalog.put(soldadora, 1);
		catalog.put(bajon, 3);
	}

	@Test
	void byName () {
		Criterio name = new Nombre ("Silla de Pino");
		assertEquals (Map.of(silla, 0), name.filter(catalog));
	}
	
	@Test
	void byCategory () {
		Criterio category = new Categoria ("Alimento");
		assertEquals (Map.of(alfajor, 5, snack, 10, gaseosa, 3, bajon, 3), category.filter(catalog));
	}
	
	@Test
	void byPrice () {
		Criterio price = new Precio (5000);
		assertEquals (Map.of(alfajor, 5, snack, 10, gaseosa, 3), price.filter(catalog));
	}
	
	@Test
	void byAvailability () {
		Criterio availability = new Disponibilidad ();
		assertEquals (Map.of(celular, 2, soldadora, 1, alfajor, 5, snack, 10, gaseosa, 3, bajon, 3), availability.filter(catalog));
	}
	
	@Test
	void or () {
		Criterio name = new Nombre ("Soldadora");
		Criterio category = new Categoria ("Alimento");
		Criterio price = new Precio (5000);
		Criterio or = new OR (List.of(name, category, price));
		assertEquals (Map.of(soldadora, 1, alfajor, 5, snack, 10, gaseosa, 3, bajon, 3), or.filter(catalog));
	}
	
	@Test
	void and () {
		Criterio category = new Categoria ("Alimento");
		Criterio price = new Precio (3000);
		Criterio availability = new Disponibilidad ();
		Criterio and = new AND (List.of(category, price, availability));
		assertEquals (Map.of(alfajor, 5, gaseosa, 3), and.filter(catalog));
	}
	
	@Test
	void not () {
		Criterio availability = new Disponibilidad ();
		Criterio not = new NOT (availability);
		assertEquals (Map.of(silla, 0), not.filter(catalog));
	}


}
