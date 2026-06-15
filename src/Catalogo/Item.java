package Catalogo;

import java.util.HashMap;
import java.util.Map;



public abstract class Item {

	
	
    private String nombre;
    private String descripcion;
    private double precioBase;
    protected double descuento;
    private Map<String, Object> atributosDinamicos;

    
    
    public Item(String nombre, String descripcion, double precioBase, double descuento) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.descuento = descuento;
        this.atributosDinamicos = new HashMap<>();
    }

    
    
    public void addAttribute(String attribute, Object data) {
        this.atributosDinamicos.put(attribute, data);
    }

    
    public Object getAttribute(String attribute) {
        return this.atributosDinamicos.get(attribute);
    }

    
    protected Map<String, Object> getAtributosDinamicos() {
        return this.atributosDinamicos;
    }

    
    public String getNombre() {
        return this.nombre;
    }

    
    public String getDescripcion() {
        return this.descripcion;
    }

    
    public double getPrecioBase() {
        return this.precioBase;
    }

    
    public double getDescuento() {
        return this.descuento;
    }

    
    public abstract double getPrecioFinal();
    
    
    public abstract boolean validar();
}