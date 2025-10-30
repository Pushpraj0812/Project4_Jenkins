<%@page import="java.util.HashMap"%>
<%@page import="com.rays.pro4.controller.ORSView"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.controller.DoctorListCtl"%>
<%@page import="com.rays.pro4.Bean.DoctorBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>Doctor List</title>



<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>
</head>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.DoctorBean"
		scope="request"></jsp:useBean>
	<%@include file="Header.jsp"%>

	<form action="<%=ORSView.DOCTOR_LIST_CTL%>" method="post">

		<center>

			<div align="center">
				<h1>Doctor List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>

			</div>

			<%
				int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
				HashMap<String, String> map = (HashMap<String, String>) request.getAttribute("expertise");
			%>


			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<DoctorBean> it = list.iterator();

				if (list.size() != 0) {
			%>
			<table width="100%" align="center">
				<tr>
					<th></th>
					<td align="center"><label>Name:</label> <input type="text"
						name="Name" placeholder="Enter Name"
						value="<%=ServletUtility.getParameter("Name", request)%>">

						&emsp; <label>Mobile:</label> <input type="number" name="Mobile"
						placeholder="Enter Mobile"
						value="<%=ServletUtility.getParameter("Mobile", request)%>">

						&emsp; <label>Experties:</label> <%
 	String expertise = HTMLUtility.getList("expertise", bean.getExperties(), map);
 %> <%=expertise%> &emsp; <input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_SEARCH%>"> &nbsp; <input
						type="submit" name="operation" value="<%=DoctorListCtl.OP_RESET%>">
					</td>
				</tr>
			</table>
			<br>


			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">
				<tr style="background: Pink">
					<th><input type="checkbox" id="selectall">Select All</th>

					<th>S.No.</th>
					<th>Name</th>
					<th>Mobile</th>
					<th>Experties</th>
					<th>Edit</th>
				</tr>

				<%
					while (it.hasNext()) {
							bean = it.next();
				%>


				<tr align="center">
					<td><input type="checkbox" class="case" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getMobile()%></td>
					<td><%=bean.getExperties()%></td>
					<td><a href="DoctorCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>

			<table width="100%">
				<tr>
					<th></th>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=DoctorListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>

					<td><input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_NEW%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_NEXT%>"
						<%=(list.size() < pageSize || next == 0) ? "disabled" : ""%>></td>
				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=DoctorListCtl.OP_BACK%>"></td>
			<%
				}
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>

	</center>

	<%@include file="Footer.jsp"%>
</body>
</html>