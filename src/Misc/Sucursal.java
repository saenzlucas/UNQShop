package Misc;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Catalogo.Item;
import Reportes.Reporte;

public class Sucursal {
	private Map<Item, Integer> catalog;
	private List<Venta> sales;
	private int stock;
	
	public Sucursal(Map<Item, Integer> catalog) {
		this.catalog = catalog;
		this.sales = new ArrayList<>();
	}

	public Map<Item, Integer> getCatalog() {
		return catalog;
	}

	public List<Venta> getSales() {
		return sales;
	}

	public void newSale (Map<Item, Integer> items, LocalDate date) {
		sales.add(new Venta(items, date));
	}
	
	public Integer getStock(Item item) {
		return catalog.getOrDefault(item, 0);
	}
	
	public void increaseStock (Item item, int amount) {
		catalog.merge(item, amount, Integer::sum);
	}
	
	public void reduceStock (Item item, int amount) {
		catalog.merge(item, 0, (oldValue, newValue) -> oldValue - amount);
	}
	
	public Map<Item, Double> getBestSellers (LocalDate periodOne, LocalDate periodTwo) {
		return getSales().stream()
		        .filter(sale -> !sale.getDate().isBefore(periodOne) && !sale.getDate().isAfter(periodTwo))     
		        .flatMap(sale -> sale.getItems().entrySet().stream())
		        .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingInt(Map.Entry::getValue)))
		        .entrySet().stream()
		        .sorted((saleOne, saleTwo) -> Integer.compare(saleOne.getValue(), saleTwo.getValue()))    
		        .collect(Collectors.toMap(Map.Entry::getKey,sale -> sale.getKey().getPrice(),(saleOne, saleTwo) -> saleOne, LinkedHashMap::new)).reversed();					
	}
	
	public void newReport(Reporte reporte, LocalDate periodOne, LocalDate periodTwo) throws IOException {
        reporte.generateReport(this, periodOne, periodTwo);
    }
}
