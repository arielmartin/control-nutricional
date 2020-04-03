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
                    	<li><a class="btn" href="registrarusuario">Registrar Paciente</a></li>  
                    	<li><a class="btn" href="registrarPesoDiario">Registrar Peso Diario</a></li>
                   		<li><a class="btn" href="progresoSeleccionarPaciente">Ver Progreso</a></li>
                   	</c:if>
                    <c:if test="${ROL!='medico'}" >
                    	<li><a class="btn" href="verplan">Ver Plan</a></li>
                    	<li><a class="btn" href="registrarConsumoAlimento">Registrar Comida</a></li>
                    </c:if>
                    <li><a class="btn active white" href="historialDeComidas">Historial Comidas</a></li>
                </ul>
            </nav>
		</header>
		
		<div class = "main container">
		
		<div id="table" class="mainbox col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
			
			<h3> Historial de Comidas</h3>
			<h4></h4> 

        <table id="acrylic">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Hora</th>  
                    <th>Comida</th>
                    <th>Bebida</th>
                    <th>KCal</th>    
                </tr>
            </thead>
            
            <tbody>
                
                <c:forEach items="${listaRegistros}" var="reg">
                <tr>
                    <td>${reg.fecha}</td>
                    <td>${reg.hora}</td>
                    <td>${reg.comida} (x${reg.cantComida})</td>
                    <td>${reg.bebida} (x${reg.cantBebida})</td>
                    <td>${reg.totalCalorias}</td>
                </tr>
                </c:forEach>
                
            </tbody>
        </table>
		</div>
	  	    
	  </div>
		
		<jsp:include page="footer.jsp"></jsp:include>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>