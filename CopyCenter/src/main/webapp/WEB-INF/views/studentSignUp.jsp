<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!--<form:form method="post" commandName="student" action="studentReg.htm">
	<table>
		<tr>
			<td>First Name:</td>
			<td><form:input path="firstName" name="firstname" /></td>
			<td><form:errors path="firstName"></form:errors>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><form:input path="lastName" name="lastname" /></td>
			<td><form:errors path="lastName"></form:errors>
		</tr>
		<tr>
			<td>Email:</td>
			<td><form:input id="email" path="email" name="email" /></td>
		</tr>
		<tr>
			<td>Contact Number:</td>
			<td><form:input path="contactNumber" name="contactnumber" /></td>
		</tr>
		<tr>
			<td>Desired Username:</td>
			<td><form:input path="userAccount.userName" name="username" /></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><form:input path="userAccount.password" name="password" /></td>
		</tr>
		<tr>
			<form:hidden path="userAccount.role" value="Student" />
			<td><input id ="sgup" type="submit" value="Submit"></td>
		</tr>
	</table>
</form:form>-->


<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="well well-sm">
				<form:form class="form-horizontal" commandName="student"
					action="studentReg.htm" method="post">
					<fieldset>
						<legend class="text-center">Student Registration</legend>

						<!-- Name input-->
						<div class="form-group">
							<label class="col-md-3 control-label" for="name">First
								Name</label>
							<form:errors path="firstName" class="text-danger" />
							<div class="col-md-9">
								<form:input id="name" name="name" type="text" path="firstName"
									placeholder="Your name" class="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label" for="lname">Last
								Name</label>
							<form:errors path="lastName" class="text-danger" />
							<div class="col-md-9">
								<form:input id="lname" name="lname" type="text" path="lastName"
									placeholder="Your last name" class="form-control" />
							</div>
						</div>

						<!-- Email input-->
						<div class="form-group">
							<label class="col-md-3 control-label" for="email">Your
								E-mail</label>
							<form:errors path="email" class="text-danger" />
							<div class="col-md-9">
								<form:input id="email" name="email" type="text" path="email"
									placeholder="Your email" class="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label" for="contactno">
								Contact Number</label>
							<form:errors path="contactNumber" class="text-danger" />
							<div class="col-md-9">
								<form:input id="contactno" name="cotactno" type="text"
									path="contactNumber" placeholder="Your contact no"
									class="form-control" />
							</div>
						</div>


						<div class="form-group">
							<label class="col-md-3 control-label" for="userName">Desired
								Username</label>
							<form:errors path="userAccount.userName" class="text-danger" />
							<div class="col-md-9">
								<form:input id="userName" name="userName" type="text"
									path="userAccount.userName" placeholder="Your username"
									class="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label" for="pass">Password
							</label>
							<form:errors path="userAccount.password" class="text-danger" />
							<div class="col-md-9">

								<form:input id="pass" name="pass" type="password"
									path="userAccount.password" placeholder="Your password"
									class="form-control" />
							</div>
						</div>
						<form:hidden path="userAccount.role" value="Student" />
						<!-- Form actions -->
						<div class="form-group">
							<div class="col-md-12 text-right">
								<button type="submit" class="btn btn-primary btn-lg">Submit</button>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
</div>