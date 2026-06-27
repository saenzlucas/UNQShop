package Exceptions;

public class StockUnchangeableException extends RuntimeException {
	public StockUnchangeableException () {
        super(String.format("No se puede usar modificar el stock"));
    }
}
