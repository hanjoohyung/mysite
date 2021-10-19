<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="paginate">
		<a href="javascript:goPage(${param.firstPageNo})" class="first">처음페이지</a> 
		<a href="javascript:goPage(${param.prevPageNo})" class="prev">이전페이지</a> 
		<span> <c:forEach var="i" begin="${param.startPageNo}"
				end="${param.endPageNo}" step="1">
				<c:choose>
					<c:when test="${i eq param.pageNo}">
						<a href="javascript:goPage(${i})" class="choice">${i}</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:goPage(${i})">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</span> <a href="javascript:goPage(${param.nextPageNo})" class="next">다음페이지</a> 
				<a href="javascript:goPage(${param.finalPageNo})" class="last">마지막페이지</a>
	</div>


	출처: https://unabated.tistory.com/entry/JSP-게시판-구현-시-Paging-처리-하기 [랄라라]
</body>
</html>