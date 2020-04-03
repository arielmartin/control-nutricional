package controladores;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

import modelo.*;
import servicios.ServicioAlimentos;
import servicios.ServicioPacientes;

@Controller
public class ControladorAlimento {
	@Inject
	private ServicioAlimentos servicioAlimentos;
	
	public void setServicioAlimentos(ServicioAlimentos servicioAlimentos) {
		this.servicioAlimentos = servicioAlimentos;
	}

	@Inject
	private ServicioPacientes servicioPacientes;

	public void setServicioPacientes(ServicioPacientes servicioPacientes) {
		this.servicioPacientes = servicioPacientes;
	}

	@RequestMapping(path = "/registrarConsumoAlimento", method = RequestMethod.GET)
	public ModelAndView irAregistrarConsumoAlimento() {
		
		ModelMap model = new ModelMap();
		
		model.put("alimentoDTO", new AlimentoDTO());
		
		List<Alimento> listadoAlimentos = servicioAlimentos.obtenerListadoDeAlimentos("comida");
		model.put("listadoAlimentos",listadoAlimentos);
		
		List<Alimento> listadoBebidas = servicioAlimentos.obtenerListadoDeAlimentos("bebida");
		model.put("listadoBebidas",listadoBebidas);
		
		return new ModelAndView("registrarConsumoAlimento", model);
	}
	
	@RequestMapping(path = "/ConsumoAlimento", method = RequestMethod.POST)
	public ModelAndView ConsumoAlimento(@ModelAttribute("alimentoDTO") AlimentoDTO alimentoDTO, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		Alimento comida = servicioAlimentos.getAlimentoById(alimentoDTO.getComida().getId());
		model.put("comida",comida);
		Alimento bebida = servicioAlimentos.getAlimentoById(alimentoDTO.getBebida().getId());
		model.put("bebida",bebida);
		int cantComida=alimentoDTO.getCantComida();
		model.put("cantComida", cantComida);
		int cantBebida=alimentoDTO.getCantBebida();
		model.put("cantBebida", cantBebida);
		
		alimentoDTO.setComida(comida);
		alimentoDTO.setBebida(bebida);
		alimentoDTO.setCantComida(cantComida);
		alimentoDTO.setCantBebida(cantBebida);
		
		int totalCalorias=alimentoDTO.getComida().getCaloriasPorPorcion()*cantComida + alimentoDTO.getBebida().getCaloriasPorPorcion()*cantBebida;
		model.put("totalCalorias", totalCalorias);
		
		alimentoDTO.setTotalCalorias(totalCalorias);
		
		model.put("alimentoDTO", alimentoDTO);
		
		//System.out.println(totalCalorias );
		
		return new ModelAndView("calorias", model);
	}
	
	@RequestMapping(path = "/registrarComida", method = RequestMethod.POST)
	public ModelAndView registrarComida(@ModelAttribute("alimentoDTO") AlimentoDTO alimentoDTO, HttpServletRequest request) {
		
		ModelMap model = new ModelMap();
		Alimento comida=servicioAlimentos.getAlimentoById(alimentoDTO.getComida().getId());
		Alimento bebida=servicioAlimentos.getAlimentoById(alimentoDTO.getBebida().getId());
		int cantComida=alimentoDTO.getCantComida();
		int cantBebida=alimentoDTO.getCantBebida();
		int totalCalorias=alimentoDTO.getTotalCalorias();
		
		//obtenemos el id del Usuario directamente de la session
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		Long idPaciente = (Long) attr.getRequest().getSession().getAttribute("idUsuario");

		//Obtenemos la fecha actual en formato LocalDate y lo convertimos a String
		LocalDate fechaHoy = LocalDate.now();
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    String fechaFormateada = fechaHoy.format(formatoFecha);
	    
		//Obtenemos la hora actual en formato Date y lo convertimos a String
	    Date fechaActual = new Date ();
	    DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
	    String hora=formatoHora.format(fechaActual);
		
//		System.out.println(comida.getNombre() );
//		System.out.println(cantComida );
//		System.out.println(comida.getCaloriasPorPorcion() );
//		System.out.println(bebida.getNombre() );
//		System.out.println(cantBebida );
//		System.out.println(bebida.getCaloriasPorPorcion() );
//		System.out.println(totalCalorias);
//		System.out.println(idPaciente);
//		System.out.println(fechaFormateada);
//		System.out.println(hora);
	    
		HistorialComidas registroComida=new HistorialComidas();
		registroComida.setIdPaciente(idPaciente);
		registroComida.setIdAlimentoComida(alimentoDTO.getComida().getId() );
		registroComida.setIdAlimentoBebida(alimentoDTO.getBebida().getId() );
		registroComida.setComida(comida.getNombre() );
		registroComida.setBebida(bebida.getNombre() );
		registroComida.setCantComida(cantComida);
		registroComida.setCantBebida(cantBebida);
		registroComida.setTotalCalorias(totalCalorias);
		registroComida.setFecha(fechaFormateada);
		registroComida.setHora(hora);
		
		servicioAlimentos.guardarRegistroComida(registroComida);
		
		// servicio para obtener listado de pacientes
		List<Paciente> listadoPacientes = servicioPacientes.obtenerListadoPacientes();
		Paciente paciente = new Paciente();
		model.put("paciente", paciente);
		model.put("listadoPacientes", listadoPacientes);
		
		return new ModelAndView("home", model);
	}
	
