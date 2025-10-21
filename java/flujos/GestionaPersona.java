package flujos;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import utiles.ManejaFicheropersona;

public class GestionaPersona {
		 
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Nota> listaNotas = new ArrayList<Nota>();
		ManejaFicheropersona p = new ManejaFicheropersona();
		p.cargarAlumno("\\src\\main\\java\\tema1\\fichero1.txt");

	}
}
