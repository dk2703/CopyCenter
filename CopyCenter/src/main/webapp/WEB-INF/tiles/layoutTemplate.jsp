<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script>
	function ValidateEmail(email) {
		var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		return expr.test(email);
	};

	$("#sgup").live("click", function() {
		if (!ValidateEmail($("#email").val())) {
			alert("Invalid email address.");
		} else {
			alert("Valid email address.");
		}
	});

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
</script>
<title>Insert title here</title>
</head>
<body>
	<table width="1260" class="table-bordered" cellpadding="10"
		cellspacing="8">
		<tr>
			<td height="50" colspan="2"><tiles:insertAttribute name="title" /></td>
		</tr>
		<tr>
			<td height="25" colspan="2"><tiles:insertAttribute name="header" /></td>
		</tr>
		<tr>
			<td width="250" height="500"><tiles:insertAttribute
					name="sidemenu" /></td>
			<td width="700" height="500"><tiles:insertAttribute name="body" /></td>
		</tr>
		<tr>
			<td height="30" colspan="2"><tiles:insertAttribute name="footer" />
			</td>
		</tr>
	</table>
</html>