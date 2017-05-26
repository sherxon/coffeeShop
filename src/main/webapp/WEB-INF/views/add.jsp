<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="row1">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Brand</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/">Products</a></li>
					<li class=""><a href="/orders/view">Orders</a></li>
				</ul>
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/login">Login</a></li>
					<li><a href="/registration">Register</a></li>
				</ul>
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<form autocomplete="off" action="/products/add"
				   method="post" class="form-horizontal" role="form">
				<h2>Product Form</h2>
				<div class="form-group">
					<div class="col-sm-9">
						<input type="text" name="productName" placeholder="productName" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-9">
						<input type="text" name="description" placeholder="description" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-9">
						<input type="text" name="price" placeholder="price" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-9">
						<select name="productType" class="form-control">
							<option value="DINNER">DINNER</option>
							<option value="LUNCH">LUNCH</option>
							<option value="BREAKFAST">BREAKFAST</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-9">
						<button type="submit" class="btn btn-primary btn-block">Save</button>
					</div>
				</div>
				<h3>${successMessage}</h3>
			</form>
		</div>
	</div>
</div>


</body>
</html>