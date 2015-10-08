<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-offset-6">
	<h2>Message Sent</h2>
	<c:choose>
		<c:when test="${sessionScope.student!=null}">
			<a href="studentHome.htm"> Go To Home Page</a>
		</c:when>
		<c:otherwise>
			<a href="storeManagerHome.htm"> Go To Home Page</a>
		</c:otherwise>
	</c:choose>
</div>