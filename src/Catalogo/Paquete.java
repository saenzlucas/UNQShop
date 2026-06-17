package Catalogo;

import java.util.List;

public class Paquete extends Item {

private List<Item> items;
	
	public Paquete(String nombre, String descripcion, double precio, double descuento, int peso, List<Item> items) {
		super(nombre, descripcion, precio, descuento, peso);
		this.items = items;
	}
	
	public double getPrecioFinal () {
		double total = items.stream().mapToDouble(item -> ((Producto) item).getPrecioFinal()).sum();
		return (total * (1-descuento));
	}
	
	@Override
    public boolean validarProducto() {
		boolean nombreValido = nombre != null;        
        boolean itemsValidos = this.items.stream().allMatch(item -> item.validarProducto());                            
        boolean dinamicosValidos = !this.getAttributes().containsValue(null);
        return nombreValido && itemsValidos && dinamicosValidos;
    }
	
}
