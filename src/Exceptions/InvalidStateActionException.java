package Exceptions;

public class InvalidStateActionException extends RuntimeException {
	public InvalidStateActionException () {
        super(String.format("No se puede realizar esta accion en el estado actual del pedido"));
    }
}
