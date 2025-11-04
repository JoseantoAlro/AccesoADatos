package PeliculasXML;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GestionaPeliculas {

	private static final Logger logger = LogManager.getLogger(GestionaPeliculas.class); 
	public static void main(String[]args) throws Exception {
		
		XMLDomPeliculas xml = new XMLDomPeliculas("peliculas.xml"); 
		List<Pelicula> ps = new ArrayList<>();
		RepositorioPeliculas repo = new RepositorioPeliculas(ps);
		PeliculaService serv = new PeliculaService(xml,repo);
		serv.cargaRepositorio(serv.leerPeliculasDesdeXML());
		repo = serv.getRepo();
		
		
		for (Pelicula p : repo.getRepositorio() ) { 
			logger.debug(p.getTitulo()+" "+p.getFecha()+" "+p.getDirector()+p.getActores());
		}
	}
}
