package TowerGPT;

import java.util.Objects;

public class InteraccionAgente {
	private int numValoracionesPositivas;
	private TipoAgente Agente;
	private String identificador, peticion, respuesta;
	private double tiempoEjecucion, porcentajeAcierto;

	// constructor

	public InteraccionAgente(int numValoracioesPositivas, TipoAgente Agente, String identificador, String peticion,
			String respuesta, double tiempoEjecucion, double porcentajeAcierto) {
		super();
		this.numValoracionesPositivas = numValoracioesPositivas;
		this.Agente = Agente;
		this.peticion = peticion;
		this.respuesta = respuesta;
		this.tiempoEjecucion = tiempoEjecucion;
		this.porcentajeAcierto = porcentajeAcierto;
	}

	public InteraccionAgente(TipoAgente agente, String peticion, String respuesta) {
		super();
		Agente = agente;
		this.peticion = peticion;
		this.respuesta = respuesta;
	}

	public String calcularIdentificador() {  //identificador generado automaticamente
		int hash = Objects.hash(numValoracionesPositivas, peticion, porcentajeAcierto, respuesta, Agente,
				tiempoEjecucion);
		return String.valueOf(hash);
	}
	// getters y setters

	public int getNumValoracionesPositivas() {
		return numValoracionesPositivas;
	}

	public void setNumValoracionesPositivas(int numValoracioesPositivas) {
		this.numValoracionesPositivas = numValoracioesPositivas;
	}

	public TipoAgente getAgente() {
		return Agente;
	}

	public void setAgente(TipoAgente tAgente) {
		this.Agente = tAgente;
	}

	public String getIdentificador() {
		return this.calcularIdentificador();
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

	// to string y equals
	@Override
	public String toString() {
		return "InteraccionAgente [numValoracioesPositivas=" + numValoracionesPositivas + ", tAgente=" + Agente
				+ ", identificador=" + identificador + ", peticion=" + peticion + ", respuesta=" + respuesta
				+ ", tiempoEjecucion=" + tiempoEjecucion + ", porcentajeAcierto=" + porcentajeAcierto + "]";
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
				&& numValoracionesPositivas == other.numValoracionesPositivas
				&& Objects.equals(peticion, other.peticion)
				&& Double.doubleToLongBits(porcentajeAcierto) == Double.doubleToLongBits(other.porcentajeAcierto)
				&& Objects.equals(respuesta, other.respuesta) && Agente == other.Agente
				&& Double.doubleToLongBits(tiempoEjecucion) == Double.doubleToLongBits(other.tiempoEjecucion);
	}

}