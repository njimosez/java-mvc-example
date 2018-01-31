	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
	
	  
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>PUBHUB <small>Create an Account</small></h1>
		<hr class="book-primary">

		<form action="RegisterUser" id="myForm" method="post" class="form-horizontal" enctype="multipart/form-data">
		  <div class="form-group">
		    <label for="username" class="col-sm-4 control-label">Username</label>
		    <div class="col-sm-5">
		    <div class="input-group">
             <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		      <input type="text" class="form-control" id="Username" name="username" placeholder="Enter a Username" required="required" value="${param.Username }" />
		    </div>
		    </div>
		     </div>
		     <div class="form-group">
		    <label for="firstname" class="col-sm-4 control-label">First Name</label>
		    <div class="col-sm-5">
		    <div class="input-group">
             <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		      <input type="text" class="form-control"  name="firstname" placeholder="First Name" required="required" />
		    </div>
		    </div>
		     </div>
		     <div class="form-group">
		    <label for="lastname" class="col-sm-4 control-label">Last Name</label>
		    <div class="col-sm-5">
		    <div class="input-group">
             <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		      <input type="text" class="form-control"  name="lastname" placeholder="Last Name" required="required" />
		         </div>
		         </div>
		     </div>
		     <div class="form-group">
		    <label for="phone" class="col-sm-4 control-label">Phone #</label>
		    <div class="col-sm-5">
		    <div class="input-group">
             <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
		      <input type="text" class="form-control"  name="phone" placeholder="(555)325-1212" required="required" />
		    </div>
		    </div>
		     </div>
		     <div class="form-group">
		    <label for="title" class="col-sm-4 control-label">Email</label>
		    <div class="col-sm-5">
		    <div class="input-group">
             <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
		      <input type="email" class="form-control" id="email" name="email" placeholder=" Valid Email" required="required" value="${param.email}" />
		    </div>
		    </div>
		  </div>
		     <div class="form-group">
		    <label for="password" class="col-sm-4 control-label">Password</label>
		    <div class="col-sm-5">
		     <div class="input-group">
             <span class="input-group-addon"><i class="glyphicon glyphicon-paperclip"></i></span>
		      <input type="password" class="form-control" id="password" name="password" placeholder="Enter a password" required="required" value="${param.password }" />
		    </div>
		     </div>
		   </div>
	
		  <div class="form-group">
		  <div class="row">
		 
		    <div class="col-sm-offset-4 col-sm-1">
		    
		      <button type="submit" class="btn btn-info">Register</button>
		    </div>
		     <div class="col-sm-1">
		    
		      <input type="button" class="btn btn-default"onclick="myFunction()" value="Reset ">
		    </div>
		    </div>
		  </div>
		</form>	

	  </div>
	</header>

<script>

function myFunction() {
    document.getElementById("myForm").reset();
}
</script>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	