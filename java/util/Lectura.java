package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

import Excepciones.BancoException;
import Jsons.Empleados;
import PokemonJson.Pokemon;
import TowerGPT.InteraccionAgente;
import TowerGPT.RepositorioInteracciones;
import bancoAlimentos.CentroLogistico;
import bancoAlimentos.Trabajador;

public class Lectura {
	
	//XMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLMLXMLXMLXMLXMLXMLXMLXMLXMLXMLMLXMLXMLXMLXMLXMLXMLXMLXMLXMLMLXMLXMLXMLXMLXMLXMLXMLXMLXMLML
	//lectura desde XML en archivo gestionaficheros
	//esta parte NO SE TOCA pasa del fichero a un objeto documento
	public Document getDocumentFromXML(String rutafichero) { 
		File file = new File(rutafichero);
		Document documento = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			documento = dBuilder.parse(file);

		} catch (Exception e) {
			// logger.error(e.getMessage());
		}
		return documento;
	}
	
	
	
	
	
	// en servicios inicializando gestionaficheros (gest)
	
	//esta parte si es necesario modificarlo, coge el documento y lo transforma a una lista de nodos,
	//y llamando a una funcion q pase de nodos a objetos devuelve una lista de dichos objetos
	
	private List<CentroLogistico> leerBancosDesdeXML(String ruta) throws Exception {
		List<CentroLogistico> centros = new ArrayList<CentroLogistico>();      //inicia una lista de objetos vacia

		Document doc = gest.getDocumentFromXML(Rutares+ruta);
		
		NodeList nodosEquipos = doc.getElementsByTagName("CentroLogistico");	//modificar segun el nodo a obtener

		for (int j = 0; j < nodosEquipos.getLength(); j++) {					//por cada nodo
			Node modeloNodo = nodosEquipos.item(j);
			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {				
				CentroLogistico e = this.getCentro((Element) modeloNodo);		//cambia el nodo, casteado a elemento, a objeto (siguiente funcion)
				centros.add(e);													//añade el objeto a la lista vacia
			}	
		}
		return centros;															//devuelve una lista de objetos
	}
	
	
	
	//coge un elemento y lo convierte en objeto
	
	private CentroLogistico getCentro(Element elemento) throws BancoException {		
		CentroLogistico c = new CentroLogistico();								//inicializamos un objeto vacio.
		List<Trabajador> listatrabajadores = new ArrayList<>();					//la lista de objetos a guardar en caso de lista de nodos dentro
			
		//uno a uno los atributos segun el modelo
		String id = elemento.getElementsByTagName("ID").item(0).getTextContent();
		String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent();
		String ciudad = elemento.getElementsByTagName("Ciudad").item(0).getTextContent();	
		int comedores = Integer.parseInt(elemento.getElementsByTagName("ComedoresAbastecidos").item(0).getTextContent());
		
		//en caso de tener una lista de nodos dentro de otra pasamos los elementos por otra funcion que cambie de elemento a objeto.
		//(la estructura de la segunda funcion es igual)    Mirar Ejercicio bancoAlimentos en caso de duda.
		NodeList listaTrabajadoresaux = ((Element) elemento.getElementsByTagName("Trabajadores").item(0)).getElementsByTagName("Trabajador");  //lista de nodos
		// = actores.actor como en Javascript
		for (int i = 0; i < listaTrabajadoresaux.getLength(); i++) {
			listatrabajadores.add(getTrabajador((Element) listaTrabajadoresaux.item(i)));			//casting para pasar de nodos a elementos
		}
		
		String atributo = elemento.getAttribute("identificadorPiloto"); //en caso de tener un atributo
		
		c.setId(id);
		c.setNombre(nombre);
		c.setCiudad(ciudad);
		c.setComedores(comedores);
		
		for(Trabajador t : listatrabajadores) {
			c.agregarTrabajador(t);
		}
		
		
		return c;
	}
	
	
	
	
	
	//CSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSV
	//lee un csv y lo combierte a una lista de objetos 
	public List<Pokemon> leePokemonsFromCSV(String ruta) throws FileNotFoundException{	//pasamos la ruta del csv a leer
		Scanner in = null;
		List<Pokemon> plist = new ArrayList<Pokemon>();								//inicamos la lista de objetos a devolver
		try {
			FileReader f = new FileReader(ruta); 									

			in = new Scanner(f);
			//in.nextLine();														//en caso que el csv tenga titulos salta la primera linea
			while (in.hasNextLine()) {
				
				String linea = in.nextLine();										//por cada linea llama a funcion de linea a obgeto
				Pokemon p = cargarPokemon(linea);
				plist.add(p);														//lo carga a la lista a devolver
			}
		} finally{																	//cierre del csv
			if (in != null) {
				in.close();
			}
		}
		return plist;
	}
	
	
	public Pokemon cargarPokemon(String linea) { 						//coge la linea y la convierte a objeto
		String[] cadena = linea.split(",");								//segun la separacion del csv (no se si un csv se puede serparar por algo que no sean comas
		Pokemon p = new Pokemon(Integer.parseInt(cadena[0])				//segun el modelo, todos son String mirar cuales son ints en el modelo.
				,cadena[1]
				,cadena[2]
				,Float.parseFloat(cadena[3])
				,Float.parseFloat(cadena[4])
				,cadena[5]
				,cadena[6]);
		return p;														//devuelve objeto
	}
	
	
	
	
	
	
	
	
	//JsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJson
	//coge la ruta de un json por parametro y lo convierte a UN SOLO objeto
	public Pokemon leePokemonFromJson(String ruta) {
		 Pokemon pkm = null;
		try {
			
			Gson gson = new Gson();
			FileReader fichero = new FileReader(ruta);
			// Leer el archivo JSON y convertirlo a un objeto pokemon
			pkm = gson.fromJson(fichero, Pokemon.class);
			
			} catch (Exception e) {
				System.out.println("Error al leer pokemons"+e.getMessage());
			}
		return pkm;
	} 
	
	
	
	
	
	
	//pasa de Json a lista de objetos Json
	public List<Empleado> leeObjetosdeJson(String rutaFichero) { //recibe la ruta del json
		try {
			//Esto es igual
			Gson gson = new Gson();
			FileReader fichero = new FileReader(rutaFichero);
		  
			// Leer el archivo JSON y convertirlo a un array de objetos
			Empleado[] empleadosArray = gson.fromJson(fichero, Empleado[].class);  	//lo devuelve como array no mutable
			List<Empleado> empleados = Arrays.asList(empleadosArray);				//lo pasamos a lista
			return empleados;														//devuelve la lista
			
		}catch (Exception e) {
			System.out.println("Error al leer empleados"+e.getMessage());
			return new ArrayList<>();
		}	
	}

	
	

}
