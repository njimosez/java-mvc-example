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
	
		<h1>PUBHUB <small>Book Details - ${book.isbn13 }</small></h1>
		<hr class="book-primary">
		
		<table class="table table-striped table-hover table-responsive ">
			<thead>
				<tr>
					<td>ISBN-13:</td>
					<td>Title:</td>
					<td>Author:</td>
					<td>Publish Date:</td>
					<td>Price:</td>
					
					<td></td>
					
				</tr>
			</thead>
			<tbody>
				<c:url value="ViewAuthorDetails" var="authordetails">
						    <c:param name="author" value="${author.authorname}" />
						
						</c:url>
					<tr>
						<td><c:out value="${book.isbn13}" /></td>
						
						<td><c:out value="${book.title}" /></td>
						<td>
						<a 
									href='<c:out value="${authordetails}" />'>
									<span style="color:#8B0000" ><c:out value="${author.authorname}" /></span>
								</a>
						
						</td>
						<td><c:out value="${book.publishDate}" /></td>
						<td><fmt:formatNumber value="${book.price }" type="CURRENCY"/></td>
						 <c:forEach var="bookOrdered" items="${previousOrder}">
							
							 <c:if test="${bookOrdered.books.isbn13 eq book.isbn13}">
							  <c:set var="purchased" value="${bookOrdered.books.isbn13}"/>
							  
							 
							</c:if>
							 
							
						
							 </c:forEach>
							  <c:choose>
							 
								<c:when test="${fn:contains(purchased, book.isbn13)}">
								
								<td><form action="DownloadBook" method="get">
										<input type="hidden" name="isbn13"
											value="${book.isbn13}">
										<button class="btn btn-success">Download</button>
									</form></td>
									
								</c:when>
								
								<c:otherwise>
									 
									 	<td><form action="ShoppingCart" method="post">
											<input type="hidden" name="isbn13" value="${book.isbn13}">
											<button class="btn btn-danger">Add to Cart</button>
										</form></td>
								</c:otherwise>
                            
							</c:choose>
						
						
					</tr>
				
			</tbody>
		</table >

		<table class=" table-condensed table-responsive">
			<thead>
				<tr>
					<td></td>
					<td><form action="CreateTag?tagID=${book.isbn13}" method="get">
								<input type="hidden" name="tagID" value="${book.isbn13}">
								<button class="btn btn-primary">Add Tag</button>
							</form></td>
				</tr>
			<thead>
				<tr>
					<td><h4>Tags:</h4> </td>
					<c:forEach var="bookTag" items="${tags}" varStatus="rowCounter">
						<c:url value="DeleteBookTag" var="deleteLink">
						    <c:param name="tagName" value="${bookTag.tagName}" />
							<c:param name="isbn_13" value="${book.isbn13}" />
						</c:url>


						<c:if test="${rowCounter.count % 7 == 1}">

						</c:if>
						<td><p class="panel-body">
								<c:out value="${bookTag.tagName}" />

								<a class="text-danger"
									href='<c:out value="${deleteLink}" />'>
									<span class="glyphicon glyphicon-remove" onclick="if(!(confirm ('Are you sure you want to remove this Tag?'))) return false"></span>
								</a>

						<c:if
							test="${rowCounter.count % 7 == 0||rowCounter.count == fn:length(values)}">
				</tr>
				</c:if>

				</c:forEach>
		</table>





	</div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
