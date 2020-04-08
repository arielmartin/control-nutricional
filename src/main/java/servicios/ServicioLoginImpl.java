package servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UsuarioDao;
import modelo.Usuario;

// Implelemtacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete "servicios"
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateContext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	@Inject
	private UsuarioDao servicioLoginDao;

	@Override
	public Usuario consultarUsuario (Usuario usuario) {
		return servicioLoginDao.consultarUsuario(usuario);
	}
	@Override
	public Long crearUsuario (Usuario usuario) {
		return servicioLoginDao.crearUsuario(usuario);
	}
	
	@Override
	public void cargarUsuariosIniciales() {
		servicioLoginDao.cargarUsuariosIniciales();
	}
	@Override
	public boolean ckeckMailUsuarioar (Usuario usuario) {
		return servicioLoginDao.checkMailUsuario(usuario);
	}
}
