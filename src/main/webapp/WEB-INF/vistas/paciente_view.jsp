<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	    <link rel="stylesheet" href="css/estilos.css">
	</head>
	<body>
	

		
		<header class="header container">
			<h1 class="logo">Control Nutricional</h1>
            <nav>
                 <ul class="container">
                    <li><a href="home">Inicio</a></li>
                    <c:if test="${ROL=='medico'}" >
                    	<li><a class="btn" href="registrar_usuario">Registrar Paciente</a></li>  
                    	<li><a class="btn" href="registrarPeso">Registrar Peso</a></li>
                   		<li><a class="btn" href="progresoSeleccionarPaciente">Ver Progreso</a></li>
                   	</c:if>
                    <c:if test="${ROL!='medico'}" >
                    	<li><a class="btn" href="verplan">Ver Plan</a></li>
                    	<li><a class="btn" href="registrarConsumoAlimento">Registrar Comida</a></li>
                    </c:if>
                </ul>
            </nav>
		</header>
		
		<div class = "main container">	
			<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				
				<!-- Stepper -->
				<div class="steps-form-2">
				    <div class="steps-row-2 setup-panel-2 d-flex justify-content-between">
				        <div class="steps-step-2">
				            <label class="btn-circle-2-selected" data-toggle="tooltip" data-placement="top"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></label><br>PACIENTE
				        </div>
				        <div class="steps-step-2">
				            <label class="btn-circle-2" data-toggle="tooltip" data-placement="top"><i class="glyphicon glyphicon-check" aria-hidden="true"></i></label><br>EXCLUSIONES
				        </div>
				        <div class="steps-step-2">
				            <label class="btn-circle-2" data-toggle="tooltip" data-placement="top"><i class="glyphicon glyphicon-cutlery" aria-hidden="true"></i></label><br>PLANES
				        </div>
				        <div class="steps-step-2">
				            <label class="btn-circle-2" data-toggle="tooltip" data-placement="top"><i class="glyphicon glyphicon-sort-by-attributes-alt" aria-hidden="true"></i></label><br>TU PLAN
				        </div>
				    </div>
				</div>
				
				<form:form action="exclusiones" method="POST" modelAttribute="pacienteDTO">
				
					<br>
					<form:label path="paciente.altura">Altura</form:label>
					<form:input path="paciente.altura" id="altura" type="number" class="form-control" required="required" placeholder="Exprese su altura en centimetros" min="150"/>
					<br>
					
					<form:label path="paciente.peso">Peso</form:label>
					<form:input path="paciente.peso" id="peso" type="number" class="form-control" required="required" placeholder="Exprese su peso en kilogramos" min="1"/> 
					<br>
					    		  
					<form:label path="paciente.sexo">Sexo</form:label> 
					<form:radiobutton path="paciente.sexo" value="Hombre" checked="checked"/> Hombre
					<form:radiobutton path="paciente.sexo" value="Mujer"/> Mujer <br>
					<br>
									
					<!--<form:label path="paciente.edad">Edad</form:label> 
					<form:input path="paciente.edad" id="edad" type="number" class="form-control" min="18" value=""/>
					<br>-->
					
					<form:label path="paciente.ejercicio">Ejercicio</form:label>
					<br>
					
					Poco o Ningun Ejercicio
					<form:radiobutton path="paciente.ejercicio" value="1" checked="checked"/>
					<br>
					
					Ejercicio Ligero (1-3 dias a la semana)
					<form:radiobutton path="paciente.ejercicio" value="2"/>
					<br>
					
					Ejercicio Moderado (3-5 dias a la semana)
					<form:radiobutton path="paciente.ejercicio" value="3"/> 
					<br>
					
					Ejercicio Fuerte (6-7 dias a la semana)
					<form:radiobutton path="paciente.ejercicio" value="4"/>
					<br>
					
					Ejercicio Extremo (2 veces al dia; todos los dias de la semana)
					<form:radiobutton path="paciente.ejercicio" value="5"/>
					<br>
					
					<form:input type="hidden" path="usuario.id" value="${usuario.id}"/>
					<br>
					
					<button class="btn btn-lg btn-primary btn-block" Type="Submit">Siguiente</button>
					
				</form:form>

			</div>
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>
