package PokemonJson;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
	
public class UtilidadesJsonPokemons {			//lee y escribe archivos Json
	
	
	public Pokemon leePokemonFromJson(String ruta) {
		 Pokemon pkm = null;
		try {
			
			Gson gson = new Gson();
			FileReader fichero = new FileReader(ruta);
			// Leer el archivo JSON y convertirlo a un objeto pokemon
			pkm = gson.fromJson(fichero, Pokemon.class);
			
			} catch (Exception e) {
				System.out.println("Error al leer pokemons"+e.getMessage());
			}
		return pkm;
		}
	
	
	
	public void escribePokemonJson(List<Pokemon> pks, String rutajson) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(pks);
		FileWriter writer = null;

		try {
			writer = new FileWriter(rutajson);
			writer.write(json);

		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
					System.out.println("Error al escribir");
				}
			}
		}
	}
}