package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Clase que modela el concepto de Usuario, la anotacion @Entity le avisa a hibernate que esta clase es persistible
// el paquete ar.edu.unlam.tallerweb1.modelo esta indicado en el archivo hibernateCOntext.xml para que hibernate
// busque entities en Ã©l
@Entity
public class Usuario {

	// La anotacion id indica que este atributo es el utilizado como clave primaria de la entity, se indica que el valor es autogenerado.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// para el resto de los atributo no se usan anotaciones entonces se usa el default de hibernate: la columna se llama igual que
	// el atributo, la misma admite nulos, y el tipo de dato se deduce del tipo de dato de java.
	private String email;
	private String password;
	private String rol;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public int getEdad() {

		String sDate=this.fechaNacimiento;
		
		//Convertimos el String en LocalDate
		LocalDate fechaNac = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("dd/MM/yyyy") );
		//Obtenemos la fecha actual y en formato LocalDate
		LocalDate fechaHoy = LocalDate.now();
		
		 //ahora comparar los años de las dos fechas y asi obtener la edad
		
	    int diffYear = fechaHoy.getYear() - fechaNac.getYear();
	    int diffMonth = fechaHoy.getMonthValue() -fechaHoy.getMonthValue();
	    int diffDay = fechaHoy.getDayOfMonth() - fechaNac.getDayOfMonth();
	    
	    // Si está en ese año pero todavía no los ha cumplido se resta 1
	    if (diffMonth < 0 || (diffMonth == 0 && diffDay < 0)) {
	        diffYear = diffYear - 1;
	    }
		
		 return diffYear;

	}
	
}
