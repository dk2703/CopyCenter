<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>
	$(function() {
		$("#pg1, #pg2, #pg3, #other").change(function() {
			if ($("#other").is(":checked")) {
				$("#othertx").removeAttr("readonly");
				$("#othertx").focus();
			} else {
				$("#othertx").attr("value", "");
				$("#othertx").attr("readonly", true);
			}
		});
	});
	$(function() {
		$("#quantity").keypress(function(e) {
			var a = [];
			var k = e.which;

			for (i = 48; i < 58; i++)
				a.push(i);

			if (!(a.indexOf(k) >= 0))
				e.preventDefault();
		});
	});
</script>


<form:form commandName="printJob" action="uploadFile.htm" method="post"
	enctype="multipart/form-data" class="form-horizontal" role="form">
	<fieldset>
		<div class="form-group">
			<label for="jp" class="col-lg-2 control-label">Job Priority</label>
			<div class="col-sm-6">
				<form:errors path="jobPriority" class="text-danger" />
				<form:select path="jobPriority" class="form-control" id="jp">
					<form:option value="Normal"></form:option>
					<form:option value="High Priority">High Priority</form:option>
				</form:select>
			</div>
		</div>

		<div class="form-group">
			<label for="dd" class="col-lg-2 control-label">Due Date</label>
			<div class="col-sm-8">
				<form:errors path="dueDate" class="text-danger" />
				<br />
				<form:input path="dueDate" type="date" name="dueDate" id="dd" />
			</div>
		</div>

		<div class="from-group">
			<label class="col-lg-2 control-label">Job Type</label>
			<div class="col-lg-10">
				<form:errors path="jobType" class="text-danger" />
				<div class="radio">
					<label> <form:radiobutton path="jobType" value="Print"
							id="option1" />Print
					</label>
				</div>
				<div class="radio">
					<label> <form:radiobutton path="jobType"
							value="Print & Bind" id="option2" />Print and Bind
					</label>
				</div>
			</div>
			<br /> <br />
		</div>

		<div class="from-group">
			<label class="col-lg-2 control-label">Page Size</label>
			<div class="col-lg-10">
				<form:errors path="pageSize" class="text-danger" />
				<div class="radio">
					<label> <form:radiobutton id="pg1" name="pageSize"
							path="pageSize" value="8 1/2 x 11" />8 1/2 x 11
					</label>
				</div>
				<div class="radio">
					<label> <form:radiobutton id="pg2" name="pageSize"
							path="pageSize" value="8 1/2 x 14" />8 1/2 x 14
					</label>
				</div>
				<div class="radio">
					<label> <form:radiobutton id="pg3" name="pageSize"
							path="pageSize" value="11 x 17" />11 x 17
					</label>
				</div>
				<div class="radio">
					<label> <form:radiobutton id="other" path="pageSize" /> <input
						class="col-lg-4" type="text" name="pageSize" value=""
						placeholder="Custom Size" id="othertx" readonly>
					</label>
				</div>
				<br /> <br /> <br />
			</div>
		</div>



		<div class="form-group">
			<label for="des" class="col-lg-2 control-label">Description</label>
			<div class="col-lg-6">
				<form:textarea path="description" class="form-control" rows="3"
					id="des" placeholder="Description" />
				<span class="help-block">Please write a detailed description
					of how you want your print</span>
			</div>
		</div>

		<div class="from-group">
			<label class="col-lg-2 control-label">Print Type</label>
			<div class="col-lg-10">
				<form:errors path="printType" class="text-danger" />
				<div class="radio">
					<label> <form:radiobutton name="printType" path="printType"
							value="Color" id="option1" />Color
					</label>
				</div>
				<div class="radio">
					<label> <form:radiobutton name="printType" path="printType"
							value="Greyscale" id="option2" />Greyscale
					</label>
				</div>
				<br /> <br />
			</div>
		</div>

		<div class="form-group">
			<label for="quantity" class="col-lg-2 control-label">Quantity</label>
			<div class="col-lg-6">
				<form:errors path="quantity" class="text-danger" />
				<form:input path="quantity" class="form-control" id="quantity"
					placeholder="Quantity" />

			</div>
		</div>
		<div class="form-group">
			<label for="upld" class="col-lg-2 control-label"> File:</label>
			<div class="col-lg-6">
				<form:errors path="filename" class="text-danger" />
				<input id="upld" type="file" name="uploadedFile">
			</div>
		</div>

		<div class="form-group">
			<div class="col-lg-10 col-lg-offset-2">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</div>
	</fieldset>
</form:form>

