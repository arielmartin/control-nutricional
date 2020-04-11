package servicios;

import java.util.List;

import modelo.Paciente;
import modelo.PacienteDTO;
import modelo.Plan;

// Interface que define los metodos del Servicio de Pacientes.
public interface ServicioPacientes {

	Plan consultarPlan(Long id);

	void insertarPlanesIniciales();
	
	List<Plan> obtenerPlanesFiltrados(String intensidad, boolean aptoCeliaco, boolean aptoHipertenso, boolean sinCarne, boolean sinLacteos);

	Plan generarPlanSugerido(PacienteDTO pacienteDTO);
	
	void savePaciente(Paciente paciente);

	Paciente obtenerPaciente(Long id);
	
	List<Paciente> obtenerListadoPacientes();

	Long getIdPlanByIdPaciente(Long idPaciente);

	public void cargarPacientesIniciales();

	Paciente getPacienteByIdUsuario(Long idUsuario);
	
}
