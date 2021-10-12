<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>MySite</h1>
			<ul>
				<li><a href="<%=request.getContextPath() %>/user?a=login">로그인</a><li>
				<li><a href="<%=request.getContextPath() %>/user?a=joinform">회원가입</a><li>
				<li><a href="<%=request.getContextPath() %>/user?a=updateform">회원정보수정</a><li>
				<li><a href="<%=request.getContextPath() %>/user?a=logout">로그아웃</a><li>
				<li>님 안녕하세요 ^^;</li>
			</ul>
		</div>
		<div id="content">
			<div id="user">
				<p class="jr-success">
					회원가입을 축하합니다.
					<br><br>
					<a href="<%=request.getContextPath() %>/user?a=login">로그인하기</a>
				</p>				
			</div>
		</div>
		<div id="navigation">
			<ul>
				<li><a href="<%=request.getContextPath() %>">한주형</a></li>
				<li><a href="<%=request.getContextPath() %>/guestbook">방명록</a></li>
				<li><a href="<%=request.getContextPath() %>/board">게시판</a></li>
			</ul>
		</div>
		<div id="footer">
			<p>(c)opyright 2015, 2016, 2017, 2018, 2019, 2020, 2021</p>
		</div>
	</div>
</body>
</html>