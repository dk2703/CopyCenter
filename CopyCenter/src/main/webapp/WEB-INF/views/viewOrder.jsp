
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-8 col-md-offset-2">
	<table class="table table-striped ">
		<thead>
			<tr>
				<th>Order ID</th>
				<th>Date Sent</th>
				<th>Status</th>
				<th>Sent By</th>
				<th class="text-center">Action</th>
			</tr>
		</thead>
		<c:forEach var="printOrder" items="${orderList}">
			<c:choose>
				<c:when test="${printOrder.status=='Pending'}">
					<tr class="info">
						<td>${printOrder.orderID}</td>
						<td>${printOrder.dateSent}</td>
						<td>${printOrder.status}</td>
						<td>${printOrder.student.firstName}</td>
						<td><a href="view.htm?print=${printOrder.orderID}">view</a> <c:if
								test="${printOrder.status=='Pending' || printOrder.status=='Cancel'}">
								<br />
								<a class="complete"
									href="complete.htm?print=${printOrder.orderID}">Mark As
									Complete</a>
							</c:if></td>
					</tr>
				</c:when>
				<c:when test="${printOrder.status=='Completed'}">
					<tr class="success">
						<td>${printOrder.orderID}</td>
						<td>${printOrder.dateSent}</td>
						<td>${printOrder.status}</td>
						<td>${printOrder.student.firstName}</td>
						<td><a href="view.htm?print=${printOrder.orderID}">view</a> <c:if
								test="${printOrder.status=='Pending'}">
								<br />
								<a class="complete"
									href="complete.htm?print=${printOrder.orderID}">Mark As
									Complete</a>
							</c:if></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr class="danger">
						<td>${printOrder.orderID}</td>
						<td>${printOrder.dateSent}</td>
						<td>${printOrder.status}</td>
						<td>${printOrder.student.firstName}</td>
						<c:if test="${printOrder.status != 'Cancelled'}">
							<td><a href="view.htm?print=${printOrder.orderID}">view</a>
								<c:if test="${printOrder.status=='Pending'}">
									<br />
									<a class="complete"
										href="complete.htm?print=${printOrder.orderID}">Mark As
										Complete</a>
								</c:if></td>
						</c:if>
					</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</table>
</div>



