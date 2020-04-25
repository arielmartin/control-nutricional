package controladores;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import modelo.Paciente;
import servicios.ServicioPacientes;

@RestController
@RequestMapping("/api")
public class ApiRest {
	
	@Inject
	private ServicioPacientes sp;

	
	@RequestMapping(path = "/paciente", method = RequestMethod.GET)
	public List<Paciente> getPacientes() {
		
		List<Paciente> pacientes = sp.obtenerListadoPacientes();
		
		return pacientes;
	}
	
	
	@RequestMapping(path = "/paciente/{id}", method = RequestMethod.GET)
	public Paciente getPaciente(@PathVariable("id") Long id) {
		
		Paciente paciente = sp.obtenerPaciente(id);
		
		return paciente;
	}
	
	
	@RequestMapping(path = "/paciente", method = RequestMethod.POST)
	public Paciente postPaciente(@RequestBody Paciente paciente) {
		
		//Paciente pacienteOld = sp.obtenerPaciente( paciente.getId() );
		Paciente pacienteNew = new Paciente();
		
		pacienteNew.setNombre( paciente.getNombre() );
		pacienteNew.setPeso(paciente.getPeso());
		pacienteNew.setAltura(paciente.getAltura());
		pacienteNew.setSexo(paciente.getSexo());
		pacienteNew.setEdad(paciente.getEdad());
		pacienteNew.setEjercicio(paciente.getEjercicio());
		pacienteNew.setFecha_inicio(paciente.getFecha_inicio());
		pacienteNew.setMedicoAsociado_id(paciente.getMedicoAsociado_id());
		pacienteNew.setPlanAsociado_id(paciente.getPlanAsociado_id());
		pacienteNew.setFnac(paciente.getFnac());
		
		Long id = sp.savePaciente(pacienteNew);
		
		System.out.println(id);
		
		return pacienteNew;
	}
	
	
	@RequestMapping(path = "/paciente", method = RequestMethod.PUT)
	public Paciente putPaciente(@RequestBody Paciente paciente) {
		
		Paciente pacienteOld = sp.obtenerPaciente( paciente.getId() );
		
		pacienteOld.setNombre( paciente.getNombre() );
		pacienteOld.setPeso(paciente.getPeso());
		pacienteOld.setAltura(paciente.getAltura());
		pacienteOld.setSexo(paciente.getSexo());
		pacienteOld.setEdad(paciente.getEdad());
		pacienteOld.setEjercicio(paciente.getEjercicio());
		pacienteOld.setFecha_inicio(paciente.getFecha_inicio());
		pacienteOld.setMedicoAsociado_id(paciente.getMedicoAsociado_id());
		pacienteOld.setPlanAsociado_id(paciente.getPlanAsociado_id());
		pacienteOld.setFnac(paciente.getFnac());
		
		Long id = sp.savePaciente(pacienteOld);
		
		System.out.println(id);
		return pacienteOld;
	}
	
	
	@RequestMapping(path = "/paciente/{id}", method = RequestMethod.DELETE)
	public Long deletePaciente(@PathVariable("id") Long id) {
		
		//Paciente pacienteOld = sp.obtenerPaciente( id );
		
		sp.deletePaciente(id);
		
		return id;		
	}
	
	
}
