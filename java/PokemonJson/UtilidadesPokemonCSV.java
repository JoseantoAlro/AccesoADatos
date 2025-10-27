package PokemonJson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;




public class UtilidadesPokemonCSV {				//lee y escribe archivos csv		
	
	
	public List<Pokemon> leePokemonsFromCSV(String ruta) throws FileNotFoundException{
		Scanner in = null;
		List<Pokemon> plist = new ArrayList<Pokemon>();
		try {
			FileReader f = new FileReader(ruta); 

			in = new Scanner(f);
			//in.nextLine();
			while (in.hasNextLine()) {
				
				String linea = in.nextLine();
				Pokemon p = cargarPokemon(linea);
				plist.add(p);
			}
		} finally{
			if (in != null) {
				in.close();
			}
		}
		return plist;
	}
	
	public Pokemon cargarPokemon(String linea) { //coge la linea y la convierte a pais
		String[] cadena = linea.split(",");
		Pokemon p = new Pokemon(Integer.parseInt(cadena[0]), cadena[1], cadena[2], Float.parseFloat(cadena[3]), Float.parseFloat(cadena[4]) , cadena[5], cadena[6]);
		return p;
	}
	
	
	
	
	public void escribePokemonCSV(Pokemon pk, String ruta) {
		PrintWriter out = null;
		try {
			FileWriter f = new FileWriter(ruta);
			out = new PrintWriter(f);
			//f.write(csv);
			out.printf(Locale.US,"id, nombre, tipo, altura, peso, habilidad, evoluciona_a %n%d, %s, %s, %f, %f, %s, %s",pk.getId(),pk.getNombre(),pk.getTipo(),pk.getAltura(),pk.getPeso(),pk.getHabilidadPrincipal(),pk.getEvoluciona_a());

		} catch (Exception e) {
			System.out.println("Error al leer pokemons"+e.getMessage());
			
		}finally{
			if (out!=null)
				out.close();
		}	
		
	}
}
