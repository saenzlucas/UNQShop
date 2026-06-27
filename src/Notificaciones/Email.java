package Notificaciones;

import java.io.File;

import Misc.MailSender;
import Pedido.Confirmado;
import Pedido.Entregado;
import Pedido.Enviado;
import Pedido.Estado;
import Pedido.Pedido;

public class Email implements Notificacion {
	public void shoutout (Pedido order, Estado oldState, Estado newState) {
		if ((newState instanceof Confirmado) || (newState instanceof Enviado) || (newState instanceof Entregado)) {
			new MailSender ().enviarMail("lsaenz05@outlook.com", 
										 "Actualizacion de estado sobre su pedido " + order, 
										 "El nuevo estado de su pedido es " + newState.getClass().getSimpleName(), 
										 new File("image.png"));
		}
	}
}
