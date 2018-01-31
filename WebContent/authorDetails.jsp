
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
			PUBHUB <small> ${author.authorname} </small>
		</h1>
		<hr class="book-primary">




	</div>
	<!-- End container -->
</header>
<section>

	<div class="container">
	
<div class="row">

 <div class="col-sm-4" >
    
 <div class=" text-left"><img src="ViewAuthorImage?authorId=${author.authorid}" class="img-center" alt="Author Portait" ></div>
   <h4>Biography</h4>
 <div class=" text-left"><b>${author.authorname}</b>
mauris venenatis venenatis augue, eu dapibus dui. Integer condimentum felis at nisl tincidunt aliquet. 
Fusce eleifend, magna ut auctor malesuada, augue ipsum finibus mauris, eu finibus nunc libero eget velit. 
Integer nec nibh et leo varius vehicula.
</div>

</div>
<h4>Books By ${author.authorname}</h4>
<div class="col-sm-8" >
 <table
			class="table table-striped table-hover table-responsive pubhub-datatable">
			<thead>
				<tr>
					<td>ISBN-13:</td>
					<td>Title:</td>
					<td>Price:</td>
					
					<td></td>
				</tr>
			</thead>
			<tbody>
			
				 <c:forEach var="book" items="${authorBooks}">
					<tr>
						<td><c:out value="${book.isbn13}" /></td>

						<td><c:out value="${book.title}" /></td>					
						<td><fmt:formatNumber value="${book.price }" type="CURRENCY" /></td>
						<td><form action="ViewBookDetails?isbn=${book.isbn13}"
								method="get">
								<input type="hidden" name="isbn13" value="${book.isbn13}">
								<button class="btn btn-primary">Details</button>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>



</div>


</div><!-- end row -->
<br>
<div class="clearfix"></div>
<div class="row">

 <div class="col-sm-4" >
  
  
  <c:choose>
							 
								<c:when test="${author.isASubscriber() eq true}">
									 
									 	<form action="RemoveSubscription"
								method="post">
								<input type="hidden" name="author" value="${author.authorname}">
								<input type="hidden" name="subscriptionId" value="${subscription.subcriberid}">
								<button class="btn btn-danger col-sm-8"> <i
								class="fa fa-minus"></i> UNFOLLOW</button>
							</form>
								
								</c:when>
								
								<c:otherwise>
							
								<form action="AddSubscription"
								method="post">
								<input type="hidden" name="author" value="${author.authorname}">
								<button class="btn btn-warning col-sm-8"> <i
								class="fa fa-plus"></i> FOLLOW</button>
							</form>
								</c:otherwise>
                            
							</c:choose>
 
 </div>

	<div class="col-sm-8">
				<div class="row">
					<c:if test="${author.isASubscriber() eq true}">
						<div class="col-xs-6 text-left">
						<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo">Send a Message to ${author.authorname}</button>
  <div id="demo" class="collapse">
   <form action="SendMessage"
								method="post">
								 <div class="form-group">
		    <label for="content" class="col-sm-4 control-label"></label>
		   <textarea rows="5" class="form-control" id="content" name="content" placeholder=" Message content" required="required"  /></textarea>
		   
		  </div>
								<input type="hidden" name="author" value="${author.authorname}">
								<button class="btn btn-primary  col-sm-4 "> <i
								class="fa fa-paper-plane"></i> Submit</button>
							</form>
  </div>

						
						</div>

						<div class="col-xs-6 text-right">
							<form action="ViewMessageList"	method="get">							 
								<input type="hidden" name="author" value="${author.authorname}">
								<button class="btn btn-primary  col-sm-8 "> <i
								class="fa fa-envelope"></i> View Author Messages</button>
							</form>
						</div>

					</c:if>

				</div>


</div><!-- end row -->

<!-- end container -->
</div>
 </div> 
  
    
  
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
