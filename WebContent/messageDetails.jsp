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
		<h1>PUBHUB <small>Message Detail</small></h1>
		<div class="row">
  <div class="col-sm-4"></div>
  <div class="col-sm-4">
    <div class="panel panel-default">
     <div class="panel-heading text-left"><b>From</b> :<span style="color:#8B0000">${message.messageAuthor}</span>
    <br><b>To</b> :<a href="ViewAuthorDetails?author=${message.toAuthor}"><span style="color:#8B0000">${message.toAuthor}</span> 
    <img src="ViewAuthorImage?authorId=${message.authorId}" class="img-responsive pull-right"  style="width:10%" alt="Author Portait" ></a>
    </div>
    <div class="panel-body">${message.content}</div>
   
  </div>
  
  </div>
  <div class="col-sm-4"></div>
</div>
		 
	
	  </div>
	</header>
	
	<section>
	  <div class="container">
	  <div class="col-sm-12">
				<div class="row">
				
						<div class="col-xs-6 text-left">
						
                        <a class="btn btn-primary col-sm-8" href="ViewAuthorDetails?author=${message.toAuthor}"><i class="fa fa-chevron-left" ></i>  Author Page</a>
						
						</div>

						<div class="col-xs-6 text-right">
							<form action="ViewMessageList"	method="get">							 
								<input type="hidden" name="author" value="${message.toAuthor}">
								<button class="btn btn-primary  col-sm-8 "> <i
								class="fa fa-eye"></i> View All Messages  <i class="fa fa-chevron-right" ></i> </button>
							</form>
						</div>

					

				</div>


</div><!-- end row -->

	  </div>
	</section>

	<section class="success">
	  <div class="container">
	    A Third Section
	  </div>
	</section>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />