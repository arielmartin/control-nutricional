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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import modelo.Paciente;
import modelo.RegistrarPesoDiarioDTO;
import servicios.ServicioRegistrarPesoDiario;

@Controller
public class ControladorRegistrarPesoDiario {
	@Inject
	private ServicioRegistrarPesoDiario servicioRegistrarPesoDiario;
	
	public void setServicioRegistrarPesoDiario(ServicioRegistrarPesoDiario servicioRegistrarPesoDiario) {
		this.servicioRegistrarPesoDiario = servicioRegistrarPesoDiario;
	}

	
	@RequestMapping(path = "/registrarPeso", method = RequestMethod.GET)
	public ModelAndView registrarPeso(HttpServletRequest request) {
		
		ModelMap model = new ModelMap();
		
		RegistrarPesoDiarioDTO registrarPesoDiarioDTO = new RegistrarPesoDiarioDTO();
		
		List<Paciente> listadoPacientes = servicioRegistrarPesoDiario.ObtenerPacientes((Long) request.getSession().getAttribute("ID"));
		
		if(listadoPacientes.isEmpty()) {
			String error = "No hay pacientes cargados en el sistema asignados al nutricionista.";
			model.put("error", error);
		}
		
		model.put("registrarPesoDiarioDTO", registrarPesoDiarioDTO);
		model.put("listaPacientes", listadoPacientes);
		
		return new ModelAndView("registrarPeso_view", model);
	}
	
	
	@RequestMapping(path = "/completarRegistroPesoDiario", method = RequestMethod.POST)
	public ModelAndView darAltaRegistroPeso(@ModelAttribute("registrarPesoDiarioDTO") RegistrarPesoDiarioDTO registrarPesoDiarioDTO, HttpServletRequest request) {
		
		ModelMap model = new ModelMap();

		//tomo la fecha actual junto con el id del usuario y busco si ya existe un registro
		
		int id = registrarPesoDiarioDTO.getIdPaciente().intValue();
		
		registrarPesoDiarioDTO.setPeso((float)registrarPesoDiarioDTO.getPeso());

		Date fecha = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String f = dateFormat.format(fecha);
		
		boolean registroBuscado = servicioRegistrarPesoDiario.ConsultarRegistroFecha(id, f);
		
		//Para Test cambiar IF a false, para Prod debe ir en true
		if(registroBuscado != true) {
			// si ya existe registro ese dia se le avisa que no puede ingresarlo nuevamente
			
			model.put("error", "Ya existe un registro en la fecha actual, vuelva mañana para ingresar un nuevo registro.");
			return new ModelAndView("/registrarPeso_view", model);
		}
		else {
			// si no existe registro en esta fecha procedemos a guardarlo en la base de datos

			registrarPesoDiarioDTO.setFecha(f);

			servicioRegistrarPesoDiario.RegistrarPesoDiario(registrarPesoDiarioDTO);
			return new ModelAndView("completarRegistroPesoDiario", model);
		}
			
	}
	
	@RequestMapping(path = "/cargarDatosRoot", method = RequestMethod.GET)
	public ModelAndView cargarDatosRoot(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		//obtenemos el id del Usuario directamente de la session
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		Long idUsuario = (Long) attr.getRequest().getSession().getAttribute("idUsuario");
		
		servicioRegistrarPesoDiario.RegistrarPesoDiario(new RegistrarPesoDiarioDTO("06/07/2018",(float)77,idUsuario));
		servicioRegistrarPesoDiario.RegistrarPesoDiario(new RegistrarPesoDiarioDTO("07/07/2018",(float)77,idUsuario));
		servicioRegistrarPesoDiario.RegistrarPesoDiario(new RegistrarPesoDiarioDTO("08/07/2018",(float)75,idUsuario));
		servicioRegistrarPesoDiario.RegistrarPesoDiario(new RegistrarPesoDiarioDTO("09/07/2018",(float)74,idUsuario));
		servicioRegistrarPesoDiario.RegistrarPesoDiario(new RegistrarPesoDiarioDTO("10/07/2018",(float)73,idUsuario));
		servicioRegistrarPesoDiario.RegistrarPesoDiario(new RegistrarPesoDiarioDTO("11/07/2018",(float)73,idUsuario));
		servicioRegistrarPesoDiario.RegistrarPesoDiario(new RegistrarPesoDiarioDTO("12/07/2018",(float)72,idUsuario));
		servicioRegistrarPesoDiario.RegistrarPesoDiario(new RegistrarPesoDiarioDTO("13/07/2018",(float)71,idUsuario));
		servicioRegistrarPesoDiario.RegistrarPesoDiario(new RegistrarPesoDiarioDTO("14/07/2018",(float)70,idUsuario));
		servicioRegistrarPesoDiario.RegistrarPesoDiario(new RegistrarPesoDiarioDTO("15/07/2018",(float)70,idUsuario));
		
		return new ModelAndView("home", model);
	}
	
}
