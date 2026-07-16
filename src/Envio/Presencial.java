package Envio;

import Pedido.Pedido;

public class Presencial implements Envio {
	@Override
	public void shippingTime (Pedido order) {
		if (order.getItems().keySet().stream().allMatch(item -> order.getBranch().getStock(item) > 0)) {
			System.out.println ("Hay stock disponible para retirar de forma inmediata en la sucursal");
		} else {
			System.out.println ("Hay una demora de hasta 3 dias para retirar el pedido por falta de existencias en la sucursal");
		};
	}
	
	@Override
	public float calculateCost (Pedido order) {
		return 0;
	}
}
