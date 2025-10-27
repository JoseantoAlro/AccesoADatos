package TowerGPT;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;


public class RepositorioInteracciones extends ServicioEstadisticaImpl {
	private List<InteraccionAgente> interacciones;

	// constructores getters y setters
	public RepositorioInteracciones(List<InteraccionAgente> interacciones) {
		super();
		this.setInteracciones(interacciones);
	}

	public List<InteraccionAgente> getInteracciones() {
		return interacciones;
	}

	public void setInteracciones(List<InteraccionAgente> interacciones) {
		this.interacciones = interacciones;
	}

	// aqui va el CRUD, lo demas a servicios.

	
	//Create repo desde json
	public RepositorioInteracciones cargarRegistrosDesdeJSON(String ruta) {
			 InteraccionAgente inters[] = null;
			 RepositorioInteracciones R = null;
			try {
				
				Gson gson = new Gson();
				FileReader fichero = new FileReader(ruta);
				
				
				inters = gson.fromJson(fichero, InteraccionAgente[].class);
				R = (RepositorioInteracciones) Arrays.asList(inters);
				} catch (Exception e) {
					System.out.println("Error al leer interacciones"+e.getMessage());
				}
			return R;
			}
		
		
	
	
	// create (o update no lo tengo claro)
	public void agregaInteraccionARegistro(InteraccionAgente interaccion) {
		if (interaccion != null) {
			interacciones.add(interaccion);
		} 
	}

	// update pero de un atributo de un objeto de la coleccion (no se si va aqui o
	// en services, imagino que aqui)
	public void actualizaPorcentajeInteraccion(InteraccionAgente interaccion, double nuevoPorcentaje) {
		for (InteraccionAgente i : interacciones) {
			if (i.equals(interaccion)) {
				interaccion.setPorcentajeAcierto(nuevoPorcentaje);
			}
		}
	}

	//update igual que el anterior
	public void incrementaNumeroValoraciones(String identificador) {
		for (InteraccionAgente i : interacciones) {
			if (i.getIdentificador().equals(identificador)) {
				i.setNumValoracionesPositivas(i.getNumValoracionesPositivas() + 1);
			}
		}
	}

	
	
	
	
}
