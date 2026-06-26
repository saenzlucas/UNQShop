package Runnable;

import java.util.ArrayList;
import java.util.List;

import Catalogo.Item;
import Catalogo.Paquete;
import Catalogo.Producto;
import Pagos.BilleteraVirtual;
import Pedido.Pedido;

public class UNQShop {
	public static void main(String[] args) {
		
		Producto alfajor = new Producto ("Havanna", "Playa Grande", 2000, 0, 90, 1, "Alimento");
		Producto snack = new Producto ("Doritos", "Sabor Queso", 3500, 0, 45, 2, "Alimento");
		Producto gaseosa = new Producto ("Coca-Cola", "Original", 1500, 0, 500, 3, "Alimento");
		
		snack.addAttribute("kcal", 350);
		
		List<Item> products = new ArrayList<>();
		products.add(alfajor);
		products.add(snack);
		products.add(gaseosa);
		
		
		Paquete bajon = new Paquete ("Combo Bajon", "Alfajor + Snack + Bebida", 7000, 0.15, 635, products);
		
		Pedido order = new Pedido (products, new BilleteraVirtual(15000));
		
		System.out.println (order.getItems());
		System.out.println (order.getPayment());
		
		order.cancel();
		
		System.out.println (bajon.getFinalPrice());
		System.out.println (snack.getAttribute("kcal"));
		
	}
}
