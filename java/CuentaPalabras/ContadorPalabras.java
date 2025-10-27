package CuentaPalabras;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ContadorPalabras {
	
	
	public static int contar(String ruta, String palabra) throws FileNotFoundException  {
		int cont=0;
		Scanner in=null;
		try {
			FileReader f = new FileReader(ruta);
			in = new Scanner(f);
			while(in.hasNext()) {
				String palabraFich = in.next();
				if(palabraFich.equalsIgnoreCase(palabra)) {
					cont++;
				}
			}
		}finally{
			if (in != null) {
				in.close();
			}
			
		} 
		return cont;
	}
	
	public void escribir(String ruta,String mensaje)  throws FileNotFoundException  {
		PrintWriter out = null;
		
		try {
			FileWriter f = new FileWriter(ruta);
			out = new PrintWriter(f);
			out.printf(mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally		{
			if (out!=null)
				out.close();
		}
	}
}
