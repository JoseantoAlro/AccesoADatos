package productos;

import java.util.ArrayList;
import java.util.List;

public class GestionaProductos {

	public static void main(String[] args) {				//lectura y escritura en utiles
		Producto p1 = new Producto(1,true, "mesa", 10.0, 4);
		Producto p2 = new Producto(2,true, "silla", 15.0, 9);
		Producto p3 = new Producto(3,true, "roca", 16.0, 8);
		Producto p4 = new Producto(4,true, "mesa baja", 17.0, 7);
		
		List<Producto> prod = new ArrayList<>();
		prod.add(p4);
		prod.add(p3);
		prod.add(p2);
		prod.add(p1);
		
		RepositorioProductos repo = new RepositorioProductos("10/07/2025", prod);
		
		ServicioProd serv = new ServicioProd(repo);
		
		serv.escribeProductosJson(serv.filtrarPorStock(5), "productosNoenVenta.json");
	}
}
