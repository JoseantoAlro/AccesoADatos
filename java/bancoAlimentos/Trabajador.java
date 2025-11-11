package bancoAlimentos;

public class Trabajador {
	private String nombre;
	private String dni;
	private String fechaNac;
	private Personal pers;
	private String idCentro;
	
	public Trabajador(String nombre, String dni, String fechaNac, Personal pers, String idCentro) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.fechaNac = fechaNac;
		this.pers = pers;
		this.idCentro = idCentro;
	}
	

	public Trabajador() {
		super();
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Personal getPers() {
		return pers;
	}

	public void setPers(Personal pers) {
		this.pers = pers;
	}

	public String getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(String idCentro) {
		this.idCentro = idCentro;
	}

	@Override
	public String toString() {
		return "Trabajador [nombre=" + nombre + ", dni=" + dni + ", fechaNac=" + fechaNac + ", pers=" + pers
				+ ", idCentro=" + idCentro + "]";
	}
	
	
	
}
