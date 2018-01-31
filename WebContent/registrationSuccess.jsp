<!--
  ____                 _                  
 |  _ \ _____   ____ _| |_ _   _ _ __ ___ 
 | |_) / _ \ \ / / _` | __| | | | '__/ _ \
 |  _ <  __/\ V / (_| | |_| |_| | | |  __/
 |_| \_\___| \_/ \__,_|\__|\__,_|_|  \___|
 
-->

	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
	
		<%
			//display message if present
			if (request.getAttribute("message") != null){
				out.println("<p class=\"alert alert-danger\">" + request.getAttribute("message") + "</p>");
			}
		%>
		<h1>PUBHUB <small>Account Created</small></h1>
		
		<p>Welcome <b> ${name}</b>!</p>
		<p>Your account has been created.</p>
		<h3>Click <a class="text-success" href="BookPublishing">Here</a>to get started</h3>

	  </div>
	</header>
	
	<section>
	  <div class="container">
	    Another Section
	  </div>
	</section>

	<section class="success">
	  <div class="container">
	    A Third Section
	  </div>
	</section>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />