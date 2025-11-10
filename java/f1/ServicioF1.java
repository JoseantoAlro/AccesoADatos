package f1;

import util.GestionaFicheros;

public class ServicioF1 {
	private RepositorioEquipos repo;
	private String ruta;
	private static final String Rutares = "src\\main\\resources\\";

	public ServicioF1(RepositorioEquipos repo, String ruta) {
		super();
		this.ruta = ruta;
		this.repo = repo;
		GestionaFicheros gest = new util.GestionaFicheros(); 
	}

	public ServicioF1() {
		super();
	}
	
	public RepositorioEquipos creaRepo() {
		
	}
	
	
}
