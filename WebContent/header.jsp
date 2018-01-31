<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- JSTL includes -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PubHub</title>
<link rel="shortcut icon" href="resources/imgs/favicon.png"
	type="image/x-icon">

<!-- CSS - Custom fonts -->
<link href="resources/libraries/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">
<!-- <link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css"> -->

<!-- CSS - jQuery DataTables -->
<link
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css" />

<!-- CSS - Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="resources/libraries/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="resources/libraries/css/freelancer.css" />
<link rel="stylesheet" type="text/css"
	href="resources/libraries/css/half-slider.css" />

<!-- CSS - Custom -->
<link rel="stylesheet" type="text/css"
	href="resources/styles/custom.css" />

</head>
<body id="page-top" class="index ">

	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath }">PubHub</a>
		</div>
<c:if test="${! empty LogUser}">
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Book Publishing<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="BookPublishing">Home (Level 100)</a></li>
						<li class="divider"></li>
						<li><a href="PublishBook">Publish Book</a></li>
						<li class="divider"></li>
						<li><a href="SearchBookByTags">Book Tags</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Marketplace<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="BookPublishing">Home (Level 200)</a></li>
						<li class="divider"></li>
						<li><a href="ShoppingCart">Cart</a></li>
						<li><a href="ViewOrder">Orders</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Fan Outreach<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="ViewAllAuthors">Home (Level 300)</a></li>
						<li class="divider"></li>
						<li><a href="ViewAllAuthors">Follow/Unfollow</a></li>
							<c:if test="${fn:contains(role.rolename,'Author')}">
						<li><a href="ViewAuthorProfile">Talk to your fans</a></li>
						
						</c:if>
						
					</ul></li>
              <!--   <c:if test="${empty LogUser}"><li><a href="login.jsp">Login</a></li></c:if> -->

				

					<li class="dropdown"><a href="ViewOrder"
						class="dropdown-toggle" data-toggle="dropdown"><i
							class="fa fa-user"></i> <span>Hi <c:out
									value="${LogUser.firstname}" /></span> <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="ViewOrder">My Orders</a></li>
							<li class="divider"></li>
							<li><a href="logout.jsp">Logout</a></li>
						</ul></li>
			

				<c:choose>
					<c:when test="${empty cartItemList}">
						<li><a href="ShoppingCart"><i
								class="fa fa-shopping-cart"></i> <span class="badge">0</span> </a></li>
					</c:when>
					<c:otherwise>
						<li><a href="ShoppingCart"><i
								class="fa fa-shopping-cart"></i> <span class="badge">${fn:length(cartItemList)}</span>
						</a></li>
						<li><a href="checkout.jsp"><span
								class="btn btn-success btn-md ">Check Out</span></a></li>

					</c:otherwise>
				</c:choose>

			</ul>
				
		</div>
		</c:if>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>