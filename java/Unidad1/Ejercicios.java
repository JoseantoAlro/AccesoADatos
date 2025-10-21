package Unidad1;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Ejercicios {
	private static final Logger logger = LogManager.getLogger(Ficheros.class);
	
	public static void main(String[]args) {
		
		String rutaDirectorio= "C:\\Users\\alumno\\desktop";
		
//1
		File f =  new File(rutaDirectorio);
		/*try {
			File f =  new File(rutaDirectorio);
		}catch (IOException e){
			logger.error("Error al crear fichero:" + e.getMessage());			
		}*/
		
		
		if(f.exists()) {
			logger.info("la ruta existe y es: "+f);
		}else {
			 
		}
		
		
		if(f.exists()&& f.isDirectory()) {
			logger.info("la ruta existe y es: "+f);
			String[] archivo = f.list(); 
			File[] archivos = f.listFiles();
			
			for(String f1:archivo) {
				logger.info("illo mira: "+f1);
			}
			for(File f1:archivos) {
			logger.info("illo mira: "+f1);
			}
		}
			
			//2
		String rutaDir = "C:\\Users\\alumno\\desktop";
		
		File d = new File(rutaDir);
		
		if(d.exists()) {
			if (d.isDirectory()) {
				logger.info("Es un directorio");
				String[] dir =d.list();
				String dirlist=" Contiene: ";
				
				for(String d1:dir) {
				dirlist= dirlist+ ", "+ d1;
				}
				logger.info(dirlist);
			}
			if (d.isFile()) {
				logger.info("Es un archivo");
			}
			logger.info("Con nombre "+ d.getName()+", con ruta absoluta "
					+d.getAbsolutePath()+ "y tamaño "+d.length()+ "bytes");

			

			if(d.canRead()) {
				logger.info("se puede leer");
			}
			if(d.canWrite()) {
				logger.info("se puede escribir");
			}
			
			
			
		}
		
		

	}
}
