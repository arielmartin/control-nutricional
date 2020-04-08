package modelo;

//import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RegistrarPesoDiarioDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fecha;
	private Float peso;
	private Long idPaciente;

	public RegistrarPesoDiarioDTO() {
		
	}
	
	public RegistrarPesoDiarioDTO(String fecha, Float peso, Long idPaciente) {
		this.fecha = fecha;
		this.peso = peso;
		this.idPaciente = idPaciente;
	}
	
public Long getId() {
		return id;
	}

public void setId(Long id) {
		this.id = id;
	}

public String getFecha() {
	return fecha;
}

public void setFecha(String fecha) {
	this.fecha = fecha;
}

public Float getPeso() {
	return peso;
}

public void setPeso(Float peso) {
	this.peso = peso;
}
public Long getIdPaciente() {
	return idPaciente;
}
public void setIdPaciente(Long idPaciente) {
	this.idPaciente = idPaciente;
}


}
