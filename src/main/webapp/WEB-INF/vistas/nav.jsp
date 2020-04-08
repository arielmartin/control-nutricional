
            <nav>
                 <ul class="container">
                 	<c:if test="${ID==null}">
                    	<li><a class="btn active white" href="login">Login</a></li>
                    </c:if>
                    <li><a class="btn active white" href="home">Inicio</a></li>
                    <c:if test="${ROL=='medico'}" >
                    	<li><a class="btn" href="registrar_usuario">Registrar Paciente</a></li>  
                    	<li><a class="btn" href="registrarPesoDiario">Registrar Peso Diario</a></li>
                   		<li><a class="btn" href="progresoSeleccionarPaciente">Ver Progreso</a></li>
                   	</c:if>
                    <c:if test="${ROL!='medico' && idUsuario!=null}" >
                    	<li><a class="btn" href="verplan">Ver Plan</a></li>
                    	<li><a class="btn" href="registrarConsumoAlimento">Registrar Comida</a></li>
                    </c:if>
                    	
                    <c:if test="${idUsuario!=null}">
                    	<li><a class="btn" href="historialDeComidas">Historial Comidas</a></li>
                    </c:if>
                </ul>
            </nav>
