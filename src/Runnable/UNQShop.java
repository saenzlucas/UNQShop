package Runnable;

import java.util.ArrayList;
import java.util.List;

import Busqueda.Categoria;
import Busqueda.Criterio;
import Busqueda.NOT;
import Busqueda.Precio;
import Catalogo.Item;
import Catalogo.Paquete;
import Catalogo.Producto;
import Envio.Sucursal;
import Pagos.BilleteraVirtual;
import Pedido.Pedido;

public class UNQShop {
	public static void main(String[] args) {
		
		Producto alfajor = new Producto ("Havanna", "Playa Grande", 2000, 0, 5, 90, 1, "Alimento");
		Producto snack = new Producto ("Doritos", "Sabor Queso", 3500, 0, 10, 45, 2, "Alimento");
		Producto gaseosa = new Producto ("Coca-Cola", "Original", 1500, 0, 3, 500, 3, "Alimento");
		
		Producto celular = new Producto ("Samsung A36", "5G 6/128GB", 100000, 0.25, 2, 195, 4, "Electronica");
		Producto silla = new Producto ("Silla de Pino", "Estilo Nordico", 25000, 0, 4, 4000, 5, "Hogar");
		Producto soldadora = new Producto ("Soldadora", "TIG", 150000, 0.10, 1, 4500, 6, "Herramienta");
		
		snack.addAttribute("kcal", 350);
		
		List<Item> products = new ArrayList<>();
		products.add(alfajor);
		products.add(snack);
		products.add(gaseosa);
		
		Paquete bajon = new Paquete ("Combo Bajon", "Alfajor + Snack + Bebida", 7000, 0.15, 635, products);		
		
		Pedido order = new Pedido (new BilleteraVirtual(), new Sucursal ());
		
		order.addItem(bajon);
		order.addItem(bajon);
		
		//order.updateState();
		
		//System.out.println (gaseosa.getStock());
		//System.out.println (snack.getStock());
		//order.cancel();
		
		//System.out.println (snack.getStock());
		//System.out.println (order.getTotalPrice());
		//System.out.println (order.getPayment());
		
		//order.cancel();
		
		//System.out.println (bajon.getFinalPrice());
		//System.out.println (snack.getAttribute("kcal"));
		
		
		List<Item> catalog = new ArrayList<>();
		catalog.add(alfajor);
		catalog.add(snack);
		catalog.add(gaseosa);
		catalog.add(celular);
		catalog.add(silla);
		catalog.add(soldadora);
		
		Criterio category = new Categoria ("Alimento");
		Criterio price = new Precio (150000);
		
		List<Criterio> criteria = List.of(category, price);
		
		Criterio not = new NOT (category);
		
		catalog = not.filter(catalog);
		
		System.out.println (catalog);
		
	}
}
