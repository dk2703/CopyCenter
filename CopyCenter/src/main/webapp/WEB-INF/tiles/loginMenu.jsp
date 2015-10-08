<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!--<form:form role="form" cssClass="form-horizontal" method="post"
	modelAttribute="userAccount" action="loggedIn.htm">
	<div class="form-group">
	<form:label path="userName" cssClass="col-sm-2 control-label">Username:</form:label>
	<form:input path="userName" name="username" />
	</div>
	password:<br />
	<form:input path="password" name="password" />

	<input type="submit" value="Log In" />
	<a href="registration.htm">New User Registration</a>
</form:form>-->

<form:form commandName="userAccount" action="loggedIn.htm"
	class="form-horizontal" role="form">

	<div class="form-group">
		<label for="username" class="col-sm-3 control-label">Username</label>
		<div class="col-sm-8">
		<form:errors path="userName" class="text-danger"/>
			<form:input path="userName" class="form-control" id="username"
				placeholder="Username" />
				
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-3 control-label">Password</label>
		<div class="col-sm-8">
		<form:errors path="password" class="text-danger"/>
			<form:input path="password" type="password" class="form-control"
				id="password" placeholder="Password" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-4 col-sm-10">
			<button type="submit" class="btn btn-default">Sign in</button>
		</div>
	</div>

	<div class="list-group">
		<a href="registration.htm" class="list-group-item">New User?</a>
	</div>
</form:form>