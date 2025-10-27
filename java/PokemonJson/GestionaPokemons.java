package PokemonJson;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GestionaPokemons {  //Json a CSV
	private static final Logger logger = LogManager.getLogger(GestionaPokemons.class); 
	
	public static void main (String[] args) {
		
		String ruta = "src\\main\\resources\\Pokemons.json";
		String rutacsv = "src\\main\\resources\\Pokemons.csv";
		
		
		UtilidadesJsonPokemons p = new UtilidadesJsonPokemons();
		UtilidadesPokemonCSV pcsv = new UtilidadesPokemonCSV();
		Pokemon pokemon = p.leePokemonFromJson(ruta);
		logger.info(pokemon);
		pcsv.escribePokemonCSV(pokemon, rutacsv);
		
		rutacsv= "src\\main\\resources\\Pokemon1.csv";
		String rutaJson = "src\\main\\resources\\Pokemons1.json";
		
		try {
			List<Pokemon> pkms = pcsv.leePokemonsFromCSV(rutacsv);
			logger.info(pkms);
			p.escribePokemonJson(pkms, rutaJson);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
