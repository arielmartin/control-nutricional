package servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.PlanDao;
//import dao.UsuarioDao;
import modelo.Plan;

@Service("servicioPlan")
@Transactional
public class ServicioPlanImpl implements ServicioPlan{
	
	@Inject
	private PlanDao servicioPlanDao;

	@Override
	public Plan consultarPlan(Long id) {
		// TODO Auto-generated method stub
		return servicioPlanDao.consultarPlan(id);
	}
	
	
	@Override
	public List<Plan> getAllPlanes() {
		// TODO Auto-generated method stub
		return servicioPlanDao.getAllPlanes();
	}
	
	

}
