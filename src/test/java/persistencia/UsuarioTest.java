package persistencia;
import static org.assertj.core.api.Assertions.*;
//import static org.junit.Assert.assertEquals;


import javax.inject.Inject;


import org.junit.Test;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import dao.*;
import modelo.Usuario;
import springTest.SpringTest;


public class UsuarioTest extends SpringTest{
   
    @Inject
    private UsuarioDao dao;
   
    @Test @Rollback @Transactional
    public void ProbarQueSeGuardeUsuarioTest(){
       
       Usuario usuario = new Usuario();
       usuario.setEmail("ariel@live.com");
       usuario.setPassword("1234");
       
        getSession().save(usuario);
              
        //Usuario prueba = new Usuario();
        
        Usuario resultado = dao.consultarUsuario(usuario);
       
        assertThat(resultado).isNotNull();     
    }
    
    @Test @Rollback @Transactional
    public void probarQueSeCalculeLaEdad() {
    	
//    	Usuario usuario = new Usuario();
//    	//fecha de nacimiento en formato "dd/MM/yyyy"
//    	usuario.setFechaNacimiento("24/11/1980");
//    	
//    	System.out.println(usuario.getFechaNacimiento());
//    	int edad=usuario.getEdad();
//    	System.out.println(edad);
//    	
//    	assertEquals(edad, 39);
    }
    
}