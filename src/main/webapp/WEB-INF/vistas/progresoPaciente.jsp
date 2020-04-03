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
	    <script src="<c:url value="js/Chart.min.js" />"></script>
	</head>
	<body>
	

		
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
                    	<li><a class="btn" href="historialDeComidas">Historial Comidas</a></li>
                </ul>
            </nav>
		</header>
		<div class = "main container">
		
		<table class="table container">
			  <thead>
			    <tr>
			      <th scope="col">Fecha</th>
			      <th scope="col">Peso Ideal</th>
			      <th scope="col">Peso Registrado por Usuario</th>
			      <th scope="col">Evaluacion</th>
			    </tr>
			  </thead>
			  <tbody>
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

			  <div id="grafico"  class="mainbox col-md-8 col-sm-1 col-sm-offset-2">	
				<!--  <div class="mainbox">-->
				<br>
					<h2 class="center">Tiempo estimado en cumplir objetivo</h2>
					<c:set var="pesoPGPorMes" value="${(caloriasPGPorDia*4)/1000}" />
					<c:set var="contador" value="${peso}" />  
					<div class="chartjs-wrapper">
							<canvas id="chartjs-0" class="chartjs" width="undefined" height="undefined"></canvas>
							<script>
							new Chart	(document.getElementById("chartjs-0"),
											{"type":"line","data":
												{"labels":["",	<c:forEach items="${Lista}" var = "i">
				        					 					"${i.fecha}",
						      									</c:forEach>],
						      					"datasets":
													[{"label":"Peso Ideal","data":
														[
															${pesoInicial},
															<c:forEach items="${Lista}" var = "j">	        	 
															${j.pesoIdeal}, 			
															</c:forEach>
														],"fill":
									false,"borderColor":"rgb(192, 75, 192)","lineTension":
										0.1}
								
								,{"label":"Peso Registrado","data":[
																	${pesoInicial},
																	<c:forEach items="${Lista}" var = "j">	        	 
																	${j.pesoRegistrado}, 			
																	</c:forEach>
																	],"fill":
									false,"borderColor":"rgb(75, 192, 192)","lineTension":
										0.1}
													]		
												},"options":{}
											}
										); 
							</script>
					</div>
				</div>

			  </tbody>
		</table>
			

			
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>