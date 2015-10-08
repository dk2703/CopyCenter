<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-8 col-md-offset-2">
	<table class="table table-bordered">
		<tr>
			<th>Job ID</th>
			<th>Due Date</th>
			<th>Job Priority</th>
			<th>Job Type</th>
			<th>Page Type</th>
			<th>Action</th>
		</tr>
		<c:forEach var="job" items="${printJobList}">
			<tr>
				<td>${job.printJobID}</td>
				<td>${job.dueDate}</td>
				<td>${job.jobPriority}</td>
				<td>${job.jobType}</td>
				<td>${job.pageSize}</td>
				<td><a href="downloadfile.htm?download=${job.printJobID}">Download
						File</a></td>
			</tr>
		</c:forEach>
	</table>
</div>