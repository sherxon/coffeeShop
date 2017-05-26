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
					<li class=""><a href="/">Products</a></li>
					<li class="active"><a href="/orders/view">Orders</a></li>
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
					<li><a href="/logout">Logout</a></li>
				</ul>
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>
</div>
<div class="container">
	<div class="row">
		<c:forEach items="${orders}" var="order">
			<ul>${order.orderDate} => ${order.person.firstName}
				<c:forEach items="${order.orderLines}" var="orderLine">
					<li>Product Name: ${orderLine.product.productName} </li>
					<li>Quantity: ${orderLine.quantity} </li>
				</c:forEach>
			</ul>
		</c:forEach>
	</div>
	<table id="table" class="table table-condensed table-striped table-bordered">
		<thead>
		<tr>
			<td>Order Id</td>
			<td>Order Date</td>
			<td>Product</td>
			<td>Quantity</td>
		</tr>
		</thead>
		<tbody>

		</tbody>
	</table>
</div>
</body>
</html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function () {
	  loadOrders();
    });
	function loadOrders(){
	  $.ajax({
		url:'/api/orders/',
		success:function (s) {
		  console.log(s);
		  for(var key in s){
		    if(s.hasOwnProperty(key)){
		      var order=s[key];

		      for(var k in order.orderLines){
		        if(order.orderLines.hasOwnProperty(k)){
                  	var tr=$('<tr>');
					var orderLine=order.orderLines[k];
                  	tr.append($('<td>').html(order.id));
                  	tr.append($('<td>').html(order.orderDate));
                  	tr.append($('<td>').html(orderLine.product.productName));
                  	tr.append($('<td>').html(orderLine.quantity));
                  	$('#table tbody').append(tr);
				}
			  }
			}
		  }
        },
		error:function (e) {
		  console.log(e);
        }
	  })
	}
</script>