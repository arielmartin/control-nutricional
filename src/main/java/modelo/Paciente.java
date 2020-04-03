package modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private Long idUsuario;
	private String nombre;
	private Double peso;
	private Double altura;
	private String sexo;
	private int edad;
	private int ejercicio;
	private String fecha_inicio;
	private Long medicoAsociado_id;
	private Long planAsociado_id;
		
	public Long getMedicoAsociado_id() {
		return medicoAsociado_id;
	}

	public void setMedicoAsociado_id(Long medicoAsociado_id) {
		this.medicoAsociado_id = medicoAsociado_id;
	}

	public Paciente() {
	}
	
	public Paciente(Double peso, Double altura) {
		this.peso = peso;
		this.altura = altura;
	}
		
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(int ejercicio) {
		this.ejercicio = ejercicio;
	}
	
	public Double calcularImc(){
		Formula formula= new Formula();
		return formula.calcularIMC(this.peso, this.altura);
	}

	public Long getPlanAsociado_id() {
		return planAsociado_id;
	}

	public void setPlanAsociado_id(Long planAsociado_id) {
		this.planAsociado_id = planAsociado_id;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	
}
