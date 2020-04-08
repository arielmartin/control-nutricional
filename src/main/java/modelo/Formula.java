package modelo;

//import java.text.DateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Locale;
//import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class Formula {

	public Double calcularIMC(Double peso, Double altura) {
		Double resultado;
		Double metros = altura / 100;
		resultado = (peso / (Math.pow(metros,2)));
		return resultado;
	}
	
	public Double calcularPesoIdeal(Double altura, String sexo) {
		Double resultado;
		if(sexo.equals("Hombre"))
			resultado = ((altura - 100) * 0.90);
		else
			resultado = ((altura - 100)) * 0.85;
			
		return resultado;
	}
	
	public Double calcularTMB(Double peso, Double altura, int edad, String sexo, int ejercicio) {
		Double resultado;
		if(sexo.equals("Hombre")) {
			resultado = (10 * peso) + (6.25 * altura) - (5 * edad) + 5;
		}
		else {
			resultado = (10 * peso) + (6.25 * altura) - (5 * edad) - 161;
		}
		
		switch (ejercicio) {
        case 1:
        		resultado = resultado * 1.2;
        		break;
        case 2:
				resultado = resultado * 1.375;
				break;
        case 3:
				resultado = resultado * 1.55;
				break;
        case 4:
				resultado = resultado * 1.725;
				break;
        case 5:
				resultado = resultado * 1.9;
				break;
		}
		
		return resultado;
	}
	
	public List<CompararProgresoDTO> generarListaComparacion(List<RegistrarPesoDiarioDTO> pesoRegistrado,List<ProgresoPesoIdeal> pesoIdeal) {
		List<CompararProgresoDTO> resultado = new ArrayList<CompararProgresoDTO>();
		
		for(ProgresoPesoIdeal ideal : pesoIdeal){
			
			for(RegistrarPesoDiarioDTO registrado : pesoRegistrado){
				
				if(ideal.getFecha().equals(registrado.getFecha())) {
					
				resultado.add(new CompararProgresoDTO(ideal.getFecha(),ideal.getPeso(),registrado.getPeso()));	
					
				}			
			}	
		}
		
		/*for(CompararProgresoDTO comparar : resultado){
			System.out.println("Fecha: "+comparar.getFecha()+" PesoIdeal: "+comparar.getPesoIdeal()+" PesoRegistrado: "+comparar.getPesoRegistrado());
		}*/
		
		return resultado;
		
	}
	
	public List<ProgresoPesoIdeal> generarListaPesoIdeal(String fecha, int dias, Double peso, Double caloriasPG){
		
		List<ProgresoPesoIdeal> resultado = new ArrayList<ProgresoPesoIdeal>();
		Float calPorDia;
		String f = fecha;
		Float pesoFinal;
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(f));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		calPorDia = (float) (caloriasPG / 7);
		
		Float p = (float)(peso*1);
		
		resultado.add(new ProgresoPesoIdeal(f,p));
		
		p = (float)(peso*1000);
		
		for(int i = 0;i<dias;i++) {
			
			c.add(Calendar.DATE, 1);
			f = format.format(c.getTime());;
			p = p - calPorDia;
			pesoFinal = p/1000;
			resultado.add(new ProgresoPesoIdeal(f,pesoFinal));
		}
		
		/*for(ProgresoPesoIdeal res : resultado){
			System.out.println("Fecha: "+res.getFecha()+" Peso: "+res.getPeso());
		}*/
		
		return resultado;
	}
	
}
