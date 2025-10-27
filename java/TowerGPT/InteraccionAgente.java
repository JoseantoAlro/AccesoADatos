package TowerGPT;

import java.util.Objects;

public class InteraccionAgente {
	private int numValoracioesPositivas;
	private TipoAgente tAgente;
	private String identificador, peticion, respuesta;
	private double tiempoEjecucion, porcentajeAcierto;
	
	//constructor

	public InteraccionAgente(int numValoracioesPositivas, TipoAgente tAgente, String identificador, String peticion,
			String respuesta, double tiempoEjecucion, double porcentajeAcierto) {
		super();
		this.numValoracioesPositivas = numValoracioesPositivas;
		this.tAgente = tAgente;
		this.identificador = identificador;
		this.peticion = peticion;
		this.respuesta = respuesta;
		this.tiempoEjecucion = tiempoEjecucion;
		this.porcentajeAcierto = porcentajeAcierto;
	}
	
	//getters y setters

	public int getNumValoracioesPositivas() {
		return numValoracioesPositivas;
	}

	public void setNumValoracioesPositivas(int numValoracioesPositivas) {
		this.numValoracioesPositivas = numValoracioesPositivas;
	}

	public TipoAgente gettAgente() {
		return tAgente;
	}

	public void settAgente(TipoAgente tAgente) {
		this.tAgente = tAgente;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getPeticion() {
		return peticion;
	}

	public void setPeticion(String peticion) {
		this.peticion = peticion;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public double getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public void setTiempoEjecucion(double tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}

	public double getPorcentajeAcierto() {
		return porcentajeAcierto;
	}

	public void setPorcentajeAcierto(double porcentajeAcierto) {
		this.porcentajeAcierto = porcentajeAcierto;
	}
	
	
	//to string hashcode y equals
	@Override
	public String toString() {
		return "InteraccionAgente [numValoracioesPositivas=" + numValoracioesPositivas + ", tAgente=" + tAgente
				+ ", identificador=" + identificador + ", peticion=" + peticion + ", respuesta=" + respuesta
				+ ", tiempoEjecucion=" + tiempoEjecucion + ", porcentajeAcierto=" + porcentajeAcierto + "]";
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(identificador, numValoracioesPositivas, peticion, porcentajeAcierto, respuesta, tAgente,
				tiempoEjecucion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InteraccionAgente other = (InteraccionAgente) obj;
		return Objects.equals(identificador, other.identificador)
				&& numValoracioesPositivas == other.numValoracioesPositivas && Objects.equals(peticion, other.peticion)
				&& Double.doubleToLongBits(porcentajeAcierto) == Double.doubleToLongBits(other.porcentajeAcierto)
				&& Objects.equals(respuesta, other.respuesta) && tAgente == other.tAgente
				&& Double.doubleToLongBits(tiempoEjecucion) == Double.doubleToLongBits(other.tiempoEjecucion);
	}

	
	
	public void interaccionAgente() {

	}

}