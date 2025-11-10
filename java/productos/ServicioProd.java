package productos;

import java.util.ArrayList;
import java.util.List;

public class ServicioProd extends GestionaFicheros{
	
	private RepositorioProductos repo;

	public ServicioProd(RepositorioProductos repo) {
		super();
		this.repo = repo;
	}
	
	
	public RepositorioProductos getRepo() {
		return repo;
	}


	public void setRepo(RepositorioProductos repo) {
		this.repo = repo;
	}


	public List<Producto> filtrarPorStock(int stock){
		List<Producto> filtroProds = new ArrayList<>();
		for (Producto p : repo.getProductos()) {
			if (p.getStock()<stock) {
				filtroProds.add(p);
			}
		}
		return filtroProds;
	}
	
	public void retiraDeVentaProductos() {
		List<Producto> filtroProds = new ArrayList<>();
		for (Producto p : repo.getProductos()) {
			if(p.getStock()<5) {
				p.setEnVenta(false);
			}
			filtroProds.add(p);
		}
		RepositorioProductos newrep = new RepositorioProductos(repo.getFecha(), filtroProds);
		this.setRepo(newrep);
	}
	
	
	
	
}
