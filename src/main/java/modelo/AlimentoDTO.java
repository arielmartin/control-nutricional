package modelo;

public class AlimentoDTO {

	private Alimento comida;
	private Alimento bebida;
	private int cantComida;
	private int cantBebida;
	private int totalCalorias;
	
	public AlimentoDTO() {}

	public Alimento getComida() {
		return comida;
	}

	public void setComida(Alimento comida) {
		this.comida = comida;
	}

	public Alimento getBebida() {
		return bebida;
	}

	public void setBebida(Alimento bebida) {
		this.bebida = bebida;
	}

	public int getCantComida() {
		return cantComida;
	}

	public void setCantComida(int cantComida) {
		this.cantComida = cantComida;
	}

	public int getCantBebida() {
		return cantBebida;
	}

	public void setCantBebida(int cantBebida) {
		this.cantBebida = cantBebida;
	}

	public int getTotalCalorias() {
		return totalCalorias;
	}

	public void setTotalCalorias(int totalCalorias) {
		this.totalCalorias = totalCalorias;
	}
	
}
