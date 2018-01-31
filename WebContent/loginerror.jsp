	<%@ page isErrorPage="true" %>
	<!-- Header content -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<!-- Content for the body -->
	<header>
	  <div class="container">
	
		<%
			//display message if present
			out.println("<p class=\"alert alert-danger\">Invalid Login</p>");
		%>
		
		<h1>PUBHUB <small>Login error </small></h1>
		<hr class="book-primary">
		<p>
		  Sorry, your user name and password were not recognized <i class="fa fa-frown-o fa-3"></i>
		</p>
		<p>
 			 <a href="login.jsp" class="btn btn-primary btn-medium">Return to login page</a> or 
           <a href="register.jsp" class="btn btn-primary btn-medium">Register</a> 
 		</p>
 		
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>

	  </div>
	</header>

	<!-- Additional Facilities -->
	<jsp:include page="footer.jsp" />
