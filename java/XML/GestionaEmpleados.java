package XML;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class GestionaEmpleados {
	private static final Logger logger = LogManager.getLogger(GestionaEmpleados.class); 
	public static void main(String[]args) throws Exception {
		XMLDomEmpleados xmlEmp = new XMLDomEmpleados();
		Empleado e = xmlEmp.leerEmpleadoDesdeXML("empleado.xml");
		logger.info(e.toString());
	}
}
