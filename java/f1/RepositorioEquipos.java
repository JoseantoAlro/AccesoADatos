package f1;

import java.util.List;

public class RepositorioEquipos {
	private List<Equipo> equipos;
	

	public RepositorioEquipos(List<Equipo> equipos) {
		super();
		this.equipos = equipos;
	}



	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}
	
	
	
	public void cargarPilotosaEquipo(List<Piloto> ps) {
		for (Equipo e : equipos) {
			for (Piloto p : ps) {
				if (Integer.parseInt(e.getId()) == p.getIdequipo()){
					e.addPiloto(p);
				}
			}
		}
	}	
	
}
