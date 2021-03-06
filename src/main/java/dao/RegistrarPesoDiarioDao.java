package dao;

import java.util.List;

import modelo.Paciente;
import modelo.RegistrarPesoDiarioDTO;

public interface RegistrarPesoDiarioDao {
public boolean ConsultarRegistroFecha(int id, String fecha);

void RegistrarPesoDiario(RegistrarPesoDiarioDTO registrarPesoDiarioDTO);
public List<RegistrarPesoDiarioDTO> ObtenerRegistros(Long id);
public List<Paciente> ObtenerPacientes(Long id);
public void cargarRegistrosIniciales();
}
