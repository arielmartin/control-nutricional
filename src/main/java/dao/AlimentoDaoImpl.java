package dao;


import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import modelo.*;

@Repository("AlimentoDao")
public class AlimentoDaoImpl implements AlimentoDao{

	@Inject
    private SessionFactory sessionFactory;
	
	@Override @SuppressWarnings("unchecked")
	public List<Alimento> obtenerListadoDeAlimentos(String tipo) {
		
		Session session = sessionFactory.getCurrentSession();
		
		List<Alimento> listaDeAlimentos = 
		(List<Alimento>) session.createCriteria(Alimento.class)
		.add(Restrictions.eq("tipo", tipo))
		.list();
		
		return listaDeAlimentos;
	}

	@Override @SuppressWarnings("unchecked")
	public List<Alimento> obtenerListadoDeAlimentos() {
		
		Session session = sessionFactory.getCurrentSession();
		
		List<Alimento> listaDeAlimentos = 
		(List<Alimento>) session.createCriteria(Alimento.class)
		.list();
		
		return listaDeAlimentos;
	}

	@Override
	public void guardarAlimento(Alimento alimento) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(alimento);		
	}
	
	@Override
	public Alimento getAlimentoById(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		Alimento alimento =
				(Alimento) session.createCriteria(Alimento.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		return alimento;
	}
	
	@Override
	public void guardarRegistroComida(HistorialComidas registro) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(registro);		
	}
	
	@Override @SuppressWarnings("unchecked")
	public List<HistorialComidas>  obtenerRegistroComidas(Long idPaciente) {
		
		Session session = sessionFactory.getCurrentSession();
		List<HistorialComidas> registro=
		(List<HistorialComidas>) session.createCriteria(HistorialComidas.class)
		.add(Restrictions.eq("idPaciente", idPaciente) )
		.list();
		
		return registro;
	}
	
}
