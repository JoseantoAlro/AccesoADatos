package bancoAlimentos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GestionaBancoAlimentos {
	private static final Logger logger = LogManager.getLogger(GestionaBancoAlimentos.class);

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		ServicioAlimentos serv = new ServicioAlimentos();

		logger.info(serv.getRepo().getCentros().toString());
		
		logger.info(serv.getTrabajadoresPorTipo(Personal.ASALARIADO));
		
		serv.escribirEnCSV(serv.getTrabajadoresPorTipo(Personal.VOLUNTARIO));
		
		

	}
}
