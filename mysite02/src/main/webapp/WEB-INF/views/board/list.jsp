<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board?a=search" method="post">
					<input type="text" id="kwd" name="kwd" value="글 제목">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
							<tr>
								<th>번호</th>
								<th style="text-align:left">제목</th>
								<th>작성자</th>
								<th>조회수</th>
								<th>작성 날짜</th>
								<th>삭제 하기</th>
							</tr>	
							<c:forEach items='${list }' var='boardVo' varStatus='status'>	
							<tr>
								<td>${boardVo.no }</td>
								<td style="text-align:left; padding-left:0px"><a href="${pageContext.servletContext.contextPath }/board?a=view&no=${boardVo.no }&title=${boardVo.title}&regdate=${boardVo.reg_date}">${boardVo.title }</a></td>
								<td>${name }</td>
								<td>${boardVo.hit }</td>
								<td>${boardVo.reg_date }</td>
								<td><a href="${pageContext.servletContext.contextPath }/board?a=delete1&no=${boardVo.no}&title=${boardVo.title}" id="delete-book">삭제 하기</a></td>
							</tr>
							</c:forEach>								
				</table>
								
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board?a=write" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>