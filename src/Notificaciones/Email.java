package Notificaciones;

import Pedido.Confirmado;
import Pedido.Entregado;
import Pedido.Enviado;
import Pedido.Estado;
import Pedido.Pedido;

public class Email implements Notificacion {
	public void shoutout (Pedido order, Estado oldState, Estado newState) {
		if ((newState instanceof Confirmado) || (newState instanceof Enviado) || (newState instanceof Entregado)) {
			System.out.println ("de: unqshop@gmail.com");
			System.out.println ("asunto: Actualizacion de estado sobre su pedido " + order);
			System.out.println ("mensaje: El nuevo estado de su pedido es " + newState.getClass().getSimpleName());
		}
	}
}
