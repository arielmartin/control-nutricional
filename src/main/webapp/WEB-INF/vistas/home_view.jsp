<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                 	<c:if test="${ID==null}">
                    	<li><a class="btn active white" href="login">Login</a></li>
                    </c:if>
                    <li><a class="btn active white" href="home">Inicio</a></li>
                    <c:if test="${ROL=='medico'}" >
                    	<li><a class="btn" href="registrar_usuario">Registrar Paciente</a></li>  
                    	<li><a class="btn" href="registrarPeso">Registrar Peso</a></li>
                   		<li><a class="btn" href="progresoSeleccionarPaciente">Ver Progreso</a></li>
                   	</c:if>
                    <c:if test="${ROL!='medico' && idUsuario!=null}" >
                    	<li><a class="btn" href="verplan">Ver Plan</a></li>
                    	<li><a class="btn" href="registrarConsumoAlimento">Registrar Comida</a></li>
                    </c:if>
 
                </ul>
            </nav>
            
		</header>
		
		<div class = "main container">	
		
		<div id="home" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			
			<h3 class="form-signin-heading">Bienvenido ${APELLIDO} ! Elige en el Menu.</h3>
			<hr class="colorgraph"><br>
			
			<c:if test="${ROL=='medico'}" >
			Paciente seleccionado: ${NOMBRE_PACIENTE}
			</c:if>
			<br>id usuario logeado ${ID}
			<br>id usuario del paciente ${idUsuario}

				<c:if test="${ROL=='medico'}" >
					<c:choose>
					
						<c:when test="${empty listadoPacientes}">
						
							<hr class="colorgraph"><br>
							<%--Bloque que es visible si el elemento error no esta vacio	
					        <h4><span style="color:red;">${error}</span></h4><br>--%>
					        <h4><span style="color:red;">No hay pacientes cargados en el sistema.</span></h4><br>
 
				        </c:when>
				        
				        <c:otherwise>
				        
							<form:form action="selectPaciente" method="POST" modelAttribute="paciente">
								<br>
								<label>Paciente</label>
										
								<form:select path="id" class="form-control">
								<c:forEach items="${listadoPacientes}" var = "i">
									<form:option value="${i.id}">${i.nombre}</form:option>
								</c:forEach>
								</form:select>
								
								<br />
								<br />
								<button class="btn btn-lg btn-primary btn-block" Type="Submit">Seleccionar</button>
								<br />
							</form:form>
							
						</c:otherwise>
						
					</c:choose>
				</c:if>
				
			</div>


			<%--Bloque que es visible si el elemento error no esta vacio	
				<c:if test="${not empty error}">
			        <h4>
			        <span>*********************************************************************************************</span><br>
			        <span>${error}</span><br>
			        <span>*********************************************************************************************</span><br>
			        </h4>
			        <br>
		        </c:if>	--%>
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>