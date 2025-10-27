package TowerGPT;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ServicioEstadisticaImpl {
	private RepositorioInteracciones repo;
	
	List<InteraccionAgente> inters = repo.getInteracciones();
	public InteraccionAgente obtenerInteraccionConMejorValoracion() { 
		// coge el primero y lo compara con el sig, si es mejor reemplaza la
		// muestra si no coge el sig
		
		InteraccionAgente superior = inters.get(0);
		for (InteraccionAgente i : inters) {
			if (i.getNumValoracionesPositivas() > superior.getNumValoracionesPositivas()) {
				superior = i;
			}
		}
		return superior;
	}

	public double calcularTiempoMedioPorTipo(TipoAgente t) {
		// calcula media tiempo del tipo agente solicitado

		List<InteraccionAgente> inters = repo.getInteracciones();
		int cont = 0;
		double sum = 0;

		for (InteraccionAgente i : inters) {
			if (i.getAgente() == t) {
				cont++;
				sum += i.getTiempoEjecucion();
			} else {

			}
		}
		return (sum / cont);
	}

	public double calcularPorcentajeAciertoMedioPorTipo(TipoAgente t) {
		// calcula media porcentaje del tipo agente solicitado

		List<InteraccionAgente> inters = repo.getInteracciones();
		int cont = 0;
		double sum = 0;
		for (InteraccionAgente i : inters) {
			if (i.getAgente() == t) {
				cont++;
				sum += i.getPorcentajeAcierto();
			} else {

			}
		}
		return (sum / cont);
	}

	public String obtenerInteraccionesAciertoMayorQueOrdenadas(int umbral) {
		// recoge las que superan el umbral y las ordena segun su tipo

		List<InteraccionAgente> inters = repo.getInteracciones();
		List<InteraccionAgente> resH = new ArrayList<>();
		List<InteraccionAgente> resIA = new ArrayList<>();

		for (InteraccionAgente i : inters) {
			if (i.getAgente() == TipoAgente.Humano) {
				if (i.getPorcentajeAcierto() > umbral) {
					resH.add(i);
				}
			} else {
				if (i.getPorcentajeAcierto() > umbral) {
					resIA.add(i);
				}
			}
		}
		return resH.toString() + resIA.toString();
	}

	public Map<TipoAgente, InteraccionAgente> agruparInteraccionesPorTipo() {
		// recorre interracciones y segun el tipo añade al mapa con el key humano o ia

		List<InteraccionAgente> inters = repo.getInteracciones();
		Map<TipoAgente, InteraccionAgente> M = new HashMap<>();
		for (InteraccionAgente i : inters) {
			if (i.getAgente() == TipoAgente.Humano) {

				M.put(TipoAgente.Humano, i);
			} else {
				M.put(TipoAgente.IA, i);
			}
		}
		return M;
	}
	
	public void grabarFicheroCSV(String ruta) {
			List<InteraccionAgente> inters = repo.getInteracciones();

			PrintWriter out = null;
			try {
				FileWriter f = new FileWriter(ruta);
				out = new PrintWriter(f);
				out.printf(Locale.US,"numValoracioesPositivas, Agente, identificador, peticion,"
						+ "		 respuesta, tiempoEjecucion, porcentajeAcierto");
				for(InteraccionAgente i : inters) {
					out.printf(Locale.US,"%n%d %s %s %s %s %4.2f %4.2f"
						+i.getNumValoracionesPositivas()
						,i.getAgente()
						,i.getIdentificador()
						,i.getPeticion()
						,i.getRespuesta()
						,i.getTiempoEjecucion()
						,i.getPorcentajeAcierto()
						);
				}
			} catch (Exception e) {
				System.out.println("Error al leer interaccion"+e.getMessage());
				
			}finally{
				if (out!=null)
					out.close();
			}	
			
		}
	public void grabarResumenEstadisticas(String ruta) {
		List<InteraccionAgente> inters = repo.getInteracciones();
		PrintWriter out = null;
		Map<TipoAgente, InteraccionAgente> m = repo.agruparInteraccionesPorTipo();
		int contH=0;
		int contIA=0;
		for (InteraccionAgente i : m.values()) {
			if (i.getAgente()== TipoAgente.Humano) { 
				contH++;
			}else {
				contIA++;
			}
		}

		try {
			FileWriter f = new FileWriter(ruta);
			out = new PrintWriter(f);
			out.printf("RESUMEN DE INTERACCIONES:\r\n"
					+ "-------------------------------------------------------------------------------------------------------------\r\n"
					+ "Se han efectuado un total de ",inters.size()," interacciones:\r\n"
					+ "Las interacciones que han tomado más tiempo han sido las efectuadas por Humanos con un tiempo medio de ",repo.calcularTiempoMedioPorTipo(TipoAgente.Humano)," segundos.\r\n"
					+ "De todas las interacciones:\r\n"
					+ "- ",contH," han sido efectuadas por Humanos con una tasa de acierto del ",repo.calcularPorcentajeAciertoMedioPorTipo(TipoAgente.Humano),"\r\n"
					+ "- ",contIA," han sido efectuadas por IAs con  una tasa de acierto del",repo.calcularPorcentajeAciertoMedioPorTipo(TipoAgente.IA),"\r\n"
					+ "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally		{
			if (out!=null)
				out.close();
		}
	}
}
