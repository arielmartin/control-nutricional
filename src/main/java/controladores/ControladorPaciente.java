package controladores;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import modelo.Formula;
import modelo.Paciente;
import modelo.PacienteDTO;
import modelo.Plan;
import modelo.Usuario;
import servicios.ServicioLogin;
import servicios.ServicioPacientes;


@Controller
public class ControladorPaciente {
	
	@Inject
	private ServicioPacientes servicioPacientes;
	
	@Inject
	private ServicioLogin servicioLogin;
	
	@RequestMapping(path = "/paciente", method = RequestMethod.GET)
	public ModelAndView paciente() {
		
		ModelMap model = new ModelMap();
		PacienteDTO pacienteDTO = new PacienteDTO();
		Paciente paciente = new Paciente();
		pacienteDTO.setPaciente(paciente);
		model.put("pacienteDTO",pacienteDTO);
		
		return new ModelAndView("paciente_view", model);
	}
	
	@RequestMapping(path = "/exclusiones", method = RequestMethod.POST)
	public ModelAndView exclusiones(@ModelAttribute("pacienteDTO") PacienteDTO pacienteDTO, HttpServletRequest request) {
		ModelMap model = new ModelMap();
			
		model.put("pacienteDTO", pacienteDTO);
		
		return new ModelAndView("exclusiones_view", model);
	}

	
	@RequestMapping(path = "/planes", method = RequestMethod.POST)
	public ModelAndView planes(@ModelAttribute("pacienteDTO") PacienteDTO pacienteDTO, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		model.put("error", "");
		
		String[] alimentosExcluidos = pacienteDTO.getAlimentosExcluidos();
		String[] enfermedadesPadecidas = pacienteDTO.getEnfermedadesPadecidas();
		
		// Setear Exclusiones
		for (String item : alimentosExcluidos) {
		    if (item.equals("Carne")) {
		    	pacienteDTO.setExcluirCarne(true);
		    }
		    if (item.equals("Lacteos")) {
		    	pacienteDTO.setExcluirLacteos(true);
		    }
		}
		
		for (String item : enfermedadesPadecidas) {
		    if (item.equals("Hipertension")) {
		    	pacienteDTO.setAptoHipertenso(true);
		    }
		    if (item.equals("Celiaquia")) {
		    	pacienteDTO.setAptoCeliaco(true);
		    }
		}
		
		Paciente paciente = pacienteDTO.getPaciente();
		
		Double altura = paciente.getAltura();
		String sexo = paciente.getSexo();
		
		Formula formula = new Formula();
		Double pesoIdeal = formula.calcularPesoIdeal(altura, sexo);
		pacienteDTO.setPesoIdeal(pesoIdeal);
		
		if(paciente.getPeso() > pesoIdeal) {
			pacienteDTO.setObjetivo("bajar");
		}
		if(paciente.getPeso() < pesoIdeal) {
			pacienteDTO.setObjetivo("subir");
		}
		if(paciente.getPeso() == pesoIdeal) {
			pacienteDTO.setObjetivo("mantener");
		}

		model.put("pacienteDTO", pacienteDTO);
		
		// TO DO: Sacar estas líneas para dummies
		// Llama al servicio que inserta los planes iniciales hasta que desarrollemos el ABM de planes
		//ServicioPacientes.insertarPlanesIniciales();
		
		List<Plan> planesSugeridos = servicioPacientes.obtenerPlanesFiltrados(pacienteDTO.getIntensidad(), pacienteDTO.isAptoCeliaco(), 
				pacienteDTO.isAptoHipertenso(), pacienteDTO.isExcluirCarne(), pacienteDTO.isExcluirLacteos());

		if (planesSugeridos.isEmpty()) {
			// si no hay planes con las exclusiones elegidas
			model.put("error", "No hay planes que se adecúen a las opciones escogidas. Por favor, seleccione otras.");
			return new ModelAndView("exclusiones_view", model);
		}
		model.put("planesSugeridos", planesSugeridos);
		
		return new ModelAndView("planes_view", model);
	}
	
	
	@RequestMapping(path = "/final", method = RequestMethod.POST)
	public ModelAndView crearGraficoCalorias2(@ModelAttribute("pacienteDTO") PacienteDTO pacienteDTO, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		// Aca completa con los datos del plan elegido, haciendo un llamado a la BD mediante el ID
		Plan planElegido = servicioPacientes.consultarPlan(pacienteDTO.getPlan().getId());
		pacienteDTO.setPlan(planElegido);
		
		Paciente paciente = pacienteDTO.getPaciente();
		
		Double caloriasPGPorDia;
		Double pesoAPerderOGanar;
		Double peso = paciente.getPeso();
		Double altura = paciente.getAltura();
		String sexo = paciente.getSexo();
		int edad = paciente.getEdad();
		int ejercicio = paciente.getEjercicio();
		
		Formula formula = new Formula();
		Double imc = formula.calcularIMC(peso, altura);
		Double pesoIdeal = formula.calcularPesoIdeal(altura, sexo);
		Double tmb = formula.calcularTMB(peso, altura, edad, sexo, ejercicio);
		
		if(peso > pesoIdeal){
		pesoAPerderOGanar = peso - pesoIdeal;}
		else{
		pesoAPerderOGanar = pesoIdeal - peso;}
		
		if(tmb > pacienteDTO.getPlan().getCaloriasDiarias()) {
		caloriasPGPorDia = tmb - pacienteDTO.getPlan().getCaloriasDiarias();}
		else {
		caloriasPGPorDia = pacienteDTO.getPlan().getCaloriasDiarias() - tmb;
		}
		
		int diasObjetivo = (int)(((pesoAPerderOGanar * 1000) * 7) / caloriasPGPorDia);
		
		model.put("peso", peso);
		model.put("tmb", tmb);
		model.put("imc", imc);
		model.put("pesoIdeal", pesoIdeal);
		model.put("pesoAPerderOGanar", pesoAPerderOGanar);
		model.put("caloriasPGPorDia", caloriasPGPorDia);
		model.put("diasObjetivo", diasObjetivo);
		
		model.put("pacienteDTO",pacienteDTO);
		
		return new ModelAndView("final_view", model);
	}
	
	
	@RequestMapping(path = "/finalizarRegistro", method = RequestMethod.POST)
	public ModelAndView finalizarRegistro(@ModelAttribute("pacienteDTO") PacienteDTO pacienteDTO, HttpServletRequest request) {
		
		ModelMap model = new ModelMap();
		
		Date fecha = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String f = dateFormat.format(fecha);
				
		Paciente paciente = pacienteDTO.getPaciente();
		
		paciente.setFecha_inicio(f);		
		paciente.setPlanAsociado_id(pacienteDTO.getPlan().getId());
		
		//agregamos el id del medico asociado a este paciente
		paciente.setMedicoAsociado_id((Long) request.getSession().getAttribute("ID"));
		//paciente.setIdUsuario((Long) request.getSession().getAttribute("idUsuario"));
		
		// Llama al servicio que crea el usuario en la BD
		Usuario usuario = new Usuario();
		usuario.setNombre( (String) request.getSession().getAttribute("NOMBRE_PACIENTE") );
		usuario.setApellido( (String) request.getSession().getAttribute("APELLIDO_PACIENTE") );
		usuario.setEmail( (String) request.getSession().getAttribute("email") );
		usuario.setFechaNacimiento( (String) request.getSession().getAttribute("fnac") );
		usuario.setRol("paciente");
		usuario.setPassword((String) request.getSession().getAttribute("pass"));
		
		paciente.setIdUsuario( servicioLogin.crearUsuario(usuario) ) ;
		model.put("paciente",paciente);
		
		
		//seteamos el nombre del paciente
		String nombrePaciente= (String) request.getSession().getAttribute("NOMBRE_PACIENTE");
		nombrePaciente+=" "+(String) request.getSession().getAttribute("APELLIDO_PACIENTE");
		paciente.setNombre(nombrePaciente);
		

		// Llama al servicio que inserta el paciente en la BD
		paciente.setFnac( (String) request.getSession().getAttribute("fnac") );
		int edad = paciente.getEgeByDate((String) request.getSession().getAttribute("fnac"));
		paciente.setEdad(edad);
		servicioPacientes.savePaciente(paciente);
		model.put("paciente",paciente);
		
		// servicio para obtener listado de pacientes
		List<Paciente> listadoPacientes = servicioPacientes.obtenerListadoPacientes();
		model.put("listadoPacientes", listadoPacientes);
		
		return new ModelAndView("/home_view", model);
	}
	
}