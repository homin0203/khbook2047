<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.*"%>
<%
	MemberVo m = (MemberVo) session.getAttribute("member");
%>
<nav>
	<!-- 메뉴 선택을 위한 nav 태그 작성 -->
	<ul>
		<li><a href="#">메뉴1</a></li>
		<li><a href="#">메뉴2</a></li>
		<li><a href="#">메뉴3</a></li>
		<li><a href="#">메뉴4</a></li>
	</ul>
</nav>
<%
	if (m == null) {
%>
<div class="box">
	<!-- 로그인 From -->
	<p>
		&nbsp; &nbsp;아이디 : <input type="text" name="userid" id="userid">
	</p>
	<p>
		비밀번호 : <input type="password" name="userpwd" id="userpwd">
	</p>
	<button type="button" id="btnLogin">로그인하기</button>
	&nbsp;&nbsp;
	<button type="button" id="btnEnroll"
		onclick="location.href='views/member/enroll.html';">회원가입</button>
</div>
<%
	} else {
%>
<div class="box">
	<h3>
		환영합니다,
		<%=m.getName()%>님!!!
	</h3>
	<p>오늘도 좋은 하루 되세요~!!</p>
	<button onclick="location.href='view/myInfo.jsp'">회원정보보기
	</button>
	<button onclick="location.href='logout.lo'">로그아웃</button>
</div>
<%
	}
%>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
 $(function() {
	 $("#btnLogin").on('click', function() {
		 $.ajax({ // JQuery 를 통한 ajax 호출 방식 사용
			 type : "POST",
			 url : "login.lo",
			 data : {
			 id : $("#userid").val(),
			 passwd : $("#userpwd").val()
		 	},
			 dataType : "json", // 전달받을 객체는 JSON 이다.
			 success : function(data) {
				if(data.result == "ok"){
		 			var text = "<h3>환영합니다, "+data.name+"님!!!</h3>"+
		 					"<p>오늘도 좋은 하루 되세요~!!</p>\<button onclick='location.href=\"view/myInfo.jsp\"'>회원정보보기</button>\<button onclick=\"location.href='logout.lo'\">로그아웃</button>";
		 			$(".box").html(text);
				 } else {
				 	alert("로그인 실패!\nID와 비밀번호를 다시 확인하세요.");
				 }
		 	},
			 error : function(request,status,error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			 }
		 });
	 });
 });
</script>
<hr style="clear: both;">
<!-- 이전 float 속성을 지우고 구분선을 표시한다. -->