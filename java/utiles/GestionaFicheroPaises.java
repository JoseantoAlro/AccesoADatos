package utiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Bol3_e2.Pais;
import Bol3_e2.RepositorioPaises;

public class GestionaFicheroPaises {
	private static final Logger logger = LogManager.getLogger();
	
	
	public static Set<Pais> LeerCSV(String ruta) throws FileNotFoundException {  //coge el fichero y por cada linea llama a cargarpais()
		Scanner in = null;
		Set <Pais> rp = new HashSet<>();

		try {
			FileReader fichero = new FileReader(ruta); 

			in = new Scanner(fichero);
	
			while (in.hasNextLine()) {
				
				String linea = in.nextLine();
				Pais p = cargarPais(linea);
				rp.add(p);
			}
		} finally{
			if (in != null) {
				in.close();
			}
		}
		return rp;
	}
	
	
	public static Pais cargarPais(String linea) { //coge la linea y la convierte a pais
		String[] cadena = linea.split(",");
		Pais p = new Pais(cadena[0], cadena[1],Integer.parseInt(cadena[2]), cadena[3],cadena[4]);
		return p;
	}
}