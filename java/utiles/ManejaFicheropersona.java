package utiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import accesoDatos.Main;
import flujos.Alumnos;
import flujos.Nota;

public class ManejaFicheropersona {
	private static final Logger logger = LogManager.getLogger(Main.class);

	
	public static void LeerCSV( String ruta) throws FileNotFoundException {
		Scanner in = null;
		try {
			FileReader fichero = new FileReader(ruta);
			
			in = new Scanner(fichero);
			
			while (in.hasNextLine()) {				
				String linea = in.nextLine();	
				Alumnos p = cargarAlumno(linea);
				logger.info(p.toString());
			}
		} finally{
			if (in != null) {
				in.close();
			}
		}
	}
	
	
	public static Alumnos cargarAlumno(String linea) {
		String[] cadena = linea.split(",");
		List<Nota> lista = new ArrayList<Nota>();
		for(int i=1; i<cadena.length; i++) {
			Nota n = new Nota(Double.parseDouble(cadena[i]));
			lista.add(n);
		}
		Alumnos p = new Alumnos(cadena[0], lista);
		return p;
	}
}

