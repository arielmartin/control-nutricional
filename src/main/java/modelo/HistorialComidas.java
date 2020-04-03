package modelo;

import javax.persistence.*;

@Entity
public class HistorialComidas {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private Long idPaciente;
	private Long IdAlimentoComida;
	private Long IdAlimentoBebida;
	private String comida;
	private String bebida;
	private int cantComida;
	private int cantBebida;
	private int totalCalorias;
	private String fecha;
	private String hora;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}
	public Long getIdAlimentoComida() {
		return IdAlimentoComida;
	}
	public void setIdAlimentoComida(Long idAlimentoComida) {
		IdAlimentoComida = idAlimentoComida;
	}
	public Long getIdAlimentoBebida() {
		return IdAlimentoBebida;
	}
	public void setIdAlimentoBebida(Long idAlimentoBebida) {
		IdAlimentoBebida = idAlimentoBebida;
	}
	public String getComida() {
		return comida;
	}
	public void setComida(String comida) {
		this.comida = comida;
	}
	public String getBebida() {
		return bebida;
	}
	public void setBebida(String bebida) {
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	
}
