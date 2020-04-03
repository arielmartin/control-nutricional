<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<script>
		function cambiarIDUsuario(id) {
		    document.getElementById('idUsuario').value= id;
		}
	</script>

		
		<header class="header container">
			<h1 class="logo">Control Nutricional</h1>
            <nav>
                 <ul class="container">
                    <li><a class="btn" href="home">Inicio</a></li>
                    <c:if test="${ROL=='medico'}" >
                    	<li><a class="btn" href="registrarusuario">Registrar Paciente</a></li>  
                    	<li><a class="btn" href="registrarPesoDiario">Registrar Peso Diario</a></li>
                   		<li><a class="btn active white" href="progresoSeleccionarPaciente">Ver Progreso</a></li>
                   	</c:if>
                    <c:if test="${ROL!='medico'}" >
                    	<li><a class="btn" href="verplan">Ver Plan</a></li>
                    	<li><a class="btn" href="registrarConsumoAlimento">Registrar Comida</a></li>
                    </c:if>
                </ul>
            </nav>
		</header>
		
		<div class = "main container">	
			<div class = "container">
				<div style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			    	<h3 class="form-signin-heading">Ver Progreso</h3>
					<br />
					<c:choose>
						<c:when test="${not empty error}">
					<hr class="colorgraph"><br>
							<%--Bloque que es visible si el elemento error no está vacío	--%>
					        <h4><span style="color:red;">${error}</span></h4>
					        <br>
				        </c:when>
				        <c:otherwise>
							<form:form action="progresoPaciente" method="POST" modelAttribute="paciente">
								<label>Paciente</label>				
								<form:select path="id" class="form-control">
								<c:forEach items="${listadoPacientes}" var = "i">
								<form:option value="${i.id}">${i.nombre}</form:option>
								</c:forEach>]
								</form:select>
								<br />
								<br />
								<button class="btn btn-lg btn-primary btn-block" Type="Submit">Siguiente</button>
								<br />
							</form:form>
						</c:otherwise>
					</c:choose>
						
			        
				</div>
			</div>
		</div>
			  
			  
			  
			  
		  <c:forEach items="${Lista}" var="listado">
		    <tr>
		      <td>${listado.fecha}</td>
		      <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${listado.pesoIdeal}"/> kgs.</td>
		      <td>${listado.pesoRegistrado} kgs.</td>
		      <c:if test="${listado.pesoIdeal >= listado.pesoRegistrado}">
		      <td style="background-color:#A3D444;">- <fmt:formatNumber type="number" maxFractionDigits="2" value="${listado.pesoIdeal - listado.pesoRegistrado}"/> kgs.</td> 
	       	  </c:if>	
	       	  <c:if test="${listado.pesoIdeal < listado.pesoRegistrado}">
		      <td style="background-color:#F04A58;">+ <fmt:formatNumber type="number" maxFractionDigits="2" value="${listado.pesoRegistrado - listado.pesoIdeal}"/> kgs.</td> 
	       	  </c:if>	
		    </tr>
		  </c:forEach> 

			
		
		<jsp:include page="footer.jsp"></jsp:include>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>