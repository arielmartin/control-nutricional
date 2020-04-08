package servicios;

import java.util.List;

import modelo.*;

public interface ServicioAlimentos {

	List<Alimento> obtenerListadoDeAlimentos();
	
	List<Alimento> obtenerListadoDeAlimentos(String tipo);

	void guardarAlimento(Alimento alimento);

	Alimento getAlimentoById(Long id);

	void guardarRegistroComida(HistorialComidas registro);

	List<HistorialComidas> obtenerRegistroComidas(Long idPaciente);
	
}