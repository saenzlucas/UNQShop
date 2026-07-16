package Pedido;

import Catalogo.Item;
import Exceptions.StockEmptyException;

public class Borrador extends Estado {

	public Borrador(Pedido order) {
		super(order);
	}

	@Override
	public void addItem(Item item) {
		getOrder().getItems().merge(item, 1, (oldValue, newValue) -> oldValue + 1);
	}

	@Override
	public void removeItem(Item item) {
		getOrder().getItems().computeIfPresent(item, (key, value) -> value > 1 ? value - 1 : null);
	}

	@Override
	public Estado newState() {
		Estado newState = new Confirmado(getOrder());
		if (getOrder().getItems().entrySet().stream().anyMatch(item -> getOrder().getBranch().getStock(item.getKey()) < item.getValue())) {
			throw new StockEmptyException ();
		}
		getOrder().getItems().forEach((item, cantidad) -> getOrder().getBranch().reduceStock(item, cantidad));
		shotout (this, newState);
		return newState;
	}

	@Override
	public Estado cancelled() {
		Estado newState = new Cancelado(getOrder());
		shotout (this, newState);
		return newState;
	}
}
