package Reportes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import Misc.Sucursal;

public class HTML implements Reporte {
	@Override
	public void generateReport(Sucursal branch, LocalDate periodOne, LocalDate periodTwo) throws IOException {
		PrintWriter report = new PrintWriter(new FileWriter("BestSellers.html"));
		report.println("<h1>Reporte de items mas vendidos</h1>");
		report.println("<p>Periodo desde " + periodOne + " al " + periodTwo + "</p>");
		report.println("<hr>");
		int[] position = { 1 };
		branch.getBestSellers(periodOne, periodTwo).forEach((item, averagePrice) -> {
		    report.println("<br>(" + position[0] + ") [Item] " + item.getName() + " | $" + averagePrice);
		    position[0]++;
		});
		report.close();
	}
}
