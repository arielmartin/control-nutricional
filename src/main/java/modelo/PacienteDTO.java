package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PacienteDTO {
	
	private Paciente paciente;
	private Usuario usuario;
	private String pass;
	private String objetivo;
	private Plan plan;
	private String intensidad;
	private boolean excluirCarne;
	private boolean excluirLacteos;
	private boolean aptoHipertenso;
	private boolean aptoCeliaco;
	private String [] alimentosExcluidos;
	private String [] enfermedadesPadecidas;
	private Double pesoIdeal;
	private String fnac;
	private Double IMC;
	private int edad;
	
	
//	public PacienteDTO() {
//		
//		Double altura=(this.getPaciente().getAltura() / 100d);
//		Double peso=this.paciente.getPeso();
//		this.IMC = peso / (altura*altura);
//		
//		String sDate=this.fnac;
//		
//		//Convertimos el String en LocalDate
//		LocalDate fechaNac = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("dd/MM/yyyy") );
//		//Obtenemos la fecha actual y en formato LocalDate
//		LocalDate fechaHoy = LocalDate.now();
//		
//		 //ahora comparar los años de las dos fechas y asi obtener la edad
//		
//	    int diffYear = fechaHoy.getYear() - fechaNac.getYear();
//	    int diffMonth = fechaHoy.getMonthValue() -fechaHoy.getMonthValue();
//	    int diffDay = fechaHoy.getDayOfMonth() - fechaNac.getDayOfMonth();
//	    
//	    // Si está en ese año pero todavía no los ha cumplido se resta 1
//	    if (diffMonth < 0 || (diffMonth == 0 && diffDay < 0)) {
//	        diffYear = diffYear - 1;
//	    }
//		
//	    this.edad = diffYear;
//	}
	
	
	public Double getIMC() {
		Double altura=(this.getPaciente().getAltura() /100);
		Double peso=this.paciente.getPeso();
		Double IMC =peso / (altura*altura);
		this.IMC=IMC;
		return this.IMC;
	}

	
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public String getIntensidad() {
		return intensidad;
	}
	public void setIntensidad(String intensidad) {
		this.intensidad = intensidad;
	}
	public boolean isExcluirCarne() {
		return excluirCarne;
	}
	public void setExcluirCarne(boolean excluirCarne) {
		this.excluirCarne = excluirCarne;
	}
	public boolean isExcluirLacteos() {
		return excluirLacteos;
	}
	public void setExcluirLacteos(boolean excluirLacteos) {
		this.excluirLacteos = excluirLacteos;
	}
	public boolean isAptoHipertenso() {
		return aptoHipertenso;
	}
	public void setAptoHipertenso(boolean aptoHipertenso) {
		this.aptoHipertenso = aptoHipertenso;
	}
	public boolean isAptoCeliaco() {
		return aptoCeliaco;
	}
	public void setAptoCeliaco(boolean aptoCeliaco) {
		this.aptoCeliaco = aptoCeliaco;
	}
	public String[] getAlimentosExcluidos() {
		return alimentosExcluidos;
	}
	public void setAlimentosExcluidos(String[] alimentosExcluidos) {
		this.alimentosExcluidos = alimentosExcluidos;
	}
	public String[] getEnfermedadesPadecidas() {
		return enfermedadesPadecidas;
	}
	public void setEnfermedadesPadecidas(String[] enfermedadesPadecidas) {
		this.enfermedadesPadecidas = enfermedadesPadecidas;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public Double getPesoIdeal() {
		return pesoIdeal;
	}
	public void setPesoIdeal(Double pesoIdeal) {
		this.pesoIdeal = pesoIdeal;
	}
	public String getFnac() {
		return fnac;
	}
	public void setFnac(String fnac) {
		this.fnac = fnac;
	}
	public int getEdad() {
		//int edad = getEgeByDate(this.fnac);
		return this.edad;
	}
	public void setEdad(String fnac) {
		int edad = getEgeByDate(fnac);
		this.edad = edad;
	}
	
	public int getEgeByDate(String sDate) {

		//String sDate=this.fechaNacimiento;
		
		//Convertimos el String en LocalDate
		//LocalDate fechaNac = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("dd/MM/yyyy") );
		LocalDate fechaNac = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("yyyy-MM-dd") );
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
