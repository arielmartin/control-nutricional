<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
                    <c:if test="${ROL!='medico'}" >
                    	<li><a class="btn" href="verplan">Ver Plan</a></li>
                   		<li><a class="btn active white"  href="registrarConsumoAlimento">Registrar Comida</a></li>
                   		<li><a class="btn" href="historialDeComidas">Historial Comidas</a></li>
                     </c:if>
                </ul>
            </nav>
            
		</header>
	
		<div class = "main container">	
		
			<div id="" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			
			<h3 class="">Registrar Comida</h3>
			<hr class="colorgraph">
			
			<form:form action="registrarComida" method="POST" modelAttribute="alimentoDTO">
				
			<table class="table container">
			<tbody>
				  <thead>
				    <tr>
				      <th scope="col">Comida</th>
				      <th scope="col">Kcal</th>
				      <th scope="col">Cant</th>
				      <th scope="col">Totales</th>
				    </tr>
				  </thead>
				    <tr>
				      <th scope="col">${comida.nombre}</th>
				      <th scope="col">${comida.caloriasPorPorcion}</th>
				      <th scope="col">${cantComida}</th>
				      <th scope="col">${comida.caloriasPorPorcion*cantComida}</th>
				    </tr>
				    <tr>
				      <th scope="col">Bebida</th>
				      <th scope="col"></th>
				      <th scope="col"></th>
				      <th scope="col"></th>
				    </tr>
				    <tr>
				      <th scope="col">${bebida.nombre}</th>
				      <th scope="col">${bebida.caloriasPorPorcion}</th>
				      <th scope="col">${cantBebida}</th>
				      <th scope="col">${bebida.caloriasPorPorcion*cantBebida}</th>
				    </tr>
				    <tr>
				      <th scope="col"><b>Total Kcal Comida</b></th>
				      <th scope="col"></th>
				      <th scope="col"></th>
				      <th scope="col">${totalCalorias}</th>
				    </tr>
				   </tbody>
				  <tbody>
			  </table>
			  	<c:if test="${totalCalorias >400 }">
			  		<h4 class="center"style="color:red">Advertencia!<br>Estas consumiendo demasiadas calorias</h4>
				</c:if>
				<c:if test="${totalCalorias <=400 }">
			  		<h4 class="center"style="color:green">Bien!<br>Esta cantidad de calorias se ajusta a tu plan</h4>
				</c:if>
				<form:input type="hidden" path="comida.id" value="${comida.id}"/>
				<form:input type="hidden" path="cantComida" value="${cantComida}"/>
				<form:input type="hidden" path="bebida.id" value="${bebida.id}"/>
				<form:input type="hidden" path="cantBebida" value="${cantBebida}"/>
				<form:input type="hidden" path="totalCalorias" value="${totalCalorias}"/>
				<br>
				<button class="btn btn-lg btn-primary btn-block" Type="Submit">Registrar</button>
			
			</form:form>
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