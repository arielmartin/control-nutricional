package dao;

import java.util.List;

import modelo.PacienteDTO;
import modelo.Plan;

// Interface que define los metodos del DAO de Plan.
public interface PlanDao {
	
	// Método para obtener los datos de un plan mediante el ID.
	Plan consultarPlan (Long id);
	
	// Método para insertar los planes al levantar la aplicación. Se usa para poder probar la aplicación. Cuando se desarrolle el ABM de planes, este se va a borrar.
	void insertarPlanesIniciales();

	// Método para obtener los planes recomendados en base a las opciones escogidas.
	List<Plan> obtenerPlanesFiltrados(String intensidad, boolean aptoCeliaco, boolean aptoHipertenso, boolean sinCarne, boolean sinLacteos);

	Plan generarPlanSugerido(PacienteDTO pacienteDTO);
}
