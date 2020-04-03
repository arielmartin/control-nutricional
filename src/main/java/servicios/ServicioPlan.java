package servicios;

import java.util.List;

import modelo.Plan;

public interface ServicioPlan {

	Plan consultarPlan(Long id);
	
	List<Plan> getAllPlanes();
	
}
