package Reportes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import Misc.Sucursal;

public class CSV implements Reporte {
	@Override
	public void generateReport(Sucursal branch, LocalDate periodOne, LocalDate periodTwo) throws IOException {
		PrintWriter report = new PrintWriter(new FileWriter("BestSellers.csv"));
		report.println("Puesto;Item;Precio");
		int[] position = { 1 };
		branch.getBestSellers(periodOne, periodTwo).forEach((item, averagePrice) -> {
		    report.println(position[0] + ";" + item.getName() + ";" + averagePrice);
		    position[0]++;
		});
		report.close();
	}
}
