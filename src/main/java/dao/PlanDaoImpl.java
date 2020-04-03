package dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import modelo.PacienteDTO;
import modelo.Plan;

@Repository("planDao")
public class PlanDaoImpl implements PlanDao {

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public Plan consultarPlan(Long id) {

		final Session session = sessionFactory.getCurrentSession();
		return (Plan) session.createCriteria(Plan.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}
	
	@Override
	public List<Plan> obtenerPlanesFiltrados(String intensidad, boolean aptoCeliaco, boolean aptoHipertenso, boolean sinCarne, boolean sinLacteos) {

		final Session session = sessionFactory.getCurrentSession();
		List<Plan> planesFiltrados = new ArrayList<Plan>();
		//planesFiltrados 
		Criteria criteria = session.createCriteria(Plan.class)
				.add(Restrictions.eq("intensidad", intensidad));

		// Agrega restricciones opcionales
		agregarRestriccionSiEsTrue(criteria, "aptoCeliaco", aptoCeliaco);
		agregarRestriccionSiEsTrue(criteria, "aptoHipertenso", aptoHipertenso);
		agregarRestriccionSiEsTrue(criteria, "sinCarne", sinCarne);
		agregarRestriccionSiEsTrue(criteria, "sinLacteos", sinLacteos);
		
		planesFiltrados = criteria.list();
		return planesFiltrados;
	}
	
	@Override
	public Plan generarPlanSugerido(PacienteDTO pacienteDTO) {

		final Session session = sessionFactory.getCurrentSession();
		
		Plan planBase = new Plan();
		planBase.setNombre("Plan ");
		//seteamos las calorias del plan segun la intensidad
		switch(pacienteDTO.getIntensidad() ) {
		case "Normal":
			planBase.setCaloriasDiarias(1500);
			if(pacienteDTO.getIMC()<18.5){
				planBase.setCaloriasDiarias(3000);
			}
			planBase.setIntensidad("Normal");
			
			break;
		case "Intenso": 
			planBase.setCaloriasDiarias(1000);
			if(pacienteDTO.getIMC()<18.5){
				planBase.setCaloriasDiarias(3500);
			}
			
			planBase.setIntensidad("Intenso");
			break;
		}

		
		//nombre base para el plan segun exclusiones
		if(pacienteDTO.getIMC()<18.5){
			planBase.setNombre(planBase.getNombre()+"Alto en Calorias ");
		}
		
		if(pacienteDTO.isExcluirCarne()==false && pacienteDTO.isExcluirLacteos()==false ) {
			planBase.setNombre(planBase.getNombre()+"Bajo en grasas y Azucares");
			planBase.setSinCarne(false);
			planBase.setSinLacteos(false);
		}
		if(pacienteDTO.isExcluirCarne()==false && pacienteDTO.isExcluirLacteos()==true ) {
			planBase.setNombre(planBase.getNombre()+"Intolerante Lactosa");
			planBase.setSinCarne(false);
			planBase.setSinLacteos(true);
			
		}
		if(pacienteDTO.isExcluirCarne()==true && pacienteDTO.isExcluirLacteos()==false ) {
			planBase.setNombre(planBase.getNombre()+"Lacto-Vegetariano");
			planBase.setSinCarne(true);
			planBase.setSinLacteos(false);
		}
		if(pacienteDTO.isExcluirCarne()==true && pacienteDTO.isExcluirLacteos()==true ) {
			planBase.setNombre(planBase.getNombre()+"Vegetariano");
			planBase.setSinCarne(true);
			planBase.setSinLacteos(true);
		}
		//especificaciones segun enfermedades
		if(pacienteDTO.isAptoCeliaco()==false && pacienteDTO.isAptoHipertenso()==true ) {
			planBase.setNombre(planBase.getNombre()+"(bajo en sodio)");
		}
		if(pacienteDTO.isAptoCeliaco()==true && pacienteDTO.isAptoHipertenso()==false ) {
			planBase.setNombre(planBase.getNombre()+"(sin gluten)");
		}
		if(pacienteDTO.isAptoCeliaco()==true && pacienteDTO.isAptoHipertenso()==true ) {
			planBase.setNombre(planBase.getNombre()+"(bajo en sodio sin gluten)");
		}

		
		//seteamos el plan
//		planBase.setDesayuno(desayuno);
//		planBase.setColacion1(colacion1);
//		planBase.setAlmuerzo(almuerzo);
//		planBase.setMerienda(merienda);
//		planBase.setColacion2(colacion2);
//		planBase.setCena(cena);
		
		//session.save(planBase);
		
		return planBase;
	}
	
	
	@Override
	public void insertarPlanesIniciales(){

		final Session session = sessionFactory.getCurrentSession();
		
//		session.createSQLQuery("delete from plan").executeUpdate();
//		
//		String dummyPlan="<b>Desayuno:</b><br>"+ 
//				"Infusión con ½ taza de leche descremada + 3 tostadas de gluten con ricota descremada + 1 huevo revuelto.<br>" + 
//				"<b>Media mañana:</b><br>" +
//				"Yogur descremado con frutillas.<br>" +  
//				"<b>Almuerzo:</b><br>" +
//				"Ensalada de lentejas, tomate, lechuga, pepino, cebolla y ají + 2 brochetes de banana, naranja y kiwi.<br>" +
//				"<b>Media tarde:</b><br>" +
//				"Gaseosa light + 2 bay biscuits.<br>" +
//				"<b>Merienda:</b><br>" +
//				"Infusión con ½ taza de leche descremada + 2 tostadas de pan integral con queso untable descremado.<br>" +
//				"<b>Cena:</b><br>" +
//				"Bife de lomo grillado + ensalada de lechuga, zanahoria, apio y clara de huevo duro + postre de leche light.<br>";
		
		String desayuno="1 yogur descremado con cereal<br>1 fruta";
		String colacion1="1 vaso grande de jugo light<br>1 yogur descremado";
		String almuerzo="1 plato grande de ensalada<br>2 mitades de tomate redondo relleno con arroz integral, atún, aceituna y 2 cditas. de mayonesa light<br>1 helado de agua";
		String merienda="1 latita de gaseosa light<br>1 barrita de cereal light";
		String colacion2="1 vaso de licuado de durazno con leche descremada<br>1 figazza árabe de salvado con queso fresco descremado y tomate (tostada, tipo pizzeta)";
		String cena="1 plato de ensalada<br>1 plato de verduras cocidas a elección<br>1 milanesa de soja<br>1 postre light";

		//Planes Intensidad Normal (Bajar de Peso)
		Plan plan0000NB = new Plan();
		plan0000NB.setNombre("Plan Bajo en grasas y azucares");
		plan0000NB.setSinCarne(false);
		plan0000NB.setSinLacteos(false);
		plan0000NB.setAptoHipertenso(false);
		plan0000NB.setAptoCeliaco(false);
		plan0000NB.setCaloriasDiarias(1500);
		plan0000NB.setIntensidad("Normal");
		plan0000NB.setDesayuno(desayuno);
		plan0000NB.setColacion1(colacion1);
		plan0000NB.setAlmuerzo(almuerzo);
		plan0000NB.setMerienda(merienda);
		plan0000NB.setColacion2(colacion2);
		plan0000NB.setCena(cena);
		
		
		Plan plan1000NB = new Plan();
		plan1000NB.setNombre("Plan Lacto-Vegetariano");
		plan1000NB.setSinCarne(true);
		plan1000NB.setSinLacteos(false);
		plan1000NB.setAptoHipertenso(false);
		plan1000NB.setAptoCeliaco(false);
		plan1000NB.setCaloriasDiarias(1500);
		plan1000NB.setIntensidad("Normal");
		plan1000NB.setDesayuno(desayuno);
		plan1000NB.setColacion1(colacion1);
		plan1000NB.setAlmuerzo(almuerzo);
		plan1000NB.setMerienda(merienda);
		plan1000NB.setColacion2(colacion2);
		plan1000NB.setCena(cena);
		
		Plan plan1100NB = new Plan();
		plan1100NB.setNombre("Plan Vegetariano");
		plan1100NB.setSinCarne(true);
		plan1100NB.setSinLacteos(true);
		plan1100NB.setAptoHipertenso(false);
		plan1100NB.setAptoCeliaco(false);
		plan1100NB.setCaloriasDiarias(1500);
		plan1100NB.setIntensidad("Normal");
		plan1100NB.setDesayuno(desayuno);
		plan1100NB.setColacion1(colacion1);
		plan1100NB.setAlmuerzo(almuerzo);
		plan1100NB.setMerienda(merienda);
		plan1100NB.setColacion2(colacion2);
		plan1100NB.setCena(cena);
		
		Plan plan0100NB = new Plan();
		plan0100NB.setNombre("Plan Intolerante Lactosa");
		plan0100NB.setSinCarne(false);
		plan0100NB.setSinLacteos(true);
		plan0100NB.setAptoHipertenso(false);
		plan0100NB.setAptoCeliaco(false);
		plan0100NB.setCaloriasDiarias(1500);
		plan0100NB.setIntensidad("Normal");
		plan0100NB.setDesayuno(desayuno);
		plan0100NB.setColacion1(colacion1);
		plan0100NB.setAlmuerzo(almuerzo);
		plan0100NB.setMerienda(merienda);
		plan0100NB.setColacion2(colacion2);
		plan0100NB.setCena(cena);
		
		Plan plan0001NB = new Plan();
		plan0001NB.setNombre("Plan Sin Gluten");
		plan0001NB.setSinCarne(false);
		plan0001NB.setSinLacteos(false);
		plan0001NB.setAptoHipertenso(false);
		plan0001NB.setAptoCeliaco(true);
		plan0001NB.setCaloriasDiarias(1500);
		plan0001NB.setIntensidad("Normal");
		plan0001NB.setDesayuno(desayuno);
		plan0001NB.setColacion1(colacion1);
		plan0001NB.setAlmuerzo(almuerzo);
		plan0001NB.setMerienda(merienda);
		plan0001NB.setColacion2(colacion2);
		plan0001NB.setCena(cena);
		
		Plan plan0010NB = new Plan();
		plan0010NB.setNombre("Plan Bajo en Sodio");
		plan0010NB.setSinCarne(false);
		plan0010NB.setSinLacteos(false);
		plan0010NB.setAptoHipertenso(true);
		plan0010NB.setAptoCeliaco(false);
		plan0010NB.setCaloriasDiarias(1500);
		plan0010NB.setIntensidad("Normal");
		plan0010NB.setDesayuno(desayuno);
		plan0010NB.setColacion1(colacion1);
		plan0010NB.setAlmuerzo(almuerzo);
		plan0010NB.setMerienda(merienda);
		plan0010NB.setColacion2(colacion2);
		plan0010NB.setCena(cena);
		
		//Planes Intensiadad Normal (Subir Peso)
		Plan plan0000NS = new Plan();
		plan0000NS.setNombre("Plan Alto en Carbohidratos");
		plan0000NS.setSinCarne(false);
		plan0000NS.setSinLacteos(false);
		plan0000NS.setAptoHipertenso(false);
		plan0000NS.setAptoCeliaco(false);
		plan0000NS.setCaloriasDiarias(2700);
		plan0000NS.setIntensidad("Normal");
		plan0000NS.setDesayuno(desayuno);
		plan0000NS.setColacion1(colacion1);
		plan0000NS.setAlmuerzo(almuerzo);
		plan0000NS.setMerienda(merienda);
		plan0000NS.setColacion2(colacion2);
		plan0000NS.setCena(cena);
		
		Plan plan1000NS = new Plan();
		plan1000NS.setNombre("Plan Alto en Carbohidratos Lacto-Vegetariano");
		plan1000NS.setSinCarne(true);
		plan1000NS.setSinLacteos(false);
		plan1000NS.setAptoHipertenso(false);
		plan1000NS.setAptoCeliaco(false);
		plan1000NS.setCaloriasDiarias(2700);
		plan1000NS.setIntensidad("Normal");
		plan1000NS.setDesayuno(desayuno);
		plan1000NS.setColacion1(colacion1);
		plan1000NS.setAlmuerzo(almuerzo);
		plan1000NS.setMerienda(merienda);
		plan1000NS.setColacion2(colacion2);
		plan1000NS.setCena(cena);
		
		Plan plan0010NS = new Plan();
		plan0010NS.setNombre("Plan Alto en Carbohidratos bajo en sodio");
		plan0010NS.setSinCarne(false);
		plan0010NS.setSinLacteos(false);
		plan0010NS.setAptoHipertenso(true);
		plan0010NS.setAptoCeliaco(false);
		plan0010NS.setCaloriasDiarias(2700);
		plan0010NS.setIntensidad("Normal");
		plan0010NS.setDesayuno(desayuno);
		plan0010NS.setColacion1(colacion1);
		plan0010NS.setAlmuerzo(almuerzo);
		plan0010NS.setMerienda(merienda);
		plan0010NS.setColacion2(colacion2);
		plan0010NS.setCena(cena);
		
		Plan plan0001NS = new Plan();
		plan0001NS.setNombre("Plan Alto en Carbohidratos sin Gluten");
		plan0001NS.setSinCarne(false);
		plan0001NS.setSinLacteos(false);
		plan0001NS.setAptoHipertenso(false);
		plan0001NS.setAptoCeliaco(true);
		plan0001NS.setCaloriasDiarias(2700);
		plan0001NS.setIntensidad("Normal");
		plan0001NS.setDesayuno(desayuno);
		plan0001NS.setColacion1(colacion1);
		plan0001NS.setAlmuerzo(almuerzo);
		plan0001NS.setMerienda(merienda);
		plan0001NS.setColacion2(colacion2);
		plan0001NS.setCena(cena);
	
		session.save(plan0000NB);
		session.save(plan1000NB);
		session.save(plan1100NB);
		session.save(plan0100NB);
		session.save(plan0010NB);
		session.save(plan0001NB);
		session.save(plan0000NS);
		session.save(plan1000NS);
		session.save(plan0001NS);
		session.save(plan0010NS);
		
	}
	
	// Función para agregar restricciones al criteria SOLO si son true (Ej: enfermedades. Si aptoHipertenso es True se debe agregar al filtro,
	// pero si aptoHipertenso es False no es necesario agregarlo, ya que debe traer ambos tipos de dietas.
	private void agregarRestriccionSiEsTrue(Criteria criteria, String restriccion, boolean valor) {
	    if (valor) {
	        criteria.add(Restrictions.eq(restriccion, valor));
	    }
	}
}
