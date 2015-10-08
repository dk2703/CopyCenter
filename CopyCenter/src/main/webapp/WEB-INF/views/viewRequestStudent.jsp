
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<table class="table table-bordered">
	<tr>
		<th>From User</th>
		<th>Description</th>
		<th>Sent Date</th>
		<th>Action</th>
	</tr>
	<c:forEach var="requests" items="${requestList}">
		<tr>
			<td>${requests.fromUser}</td>
			<td>${requests.description}</td>
			<td>${requests.sentDate}</td>
			<td><a href="replyToStu.htm?nam=${requests.fromUser}">Reply</a></td>
		</tr>
	</c:forEach>
</table>
