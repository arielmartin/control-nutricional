package modelo;

public class CompararProgresoDTO {
	private String fecha;
	private Float pesoIdeal;
	private Float pesoRegistrado;
	
	public CompararProgresoDTO() {
		
	}
	
	public CompararProgresoDTO(String fecha, Float pesoIdeal, Float pesoRegistrado) {
		this.fecha = fecha;
		this.pesoIdeal = pesoIdeal;
		this.pesoRegistrado = pesoRegistrado;
	}

	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Float getPesoIdeal() {
		return pesoIdeal;
	}
	public void setPesoIdeal(Float pesoIdeal) {
		this.pesoIdeal = pesoIdeal;
	}
	public Float getPesoRegistrado() {
		return pesoRegistrado;
	}
	public void setPesoRegistrado(Float pesoRegistrado) {
		this.pesoRegistrado = pesoRegistrado;
	}

}
