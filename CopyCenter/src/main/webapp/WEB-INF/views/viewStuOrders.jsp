<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-8">
	<table class="table ">
		<tr>
			<th>Order ID</th>
			<th>Date Sent</th>
			<th>Status</th>
			<th>Action</th>
		</tr>
		<c:forEach var="printOrder" items="${orderList}">
			<tr>
				<td>${printOrder.orderID}</td>
				<td>${printOrder.dateSent}</td>
				<td>${printOrder.status}</td>
				<c:if test="${printOrder.status =='Pending'}">
					<td><a href="cancel.htm?print=${printOrder.orderID}">Cancel Order</a>
					<a href="viewJobStudent.htm?pid=${printOrder.orderID}">View Jobs</a></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</div>