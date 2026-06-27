package Misc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Catalogo.Item;

public class Sucursal {
	private List<Item> catalog;
	private List<Venta> sales;
	
	public Sucursal(List<Item> catalog) {
		this.catalog = catalog;
		this.sales = new ArrayList<>();
	}

	public List<Item> getCatalog() {
		return catalog;
	}

	public List<Venta> getSales() {
		return sales;
	}

	public void newSale (Map<Item, Integer> items, LocalDate date) {
		sales.add(new Venta(items, date));
	}
}
