package Envio;

import Pedido.Pedido;

public interface Envio {
	void shippingTime (Pedido order);
	float calculateCost ();
}
