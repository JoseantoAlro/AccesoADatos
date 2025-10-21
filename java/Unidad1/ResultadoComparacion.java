package Unidad1;

public class ResultadoComparacion{
	
	private String nombre;
	private ValorComparacion valor;
	
	public ResultadoComparacion(String nombre,ValorComparacion valor) {
		this.nombre = nombre;
		this.valor = valor;
	}
	
	public String GetNomFichero() {

		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ValorComparacion getValorComparacion() {
		return valor;
	}

	@Override
	public String toString() {
		return "ResultadoComparacion [nombre=" + nombre + ", valor=" + valor + "]";
	}

	public void setValor(ValorComparacion valor) {
		this.valor = valor;
	}

	
	

}