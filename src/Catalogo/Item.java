package Catalogo;

import java.util.HashMap;
import java.util.Map;

public abstract class Item {

	public String nombre;
	public String descripcion;
	public double  precio;
	public double descuento;
	private int peso;
	private Map<String, Object> dinamics = new HashMap<>();
	
	public Item(String nombre, String descripcion, double precio, double descuento, int peso) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.descuento = descuento;
		this.peso = peso;
	}
	
	public void addAttribute (String attribute, Object data) {
		dinamics.put(attribute, data);
	}
	
	public Object getAttribute (String attribute) {
		return dinamics.get(attribute);
	}
	
	protected Map<String, Object> getAttributes() {
        return dinamics;
    }

	
	public abstract double getPrecioFinal();	
	
	public abstract boolean validarProducto();	
}
