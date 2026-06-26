package Notificaciones;

import Pedido.Cancelado;
import Pedido.Estado;
import Pedido.Pedido;

public class Fidelizacion implements Notificacion {
	public void shoutout (Pedido order, Estado oldState, Estado newState) {
		if (newState instanceof Cancelado) {
			System.out.println ("¡CUPON GRATIS! 5% de descuento en tu proxima compra, valido durante las proximas 24hs");
		}
	}
}
