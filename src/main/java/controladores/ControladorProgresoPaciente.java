package controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import modelo.CompararProgresoDTO;
import modelo.Formula;
import modelo.Paciente;
import modelo.PacienteDTO;
import modelo.Plan;
import modelo.ProgresoPesoIdeal;
import servicios.ServicioPacientes;
import servicios.ServicioPlan;
import servicios.ServicioRegistrarPesoDiario;


@Controller

public class ControladorProgresoPaciente {
	
	@Inject
	private ServicioPacientes servicioPacientes;
	
	@Inject
	private ServicioPlan servicioPlan;
	
	@Inject
	private ServicioRegistrarPesoDiario servicioRegistrarPeso;
	
	public void setServicioPlan(ServicioPlan servicioPlan) {
		this.servicioPlan = servicioPlan;
	}
	
	public void setServicioPacientes(ServicioPacientes servicioPacientes) {
		this.servicioPacientes = servicioPacientes;
	}
	
	public void setServicioRegistrarPesoDiario(ServicioRegistrarPesoDiario servicioRegistrarPesoDiario) {
		this.servicioRegistrarPeso = servicioRegistrarPesoDiario;
	}
	
	
	@RequestMapping(path = "/progresoSeleccionarPaciente", method = RequestMethod.GET)
	public ModelAndView progresoSeleccionarPaciente(HttpServletRequest request) {
		
		ModelMap model = new ModelMap();
		
		Paciente paciente = new Paciente();
		model.put("paciente", paciente);
		PacienteDTO pacienteDTO = new PacienteDTO();
		pacienteDTO.setPaciente(paciente);

		// servicio para obtener listado de pacientes
		List<Paciente> listadoPacientes = servicioPacientes.obtenerListadoPacientes();
		
		if(listadoPacientes.isEmpty()) {
			String error = "No hay pacientes cargados en el sistema.";
			model.put("error", error);
		}
		
		
		model.put("pacienteDTO", pacienteDTO);
		model.put("listadoPacientes", listadoPacientes);
		
		return new ModelAndView("progresoSeleccionarPaciente", model);
	}
	
	
	@RequestMapping(path = "/progresoPaciente", method = RequestMethod.POST)
	public ModelAndView progresoPaciente(@ModelAttribute("pacienteDTO") PacienteDTO pacienteMock, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		//Long longId = idUsuario;
		//int id = longId.intValue();
				
		Paciente paciente = servicioPacientes.obtenerPaciente(pacienteMock.getPaciente().getId() );
		
		if(paciente == null) {
			
			model.put("error", "ERROR: El Usuario no tiene Registrado ningun Plan. Por favor Registre un plan");
			return new ModelAndView("home_view", model);
		}
		
		Plan plan = servicioPlan.consultarPlan(paciente.getPlanAsociado_id());

		Formula formula = new Formula();
		
		Double pesoIdeal = formula.calcularPesoIdeal(paciente.getAltura(), paciente.getSexo());
		
		Double tmb = formula.calcularTMB(paciente.getPeso(), paciente.getAltura(), paciente.getEdad(), paciente.getSexo(), paciente.getEjercicio());
		
		double pesoAPerderOGanar;
		if(paciente.getPeso() > pesoIdeal){
		pesoAPerderOGanar = paciente.getPeso() - pesoIdeal;}
		else{
		pesoAPerderOGanar = pesoIdeal - paciente.getPeso();}
		
		double caloriasPGPorDia;
		if(tmb > plan.getCaloriasDiarias()) {
		caloriasPGPorDia = tmb - plan.getCaloriasDiarias();}
		else {
		caloriasPGPorDia = plan.getCaloriasDiarias() - tmb;
		}
		
		int diasObjetivo = (int)(((pesoAPerderOGanar * 1000) * 7) / caloriasPGPorDia);
		
		List<ProgresoPesoIdeal> listaPesoIdeal = formula.generarListaPesoIdeal(paciente.getFecha_inicio(), diasObjetivo, paciente.getPeso(), caloriasPGPorDia);

		List<CompararProgresoDTO> listaComparacion = formula.generarListaComparacion(servicioRegistrarPeso.ObtenerRegistros(pacienteMock.getPaciente().getId()), listaPesoIdeal);
		
		model.put("paciente", paciente);
		model.put("Lista", listaComparacion);
		model.put("pesoInicial", paciente.getPeso());
		
		return new ModelAndView("progresoPaciente", model);
	}
	
}
