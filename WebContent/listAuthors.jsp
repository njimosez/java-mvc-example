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
	<div class="container ">

		<%
			//display message if present
			if (request.getAttribute("message") != null) {
				out.println("<p class=\"alert alert-danger\">" + request.getAttribute("message") + "</p>");
			}
		%>
		<h1>
			PUBHUB <small>Published Authors</small>
		</h1>
		<div class="row">
			<div class="col-sm-4"></div>

			<div class="col-sm-4 text-center">
				<c:forEach var="authors" items="${authors}">


					<c:choose>
						<c:when
							test="${fn:containsIgnoreCase(authors.authorname, authorName)}">
								&nbsp;
								</c:when>

						<c:when test="${authors.isASubscriber() eq true}">
							<div class="panel ">
								<div class="panel-heading ">
									<a href="ViewAuthorDetails?author=${authors.authorname}"><span
										style="color: #8B0000"><i class="fa fa-eye"></i>
											${authors.authorname} page</span> <img
										src="http://via.placeholder.com/150x150"
										class="img-responsive pull-right" style="width: 10%"
										alt="Author Portait"> </a>
								</div>

								<div class="panel-body text-center">
									<form action="ViewMessageList" method="get">
										<input type="hidden" name="author"
											value="${authors.authorname}">
										<button class="btn btn-success ">
											<i class="fa fa-envelope"></i>${authors.authorname} Fan
											Messages
										</button>
									</form>
								</div>

							</div>
						</c:when>

						<c:otherwise>
							<div class="panel ">
								<div class="panel-heading ">
									<a href="ViewAuthorDetails?author=${authors.authorname}"><span
										style="color: #8B0000"><i class="fa fa-eye"></i>
											${authors.authorname} page</span> <img
										src="http://via.placeholder.com/150x150"
										class="img-responsive pull-right" style="width: 10%"
										alt="Author Portait"> </a>
								</div>

								<div class="panel-body text-center">

									<form action="AddSubscription" method="post">
										<input type="hidden" name="author"
											value="${authors.authorname}">
										<button class="btn btn-primary ">
											<i class="fa fa-plus"></i> Follow ${authors.authorname}
										</button>
									</form>

								</div>

							</div>

						</c:otherwise>

					</c:choose>


				</c:forEach>
			</div>

			<div class="col-sm-4"></div>
		</div>


	</div>
</header>

<section>
	<div class="container"></div>
</section>

<section class="success">
	<div class="container">A Third Section</div>
</section>


<!-- Footer -->
<jsp:include page="footer.jsp" />