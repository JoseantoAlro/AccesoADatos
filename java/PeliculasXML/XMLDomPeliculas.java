package PeliculasXML;

import java.io.File;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;


public class XMLDomPeliculas {
	private String fichero;
	String rutaResources="src\\main\\resources\\";
	
	public XMLDomPeliculas(String fichero) {
		super();
		this.fichero = fichero;
	}

	

	public String getFichero() {
		return fichero;
	}



	public void setFichero(String fichero) {
		this.fichero = fichero;
	}



	public Document getDocumentFromXML(String nombrefichero) {		//recibe fichero y lo devuelve //siempre igual
		File file = new File(rutaResources + nombrefichero);
		Document documento = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			documento = dBuilder.parse(file);

		} catch (Exception e) {
			//logger.error(e.getMessage());
		}
		return documento;
	}
	
	
	

}

