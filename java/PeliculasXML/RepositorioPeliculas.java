package PeliculasXML;

import java.util.List;

public class RepositorioPeliculas {
	private List<Pelicula> repositorio;

	public RepositorioPeliculas(List<Pelicula> repositorio) {
		super();
		this.repositorio = repositorio;
	}

	public RepositorioPeliculas() {
		super();
	}

	public void add(Pelicula p) {
		repositorio.add(p);
	}

	public List<Pelicula> getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(List<Pelicula> repositorio) {
		this.repositorio = repositorio;
	}

	
}
