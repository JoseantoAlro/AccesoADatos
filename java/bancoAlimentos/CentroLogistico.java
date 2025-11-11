package bancoAlimentos;

import java.util.ArrayList;
import java.util.List;

import Excepciones.BancoException;

public class CentroLogistico {
	private String id;
	private String nombre;
	private String ciudad;
	private int comedores;
	private List<Trabajador> trabajadores = new ArrayList<Trabajador>();
	public CentroLogistico(String id, String nombre, String ciudad, int comedores, List<Trabajador> trabajadores) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.comedores = comedores;
		this.trabajadores = trabajadores;
	}
	
	
	public CentroLogistico() {
		super();
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getComedores() {
		return comedores;
	}
	public void setComedores(int comedores) {
		this.comedores = comedores;
	}
	public List<Trabajador> getTrabajadores() {
		return trabajadores;
	}
	public void setTrabajadores(List<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}
	@Override
	public String toString() {
		return "CentroLogistico [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", comedores=" + comedores
				+ ", trabajadores=" + trabajadores + "]";
	}
	
	public void agregarTrabajador(Trabajador t) throws BancoException{
		
		if(id.equals(t.getIdCentro())) {
			throw new BancoException("El centro con ID " + t.getIdCentro() + " ya existe"); 
		}
		t.setIdCentro(id);
		trabajadores.add(t);
	}
}
