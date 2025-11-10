package productos;

import java.io.FileWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class GestionaFicheros {
	private static final String ruta = "src\\main\\resources\\";
	
	public void escribeProductosJson(List<Producto> prod, String rutajson) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(prod);
		FileWriter writer = null;

		try {
			writer = new FileWriter(ruta+rutajson);
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
