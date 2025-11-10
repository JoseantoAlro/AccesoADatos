package f1;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import util.GestionaFicherosF1;

public class ServicioF1 {

	private static final String Rutares = "src\\main\\resources\\";
	GestionaFicherosF1 gest = new util.GestionaFicherosF1(); 
	private RepositorioEquipos repo;
	private String ruta;
	
	
	public ServicioF1(String ruta) {
		super();
		this.ruta = ruta;
		try {
			this.repo = new RepositorioEquipos(leerEquiposDesdeXML("formula1.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public RepositorioEquipos getRepo() {
		return repo;
	}



	public void setRepo(RepositorioEquipos repo) {
		this.repo = repo;
	}



	public List<Equipo> leerEquiposDesdeXML(String ruta) throws Exception {
		List<Equipo> equipos = new ArrayList<Equipo>();

		Document doc = gest.getDocumentFromXML(Rutares+ruta);
		
		NodeList nodosEquipos = doc.getElementsByTagName("equipo");				//devuelve una lista de nodos 

		for (int j = 0; j < nodosEquipos.getLength(); j++) {					//por cada nodo
			Node modeloNodo = nodosEquipos.item(j);
			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {				//hace que funcione el casting (cambio de tipo)
				Equipo e = this.getEquipo((Element) modeloNodo);				//cambia al tipo elemeto
				equipos.add(e);
			}	
		}
		return equipos;															//devuelve una lista de elementos
	}

	
	public List<Piloto> leerPilotosDesdeXML() throws Exception {
		List<Piloto> equipos = new ArrayList<Piloto>();

		Document doc = gest.getDocumentFromXML(Rutares+ruta);
		
		NodeList nodosEquipos = doc.getElementsByTagName("piloto");				//devuelve una lista de nodos 

		for (int j = 0; j < nodosEquipos.getLength(); j++) {					//por cada nodo
			Node modeloNodo = nodosEquipos.item(j);
			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {				//hace que funcione el casting (cambio de tipo)
				Piloto e = this.getPiloto((Element) modeloNodo);				//cambia al tipo elemeto
				equipos.add(e);
			}	
		}
		return equipos;															//devuelve una lista de elementos
	}

	
	
	public Piloto getPiloto(Element elemento) {
		Piloto p = new Piloto();
		String id = elemento.getAttribute("identificadorPiloto");
		String nombre = elemento.getElementsByTagName("nombrePiloto").item(0).getTextContent();
		int puntos = Integer.parseInt(elemento.getElementsByTagName("puntos").item(0).getTextContent());
		int idequipo = Integer.parseInt(elemento.getElementsByTagName("identificadorEquipo").item(0).getTextContent());
		String pais = elemento.getElementsByTagName("pais").item(0).getTextContent();
		
		p.setId(id);
		p.setNombre(nombre);
		p.setPuntos(puntos);
		p.setIdequipo(idequipo);
		p.setPais(pais);
		
		return p;
		
	}
	
	
	
	private Equipo getEquipo(Element elemento) {		//varia segun modelo //lee solo un equipo
		Equipo e = new Equipo();
		List<Piloto> listapilotos = new ArrayList<>();
		
		//uno a uno los atributos
		
		String id = elemento.getAttribute("identificadorEquipo");
		String nombre = elemento.getElementsByTagName("nombreEquipo").item(0).getTextContent();
		int puntos = Integer.parseInt(elemento.getElementsByTagName("puntos").item(0).getTextContent());	
		
		e.setId(id);
		e.setNombre(nombre);
		e.setPuntos(puntos);
		e.setPilotos(listapilotos);
		
		return e;
	}
	
	
	public List<Piloto> getPilotosConMasPuntosQue(int i){
		List<Piloto> ps = new ArrayList<>();
		for(Equipo e : repo.getEquipos()) {
			for (Piloto p : e.getPilotos()) {
				if (p.getPuntos()>i) {
					ps.add(p);
				}
			}
		}
		return ps;
	}
	
	public void escribirPilotos(List<Piloto> pilotos) {
		String ruta = Rutares+"pilotos.json";
		gest.escribePilotosJson(pilotos, ruta);
	}
	
	public void escribirXML(String ruta) {
		for (Equipo e: repo.getEquipos()) {
			gest.escribeEquiposEnXML(Rutares+ruta, repo.getEquipos());
		}
	}
	
	
}
