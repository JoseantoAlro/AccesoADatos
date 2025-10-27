package Bol3_e2;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import accesoDatos.Main;
import utiles.GestionaFicheroPaises;


public class GestionaPaises {
	private static final Logger logger = LogManager.getLogger(Main.class); 
	
	public static void main(String[]args) throws FileNotFoundException {
		String ruta="src\\main\\resources\\paises.csv";
		
		GestionaFicheroPaises p = new GestionaFicheroPaises();
		RepositorioPaises rp = new RepositorioPaises(p.LeerCSV(ruta));   //los devuelve como un set de clase Pais
		

		logger.info(rp.toString());

	}
}
