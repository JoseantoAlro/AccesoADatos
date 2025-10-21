package flujos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import accesoDatos.Main;

public class FlujosYStreams {
	private static final Logger logger = LogManager.getLogger(Main.class);
	
		public static void muestraContenidoFich(String rutaYNombre) throws FileNotFoundException {
			Scanner in = null;
			try {
				// abre el fichero
				FileReader fichero = new FileReader(rutaYNombre);
				//Se crea el flujo
				in = new Scanner(fichero);
				// lee el fichero
				in.useDelimiter(",");
				while (in.hasNext()) { //Lectura palabra a palabra
				logger.info(in.next()); 
				}
				
			} finally {
				if (in != null) {
					in.close();
				}
			}
		}
		public static void LeerCSV( String ruta) throws FileNotFoundException {
			Scanner in = null;
			Alumnos[] n = new Alumnos[0];
			try {
				FileReader fichero = new FileReader(ruta);
				
				in = new Scanner(fichero);
				
				while (in.hasNextLine()) {
					in.useDelimiter(",");
					
					while(in.hasNext()) {
						
					}
					
					
				}
				
				
			} finally{
				if (in != null) {
					in.close();
				}
			}
		}
public static void main(String[]args) throws FileNotFoundException {
	String n = "src\\main\\resources\\ficheroLectura.txt";
	muestraContenidoFich(n);
	
}

}
