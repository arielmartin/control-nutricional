package dao;

import java.util.List;

import modelo.Alimento;
import modelo.HistorialComidas;

public interface AlimentoDao {

	List<Alimento> obtenerListadoDeAlimentos();

	List<Alimento> obtenerListadoDeAlimentos(String tipo);

	void guardarAlimento(Alimento alimento);

	Alimento getAlimentoById(Long id);

	void guardarRegistroComida(HistorialComidas registro);

	List<HistorialComidas> obtenerRegistroComidas(Long idPaciente);

}
