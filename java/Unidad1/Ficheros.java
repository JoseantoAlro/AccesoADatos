package Unidad1;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ficheros {
	private static final Logger logger = LogManager.getLogger(Ficheros.class);

	public static void main(String[]args) {
		
		String rutaDirectorio = "C:\\Users\\alumno\\desktop";
		File directorio = new File(rutaDirectorio);
		// Referencio a un fichero dentro del directorio soraya
		// File fichero = new File(directorio, "fichero.txt"); para crear fichero
		File fichero = new File(directorio, "fichero");

		try {
			boolean creado = fichero.createNewFile(); // Aquí Sí creo fichero

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error al crear fichero:" + e.getMessage());
		}
		fichero.mkdir(); // Aquí Sí creo fichero

	}
	//listar

	
}
