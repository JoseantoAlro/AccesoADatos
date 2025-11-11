package bancoAlimentos;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Excepciones.BancoException;
import util.GestionaFicherosAlimentos;


public class ServicioAlimentos {
	private BancoAlimentos repo;
	GestionaFicherosAlimentos gest = new util.GestionaFicherosAlimentos();
	private static final String Rutares = "src\\main\\resources\\";
	
	public ServicioAlimentos() {
		super();
		try {
			repo = new BancoAlimentos(leerBancosDesdeXML("bancoAlimentos.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
     
	

	
	public BancoAlimentos getRepo() {
		return repo;
	}




	public void setRepo(BancoAlimentos repo) {
		this.repo = repo;
	}




	private List<CentroLogistico> leerBancosDesdeXML(String ruta) throws Exception {
		List<CentroLogistico> centros = new ArrayList<CentroLogistico>();

		Document doc = gest.getDocumentFromXML(Rutares+ruta);
		
		NodeList nodosEquipos = doc.getElementsByTagName("CentroLogistico");				//devuelve una lista de nodos 

		for (int j = 0; j < nodosEquipos.getLength(); j++) {					//por cada nodo
			Node modeloNodo = nodosEquipos.item(j);
			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {				//hace que funcione el casting (cambio de tipo)
				CentroLogistico e = this.getCentro((Element) modeloNodo);				//cambia al tipo elemeto
				centros.add(e);
			}	
		}
		return centros;															//devuelve una lista de elementos
	}
	
	
	
	private CentroLogistico getCentro(Element elemento) throws BancoException {		//varia segun modelo //lee solo un equipo
		CentroLogistico c = new CentroLogistico();
		List<Trabajador> listatrabajadores = new ArrayList<>();
		
		//uno a uno los atributos
		
		String id = elemento.getElementsByTagName("ID").item(0).getTextContent();
		String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent();
		String ciudad = elemento.getElementsByTagName("Ciudad").item(0).getTextContent();	
		int comedores = Integer.parseInt(elemento.getElementsByTagName("ComedoresAbastecidos").item(0).getTextContent());
		
		NodeList listaTrabajadoresaux = ((Element) elemento.getElementsByTagName("Trabajadores").item(0)).getElementsByTagName("Trabajador");
		// = actores.actor como en Javascript
		for (int i = 0; i < listaTrabajadoresaux.getLength(); i++) {
			listatrabajadores.add(getTrabajador((Element) listaTrabajadoresaux.item(i)));
		}
		
		c.setId(id);
		c.setNombre(nombre);
		c.setCiudad(ciudad);
		c.setComedores(comedores);
		
		for(Trabajador t : listatrabajadores) {
			c.agregarTrabajador(t);
		}
		
		
		return c;
	}
	
	
	
	private Trabajador getTrabajador(Element elemento) {		//varia segun modelo //lee solo un equipo
		Trabajador t = new Trabajador();
		
		String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent();
		String DNI = elemento.getElementsByTagName("DNI").item(0).getTextContent();
		String fechaNac = elemento.getElementsByTagName("FechaNacimiento").item(0).getTextContent();
		
		String pers = (elemento.getElementsByTagName("Tipo").item(0).getTextContent());
		Personal p = null;
		if (pers.equals("Asalariado")){
			p = Personal.ASALARIADO;
		}else {
			p = Personal.VOLUNTARIO;
		}
		
		
		t.setNombre(nombre);
		t.setDni(DNI);
		t.setFechaNac(fechaNac);
		t.setPers(p);
		return t;
	}
	
	
	public void escribirEnCSV(List<Trabajador> t) {
		gest.escribeCSV(repo, t, Rutares+"voluntarios.csv");
	}
	
	
	

	public CentroLogistico getCentroPorId(String id) {
		CentroLogistico c = new CentroLogistico();
		for(CentroLogistico centro : repo.getCentros()) {
			if (centro.getId().equals(id)) {
				c=centro;
			}
		}
		return c;
	}
	
	public Trabajador getTrabajadorPorDNI(String DNI) {
		Trabajador trabajador = new Trabajador();
		for(CentroLogistico centro : repo.getCentros()) {
			for(Trabajador t: centro.getTrabajadores()) {
				if (t.getDni().equals(DNI)){
					trabajador=t;
				}
			}
		}
		return trabajador;
	}
	
	public List<Trabajador> getTrabajadoresPorTipo(Personal p) {
		List<Trabajador> lista = new ArrayList<>();
		for(CentroLogistico c : repo.getCentros()) {
			for(Trabajador t : c.getTrabajadores()) {
				if(t.getPers()==p) {
					lista.add(t);
				}
			}
		}
		return lista;
	}
}
