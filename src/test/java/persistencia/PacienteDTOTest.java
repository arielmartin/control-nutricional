package persistencia;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import modelo.*;
import springTest.SpringTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class PacienteDTOTest extends SpringTest{

	@Test @Transactional @Rollback
	public void probarQueElIMCdeSobrepeso(){
		
		Paciente paciente = new Paciente(80d, 170d);
		PacienteDTO pacienteDTO = new PacienteDTO();
		pacienteDTO.setPaciente(paciente);
		
		//IMC < 18,5 bajo peso
		//IMC > 25 sobrepeso
		//IMC > 18,5 && IMC < 25 Peso saludable
		
		Double imc=pacienteDTO.getIMC();
		String estado="peso saludable";
		if(imc>25){
			estado="sobrepeso";
		}
		if(imc<18.5){
			estado="bajo peso";
		}

		System.out.println(imc);
		System.out.println(estado);
		
		assertEquals(estado,"sobrepeso");
		
	}
	
}
