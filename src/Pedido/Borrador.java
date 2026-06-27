package Pedido;

import Catalogo.Item;
import Exceptions.StockEmptyException;

public class Borrador extends Estado {

	public Borrador(Pedido order) {
		super(order);
	}

	@Override
	public void addItem(Item item) {
		order.getItems().merge(item, 1, (oldValue, newValue) -> oldValue + 1);
	}

	@Override
	public void removeItem(Item item) {
		order.getItems().computeIfPresent(item, (key, value) -> value > 1 ? value - 1 : null);
	}

	@Override
	public Estado newState() {
		if (order.getItems().entrySet().stream().anyMatch(entry -> entry.getKey().getStock() < entry.getValue())) {
			throw new StockEmptyException ();
		}
		order.getItems().forEach((item, cantidad) -> item.reduceStock(cantidad));
		return new Confirmado(order);
	}

	@Override
	public Estado cancelled() {
		return new Cancelado(order);
	}
}
