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
<title>Products</title>
</head>
<body>
	<div class="container1">
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
							<li><a href="/logout">Logout</a></li>
						</ul>
					</div><!-- /.navbar-collapse -->
				</div><!-- /.container-fluid -->
			</nav>
		</div>
		<div class="container">
			<div class="row" id="products">

			</div>
			<div class="row">
				<div class="col-md-2">
					<c:choose>
						<c:when test="${user == 'ADMIN'}">
							<a class="btn btn-success" href="/products/add">Add Product</a>
						</c:when>
						<c:otherwise>
							<button class="btn btn-success placeOrder">Place Order</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js" type="text/javascript"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function () {
	  loadProductList();

	  $('.placeOrder').on('click', function () {
			placeOrder();
      });
	  $('#products').on('click', '.add', function () {
		 var orders = localStorage.getItem('orders');
		 if(orders===null)
           localStorage.setItem('orders', JSON.stringify({}));
		 orders=JSON.parse(localStorage.getItem('orders'));
		 orders[$(this).data('id')]=parseInt($(this).data('q'))+1;
        localStorage.setItem('orders', JSON.stringify(orders));
        $(this).data('q', parseInt($(this).data('q'))+1);
        refreshQuantity();

      })

    });
	function refreshQuantity(){
     var  orders=JSON.parse(localStorage.getItem('orders'));
      if(orders!==null){
        for(var key in orders){
          if(orders.hasOwnProperty(key)){
            $('#products').find('.add[data-id="'+key+'"]').next().text(orders[key]);
		  }
		}
	  }
	}

	function loadProductList(){
	  $.ajax({
		url:'/api/products/',
		success:function (s) {
		  	console.log(s);
		  	for(var key in s) {
              if (s.hasOwnProperty(key)) {
                var item ='<div class="col-sm-6 col-md-4"><div class="thumbnail"> ' +
                    '<div class="caption"> ' +
                    '<h3>'+s[key].productName+'</h3> ' +
                    '<p>'+s[key].description +'</p> ' +
                    '<p> ' +
                    '<a href="#" class="btn btn-default" role="button">Add To Wish List</a> ' +
                    '<a href="#" class="btn btn-default add" data-q="0" data-id="'+s[key].id+'" role="button">Add to Bucket</a> ' +
                    '<span></span>' +
					'</p> ' +
                    '</div> ' +
                    '</div> ' +
                    '</div>';
                $('#products').append(item);
              }
              refreshQuantity();
            }
        },
		error:function (e) {
		  console.log(e);
        }
	  })
	}

	function placeOrder(){
      var  orders=JSON.parse(localStorage.getItem('orders'));
      if(orders!==null){
        console.log(orders);
        $.ajax({
		  url:'/orders',
		  type:'post',
		  data:orders,
          async:false,
		  success:function (s) {
			console.log(s);
            $('#products').find('.add').each(function () {
			  	$(this).next().text('');
            });
            alert('Order Placed');
			localStorage.clear();

          },
		  error:function (e) {
			console.log(e);
			location.href='/login';
          }
		})
	  }
	  console.log(orders);
	}
</script>