package Bol3_e2;

public class Pais {
private String nombre;
private String continente;
private int poblacion;
private String idioma;
private String moneda;

public Pais(String nombre, String continente, int poblacion, String idioma, String moneda) {
	super();
	this.nombre = nombre;
	this.continente = continente;
	this.poblacion = poblacion;
	this.idioma = idioma;
	this.moneda = moneda;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getContinente() {
	return continente;
}

public void setContinente(String continente) {
	this.continente = continente;
}

@Override
public String toString() {
	return "Pais [nombre=" + nombre + ", continente=" + continente + ", poblacion=" + poblacion + ", idioma=" + idioma
			+ ", moneda=" + moneda + "]";
}

public int getPoblacion() {
	return poblacion;
}

public void setPoblacion(int poblacion) {
	this.poblacion = poblacion;
}

public String getIdioma() {
	return idioma;
}

public void setIdioma(String idioma) {
	this.idioma = idioma;
}

public String getMoneda() {
	return moneda;
}

public void setMoneda(String moneda) {
	this.moneda = moneda;
}

}
