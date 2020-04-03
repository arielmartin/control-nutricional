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
import modelo.Usuario;
import servicios.ServicioLogin;
import servicios.ServicioPacientes;
//import servicios.ServicioPlan;

@Controller
public class ControladorLogin {

	@Inject
	private ServicioLogin servicioLogin;
	
	@Inject
	private ServicioPacientes servicioPacientes;
	
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

		ModelMap modelo = new ModelMap();

		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);

		return new ModelAndView("login", modelo);
	}
	
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {

		ModelMap modelo = new ModelMap();
		
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);

        request.getSession().invalidate();

		return new ModelAndView("login", modelo);
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		
		ModelMap model = new ModelMap();

		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		if (usuarioBuscado != null) {

			request.getSession().setAttribute("ROL", usuarioBuscado.getRol());

			request.getSession().setAttribute("idUsuario", usuarioBuscado.getId() );
			request.getSession().setAttribute("APELLIDO_PACIENTE", usuario.getApellido() );
			request.getSession().setAttribute("NOMBRE_PACIENTE", usuario.getNombre() );

			request.getSession().setAttribute("EMAIL", usuarioBuscado.getEmail());
			request.getSession().setAttribute("ID", usuarioBuscado.getId());
			request.getSession().setAttribute("APELLIDO", usuarioBuscado.getApellido());
			
			List<Paciente> listadoPacientes = servicioPacientes.obtenerListadoPacientes();
			model.put("paciente",new Paciente() );
			model.put("listadoPacientes", listadoPacientes);

			return new ModelAndView("home",model);
			
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Usuario o clave incorrecta");
		}
		
		return new ModelAndView("login", model);
	}

	
	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		
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
			
			return new ModelAndView("home", model);
	}
	
	
	@RequestMapping(path = "/selectPaciente", method = RequestMethod.POST)
	public ModelAndView selectPaciente(@ModelAttribute("paciente") Paciente paciente, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		paciente=servicioPacientes.obtenerPaciente(paciente.getId() );
		model.put("paciente", paciente);
		
		// servicio para obtener listado de pacientes
		List<Paciente> listadoPacientes = servicioPacientes.obtenerListadoPacientes();
		
		if(listadoPacientes.isEmpty()) {
			String error = "No hay pacientes cargados en el sistema.";
			model.put("error", error);
		}
		
		request.getSession().setAttribute("idUsuario", paciente.getIdUsuario() );
		request.getSession().setAttribute("NOMBRE_PACIENTE", paciente.getNombre() );
		
		model.put("listadoPacientes", listadoPacientes);
		
		return new ModelAndView("home", model);
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
		
		return new ModelAndView("redirect:/login");
		
	}
	
	
	@RequestMapping(path ="/registrarusuario", method = RequestMethod.GET)
	public ModelAndView registarUsuario() {

		ModelMap modelo = new ModelMap();

		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);

		return new ModelAndView("registrarusuario", modelo);
	}
	
	@RequestMapping(path = "/crear-usuario", method = RequestMethod.POST)
	public ModelAndView crearUsuario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		if (usuario != null) {
			// invoca el método crearUsuario del servicio
			// SE COMENTA PORQUE TODAVIA NO ESTA CREADO EL SERVICIO
			if(servicioLogin.crearUsuario(usuario)){
				model.put("usuario", usuario);
				PacienteDTO pacienteDTO = new PacienteDTO();
				pacienteDTO.setUsuario(usuario);
				request.getSession().setAttribute("idUsuario", usuario.getId());
				request.getSession().setAttribute("APELLIDO_PACIENTE", usuario.getApellido() );
				request.getSession().setAttribute("NOMBRE_PACIENTE", usuario.getNombre() );

				model.put("pacienteDTO", pacienteDTO);
				return new ModelAndView("paciente", model);
			}
			else {
				model.put("error", "El E-mail Ingresado ya esta Registrado. Por Favor ingrese otro E-mail");
				return new ModelAndView("registrarusuario", model);
			}
			
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("registrarusuario", model);
	}
	
}
