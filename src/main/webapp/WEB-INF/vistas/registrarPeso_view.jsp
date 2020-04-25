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
                    <li><a class="btn" href="home">Inicio</a></li>
                    <c:if test="${ROL=='medico'}" >
                    	<li><a class="btn" href="registrar_usuario">Registrar Paciente</a></li>  
                    	<li><a class="btn active white" href="registrarPeso">Registrar Peso</a></li>
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
			<div id="loginbox" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			
			<h3 class="form-signin-heading">Registrar Peso</h3>
			<hr class="colorgraph"><br>
			
			<c:choose>
					<c:when test="${not empty error}">
						
						<%--Bloque que es visible si el elemento error no está vacío	--%>
				        <h4><span style="color:red;">${error}</span></h4>
				        
			        </c:when>
			<c:otherwise>
			        
			<form:form action="completarRegistroPesoDiario" method="POST" modelAttribute="registrarPesoDiarioDTO">
				
				<label>Paciente</label>
				<form:select path="idPaciente" class="form-control">
				<c:forEach items="${listaPacientes}" var = "i">
				<form:option value="${i.id}">${i.nombre}</form:option>
				</c:forEach>]
					
				</form:select>
				<br>
				<form:label path="peso">Peso</form:label>
				<form:input path="peso" id="peso" type="number" step=".01" class="form-control" required="required" placeholder="Exprese su peso en kilogramos,gramos" /> 
				<br>
				<button class="btn btn-lg btn-primary btn-block" Type="Submit">Confirmar</button>
			
			</form:form>
			
			</c:otherwise>
				</c:choose>
			<%--Bloque que es visible si el elemento error no está vacío	--%>
				<c:if test="${not empty error}">
			        <h4><span>${error}</span></h4>
			        <br>
		        </c:if>	
			</div>
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>


