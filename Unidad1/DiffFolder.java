package Unidad1;

import java.io.File;
import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import Exepciones.GestionFicherosException;

public class DiffFolder {

	
	
	private File[] dir1;
	private File[] dir2;
	
	
	Set<String> listaDir = new HashSet<>();
			
	public void setFolders(File file1, File file2) throws GestionFicherosException {
		if(file1.isDirectory()&&file2.isDirectory()) {
			dir1 = file1.listFiles();
			dir2 = file2.listFiles();
		}else {
			throw new GestionFicherosException("no son dos directorios");
		}
	}
	public File[] getFolder1() {
		return dir1;
	}
	public File[] getFolder2() {
		return dir2;
	}
	public ValorComparacion comparaFich(File f1,File f2) {
		ValorComparacion v = null;
		if (f1.getName().equals(f2.getName())){
			
			if(f1.lastModified()==f2.lastModified()) {
				logger.info("illo");
				v= ValorComparacion.IGUALES;
			}else if(f1.lastModified()>f2.lastModified()) {
				v = ValorComparacion.MENOS_NUEVO_EN_2;
			}else{
				v = ValorComparacion.MENOS_NUEVO_EN_1;
			}
		}
		return v;
	}
	
	
	private Set<ResultadoComparacion>comparaLista(File[] dir1, File[] dir2){

		Set<ResultadoComparacion> resultados = new HashSet<>();
		ValorComparacion v = null;
		
		//falta q empiece por la carpeta mas grande
		
		for (File f1 : dir1){
			for(File f2 : dir2) {
				v=comparaFich(f1,f2);
				if(v!=null) {
					resultados.add(new ResultadoComparacion(f1.getName(),v));
					break;
				}
			}
			if (v==null){
				resultados.add(new ResultadoComparacion(f1.getName(), ValorComparacion.FALTA_EN_2));
			}
			
		}
		
		
		
		for (File f2 : dir2){
			for(File f1 : dir1) {
				v=comparaFich(f2,f1);
			}
			if (v==null){
				resultados.add(new ResultadoComparacion(f2.getName(), ValorComparacion.FALTA_EN_1));
			}else if(v!=ValorComparacion.IGUALES){
				resultados.add(new ResultadoComparacion(f2.getName(),v));
			}else {
				
			}
		}
		return resultados;
	}
	
	
	 private static final Logger logger = Logger.getLogger(DiffFolder.class.getName());
	public static void main(String[]args) {   //esto seria el gestiona pero weno
		
		String rutaDir1 ="C:\\Users\\User\\Desktop\\ejemplo1";
		String rutaDir2="C:\\Users\\User\\Desktop\\ejemplo2";
		
		
		File file1 = new File(rutaDir1);
		File file2 = new File(rutaDir2);
		
		DiffFolder f = new DiffFolder();
		
		try {
			f.setFolders(file1,file2);
		} catch (GestionFicherosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Set<ResultadoComparacion> resultados = new HashSet<>(f.comparaLista(f.getFolder1(),f.getFolder2()));
		logger.info(resultados.toString());
		
		
		
	}
}
