package servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.RegistrarPesoDiarioDao;
import modelo.Paciente;
import modelo.RegistrarPesoDiarioDTO;



@Service("servicioRegistrarPesoDiario")
@Transactional
public class ServicioRegistrarPesoDiarioImpl implements ServicioRegistrarPesoDiario{
	@Inject
	private RegistrarPesoDiarioDao registrarPesoDiarioDao;
	@Override
	public boolean ConsultarRegistroFecha(int id, String fecha){
		return registrarPesoDiarioDao.ConsultarRegistroFecha(id, fecha);
	}
	@Override
	public void RegistrarPesoDiario(RegistrarPesoDiarioDTO registrarPesoDiarioDTO) {
		registrarPesoDiarioDao.RegistrarPesoDiario(registrarPesoDiarioDTO);
	}
	
	@Override
	public List<RegistrarPesoDiarioDTO> ObtenerRegistros(Long id) {
		return registrarPesoDiarioDao.ObtenerRegistros(id);
	}
	
	@Override
	public List<Paciente> ObtenerPacientes(Long id) {
		return registrarPesoDiarioDao.ObtenerPacientes(id);
	}
	
	@Override
	public void cargarRegistrosIniciales() {
		registrarPesoDiarioDao.cargarRegistrosIniciales();
	}
}
