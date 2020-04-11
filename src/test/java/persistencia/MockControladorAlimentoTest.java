package persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import controladores.ControladorAlimento;
import modelo.Alimento;
import servicios.ServicioAlimentos;
import springTest.SpringTest;

public class MockControladorAlimentoTest extends SpringTest{

	@Test @Rollback @Transactional
	public void testQuePruebairAregistrarConsumoAlimento(){
		
		ControladorAlimento miControlador = new ControladorAlimento();
		ServicioAlimentos servicioAlimentosMock = mock(ServicioAlimentos.class);
		miControlador.setServicioAlimentos(servicioAlimentosMock);
		
		@SuppressWarnings("unchecked")
		List<Alimento> listadoAlimentos = mock(List.class);
		@SuppressWarnings("unchecked")
		List<Alimento> listadoBebidas = mock(List.class);
		
		when(servicioAlimentosMock.obtenerListadoDeAlimentos("comida") ).thenReturn(listadoAlimentos);
		when(servicioAlimentosMock.obtenerListadoDeAlimentos("bebida") ).thenReturn(listadoBebidas);
		

		ModelAndView modelAndView = miControlador.irAregistrarConsumoAlimento();

		assertThat(modelAndView.getViewName() ).isEqualTo("registrarConsumoAlimento");
		
	}
	
}
