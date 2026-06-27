package Misc;

import java.time.LocalDate;
import java.util.Map;

import Catalogo.Item;
import Reportes.Reporte;

public class Venta {
	
	private Map<Item, Integer> items;
	private LocalDate date;
	
	public Venta(Map<Item, Integer> items, LocalDate date) {
		this.items = items;
		this.date = date;
	}

	public void accept (Reporte report) {
		
	}
	
}
