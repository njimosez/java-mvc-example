
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

		<h1>
			PUBHUB <small>Publishing Welcome Page</small>
		</h1>
		<hr class="book-primary">




	</div>
	<!-- End container -->
</header>
<section>

	<div class="container">
 <div class="row">
    <div class="col-sm-2 well" ></div>
    <div class="col-sm-8" >
    <div class="panel panel-success">
  <div class="panel-heading text-center">New Author Message</div>
  <div class="panel-body well"><p>
				Hello ${author.authorname},<br> 
			<p>	<small> Welcome to Pubhub publishing! <br>Kindly <a href="logout.jsp"><span style="color: #8B0000">
							log-out</span></a> and re-login for the system to refresh and enable you to
					start talking to your fans.
				</small>
			</p>
			
			</div>
</div>
    
    
    </div>
    <div class="col-sm-2" ></div>
  </div>
</div>
		





	<div class="clearfix"></div>





</section>

<section class="success">
	<div class="container">
		<div class="col-xs-12"></div>
		<!-- end column 12 -->
	</div>
</section>

<br>
<br>
<!-- Footer -->
<jsp:include page="footer.jsp" />
