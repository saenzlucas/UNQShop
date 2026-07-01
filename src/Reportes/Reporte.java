package Reportes;

import java.io.IOException;
import java.time.LocalDate;

import Misc.Sucursal;

public interface Reporte {
	void generateReport (Sucursal branch, LocalDate periodOne, LocalDate periodTwo) throws IOException;
}
