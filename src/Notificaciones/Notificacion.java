package Notificaciones;

import Pedido.Estado;
import Pedido.Pedido;

public interface Notificacion {
	void shoutout (Pedido order, Estado oldState, Estado newState);
}
