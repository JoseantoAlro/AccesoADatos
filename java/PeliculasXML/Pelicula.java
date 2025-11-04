package PeliculasXML;

import java.util.List;

public class Pelicula {
	private String titulo;
	private int duracion;
	private String genero;
	private String sinopsis;
	private List<String> actores;
	private int fecha;
	private String director;
	
	public Pelicula(String titulo, int duracion, String genero, String sinopsis, List<String> actores, int fecha,
			String director) {
		super();
		this.titulo = titulo;
		this.duracion = duracion;
		this.genero = genero;
		this.sinopsis = sinopsis;
		this.actores = actores;
		this.fecha = fecha;
		this.director = director;
	}
	

	public Pelicula() {
		super();
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public List<String> getActores() {
		return actores;
	}

	public void setActores(List<String> actores) {
		this.actores = actores;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", duracion=" + duracion + ", genero=" + genero + ", sinopsis=" + sinopsis
				+ ", actores=" + actores + ", fecha=" + fecha + ", director=" + director + "]";
	}
	
	
	
	
}
