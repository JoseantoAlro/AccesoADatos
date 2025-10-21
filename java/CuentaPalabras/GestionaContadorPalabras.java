package CuentaPalabras;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GestionaContadorPalabras {
	private static final Logger logger = LogManager.getLogger(GestionaContadorPalabras.class); 
	public static void main(String[] args) throws FileNotFoundException{
		
		String ruta="src\\main\\resources\\fichero.txt";
		String rutafin="src\\main\\resources\\ficherofin.txt";
		ContadorPalabras p;
		try {
			p = new ContadorPalabras();
			logger.info(p.contar(ruta, "es"));
			String m ="numero: %n"+ p.contar(ruta, "es"); //mirar apuntes
			p.escribir(rutafin,m );
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