	@RequestMapping(path = "/historialDeComidas", method = RequestMethod.GET)
	public ModelAndView irHistorialDeComidas() {
		ModelMap model = new ModelMap();
		
		//obtenemos el id del Usuario directamente de la session
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		Long idPaciente = (Long) attr.getRequest().getSession().getAttribute("idUsuario");
		
		List<HistorialComidas> listaRegistros= new ArrayList<HistorialComidas>();
		
		listaRegistros=servicioAlimentos.obtenerRegistroComidas(idPaciente);
		
		model.put("listaRegistros", listaRegistros);
		
		return new ModelAndView("historialDeComidas",model);
	
	}
	
	
	@RequestMapping(path = "/cargarAlimentos", method = RequestMethod.GET)
	public ModelAndView cargarAlimentos() {

		Alimento alimento1 = new Alimento("yogur descremado con cereal",150,"comida");
		Alimento alimento2 = new Alimento("naranja",57,"comida" );
		Alimento alimento3 = new Alimento("plato de ensalada lechuga y tomate",200,"comida");
		Alimento alimento4 = new Alimento("mitad de tomate relleno con arroz integral, atún, aceituna y 2 cdas. de mayonesa light",250,"comida");
		Alimento alimento5 = new Alimento("barrita de cereal light",94,"comida");
		Alimento alimento6 = new Alimento("figazza árabe de salvado con queso fresco descremado y tomate",200,"comida");
		Alimento alimento7 = new Alimento("plato de verduras cocidas a elección",150,"comida");
		Alimento alimento8 = new Alimento("milanesa de soja",200,"comida");
		Alimento alimento9 = new Alimento("porcion de gelatina diet con trozoz de fruta",30,"comida");
		Alimento alimento10 = new Alimento("filet de pescado a la plancha con limon",130,"comida");
		
		Alimento bebida0 = new Alimento("nada",0,"bebida");
		Alimento bebida1 = new Alimento("vaso grande de jugo light",50,"bebida");
		Alimento bebida2 = new Alimento("helado de agua",50,"bebida");
		Alimento bebida3 = new Alimento("latita de gaseosa light",1,"bebida");
		Alimento bebida4 = new Alimento("vaso de licuado de durazno con leche descremada",50,"bebida");
		Alimento bebida5 = new Alimento("vaso de ensalada de frutas",50,"bebida");
		Alimento bebida6 = new Alimento("licuado de melon y durazno con agua",50,"bebida");
		Alimento bebida7 = new Alimento("infucion con media taza de leche descremada",50,"bebida");
		Alimento bebida8 = new Alimento("taza de leche dscremada con 2 cdas de granola",50,"bebida");
		
		servicioAlimentos.guardarAlimento(alimento1);
		servicioAlimentos.guardarAlimento(alimento2);
		servicioAlimentos.guardarAlimento(alimento3);
		servicioAlimentos.guardarAlimento(alimento4);
		servicioAlimentos.guardarAlimento(alimento5);
		servicioAlimentos.guardarAlimento(alimento6);
		servicioAlimentos.guardarAlimento(alimento7);
		servicioAlimentos.guardarAlimento(alimento8);
		servicioAlimentos.guardarAlimento(alimento9);
		servicioAlimentos.guardarAlimento(alimento10);
		servicioAlimentos.guardarAlimento(bebida0);
		servicioAlimentos.guardarAlimento(bebida1);
		servicioAlimentos.guardarAlimento(bebida2);
		servicioAlimentos.guardarAlimento(bebida3);
		servicioAlimentos.guardarAlimento(bebida4);
		servicioAlimentos.guardarAlimento(bebida5);
		servicioAlimentos.guardarAlimento(bebida6);
		servicioAlimentos.guardarAlimento(bebida7);
		servicioAlimentos.guardarAlimento(bebida8);
			
		return new ModelAndView("redirect:/registrarConsumoAlimento");
	}
	
}
