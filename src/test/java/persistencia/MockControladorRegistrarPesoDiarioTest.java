package persistencia;


import static org.mockito.Mockito.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import controladores.ControladorRegistrarPesoDiario;
import modelo.RegistrarPesoDiarioDTO;
import servicios.ServicioRegistrarPesoDiario;
import springTest.SpringTest;

import static org.assertj.core.api.Assertions.assertThat;


public class MockControladorRegistrarPesoDiarioTest extends SpringTest{
	
	@Test @Rollback @Transactional
	public void testQuePruebaDarAltaRegistroPeso() {
		

		Date fecha = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String f = dateFormat.format(fecha);
		RegistrarPesoDiarioDTO pesoDiarioMock = mock(RegistrarPesoDiarioDTO.class);
		HttpServletRequest requestMock=mock(HttpServletRequest.class);
		HttpSession sessionMock=mock(HttpSession.class);			
		ServicioRegistrarPesoDiario servicioRegistrarPesoMock = mock(ServicioRegistrarPesoDiario.class);
		ControladorRegistrarPesoDiario miControlador = new ControladorRegistrarPesoDiario();
		
		miControlador.setServicioRegistrarPesoDiario(servicioRegistrarPesoMock);
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(pesoDiarioMock.getPeso()).thenReturn((float)80);
		when(pesoDiarioMock.getIdPaciente()).thenReturn((long)1);

		when(servicioRegistrarPesoMock.ConsultarRegistroFecha(1, f)).thenReturn(true);
	
		ModelAndView modelAndView = miControlador.darAltaRegistroPeso(pesoDiarioMock, requestMock);
		
		assertThat(modelAndView.getViewName() ).isEqualTo("completarRegistroPesoDiario");
	}
	
}
