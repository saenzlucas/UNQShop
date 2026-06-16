package Catalogo;

public class Producto extends Item {

	private int SKU;
	private double precioFinal;
	private String categoria;

	public Producto(String nombre, String descripcion, double precio, double descuento, int peso, int SKU, String categoria) {
		super(nombre, descripcion, precio, descuento, peso);
		this.SKU = SKU;
		this.precioFinal = precio * (1-descuento);
		this.categoria = categoria;
	}
	
	public double getPrecioFinal () {
		return precioFinal;
	}
	
	/*
	@Override
    public boolean validarProducto() {
        return true;
    }
    */
}
