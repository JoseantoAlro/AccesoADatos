package productos;

import java.util.List;

public class RepositorioProductos {
	
	private String fecha;
	private List<Producto> productos;

	public RepositorioProductos(String fecha, List<Producto> productos) {
		super();
		this.fecha = fecha;
		this.productos = productos;
	}
	
	public RepositorioProductos() {
		super();
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}


	
	
	
}
