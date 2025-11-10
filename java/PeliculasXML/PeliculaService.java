package PeliculasXML;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PeliculaService {
	private String rutaResources="src\\main\\resources\\";
	private  XMLDomPeliculas archivoXML;
	private RepositorioPeliculas repo;
	
	
	
	public PeliculaService(XMLDomPeliculas archivoXML, RepositorioPeliculas repo) {
		super();
		this.archivoXML = archivoXML;
		this.repo = repo;
	}
	
	public RepositorioPeliculas getRepo() {
		return repo;
	}





	private Pelicula getPeliculaFromElement(Element elemento) {		//varia segun modelo //lee solo un empleado
		Pelicula e = new Pelicula();
		String s = "";
		List<String> actores = new ArrayList<>();
		String titulo = elemento.getElementsByTagName("Titulo").item(0).getTextContent();
		int duracion = Integer.parseInt(elemento.getElementsByTagName("Duracion").item(0).getTextContent());
		String genero = elemento.getElementsByTagName("Genero").item(0).getTextContent();
		Node sinopsis = elemento.getElementsByTagName("sinopsis").item(0);
		if(sinopsis == null) {
			s = "No hay sinopsis";
		}else {
			s = sinopsis.getTextContent();
		}
		
		NodeList listaActores = ((Element) elemento.getElementsByTagName("Actores").item(0)).getElementsByTagName("Actor");
		// = actores.actor como en Javascript
		
		for (int i = 0; i < listaActores.getLength(); i++) {
			actores.add(listaActores.item(i).getTextContent());
		}
		int fecha = Integer.parseInt(elemento.getElementsByTagName("Fecha").item(0).getTextContent());
		String director = elemento.getElementsByTagName("Director").item(0).getTextContent();
		
		e.setTitulo(titulo);
		e.setDuracion(duracion);
		e.setGenero(genero);
		e.setSinopsis(s);
		e.setActores(actores);
		e.setFecha(fecha);
		e.setDirector(director);
		
		return e;
	}
	
	
	public List<Pelicula> leerPeliculasDesdeXML() throws Exception {
		List<Pelicula> pelis = new ArrayList<Pelicula>();
		

		Document doc = archivoXML.getDocumentFromXML(archivoXML.getFichero());

		NodeList nodosEmpleados = doc.getElementsByTagName("Pelicula");			//devuelve una lista de nodos 

		for (int j = 0; j < nodosEmpleados.getLength(); j++) {					//por cada nodo
			Node modeloNodo = nodosEmpleados.item(j);
			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {				//hace que funcione el casting (cambio de tipo)
				Pelicula e = this.getPeliculaFromElement((Element) modeloNodo);	//cambia al tipo elemeto
				pelis.add(e);
			}	
		}
		return pelis;														//devuelve una lista de elementos
	}


	
	public void cargaRepositorio(List<Pelicula> pelis) {
		for (Pelicula p : pelis) {
			repo.add(p);
		}
	}
}
