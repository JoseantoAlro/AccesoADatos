package util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import PokemonJson.Pokemon;
import bancoAlimentos.BancoAlimentos;
import bancoAlimentos.CentroLogistico;
import bancoAlimentos.Trabajador;
import f1.Equipo;
import f1.Piloto;
import productos.Producto;

public class Escritura {

	//XMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXMLXML
	//llamada desde servicio a la funcion de escribir, le pasamos la ruta y la lista de objetos(repositorio.getLista())
	public void escribirXML(String ruta) { 
		gest.escribeEquiposEnXML(Rutares+ruta, repo.getEquipos());
	}
	
	
	
	//Funcion principal que llama a todas las otras
	//crea un documento con el padre ==> añade al doc los elementos hijos ==> escribe el doc en un fichero
	public void escribeEquiposEnXML(String nombreFichero, List<Equipo> equipos) {
		try {
			Document documento = this.construyoObjetoDocumento("equipos");		//nombre de la etiqueta principal <ConjuntoTotal> mirar modelo
			for(Equipo e : equipos)												//por elemento de la lista
			{
				Element elemento = this.creaElemento("equipo", null, documento.getDocumentElement(), documento);	//crea elemento hijo y lo añade al doc
				agregaEquipoADocumento(documento, elemento, e);
			}
			escribeDocumentoEnFichero(documento, nombreFichero);				//cuando todo esta listo lo escribe en el fichero
		} catch (ParserConfigurationException e1) {
			logger.error(e1.getMessage());
		} catch (TransformerException e1) {
			logger.error(e1.getMessage());
		}
	}

	
	
	//DE ESTA FUNCION NO HAY QUE TOCAR NADA DE NADA
	//es necesario crear el DOM antes de escribir nada esto lo inicializa.
	private void escribeDocumentoEnFichero(Document documento, String nombreFichero) throws TransformerException {
		// clases necesarias finalizar la creación del archivo XML
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		transformer = transformerFactory.newTransformer();
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");							//pa darle indentacion al escribir
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); 	// indentación de 4 espacios

	    
	    
