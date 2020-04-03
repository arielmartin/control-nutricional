package modelo;

public class ProgresoPesoIdeal {

	private String fecha;
	private float peso;
	
	public ProgresoPesoIdeal() {
		
	}
	
	public ProgresoPesoIdeal(String fecha, float peso) {
		this.fecha = fecha;
		this.peso = peso;
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
}
