package Catalogo;



public class Producto extends Item {

	
	
    private Integer sku; 
    private String categoria;

    
    
    public Producto(String nombre, String descripcion, double precioBase, double descuento, Integer sku, String categoria) {
        super(nombre, descripcion, precioBase, descuento);
        this.sku = sku;
        this.categoria = categoria;
    }

    
    
    @Override
    public double getPrecioFinal() {
        return this.getPrecioBase() * (1 - this.getDescuento());
    }

    
    @Override
    public boolean validar() {
        boolean nombreValido = this.getNombre() != null;
        boolean skuValido = this.sku != null && this.sku > 0;
        boolean dinamicosValidos = !this.getAtributosDinamicos().containsValue(null);

        return nombreValido && skuValido && dinamicosValidos;
    }

    
    public Integer getSKU() {
        return this.sku;
    }

    
    public String getCategoria() {
        return this.categoria;
    }
}