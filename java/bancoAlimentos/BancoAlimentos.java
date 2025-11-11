package bancoAlimentos;

import java.util.List;

import Excepciones.BancoException;

public class BancoAlimentos {
	private List<CentroLogistico> centros;

	public BancoAlimentos(List<CentroLogistico> centros) {
		super();
		this.centros = centros;
	}

	public BancoAlimentos() {
		super();
	}

	public List<CentroLogistico> getCentros() {
		return centros;
	}

	public void setCentros(List<CentroLogistico> centros) {
		this.centros = centros;
	}
	
	public void agregarCentro(CentroLogistico c) throws BancoException {
		for (CentroLogistico cs : centros) {
			if (cs.getId()== (c.getId())){
				throw new BancoException("El centro con ID " + c.getId() + " ya existe"); 
			}
		}
		centros.add(c);
	}
}

