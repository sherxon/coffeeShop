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
<title>You are in!</title>
</head>
<div class="container">
	<div class="row">
		<a href="/registration">Register</a>
		<form action="/login" method="POST" class="form-signin">
			<h3 class="form-signin-heading">Welcome</h3>
			<br/>
			<input type="text" id="email" name="email"  placeholder="Email"
				   class="form-control" /> <br/>
			<input type="password"  placeholder="Password"
				   id="password" name="password" class="form-control" /> <br />
			<button class="btn btn-lg btn-primary btn-block"
					name="Submit" value="Login" type="Submit">Login</button>
		</form>
	</div></div>


</body>
</html>