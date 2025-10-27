package TowerGPT;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainApp {
	private static final Logger logger = LogManager.getLogger(MainApp.class);
	public static void main(String[] args) {
		String rutaJson="src\\main\\resources\\interacciones.json";
		String rutaCSV ="src\\main\\resources\\salidaOrdenada.csv";
		String rutafin ="src\\main\\resources\\resumenEstadistica.txt";
		
		
		//Crear un objeto Repositorio
		RepositorioInteracciones is = null; 	
		
		// Leer fichero interacciones.json y cargarlo en una colección de interacciones
		List<InteraccionAgente> i = new ArrayList<>(is.cargarRegistrosDesdeJSON(rutaJson).getInteracciones());
		is.cargarRegistrosDesdeJSON(rutaJson);
		
		//Devolver los datos de la interacción con mejor valoración
		logger.info(is.obtenerInteraccionConMejorValoracion());
		
		//Modificar una valoración y un porcentaje de acierto usando el método correspondiente.
		is.actualizaPorcentajeInteraccion(i.get(0), 75);
		is.incrementaNumeroValoraciones(i.get(0).getIdentificador());
		
		//Devolver los datos de la interacción con mayor porcentaje de acierto.
		logger.info(is.obtenerInteraccionConMejorValoracion().toString());
		
		//Grabar en un fichero llamado resumenEstadistica.txt el resumen de las estadísticas de las interacciones. 
		is.grabarResumenEstadisticas(rutafin);
		
		//Mostrar por pantallas las interacciones cuya valoración es superior a 70.0% ordenadas por % de acierto y luego por tipo.
		logger.info(is.obtenerInteraccionesAciertoMayorQueOrdenadas(70));
		
		// Genera un fichero llamado salidaOrdenada.csv con los datos de las interacciones grabadas en el registro ordenadas por identificador.
		is.grabarFicheroCSV(rutaCSV);
	}

}
