package TowerGPT;

public class GestionaMainApp {

	private final static String directorioGenerarClasses = "target\\";
	private final static String rutaFicherosJava = "src\\main\\java\\TowerGPT\\MainApp.java";

	public static void main(String[] args) {
		GestionaMainApp p = new GestionaMainApp();
		p.Compila();
		p.Ejecuta();
	}

	public void Compila() {
		String[] comando = { "javac", "-d", directorioGenerarClasses, rutaFicherosJava };
		ProcessBuilder pb = new ProcessBuilder(comando);
		try {
			pb.redirectErrorStream(true);
			pb.inheritIO();

			pb.start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Ejecuta() {
		String[] comando = { "java", "-cp", directorioGenerarClasses, rutaFicherosJava};
		ProcessBuilder pb = new ProcessBuilder(comando);

		try {
			pb.redirectErrorStream(true);
			pb.inheritIO();

			pb.start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
