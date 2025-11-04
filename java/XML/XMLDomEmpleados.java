package XML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLDomEmpleados {
	String rutaResources="src\\main\\resources\\";
	
	
	private Document getDocumentFromXML(String nombrefichero) {		//recibe fichero y lo devuelve //siempre igual
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

	
	
	private Empleado getEmpleadoFromElement(Element elemento) {		//varia segun modelo //lee solo un empleado
		Empleado e = new Empleado();
		String nombre = elemento.getElementsByTagName("nombreApellido").item(0).getTextContent();
		int edad = Integer.parseInt(elemento.getElementsByTagName("edad").item(0).getTextContent());
		String empresa = elemento.getElementsByTagName("empresa").item(0).getTextContent();
		String id = elemento.getAttribute("identificador"); // La etiqueta empleado tiene el atributo identificador
		e.setEdad(edad);
		e.setNombreApellido(nombre);
		e.setIdentificador(id);
		e.setEmpresa(empresa);
		return e;
	}
	
	
	public Empleado leerEmpleadoDesdeXML(String rutaFichero) throws Exception {  //no cambia //solo lee uno
	       Document doc = getDocumentFromXML(rutaFichero);
	       // Obtener el elemento raíz (el único <empleado>)
	       Element elementoEmpleado = doc.getDocumentElement();
	       // Usar tu método
	       return getEmpleadoFromElement(elementoEmpleado);
	   }
	
	
	public List<Empleado> leerEmpleadosDesdeXML(String rutaFichero) throws Exception {
		List<Empleado> empleados = new ArrayList<Empleado>();
		// 1. Calcula el dom
		Document doc = getDocumentFromXML(rutaFichero);
		// 2. Obtener todos los nodos con etiqueta empleados
		NodeList nodosEmpleados = doc.getElementsByTagName("empleado");			//devuelve una lista de nodos 
 // 3. Recorro la lista de los nodos empleado
		for (int j = 0; j < nodosEmpleados.getLength(); j++) {					//por cada nodo
			Node modeloNodo = nodosEmpleados.item(j);
			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {				//hace que funcione el casting (cambio de tipo)
				Empleado e = this.getEmpleadoFromElement((Element) modeloNodo);	//cambia al tipo elemeto
				empleados.add(e);
			}	
		}
		return empleados;														//devuelve una lista de elementos
	}


}
