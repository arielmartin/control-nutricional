package dao;


import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import modelo.Paciente;
import modelo.RegistrarPesoDiarioDTO;

@Repository("registrarPesoDiarioDao")
public class RegistrarPesoDiarioDaoImpl implements RegistrarPesoDiarioDao{
	
	@Inject
    private SessionFactory sessionFactory;
	
	
	@Override
	public boolean ConsultarRegistroFecha(int id, String fecha) {
		Long l = new Long(id);

		final Session session = sessionFactory.getCurrentSession();
		RegistrarPesoDiarioDTO resultado = (RegistrarPesoDiarioDTO) session.createCriteria(RegistrarPesoDiarioDTO.class)
											.add(Restrictions.eq("idPaciente", l))
											.add(Restrictions.eq("fecha", fecha))
											.uniqueResult();
		if(resultado != null) {
		return false;}
		else {
		return true;}
	}
	
	@Override
	public void RegistrarPesoDiario(RegistrarPesoDiarioDTO registrarPesoDiarioDTO) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(registrarPesoDiarioDTO);	
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistrarPesoDiarioDTO> ObtenerRegistros(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		List<RegistrarPesoDiarioDTO> resultado = 	session.createCriteria(RegistrarPesoDiarioDTO.class)
													.add(Restrictions.eq("idPaciente", id))
													.list();
		return resultado;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> ObtenerPacientes(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		List<Paciente> resultado = 	session.createCriteria(Paciente.class)
									.add(Restrictions.eq("medicoAsociado_id", id))
									.list();
		return resultado;
	}
	
	
	@Override
	public void cargarRegistrosIniciales() {
		
		final Session session = sessionFactory.getCurrentSession();
		
		RegistrarPesoDiarioDTO registro = new RegistrarPesoDiarioDTO();
		registro.setId((long)1);
		registro.setIdPaciente((long)1);
		registro.setFecha("17/07/2018");
		registro.setPeso((float)88);
		
		RegistrarPesoDiarioDTO registro2 = new RegistrarPesoDiarioDTO();
		registro2.setId((long)2);
		registro2.setIdPaciente((long)1);
		registro2.setFecha("18/07/2018");
		registro2.setPeso((float)87);
		
		RegistrarPesoDiarioDTO registro3 = new RegistrarPesoDiarioDTO();
		registro3.setId((long)3);
		registro3.setIdPaciente((long)1);
		registro3.setFecha("19/07/2018");
		registro3.setPeso((float)85);
		
		RegistrarPesoDiarioDTO registro4 = new RegistrarPesoDiarioDTO();
		registro4.setId((long)4);
		registro4.setIdPaciente((long)2);
		registro4.setFecha("17/07/2018");
		registro4.setPeso((float)70);
		
		RegistrarPesoDiarioDTO registro5 = new RegistrarPesoDiarioDTO();
		registro5.setId((long)5);
		registro5.setIdPaciente((long)2);
		registro5.setFecha("18/07/2018");
		registro5.setPeso((float)70);
		
		RegistrarPesoDiarioDTO registro6 = new RegistrarPesoDiarioDTO();
		registro6.setId((long)6);
		registro6.setIdPaciente((long)2);
		registro6.setFecha("19/07/2018");
		registro6.setPeso((float)69);
		
		RegistrarPesoDiarioDTO registro7 = new RegistrarPesoDiarioDTO();
		registro7.setId((long)7);
		registro7.setIdPaciente((long)3);
		registro7.setFecha("17/07/2018");
		registro7.setPeso((float)86);
		
		RegistrarPesoDiarioDTO registro8 = new RegistrarPesoDiarioDTO();
		registro8.setId((long)8);
		registro8.setIdPaciente((long)3);
		registro8.setFecha("18/07/2018");
		registro8.setPeso((float)85);

		RegistrarPesoDiarioDTO registro9 = new RegistrarPesoDiarioDTO();
		registro9.setId((long)9);
		registro9.setIdPaciente((long)3);
		registro9.setFecha("19/07/2018");
		registro9.setPeso((float)83);
		
		RegistrarPesoDiarioDTO registro10 = new RegistrarPesoDiarioDTO();
		registro10.setId((long)10);
		registro10.setIdPaciente((long)4);
		registro10.setFecha("17/07/2018");
		registro10.setPeso((float)79);
		
		RegistrarPesoDiarioDTO registro11 = new RegistrarPesoDiarioDTO();
		registro11.setId((long)11);
		registro11.setIdPaciente((long)4);
		registro11.setFecha("18/07/2018");
		registro11.setPeso((float)80);
		
		RegistrarPesoDiarioDTO registro12 = new RegistrarPesoDiarioDTO();
		registro12.setId((long)12);
		registro12.setIdPaciente((long)4);
		registro12.setFecha("19/07/2018");
		registro12.setPeso((float)79);
		
		RegistrarPesoDiarioDTO registro13 = new RegistrarPesoDiarioDTO();
		registro13.setId((long)13);
		registro13.setIdPaciente((long)5);
		registro13.setFecha("17/07/2018");
		registro13.setPeso((float)87);
		
		RegistrarPesoDiarioDTO registro14 = new RegistrarPesoDiarioDTO();
		registro14.setId((long)14);
		registro14.setIdPaciente((long)5);
		registro14.setFecha("18/07/2018");
		registro14.setPeso((float)86);
		
		RegistrarPesoDiarioDTO registro15 = new RegistrarPesoDiarioDTO();
		registro15.setId((long)15);
		registro15.setIdPaciente((long)5);
		registro15.setFecha("19/07/2018");
		registro15.setPeso((float)84);
		
		session.save(registro);
		session.save(registro2);
		session.save(registro3);
		session.save(registro4);
		session.save(registro5);
		session.save(registro6);
		session.save(registro7);
		session.save(registro8);
		session.save(registro9);
		session.save(registro10);
		session.save(registro11);
		session.save(registro12);
		session.save(registro13);
		session.save(registro14);
		session.save(registro15);
	}
}
