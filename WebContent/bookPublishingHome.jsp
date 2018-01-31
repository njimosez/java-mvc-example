
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
			PUBHUB <small>Book Publishing</small>
		</h1>
		<hr class="book-primary">

		<table
			class="table table-striped table-hover table-responsive pubhub-datatable">
			<thead>
				<tr>
					<td>ISBN-13:</td>
					<td>Title:</td>
					<td>Author:</td>
					<td>Publish Date:</td>
					<td>Price:</td>
					<td></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
			
				 <c:forEach var="book" items="${bookList}">
				 <c:url value="ViewAuthorDetails" var="authordetails">
						    <c:param name="author" value="${book.author}" />
						
						</c:url>
					<tr>
						<td><c:out value="${book.isbn13}" /></td>

						<td><c:out value="${book.title}" /></td>
						<td>
						<a 
									href='<c:out value="${authordetails}" />'>
									<span style="color:#8B0000" ><c:out value="${book.author}" /></span>
								</a>
						</td>
						<td><c:out value="${book.publishDate}" /></td>
						<td><fmt:formatNumber value="${book.price }" type="CURRENCY" /></td>
						
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
											<button class="btn btn-default">Add to Cart</button>
										</form></td>
								</c:otherwise>
                            
							</c:choose>
                             
						    
							
						

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
</header>

<!-- Footer -->
<jsp:include page="footer.jsp" />