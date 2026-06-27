package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
	}

	@Test
	void byName () {
		Criterio name = new Nombre ("Silla de Pino");
		assertEquals (name.filter(catalog), List.of(silla));
	}
	
	@Test
	void byCategory () {
		Criterio category = new Categoria ("Alimento");
		assertEquals (category.filter(catalog), List.of(alfajor, snack, gaseosa, bajon));
	}
	
	@Test
	void byPrice () {
		Criterio price = new Precio (5000);
		assertEquals (price.filter(catalog), List.of(alfajor, snack, gaseosa));
	}
	
	@Test
	void byAvailability () {
		Criterio availability = new Disponibilidad ();
		assertEquals (availability.filter(catalog), List.of(celular, soldadora, alfajor, snack, gaseosa, bajon));
	}
	
	@Test
	void or () {
		Criterio name = new Nombre ("Soldadora");
		Criterio category = new Categoria ("Alimento");
		Criterio price = new Precio (5000);
		Criterio or = new OR (List.of(name, category, price));
		assertEquals (or.filter(catalog), List.of(soldadora, alfajor, snack, gaseosa, bajon));
	}
	
	@Test
	void and () {
		Criterio category = new Categoria ("Alimento");
		Criterio price = new Precio (3000);
		Criterio availability = new Disponibilidad ();
		Criterio and = new AND (List.of(category, price, availability));
		assertEquals (and.filter(catalog), List.of(alfajor, gaseosa));
	}
	
	@Test
	void not () {
		Criterio availability = new Disponibilidad ();
		Criterio not = new NOT (availability);
		assertEquals (not.filter(catalog), List.of(silla));
	}


}
