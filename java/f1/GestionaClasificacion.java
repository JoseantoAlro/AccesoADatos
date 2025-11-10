package f1;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class GestionaClasificacion {
	private static final Logger logger = LogManager.getLogger(GestionaClasificacion.class);

	public static void main(String[] args) {
		
		ServicioF1 serv = new ServicioF1("formula1.xml");

		
		try {
			
			List<Piloto> listaPilotos= serv.leerPilotosDesdeXML();
			
			logger.info(listaPilotos);	
			logger.info(serv.getRepo().getEquipos());	
			
			serv.getRepo().cargarPilotosaEquipo(listaPilotos);
			logger.info(serv.getRepo().getEquipos());	
		
			
			logger.info(serv.getPilotosConMasPuntosQue(4));
			
			serv.escribirPilotos(serv.getPilotosConMasPuntosQue(4));
			
			serv.escribirXML("formula1Final.xml");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
