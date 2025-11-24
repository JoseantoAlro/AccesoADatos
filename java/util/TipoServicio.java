package util;



public class TipoServicio {
	
	private Repositorio repo;
	GestionaFicheros gest = new util.GestionaFicheros();
	private static final String Rutares = "src\\main\\resources\\";
	
	
	public TipoServicio() {
		super();
		try {
			repo = new Repositorio(funcionLeer(ruta));
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//get repo
	//set repo
	
	
	//funciones que pidan.
}
