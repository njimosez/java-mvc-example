
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
			PUBHUB <small>Hello ${author.authorname} </small>
		</h1>
		<hr class="book-primary">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<div class="panel panel-success">
					<div class="panel-heading text-center">Upload a profile image below to proceed</div>
					
				</div>


			</div>
			<div class="col-sm-4"></div>
		</div>
	</div>

	<form action="AddAuthorProfile" method="post" class="form-horizontal"
		enctype="multipart/form-data">

		<div class="form-group">
			<label for="content" class="col-sm-4 control-label">Author
				Portrait</label>
			<div class="col-sm-4">
				<input type="file" class="form-control" id="content" name="content"
					placeholder="Upload a portrait image" required="required"
					value="${param.content }" />
				<p>
					<small>Recommended picture size is 250 by 300 </small>
				</p>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-1">
				<button type="submit" class="btn btn-info">Upload</button>
			</div>
		</div>
	</form>
	

	</div><!-- End container -->
	
</header>
<section>

	<div class="container"></div>
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
