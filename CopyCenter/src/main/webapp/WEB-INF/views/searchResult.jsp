
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<div class="row col-md-8">
		<table class="table table-striped custab">
			<thead>
				<tr>
					<th>Order ID</th>
					<th>Date Sent</th>
					<th>Status</th>
					<th>Sent By</th>
					<th class="text-center">Action</th>
				</tr>
			</thead>
			<c:forEach var="student" items="${searchedPrintOrder}">
				<c:forEach var="orders" items="${student.printOrdersList}">
					<c:choose>
						<c:when test="${orders.status=='Pending' }">
							<tr class="info">
								<td>${orders.orderID}</td>
								<td>${orders.dateSent}</td>
								<td>${orders.status}</td>
								<td>${student.firstName}&nbsp${student.lastName}</td>
								<td><a href="view.htm?print=${orders.orderID}">view</a>
									<c:if
										test="${orders.status=='Pending' || orders.status=='Cancel'}">
										<br />
										<a class="complete"
											href="complete.htm?print=${orders.orderID}">Mark As
											Complete</a>
									</c:if></td>
							</tr>
						</c:when>
						<c:when test="${orders.status=='Completed' }">
							<tr class="success">
								<td>${orders.orderID}</td>
								<td>${orders.dateSent}</td>
								<td>${orders.status}</td>
								<td>${student.firstName}&nbsp${student.lastName}</td>
								<td><a href="view.htm?print=${orders.orderID}">view</a>
									<c:if
										test="${orders.status=='Pending' || orders.status=='Cancel'}">
										<br />
										<a class="complete"
											href="complete.htm?print=${orders.orderID}">Mark As
											Complete</a>
									</c:if></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr class="danger">
								<td>${orders.orderID}</td>
								<td>${orders.dateSent}</td>
								<td>${orders.status}</td>
								<td>${student.firstName}&nbsp${student.lastName}</td>
								<td><a href="view.htm?print=${orders.orderID}">view</a>
									<c:if
										test="${orders.status=='Pending' || orders.status=='Cancel'}">
										<br />
										<a class="complete"
											href="complete.htm?print=${orders.orderID}">Mark As
											Complete</a>
									</c:if></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:forEach>
			<!--<c:forEach var="student" items="${stuList}">
				<c:choose>
					<c:when test="${student.printOrder.status=='Pending'}">
						<tr class="info">
							<td>${printOrder.orderID}</td>
							<td>${printOrder.dateSent}</td>
							<td>${printOrder.status}</td>
							<td>${printOrder.student.firstName}</td>
							<td><a href="view.htm?print=${printOrder.orderID}">view</a>
								<c:if
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
							<td><a href="view.htm?print=${printOrder.orderID}">view</a>
								<c:if test="${printOrder.status=='Pending'}">
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
			</c:forEach>-->
		</table>
	</div>
</div>
