<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.*"%>
<%
	MemberVo m = (MemberVo) session.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>>My Info View</title>
<link rel="stylesheet" href="../../css/myStyle.css" />
</head>
<body>
	<h2 align="center">내 정보 보기</h2>
	<hr>
	<%
		if (m != null) {
	%>
	<section id="myinfo">
		<form action="../../mupdate.lo" method="post">
			<table>
				<tr>
					<td>ID :</td>
					<td><input type="text" name="id" value="<%=m.getId()%>"
						readonly="readonly"></td>
				</tr>
				<tr>
					<td>Password :</td>
					<td><input type="password" name="passwd"
						value="<%=m.getPasswd()%>"></td>
				</tr>
				<tr>
					<td>이름 :</td>
					<td><input type="text" name="name" value="<%=m.getName()%>"></td>
				</tr>
				<tr>
					<td>E-Mail :</td>
					<td><input type="email" name="email" value="<%=m.getEmail()%>"></td>
				</tr>
			</table>
			<p align="center">
				<button type="submit">회원 정보 수정</button>
				&nbsp; &nbsp;
				<button type="button"
					onclick="location.href='../../mdelete.lo?id=<%=m.getId()%>';">회원
					탈퇴</button>
			</p>
		</form>
	</section>
	<%
		} else {
	%>
	<jsp:forward page="enroll.html" />
	<!-- 가입하지 않은 사용자가 접속할 경우 enroll.html로 forwarding -->
	<%
		}
	%>
	<br>
	<br>
	<p align="center">
		<button type="button" onclick="location.href='../../index.jsp';">메인으로
			가기</button>
	</p>
</body>
</html>