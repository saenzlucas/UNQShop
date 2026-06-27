package Exceptions;

public class StockEmptyException extends RuntimeException {
	public StockEmptyException () {
        super(String.format("Algun producto de tu carrito no tiene stock"));
    }
}
