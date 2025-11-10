package util;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import f1.Equipo;
import f1.GestionaClasificacion;
import f1.Piloto;

public class GestionaFicherosF1 {
	private static final Logger logger = LogManager.getLogger(GestionaFicherosF1.class);
	public Document getDocumentFromXML(String rutafichero) { // recibe fichero y lo devuelve //siempre igual
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

	public void escribePilotosJson(List<Piloto> pilotos, String rutajson) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(pilotos);
		FileWriter writer = null;

		try {
			writer = new FileWriter(rutajson);
			writer.write(json);

		} catch (Exception e) {
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
	
	
	
	
	
	public void escribeEquiposEnXML(String nombreFichero, List<Equipo> equipos) {
		try {
			Document documento = this.construyoObjetoDocumento("equipos");
			for(Equipo e : equipos)
			{
				Element elemento = this.creaElemento("equipo", null, documento.getDocumentElement(), documento);
				agregaEquipoADocumento(documento, elemento, e);
			}
			escribeDocumentoEnFichero(documento, nombreFichero);
		} catch (ParserConfigurationException e1) {
			logger.error(e1.getMessage());
		} catch (TransformerException e1) {
			logger.error(e1.getMessage());
		}
	}


	
	private void escribeDocumentoEnFichero(Document documento, String nombreFichero) throws TransformerException {
		// clases necesarias finalizar la creación del archivo XML
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		transformer = transformerFactory.newTransformer();
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // indentación de 4 espacios

	    
	    
		DOMSource source = new DOMSource(documento);
		StreamResult resultado = new StreamResult(new File(nombreFichero));
		transformer.transform(source, resultado);
	} 

	private void agregaEquipoADocumento(Document documento,Element padre,  Equipo e) {
		// Para cada una de los atributos de equipo, creo un elemento hijo
		List<Piloto> listap = e.getPilotos();
		Element nombre = this.creaElemento("nombreEquipo", e.getNombre(), padre, documento);								//por que declararlo como elemento????
		Element edad = this.creaElemento("puntos", Integer.toString(e.getPuntos()), padre, documento);
		Element pilotos = this.creaElemento("pilotos", null, padre, documento);
		
		
		for (Piloto p : listap) {
			Element piloto = this.creaElemento("piloto", null, pilotos, documento);
			Element nombrePiloto = this.creaElemento("nombrePiloto", p.getNombre(), piloto, documento);
			Element puntosPiloto = this.creaElemento("puntos", Integer.toString(p.getPuntos()), piloto, documento);
			Element idEquipo = this.creaElemento("identificadorEquipo", String.valueOf(p.getIdequipo()), piloto, documento);
			Element pais =  this.creaElemento("pais", p.getPais(), piloto, documento);
			piloto.setAttribute("identificador", p.getId());
		}
		
     
		// El identificador lo vamos a crear como un atributo de la etiqueta empleado
		padre.setAttribute("identificador", e.getId());
	}

	
	
	
	private Element creaElemento(String nombreElemento, String valorElemento, Element padre, Document documento) {
	    Element elemento = documento.createElement(nombreElemento);
	    if (valorElemento != null) {
	        Text texto = documento.createTextNode(valorElemento);
	        elemento.appendChild(texto);
	    }
	    padre.appendChild(elemento);
	    return elemento;
	}
	
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


	
	
}