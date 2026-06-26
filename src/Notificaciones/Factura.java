package Notificaciones;

import Misc.ComprobanteFiscal;
import Pedido.Estado;
import Pedido.Pedido;

public class Factura implements Notificacion {
	public void shoutout (Pedido order, Estado oldState, Estado newState) {
		ComprobanteFiscal taxReceipt = new ComprobanteFiscal ();
	}
}
