package util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import bancoAlimentos.BancoAlimentos;
import bancoAlimentos.CentroLogistico;
import bancoAlimentos.Trabajador;

public class GestionaFicherosAlimentos {

	
	public Document getDocumentFromXML(String rutafichero) { // recibe fichero XML y lo devuelve //siempre igual
		File file = new File(rutafichero);
		Document documento = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			documento = dBuilder.parse(file);

		} catch (Exception e) {
			// logger.error(e.getMessage());
		}
		return documento;
	}
	
	
	public void escribeCSV(BancoAlimentos repo,List<Trabajador> ts, String ruta) {
		PrintWriter out = null;
		CentroLogistico c = null;
		try {
			FileWriter f = new FileWriter(ruta);
			out = new PrintWriter(f);
			//f.write(csv);
			out.printf(Locale.US,"nombreV, dniV, fechaNacimiento, NombreC, identificadorC, CiudadC %n");
			for (Trabajador t : ts) {
				for (CentroLogistico centro : repo.getCentros()){
					if(centro.getId().equals(t.getIdCentro())) {
						c=centro;
					}
				}
				out.printf(Locale.US, "%s, %s, %s, %s, %s, %s%n"
						,t.getNombre()
						,t.getDni()
						,t.getFechaNac()
						,c.getNombre()
						,c.getId()
						,c.getCiudad());
			}
			

		} catch (Exception e) {
			System.out.println("Error al leer "+e.getMessage());
			
		}finally{
			if (out!=null)
				out.close();
		}	
		
	}
	
}
