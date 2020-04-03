package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import modelo.Usuario;

import javax.inject.Inject;

// implelemtacion del DAO de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("usuarioDao")
public class UsuarioDaoImpl implements UsuarioDao {

	// Como todo dao maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	@Inject
    private SessionFactory sessionFactory;

	@Override
	public Usuario consultarUsuario(Usuario usuario) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del objeto recibido como parametro
		// uniqueResult da error si se encuentran mas de un resultado en la busqueda.
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword()))
				.uniqueResult();
	}
	
	@Override
	public boolean crearUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();	
		
		Usuario resultado = (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.uniqueResult();
		
		if(resultado != null) {
			return false;
		}
		else {
			Usuario nuevo = new Usuario();
			nuevo=usuario;
			session.save(usuario);
			return true;
		}
		
	}
	
	@Override
	public void cargarUsuariosIniciales() {
		
		final Session session = sessionFactory.getCurrentSession();
		
		Usuario doctor = new Usuario();
		doctor.setNombre("Gonzalo");
		doctor.setApellido("Garcia");
		doctor.setEmail("gonzalogarcia@gmail.com");
		doctor.setFechaNacimiento("20/03/1987");
		doctor.setRol("medico");
		doctor.setId((long)1);
		doctor.setPassword("");
		
		Usuario doctor2 = new Usuario();
		doctor2.setNombre("Gabriela");
		doctor2.setApellido("Perez");
		doctor2.setEmail("gabrielaperez@gmail.com");
		doctor2.setFechaNacimiento("15/05/1990");
		doctor2.setRol("medico");
		doctor2.setId((long)2);
		doctor2.setPassword("");
		
		session.save(doctor);
		session.save(doctor2);
		
		Usuario paciente = new Usuario();
		paciente.setNombre("Pedro");
		paciente.setApellido("Smith");
		paciente.setEmail("pedrosmith@gmail.com");
		paciente.setFechaNacimiento("12/08/1993");
		paciente.setRol("paciente");
		paciente.setId((long)3);
		paciente.setPassword("");
		
		Usuario paciente2 = new Usuario();
		paciente2.setNombre("Jimena");
		paciente2.setApellido("Atila");
		paciente2.setEmail("jimenaatila@gmail.com");
		paciente2.setFechaNacimiento("23/07/1995");
		paciente2.setRol("paciente");
		paciente2.setId((long)4);
		paciente2.setPassword("");
		
		Usuario paciente3 = new Usuario();
		paciente3.setNombre("Carlos");
		paciente3.setApellido("Carrizo");
		paciente3.setEmail("carloscarrizo@gmail.com");
		paciente3.setFechaNacimiento("1/07/1988");
		paciente3.setRol("paciente");
		paciente3.setId((long)5);
		paciente3.setPassword("");
		
		Usuario paciente4 = new Usuario();
		paciente4.setNombre("Pamela");
		paciente4.setApellido("Borges");
		paciente4.setEmail("pamelaborges@gmail.com");
		paciente4.setFechaNacimiento("11/11/1994");
		paciente4.setRol("paciente");
		paciente4.setId((long)6);
		paciente4.setPassword("");
		
		Usuario paciente5 = new Usuario();
		paciente5.setNombre("Pablo");
		paciente5.setApellido("Garcia");
		paciente5.setEmail("pablogarcia@gmail.com");
		paciente5.setFechaNacimiento("20/02/1997");
		paciente5.setRol("paciente");
		paciente5.setId((long)7);
		paciente5.setPassword("");
		
		session.save(paciente);
		session.save(paciente2);
		session.save(paciente3);
		session.save(paciente4);
		session.save(paciente5);

	}

}
