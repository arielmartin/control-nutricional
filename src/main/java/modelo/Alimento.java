package modelo;

import javax.persistence.*;

@Entity
public class Alimento {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public String nombre;
	private int porcionEnGramos;
	public int caloriasPorPorcion;
	public String tipo;
	
	public Alimento() {}

	public Alimento(String nombre, int caloriasPorPorcion, String tipo) {
		super();
		this.nombre = nombre;
		this.caloriasPorPorcion = caloriasPorPorcion;
		this.tipo = tipo;
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

	public int getPorcionEnGramos() {
		return porcionEnGramos;
	}

	public void setPorcionEnGramos(int porcionEnGramos) {
		this.porcionEnGramos = porcionEnGramos;
	}

	public int getCaloriasPorPorcion() {
		return caloriasPorPorcion;
	}

	public void setCaloriasPorPorcion(int caloriasPorPorcion) {
		this.caloriasPorPorcion = caloriasPorPorcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	
}
