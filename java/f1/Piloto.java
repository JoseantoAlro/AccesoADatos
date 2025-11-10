package f1;

public class Piloto {
	private int id;
	private String nombre;
	private int puntos;
	private int idequipo;
	private String pais;
	public Piloto(int id, String nombre, int puntos, int idequipo, String pais) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.puntos = puntos;
		this.idequipo = idequipo;
		this.pais = pais;
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
	public int getIdequipo() {
		return idequipo;
	}
	public void setIdequipo(int idequipo) {
		this.idequipo = idequipo;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	@Override
	public String toString() {
		return "Piloto [id=" + id + ", nombre=" + nombre + ", puntos=" + puntos + ", idequipo=" + idequipo + ", pais="
				+ pais + "]";
	}
	
	
}
