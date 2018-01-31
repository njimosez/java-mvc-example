
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
			PUBHUB <small>Checkout </small>
		</h1>
		<hr class="book-primary">



		<div class="row" style="margin-top: 10px;">
			<div class="col-xs-12"></div>
			<!-- end column 12 -->
		</div>
		<!-- end page row -->
	</div>
	<!-- End container -->
</header>
<section>

	<div class="container">

		<div class="row" style="margin-top: 10px;">
			<c:choose>
				<c:when test="${empty cartItemList}">
					<div class="text-center">
						<h1>
							Oops! You need to...<a class="small" href="BookPublishing">Go
								Shopping</a>
						</h1>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-xs-12">
						<div class="text-center">
							<h3>Review Your Order</h3>
						</div>
						<br>
						<hr class="book">
						<div class="row">

							<div class="col-xs-2"></div>
							<div class="col-xs-4 text-left">
								<h4>Product</h4>
							</div>
							<div class="col-xs-2">
								<h4>Price</h4>
							</div>
							<div class="col-xs-2 text-center">
								<h4>Qty</h4>
							</div>
							<div class="col-xs-2 visible-lg">
								<h4>SubTotal</h4>
							</div>

						</div>
						<!--**************** display book items in cart **************** -->



						<c:forEach var="cartItem" items="${cartItemList}">
							<div class="row gborder">
								<div class="col-xs-2">
									<img src="http://placehold.it/140x100?text=IMAGE"
										class="img-responsive float-left" style="width: 50%"
										alt="Image">
								</div>
								<div class="col-xs-4 text-left">
									<b>Book Title:</b>
									<c:out value="${cartItem.books.title}" />
									<br>
									<b>ISBN</b>:
									<c:out value="${cartItem.books.isbn13}" />

								</div>
								<div class="col-xs-2">
									<fmt:formatNumber value="${cartItem.books.price}"
										type="CURRENCY" />
								</div>
								<div class="col-xs-2 text-center">
									<c:out value="${cartItem.quantity}" />
								</div>
								<div class="col-xs-2 visible-lg text-right">
									<span>$</span>
									<c:out value="${cartItem.subTotal}" />
								</div>

							</div>

						</c:forEach>


						<!--**************** display  cart total ****************-->

						<div class="row">


							<h4 class="col-xs-12 text-right">

								<strong style="font-size: large;">Order Total: (<span
									style="color: #db3208; font-size: large;">${fn:length(cartItemList)}</span>
									items):
								</strong>
								<c:set var="cart" value="${ShoppingCart}" />
								<span style="color: #db3208; font-szie: large;">$<span><c:out
											value="${cart.grandTotal}" /></span></span>
							</h4>
						</div>
						<div class="row">
							
							<form action="Checkout" method="post">
						            	<h4 class="col-xs-12 text-center">
										<input type="hidden" name="ShoppingCartId" value="${ShoppingCart.id}">
										<button class="btn btn-success btn-block">Place	your order</button>
										
										</h4>
									</form>

								
							


						</div>



					</div>
					<!-- end column 12 -->

				</c:otherwise>
			</c:choose>

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
