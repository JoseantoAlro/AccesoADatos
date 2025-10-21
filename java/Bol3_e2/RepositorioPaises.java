package Bol3_e2;

import java.util.Set;

public class RepositorioPaises {
	private Set<Pais> pais;


	public RepositorioPaises() {
		super();
	}
	public RepositorioPaises(Set<Pais> pais) {
		this.pais = pais;
	}



	public Set<Pais> getPais() {
		return pais;
	}



	public void setPais(Set<Pais> pais) {
		this.pais = pais;
	}



	@Override
	public String toString() {
		return "RepositorioPaises [pais=" + pais + "]";
	}



}
