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

import modelo.Paciente;
import modelo.PacienteDTO;
import modelo.Plan;
import modelo.Usuario;
import servicios.ServicioLogin;
import servicios.ServicioPacientes;
import servicios.ServicioPlan;

@Controller
public class ControladorUsuario {

	@Inject
	private ServicioLogin servicioLogin;
	
	@Inject
	private ServicioPacientes servicioPacientes;
	
	@Inject
	private ServicioPlan servicioPlan;
	
	//@Inject
	//private ServicioRegistrarPesoDiario servicioRegistrarPesoDiario;

	public void setServicioLogin(ServicioLogin servicioLogin) {
		this.servicioLogin = servicioLogin;
	}
	
	public void setServicioPacientes(ServicioPacientes servicioPacientes) {
		this.servicioPacientes = servicioPacientes;
	}
	
	
	@RequestMapping("/login")
	public ModelAndView login() {

		ModelMap model = new ModelMap();

		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("error", "Usuario o clave incorrecta");

		return new ModelAndView("login_view", model);
	}
	
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {

		ModelMap model = new ModelMap();
		
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);

        request.getSession().invalidate();

		return new ModelAndView("login_view", model);
	}

	
	@RequestMapping(path = "/home", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		
		ModelMap model = new ModelMap();
		String view ="";

		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		
		if (usuarioBuscado != null) {

			request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
			
			request.getSession().setAttribute("ID", usuarioBuscado.getId());
			request.getSession().setAttribute("EMAIL", usuarioBuscado.getEmail());
			request.getSession().setAttribute("APELLIDO", usuarioBuscado.getApellido());

			if(usuarioBuscado.getRol() == "paciente"){
				request.getSession().setAttribute("idUsuario", usuarioBuscado.getId() );
				request.getSession().setAttribute("APELLIDO_PACIENTE", usuario.getApellido() );
				request.getSession().setAttribute("NOMBRE_PACIENTE", usuario.getNombre() );
			}
			
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Usuario o clave incorrecta");
			return new ModelAndView("redirect:/login");
		}
		
		if(usuarioBuscado.getRol().equals("paciente")) {
			
			Paciente paciente = servicioPacientes.getPacienteByIdUsuario(usuarioBuscado.getId());
			PacienteDTO pacienteDTO = new PacienteDTO();
			pacienteDTO.setPaciente(paciente);
			
			model.put("pacienteDTO", pacienteDTO );
			
			view = "detalle_view";
		
		} 
		
		if(usuarioBuscado.getRol().equals("medico")) {
			List<Paciente> listadoPacientes = servicioPacientes.obtenerListadoPacientes();
			
			model.put("paciente",new Paciente() );
			model.put("listadoPacientes", listadoPacientes);
			
			view = "home_view";	
		}
		
		return new ModelAndView(view, model);
		
	}
	
	
	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		
			ModelMap model = new ModelMap();
			
			Paciente paciente = new Paciente();
			model.put("paciente", paciente);
			
			// servicio para obtener listado de pacientes
			List<Paciente> listadoPacientes = servicioPacientes.obtenerListadoPacientes();
			
			if(listadoPacientes.isEmpty()) {
				String error = "No hay pacientes cargados en el sistema.";
				model.put("error", error);
			}
			
			model.put("listadoPacientes", listadoPacientes);
			
			return new ModelAndView("home_view", model);
	}
	
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio(HttpServletRequest request) {
		//carga de datos de prueba, solo necesarios en caso de no tener una base de datos creada con datos ya cargados
		
		//		if(request.getSession().getAttribute("cargaInicial") == null ) {
		//		servicioLogin.cargarUsuariosIniciales();
		//		servicioPacientes.cargarPacientesIniciales();
		//		servicioPacientes.insertarPlanesIniciales();
		//		servicioRegistrarPesoDiario.cargarRegistrosIniciales();
		//		request.getSession().setAttribute("cargaInicial", 1);
		//		}
		
		//datos.cargarRegistroPesoDiario();
		
		//esta es una cuenta dummy para facilitar las pruebas
		
		Usuario usuario = new Usuario();
		usuario.setEmail("root@root.com");
		usuario.setApellido("Dr X");
		usuario.setPassword("");
		usuario.setRol("medico");
		servicioLogin.crearUsuario(usuario);
		
		//metodo que agrega los Planes
		List<Plan> planes = servicioPlan.getAllPlanes();
		
		if(planes.size() == 0){
			servicioPacientes.insertarPlanesIniciales();
		}
		return new ModelAndView("redirect:/login");
		
	}
	
	
	@RequestMapping(path ="/registrar_usuario", method = RequestMethod.GET)
	public ModelAndView registarUsuario() {

		ModelMap modelo = new ModelMap();

		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);

		return new ModelAndView("registrar_usuario_view", modelo);
	}
	
	
}
