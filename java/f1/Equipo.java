package f1;

import java.util.List;

public class Equipo {
	private int id;
	private String nombre;
	private int puntos;
	private List<Piloto> pilotos;

	public Equipo(int id, String nombre, int puntos, List<Piloto> pilotos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.puntos = puntos;
		this.pilotos = pilotos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public List<Piloto> getPilotos() {
		return pilotos;
	}

	public void setPilotos(List<Piloto> pilotos) {
		this.pilotos = pilotos;
	}

	@Override
	public String toString() {
		return "Equipo [id=" + id + ", nombre=" + nombre + ", puntos=" + puntos + ", pilotos=" + pilotos + "]";
	}
	
	

}
