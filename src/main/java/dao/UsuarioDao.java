package dao;

import modelo.Usuario;

// Interface que define los metodos del DAO de Usuarios.
public interface UsuarioDao {
	
	Usuario consultarUsuario (Usuario usuario);
	
	boolean crearUsuario (Usuario usuario);
	
	public void cargarUsuariosIniciales();
}
