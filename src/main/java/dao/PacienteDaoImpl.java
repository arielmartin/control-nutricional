package dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import modelo.Paciente;


@Repository("pacienteDao")
public class PacienteDaoImpl implements PacienteDao{
	
	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public Paciente getPacienteById (Long id) {
		Session session = sessionFactory.getCurrentSession();
		Paciente resultado = 
		(Paciente) session.createCriteria(Paciente.class)
		.add(Restrictions.eq("id", id ))
		.uniqueResult();
			
		return resultado;
	}
	
	@Override
	public Long savePacienteInBd (Paciente paciente) {
		Session session = sessionFactory.getCurrentSession();
		session.save(paciente);
		
		return paciente.getId();
	}
	
	@Override
	public Double getPesoPaciente(Long id){
		Session session = sessionFactory.getCurrentSession();
		Paciente paciente=
		(Paciente) session.createCriteria(Paciente.class)
		.add(Restrictions.eq("id", id))
		.uniqueResult();
		
		return paciente.getPeso();	
	}
	
	@Override
	public Paciente obtenerPaciente(Long id){

		final Session session = sessionFactory.getCurrentSession();
		Paciente resultado = (Paciente) session.createCriteria(Paciente.class)
											.add(Restrictions.eq("id", id))
											.uniqueResult();

		return resultado;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> obtenerListadoPacientes(){

		final Session session = sessionFactory.getCurrentSession();
		List<Paciente> resultado = (List<Paciente>) session.createCriteria(Paciente.class)
											.list();
		return resultado;
		
	}

	@Override
	public Long getIdPlanByIdPaciente(Long id) {
		
		final Session session = sessionFactory.getCurrentSession();
		Paciente paciente = (Paciente) session.createCriteria(Paciente.class)
							.add(Restrictions.eq("idUsuario", id))
							.uniqueResult();
		
		return paciente.getPlanAsociado_id();
	}
	
	@Override
	public void cargarPacientesIniciales() {
		
		final Session session = sessionFactory.getCurrentSession();
		
		Paciente pacienteInfo = new Paciente();
		pacienteInfo.setAltura((double)170);
		pacienteInfo.setEdad(30);
		pacienteInfo.setEjercicio(1);
		pacienteInfo.setFecha_inicio("16/07/2018");
		pacienteInfo.setId((long)1);
		pacienteInfo.setMedicoAsociado_id((long)1);
		pacienteInfo.setNombre("Pedro Smith");
		pacienteInfo.setPeso((double)88);
		pacienteInfo.setPlanAsociado_id((long)1);
		pacienteInfo.setSexo("Hombre");
		
		Paciente pacienteInfo2 = new Paciente();
		pacienteInfo2.setAltura((double)170);
		pacienteInfo2.setEdad(23);
		pacienteInfo2.setEjercicio(1);
		pacienteInfo2.setFecha_inicio("16/07/2018");
		pacienteInfo2.setId((long)2);
		pacienteInfo2.setMedicoAsociado_id((long)1);
		pacienteInfo2.setNombre("Jimena Atila");
		pacienteInfo2.setPeso((double)70);
		pacienteInfo2.setPlanAsociado_id((long)2);
		pacienteInfo2.setSexo("Mujer");
		
		Paciente pacienteInfo3 = new Paciente();
		pacienteInfo3.setAltura((double)175);
		pacienteInfo3.setEdad(30);
		pacienteInfo3.setEjercicio(2);
		pacienteInfo3.setFecha_inicio("16/07/2018");
		pacienteInfo3.setId((long)3);
		pacienteInfo3.setMedicoAsociado_id((long)1);
		pacienteInfo3.setNombre("Carlos Carrizo");
		pacienteInfo3.setPeso((double)87);
		pacienteInfo3.setPlanAsociado_id((long)3);
		pacienteInfo3.setSexo("Hombre");
		
		Paciente pacienteInfo4 = new Paciente();
		pacienteInfo4.setAltura((double)165);
		pacienteInfo4.setEdad(24);
		pacienteInfo4.setEjercicio(1);
		pacienteInfo4.setFecha_inicio("16/07/2018");
		pacienteInfo4.setId((long)4);
		pacienteInfo4.setMedicoAsociado_id((long)2);
		pacienteInfo4.setNombre("Pamela Borges");
		pacienteInfo4.setPeso((double)79);
		pacienteInfo4.setPlanAsociado_id((long)4);
		pacienteInfo4.setSexo("Mujer");
		
		Paciente pacienteInfo5 = new Paciente();
		pacienteInfo5.setAltura((double)172);
		pacienteInfo5.setEdad(21);
		pacienteInfo5.setEjercicio(2);
		pacienteInfo5.setFecha_inicio("16/07/2018");
		pacienteInfo5.setId((long)5);
		pacienteInfo5.setMedicoAsociado_id((long)2);
		pacienteInfo5.setNombre("Pablo Garcia");
		pacienteInfo5.setPeso((double)87);
		pacienteInfo5.setPlanAsociado_id((long)3);
		pacienteInfo5.setSexo("Hombre");
		
		session.save(pacienteInfo);
		session.save(pacienteInfo2);
		session.save(pacienteInfo3);
		session.save(pacienteInfo4);
		session.save(pacienteInfo5);
	}
}
