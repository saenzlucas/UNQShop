package Catalogo;

import java.util.List;



public class Paquete extends Item {

	
	
    private List<Item> items;

  
    
    public Paquete(String nombre, String descripcion, double precioBase, double descuento, List<Item> items) {
        super(nombre, descripcion, precioBase, descuento);
        this.items = items;
    }

    
    
    @Override
    public double getPrecioFinal() {
        double totalItems = this.items.stream()
                                            .mapToDouble(item -> item.getPrecioFinal())
                                            .sum();
        
        return totalItems * (1 - this.getDescuento());
    }

    
    @Override
    public boolean validar() {
        boolean nombreValido = this.getNombre() != null;
        
        boolean itemsValidos = this.items.stream().allMatch(item -> item.validar());
                               
        boolean dinamicosValidos = !this.getAtributosDinamicos().containsValue(null);

        return nombreValido && itemsValidos && dinamicosValidos;
    }

    
    public List<Item> getItems() {
        return this.items;
    }
}