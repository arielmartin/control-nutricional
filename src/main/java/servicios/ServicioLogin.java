package servicios;

import modelo.Usuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioLogin {

	Usuario consultarUsuario(Usuario usuario);
	boolean crearUsuario(Usuario usuario);
	public void cargarUsuariosIniciales() ;
}
