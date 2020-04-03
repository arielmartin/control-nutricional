package modelo;

	public class PacienteDTO {
	private Paciente paciente;
	private Usuario usuario;
	private Plan plan;
	private String intensidad;
	private boolean excluirCarne;
	private boolean excluirLacteos;
	private boolean aptoHipertenso;
	private boolean aptoCeliaco;
	private String [] alimentosExcluidos;
	private String [] enfermedadesPadecidas;
	private Double IMC;
	private Double pesoIdeal;
	private int edad;
	
	public Double getIMC() {
		Double altura=(this.getPaciente().getAltura() /100);
		Double peso=this.paciente.getPeso();
		Double IMC =peso / (altura*altura);
		this.IMC=IMC;
		return IMC;
	}
	public void setIMC(Double peso,Double altura) {
		altura=(this.getPaciente().getAltura() / 100d);
		peso=this.paciente.getPeso();
		this.IMC =peso / (altura*altura);
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public Double getPesoIdeal() {
		return pesoIdeal;
	}
	public void setPesoIdeal(Double pesoIdeal) {
		this.pesoIdeal = pesoIdeal;
	}


}
