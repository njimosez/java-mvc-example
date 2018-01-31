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
		<h1>PUBHUB <small>Login</small></h1>
		
		<form action="j_security_check" method="post" class="form-horizontal">
		
	<div class="form-group">
		<label class="control-label col-sm-4">Username:</label>
		<div class="col-sm-5">
			<input type="text" class="form-control" name="j_username" placeholder="Enter Username" />		
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4">Password:</label>
		<div class="col-sm-5">
			<input type="password" class="form-control" name="j_password" placeholder="Enter Password" />		
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary">Login</button>		
		</div>
	</div>
</form>
<p>Don't have an account? <a  href="RegisterUser"><span style="color:#8B0000"> Click here to register!</span></a></p>
	  </div>
	
	
	

	<section class="success">
	  <div class="container">
	 
	  </div>
	</section>

</header>
	<!-- Footer -->
	<jsp:include page="footer.jsp" />