<!DOCTYPE html>
<%@page import="com.rays.pro4.Bean.UserBean"%>
<%@page import="com.rays.pro4.Bean.RoleBean"%>
<%@ page import="com.rays.pro4.controller.LoginCtl"%>
<%@page import="com.rays.pro4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Include jQuery -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<!-- Include jQuery UI -->
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>
<!-- Include jQuery UI CSS -->
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="/ORS_Project4/js/checkbBox.js"></script>
<script src="/ORS_Project4/js/datepicker.js"></script>
</head>
<body>
	<%
		UserBean userBean = (UserBean) session.getAttribute("user");
		if (userBean != null) {
	%>
	<h3>
		Hi,
		<%=userBean.getFirstName()%>(<%=session.getAttribute("role")%>)
	</h3>
	<%
		} else {
	%>
	Hi, Guest
	<%
		}
	%>
	<table>
		<tr>
			<th></th>
			<td width="90%"><a href="<%=ORSView.WELCOME_CTL%>">Welcome</b></a> |
				<%
				if (userBean != null) {
			%> <a
				href=" <%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</b></a>

				<%
					} else {
				%> <a href="<%=ORSView.LOGIN_CTL%>">Login</b></a> <%
 	}
 %></td>
			<td rowspan="2">
				<h1 align="right">
					<img src="<%=ORSView.APP_CONTEXT%>/img/customLogo.jpg" width="175"
						height="50">
				</h1>
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<%
			if (userBean != null) {
		%>

		<tr>
			<th></th>
			<td colspan="2"><font style="font-size: 18px"> <%
 	if (userBean.getRoleId() == RoleBean.ADMIN) {
 %> <a href="<%=ORSView.MY_PROFILE_CTL%>">My Profile</a> | <a
					href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a> | <a
					href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</a> | <a
					href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet Merit
						List</a> | <a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</b></a> | <a
					href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</b></a> | <a
					href="<%=ORSView.USER_CTL%>">Add User</b></a> | <a
					href="<%=ORSView.USER_LIST_CTL%>">User List</b></a> | <a
					href="<%=ORSView.COLLEGE_CTL%>">Add College</b></a> | <a
					href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> | <a
					href="<%=ORSView.ROLE_CTL%>">Add Role</b></a> | <a
					href="<%=ORSView.ROLE_LIST_CTL%>">Role List</b></a> | <a
					href="<%=ORSView.STUDENT_CTL%>">Add Student</b></a> | <a
					href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> | <a
					href="<%=ORSView.COURSE_CTL%>">Add Course</b></a> | <a
					href="<%=ORSView.COURSE_LIST_CTL%>">Course List</b></a> | <a
					href="<%=ORSView.SUBJECT_CTL%>">Add Subject</b></a> | <a
					href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</b></a> | <a
					href="<%=ORSView.FACULTY_CTL%>">Add Faculty</b></a> | <a
					href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</b></a> | <a
					href="<%=ORSView.TIMETABLE_CTL%>">Add TimeTable</b></a> | <a
					href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List</b></a>| <a
					href="<%=ORSView.DOCTOR_CTL%>">Add Doctor</b></a>| <a
					href="<%=ORSView.DOCTOR_LIST_CTL%>">Doctor List</b></a>| <a
					target="blank" href="<%=ORSView.JAVA_DOC_VIEW%>">Java Doc</b></a> <%
 	}
 %> <%
 	if (userBean.getRoleId() == RoleBean.STUDENT) {
 %> <a href="<%=ORSView.MY_PROFILE_CTL%>">My Profile</a> | <a
					href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> | <a
					href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</a> | href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet
					Merit List</a> |<a href="<%=ORSView.STUDENT_LIST_CTL%>">Student
						List</b>
				</a> | <a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</b></a> | <a
					href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</b></a> | <a
					href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</b></a> | <a
					href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List</b></a> | <%
					}
				%> <%
 	if (userBean.getRoleId() == RoleBean.KIOSK) {
 %> <a href="<%=ORSView.MY_PROFILE_CTL%>">My Profile</a> | <a
					href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> | <a
					href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</a> | href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet
					Merit List</a> |<a href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable
						List</b>
				</a> | <a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</b></a> | <%
					}
				%> <%
 	if (userBean.getRoleId() == RoleBean.FACULTY) {
 			// System.out.println("======>><><>"+userBean.getRoleId());
 %> <a href="<%=ORSView.MY_PROFILE_CTL%>">My Profile</a> | <a
					href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</b></a> | <a
					href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</a> | href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet
					Merit List</a> |<a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet
						List</b>
				</a> | <a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> | <a
					href="<%=ORSView.STUDENT_CTL%>">Add Student</b></a> | <a
					href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> | <a
					href="<%=ORSView.COURSE_LIST_CTL%>">Course List</b></a> | <a
					href="<%=ORSView.SUBJECT_CTL%>">Add Subject</b></a> | <br> <a
					href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</b></a> | <a
					href="<%=ORSView.TIMETABLE_CTL%>">Add TimeTable</b></a> | <a
					href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List</b></a> | <%
 	}
 %> <%
 	if (userBean.getRoleId() == RoleBean.COLLEGE) {
 			//    System.out.println("======>><><>"+userBean.getRoleId());
 %> <a href="<%=ORSView.MY_PROFILE_CTL%>">My Profile</a> | <a
					href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</b></a> | <a
					href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</a> | href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet
					Merit List</a> |<a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet
						List</b>
				</a> | <a href="<%=ORSView.STUDENT_CTL%>">Add Student</b></a> | <a
					href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> | <a
					href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</b></a> | <a
					href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List</b></a> | <a
					href="<%=ORSView.COURSE_LIST_CTL%>">Course List</b></a> | <%
					}
				%>

			</font></td>
		</tr>
		<%
			}
		%>
	</table>
	<hr>
</body>
</html>