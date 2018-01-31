	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ page import="org.apache.log4j.*" %>
	
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
	        <%
	        Logger log = Logger.getLogger(getClass());	 
	     
				   log.setLevel(Level.ALL);
				
			%>
	    
	</c:when>
	</c:choose>
	
		<h1>PUBHUB <small>Create a Book Tag</small></h1>
		<hr class="book-primary">

		<form action="CreateTag" method="post" class="form-horizontal" enctype="multipart/form-data">
		  <div class="form-group">
		    <label for="isbn13" class="col-sm-4 control-label">ISBN</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="tagID" name="tagID" placeholder="Book ISBN" required="required" value="${param.tagID }" />
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="title" class="col-sm-4 control-label">Tag Name</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="tagName" name="tagName" placeholder="Tag Name" required="required" value="${param.tagName}" />
		    </div>
		  </div>
	
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-info">Create</button>
		    </div>
		  </div>
		</form>	

	  </div>
	</header>



	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	