		DOMSource source = new DOMSource(documento);
		StreamResult resultado = new StreamResult(new File(nombreFichero));
		transformer.transform(source, resultado);
	} 
	
	
	//Agrega objeto al doc                                //esto si es lo que hay que modificar
	//se le pasa el doc, el elemento padre y el OBJETO
	private void agregaEquipoADocumento(Document documento, Element padre, Equipo e) {
		// Para cada una de los atributos de equipo, creo un elemento hijo
		List<Piloto> listap = e.getPilotos();		//en caso de que haya una lista hija dentro de un elemento a añadir
		
		
		//Todo ha de ser STRING
		//parametros a escribir segun modelo  etiqueta       llamada     padre     doc
		Element nombre = this.creaElemento("nombreEquipo", e.getNombre(), padre, documento);								//por que declararlo como elemento????
		Element edad = this.creaElemento("puntos", Integer.toString(e.getPuntos()), padre, documento);
		
		
		//nueva etiqueta global que contendra la lista de elementos hijos													//cuidado cual es el padre al que añadir
		Element pilotos = this.creaElemento("pilotos", null, padre, documento);
		for (Piloto p : listap) { //en caso de lista de elementos
			//crear una etiqueta hija nueva						padre
			Element piloto = this.creaElemento("piloto", null, pilotos, documento); 
			
			//parametros segun modelo					etiqueta 		llamada		padre
			Element nombrePiloto = this.creaElemento("nombrePiloto", p.getNombre(), piloto, documento);
			Element puntosPiloto = this.creaElemento("puntos", Integer.toString(p.getPuntos()), piloto, documento);
			Element idEquipo = this.creaElemento("identificadorEquipo", String.valueOf(p.getIdequipo()), piloto, documento);
			Element pais =  this.creaElemento("pais", p.getPais(), piloto, documento);
			piloto.setAttribute("identificador", p.getId());  //si tiene atributo es asi
		}
		
     
		// El identificador lo vamos a crear como un atributo de la etiqueta
		padre.setAttribute("identificador", e.getId());
	}

	
	
	
	//DE ESTA FUNCION NO HAY QUE TOCAR NADA DE NADA
	//crea elemento hijo segun string y el padre
	private Element creaElemento(String nombreElemento, String valorElemento, Element padre, Document documento) {
	    Element elemento = documento.createElement(nombreElemento);
	    if (valorElemento != null) {
	        Text texto = documento.createTextNode(valorElemento);
	        elemento.appendChild(texto);
	    }
	    padre.appendChild(elemento);
	    return elemento;
	}
	
	//DE ESTA FUNCION NO HAY QUE TOCAR NADA DE NADA
	//construye el doc
	private Document construyoObjetoDocumento(String nombreRaiz) throws ParserConfigurationException {
		Document documento = null;
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factoria.newDocumentBuilder();
		DOMImplementation implementacion = builder.getDOMImplementation();
		documento = implementacion.createDocument(null, nombreRaiz, null);
		// Primer parámetro uri: si null no está validado por ninguna ruta		
		// segundo parámetro: nombre fichero
		// tercer parámetro: document type Por defecto null
		return documento;
	}
	
	
	
	
	
	//CSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSVCSV
	//funcion para escribir UN SOLO OBJETO en csv  (lo pongo por respeto pero no sirve pa mucho)
	public void escribePokemonCSV(Pokemon pk, String ruta) {
		PrintWriter out = null;
		try {
			FileWriter f = new FileWriter(ruta);
			out = new PrintWriter(f);
			//f.write(csv);
			out.printf(Locale.US,"id, nombre, tipo, altura, peso, habilidad, evoluciona_a %n%d, %s, %s, %f, %f, %s, %s"
					,pk.getId()
					,pk.getNombre()
					,pk.getTipo()
					,pk.getAltura()
					,pk.getPeso()
					,pk.getHabilidadPrincipal()
					,pk.getEvoluciona_a());

		} catch (Exception e) {
			System.out.println("Error al leer pokemons"+e.getMessage());
			
		}finally{
			if (out!=null)
				out.close();
		}	
		
	}
	
	
	//escribir una lista en csv
	
	//esta funcion es para coger la mitad de los datos de un objeto y la mitad de otro, busca el objeto B asociado al A y escribe despues de eso
	public void escribeCSV(BancoAlimentos repo,List<Trabajador> ts, String ruta) {
		PrintWriter out = null;	
		CentroLogistico c = null;		//auxiliar para guardar objeto b asociado
		try {
			FileWriter f = new FileWriter(ruta);
			out = new PrintWriter(f);
			//f.write(csv);
			out.printf(Locale.US,"nombreV, dniV, fechaNacimiento, NombreC, identificadorC, CiudadC %n");	//escribe el titulo del csv
			for (Trabajador t : ts) {																		//por cada objeto A de la lista
				for (CentroLogistico centro : repo.getCentros()){											//busca en el repositorio el objeto b asociado
					if(centro.getId().equals(t.getIdCentro())) {											//en caso de no necesitar dos objetos borrar segundo for
						c=centro;
					}
				}
				out.printf(Locale.US, "%s, %s, %s, %s, %s, %s%n"		//escribe cada uno de la lista definiendo su valor
						,t.getNombre()									// %s String
						,t.getDni()										// %d int
						,t.getFechaNac()								// %f float y double coma fija
						,c.getNombre()									// % un unmero para anchura minima y num de decimales (- alinea a la izq, 0 rellena con 0)
						,c.getId()										// %n salto de linea SIEMPRE UNO AL FINAL
						,c.getCiudad());
			}
			

		} catch (Exception e) {
			System.out.println("Error al leer "+e.getMessage());
			
		}finally{
			if (out!=null)
				out.close();
		}	
		
	}
	
	
	
	//JsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJsonJson
	//NO HAY QUE TOCAR NADA DE ESTA FUNCION PARA QUE ESCRIBA el tipo de lista si (cambiar el nombre del metodo estaria bien)
	public void escribeProductosJson(List<Producto> prod, String rutajson) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(prod);
		FileWriter writer = null;

		try {
			writer = new FileWriter(Rutares+rutajson);
			writer.write(json);

		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
					System.out.println("Error al escribir");
				}
			}
		}
	}
}
