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
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.servletContext.contextPath }/board?a=search"
					method="post">
					<input type="text" id="kwd" name="kwd" value="글 제목"> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align: left">제목</th>
						<th>작성자 </th>
						<th>조회수</th>
						<th>작성 날짜</th>
						<th>삭제 하기</th>
					</tr>
					<c:set var='no=1' value='${fn:length(list) }' />
					<c:forEach items='${list }' var='boardVo' varStatus='status'>
						<tr>
							<td>${no+1 }</td>
							<c:choose>
								<c:when test="${boardVo.depth > 1}"> 
									<td style="text-align: left; padding-left: 0px"><c:forEach begin="2" end="${boardVo.depth}" var="i" varStatus='status'>&nbsp&nbsp&nbsp&nbsp</c:forEach>
									<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png"/>
									<a href="${pageContext.servletContext.contextPath }/board?a=view&no=${boardVo.no }&title=${boardVo.title}&order_no=${boardVo.order_no}&regdate=${boardVo.reg_date}
											&group_no=${boardVo.group_no}&depth=${boardVo.depth}">${boardVo.title }</a></td>
								
								
								</c:when>
								<c:otherwise>
							<td style="text-align: left; padding-left: 0px"><a href="${pageContext.servletContext.contextPath }/board?a=view&no=${boardVo.no }&title=${boardVo.title}&order_no=${boardVo.order_no}&regdate=${boardVo.reg_date}
																&group_no=${boardVo.group_no}&depth=${boardVo.depth}">${boardVo.title }</a></td>
								</c:otherwise>
							</c:choose>
							<td>${boardVo.name }</td>
							<td>${boardVo.hit }</td>
							<td>${boardVo.reg_date }</td>
							<td><a href="${pageContext.servletContext.contextPath }/board?a=delete1&no=${boardVo.no }&title=${boardVo.title}" id="delete-book">
									<img src="${pageContext.servletContext.contextPath }/assets/images/recycle.png" alt=""/></a></td>
						</tr>
						<c:set var="no" value="${no+1 }"></c:set>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:choose >
	      					<c:when test="${blockNo eq 1 }">◁
	      					</c:when>		
	      					<c:otherwise><a href="${pageContext.servletContext.contextPath }/board?pageNo=${((blockNo-2)*5)+1 }&blockNo=${blockNo-1 }">◀</a></c:otherwise>			
						</c:choose>
						<c:forEach begin="${start }" end="${end }" var="i" step="1">
							<c:choose >
	      						<c:when test="${i eq pageNo }">
	      							<li class="selected"><a href="${pageContext.servletContext.contextPath }/board?pageNo=${i }&blockNo=${blockNo }"> ${i }</a></li>
	      						</c:when>		
	      						<c:otherwise><li><a href="${pageContext.servletContext.contextPath }/board?pageNo=${i }&blockNo=${blockNo }"> ${i } </a></c:otherwise>			
							</c:choose>
						</c:forEach>
						<c:choose >
	      					<c:when test="${blockNo > pageCount/5}">▷
	      					</c:when>		
	      					<c:otherwise><a href="${pageContext.servletContext.contextPath }/board?pageNo=${((blockNo+1)*5)-4 }&blockNo=${blockNo+1 }">▶</a></c:otherwise>			
							</c:choose>
					</ul>
				</div>					
				<!-- pager 추가 -->

				

	<div class="bottom">
		<a href="${pageContext.servletContext.contextPath }/board?a=write" id="new-book">글쓰기</a>
	</div>
	</div>
	</div>
	<c:import url="/WEB-INF/views/includes/navigation.jsp" />
	<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>