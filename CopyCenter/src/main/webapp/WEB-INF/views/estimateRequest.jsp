

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="well well-sm">
				<form:form class="form-horizontal" commandName="estimateRequest"
					action="sendMessage.htm" method="get">
					<fieldset>
						<legend class="text-center">Estimate Request</legend>

						<!-- Name input-->
						<div class="form-group">
							<label class="col-md-3 control-label" for="topic">Topic</label>
							<div class="col-md-9">
								<form:errors path="topic" class="text-danger" />
								<form:input path="topic" id="topic" name="topic" type="text"
									placeholder="Topic" class="form-control" />
							</div>
						</div>

						<!-- Message body -->
						<div class="form-group">
							<label class="col-md-3 control-label" for="description">Description</label>
							<div class="col-md-9">
								<form:errors path="description" class="text-danger" />
								<form:textarea path="description" class="form-control"
									id="description" name="description"
									placeholder="Please enter your message here..." rows="5" />
							</div>
						</div>

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