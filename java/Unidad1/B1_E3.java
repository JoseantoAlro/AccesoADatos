package Unidad1;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class B1_E3 {
	
	private static final Logger logger = LogManager.getLogger(Ficheros.class);
	
	
	public static void main(String[]args) {
		
		/*
		String raiz= "C:\\";
		File carpeta = new File(raiz, "miDirectorio");
		if(carpeta.mkdir()) {
			logger.info("directorio creado");
		}else {
			logger.info("no se pudo crear el directorio");
		}
		
		File archivo = new File(raiz+"\\miDirectorio", "archivo.txt");
		try {
			boolean creado = archivo.createNewFile(); // Aquí Sí creo fichero
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
	B1_E3 a = new B1_E3();
	String ruta = "C:\\Users\\alumno\\desktop";
	File rutapadre = new File(ruta);
	a.getContenidoRecursivo(rutapadre);
}
	
	public void getContenidoRecursivo(File padre) {
		
		boolean existe= padre.isDirectory();
		
		if(existe) {
			File[] archivos = padre.listFiles();
			for(File f: archivos) {
			
				if(f.isFile()) {
					logger.info(f.getName()/*,f.canRead(), f.canWrite(), f.length()*/);
				}
				if(f.isDirectory()) {
					logger.info("--carpeta "+f.getName()+":--");
					this.getContenidoRecursivo(f);
					logger.info("--fin de carpeta--");
				}
			}	
		}else {	
			if(padre.isFile()) {
				logger.info(padre.getName());
			}else {
				logger.info("no es un directorio valido");
			}
		}
	}	
}
