package Reportes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import Misc.Sucursal;

public class TXT implements Reporte {
	@Override
	public void generateReport(Sucursal branch, LocalDate periodOne, LocalDate periodTwo) throws IOException {
		PrintWriter report = new PrintWriter(new FileWriter("BestSellers.txt"));
		report.println("=======================================");
		report.println("Reporte de items mas vendidos");
		report.println("Periodo desde " + periodOne + " al " + periodTwo);
		report.println("=======================================");
		int[] position = { 1 };
		branch.getBestSellers(periodOne, periodTwo).forEach((item, averagePrice) -> {
			report.println("\n(" + position[0] + ") [Item] " + item.getName() + " | $"+ averagePrice + "");
			position[0]++;
		});
		report.close();
	}
}
