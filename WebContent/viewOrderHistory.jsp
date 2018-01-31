
<!-- Header -->
<jsp:include page="header.jsp" />

<!-- JSTL includes -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- 	Just some stuff you need -->
<header>
	<div class="container">
		<c:if test="${! empty customer}">
			<p class="text-right">
				<i class="fa fa-user"></i> <span>Hello, <c:out
						value="${customer.firstname}" /></span>
			</p>

		</c:if>

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
			PUBHUB <small>My Account </small>
		</h1>
		<hr class="book-primary">




	</div>
	<!-- End container -->
</header>
<section>

	<div class="container">

		<div class="row" style="margin-top: 10px;">

			<div class="col-xs-12">
				<c:choose>
					<c:when test="${empty orderList}">
						<div class="text-center">
							<h1>
								You have not placed any other <a class="small"
									href="BookPublishing">Start Shopping</a>
							</h1>
						</div>
					</c:when>
					<c:otherwise>
						<div class="text-center">
							<h3>Order History</h3>

						</div>
						<br>


						<!--**************** Display  Order Summary ****************-->
						<c:forEach var="order" items="${orderList}">

							
							<div class="panel panel-default">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-2 text-left">
											Order Placed <br>
											<c:out value="${order.orderDate}" />
										</div>
										<div class="col-xs-2 text-center">
											Total <br> <b> $<c:out value="${order.grandTotal}" /></b>
										</div>
										<div class="col-xs-2 text-center">
											Type<br>
											<b><c:out value="${order.status}" /></b>
										</div>
										<div class="col-xs-2 text-center"></div>
										<div class="col-xs-4 text-right">
											Order N#:
											<c:out value="${order.id}" />
										</div>
									</div>
								</div>
								<!-- end of panel heading -->

								<div class="panel-body">

									<!--**************** display book items purchased **************** -->

									<c:forEach var="cartItem" items="${order.purchasedBooks}">
										<div class="row ">
											<div class="col-xs-2">
												<img src="http://placehold.it/140x100?text=IMAGE"
													class="img-responsive float-left" style="width: 50%"
													alt="Image">
											</div>
											<div class="col-xs-4 text-left">
												<b>Book Title:</b>
												<c:out value="${cartItem.books.title}" />
												<br> <b>ISBN</b>:
												<c:out value="${cartItem.books.isbn13}" />
												<form action="DownloadBook" method="get">
													<input type="hidden" name="isbn13"
														value="${cartItem.books.isbn13}">
													<button class="btn btn-success">Download</button>
												</form>
											</div>
											<div class="col-xs-2">
												<b>Unit Price</b><br>
												<fmt:formatNumber value="${cartItem.books.price}"
													type="CURRENCY" />
											</div>
											<div class="col-xs-2">
												<b>Qty Ordered</b><br>
												<c:out value="${cartItem.quantity}" />


											</div>
											<div class="col-xs-2 visible-lg text-right">
												<b>SubTotal</b><br> <span>$</span>
												<c:out value="${cartItem.subTotal}" />
											</div>

										</div>

									</c:forEach>

								</div>


								
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>


			</div>
			<!-- end column 12 -->


		</div>
		<!-- end page row -->

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
