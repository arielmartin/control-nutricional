package persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import controladores.ControladorLogin;
import modelo.Paciente;
import modelo.Usuario;
import servicios.ServicioLogin;
import servicios.ServicioPacientes;
import springTest.SpringTest;

public class MockControladorLoginTest extends SpringTest{
	
	@Test @Rollback @Transactional
	public void testQuePruebaValidarLogin(){
		
		ServicioLogin servicioLoginMock = mock(ServicioLogin.class);
		ServicioPacientes servicioPacientesMock = mock(ServicioPacientes.class);
		HttpServletRequest requestMock=mock(HttpServletRequest.class);
		HttpSession sessionMock=mock(HttpSession.class);
		ControladorLogin miControlador = new ControladorLogin();
		List<Paciente> pacientesMock = mock(List.class);
		
		miControlador.setServicioLogin(servicioLoginMock);
		miControlador.setServicioPacientes(servicioPacientesMock);
				
		Usuario usuarioMock = mock(Usuario.class);
		
		when(usuarioMock.getId()).thenReturn((long)1);
		when(usuarioMock.getNombre()).thenReturn("MockNombre");
		when(usuarioMock.getApellido()).thenReturn("MockApellido");
		when(usuarioMock.getEmail()).thenReturn("Mock@email.com");
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		
		when(servicioLoginMock.consultarUsuario(any(Usuario.class))).thenReturn(usuarioMock);
		when(servicioPacientesMock.obtenerListadoPacientes()).thenReturn(pacientesMock);
		
		ModelAndView modelAndView = miControlador.validarLogin(usuarioMock, requestMock);

		assertThat(modelAndView.getViewName() ).isEqualTo("home");
	}
}
