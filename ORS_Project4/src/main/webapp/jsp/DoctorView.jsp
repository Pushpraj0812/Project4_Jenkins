<%@page import="com.rays.pro4.controller.ORSView"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.controller.DoctorCtl"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>Doctor Page</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.DoctorBean"
		scope="request"></jsp:useBean>
	<%@ include file="Header.jsp"%>

	<center>

		<form action="<%=ORSView.DOCTOR_CTL%>" method="post">

			<div align="center">
				<h1>
					<%
						List rlist = (List) request.getAttribute("rlist");
					%>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<tr>
						<th><font size="5px"> Update Doctor </font></th>
					</tr>
					<%
						} else {
					%>
					<tr>
						<th><font size="5px"> Add Doctor </font></th>
					</tr>
					<%
						}
					%>
				</h1>

				<h3>
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>



			</div>

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>
				<tr>
					<th align="left">Name<span style="color: red">*</span> :
					</th>
					<td><input type="text" name="Name"
						placeholder="Enter Doctor Name" size="25"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("Name", request)%></font></td>

				</tr>

				<tr>
					<th style="padding: 1px"></th>
				</tr>
				<tr>
					<th style="padding: 1px"></th>
				</tr>



				<tr>
					<th align="left">Mobile<span style="color: red">*</span> :
					</th>
					<td><input type="number" name="Mobile" maxlength="10"
						style="width: 195px" placeholder="Enter  Mobile"
						value="<%=DataUtility.getStringData(bean.getMobile())%>"></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("Mobile", request)%></font></td>
				</tr>



				<tr>
					<th align="left">Experties<span style="color: red">*</span> :
					</th>
					<td><%=HTMLUtility.getList("rlist", String.valueOf(bean.getExperties()), rlist)%>
					</td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("Experties", request)%></font></td>
				</tr>
				<tr>
					<th style="padding: 1px"></th>
				</tr>



				<tr>
					<th style="padding: 1px"></th>
				</tr>


				<tr>
					<th></th>
					<%
						if (bean.getId() > 0) {
					%>
					<td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=DoctorCtl.OP_UPDATE%>"> &nbsp;
						&nbsp; <input type="submit" name="operation"
						value="<%=DoctorCtl.OP_CANCEL%>"></td>

					<%
						} else {
					%>

					<td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=DoctorCtl.OP_SAVE%>"> &nbsp;
						&nbsp; <input type="submit" name="operation"
						value="<%=DoctorCtl.OP_RESET%>"></td>

					<%
						}
					%>
				</tr>
			</table>
		</form>
	</center>

	<%@ include file="Footer.jsp"%>
</body>
</html>