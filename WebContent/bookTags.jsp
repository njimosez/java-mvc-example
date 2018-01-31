
<!-- Header -->
<jsp:include page="header.jsp" />

<!-- JSTL includes -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.util.List,java.util.ArrayList"%>


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
			PUBHUB <small>Book Search by Tag</small>
		</h1>
		<hr class="book-primary">
		<c:choose>
		<c:when test="${empty tagging}">
			<div class="text-center">
				<p>
					No BookTag exist!<a
						class="small" href="BookPublishing"><span
						style="color: #8B0000"> View a book details and add the first tag</span></a>
				</p>

			</div>
			</c:when>
			<c:otherwise>

				<p>Click on Tag to view book details</p>
				<br>
				<table class=" table   table-responsive">

                      <tr>
					<c:forEach var="booktag" items="${books}" varStatus="rowCounter">


						<c:forEach var="tag" items="${booktag.bookTags}"
							varStatus="rowCounter">

							<c:if test="${rowCounter.count % 8 == 1}">
					
							</c:if>
							<td>
								<form action="ViewBookDetails?isbn=${booktag.isbn13}"
									method="get">
									<input type="hidden" name="isbn13" value="${booktag.isbn13}">
									<span>
										<button class="btn btn-success">
											<c:out value="${tag.tagName}" />
										</button>
									</span>
								</form>
							</td>

							<c:if
								test="${rowCounter.count % 8 == 0||rowCounter.count == fn:length(values)}">
					
							</c:if>

						</c:forEach>
					</c:forEach>
					</tr>
				</table>
			</c:otherwise>
		
		</c:choose>
	</div>
</header>

<!-- Footer -->
<jsp:include page="footer.jsp" />