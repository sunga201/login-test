<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<title>Insert title here</title>
</head>
<body>
	<!-- pageNum : 한번에 표시되는 페이지 수
		 totalPageNum : 전체 페이지 수
		 curPage : 현재 페이지
		 start : 첫 페이지 번호 (현재 13페이지, pageNum이 5인 경우 11)
		 end : 마지막 페이지 번호 (현재 13페이지, pageNum이 5인 경우 15)
		 testList : 데이터 리스트 
		 prev : prev 버튼을 누를 때 이동할 페이지 번호
		 next : next 버튼을 누를 때 이동할 페이지 번호
		 -->

	login test. ${curPage}
	<br/>
	<c:forEach var="tVal" items="${testList}" varStatus="status">
		${status.count} : ${tVal.testCd}   ${tVal.val}<br/>
	</c:forEach>
	<br/>
	<c:if test="${curPage>pageNum}">
		<a href="/test?page=${prev}">[prev]</a>
	</c:if>
	<c:forEach var="i" begin="${start}" end="${end}">
		<a href="/test?page=${i}" style="<c:if test='${curPage==i}'> color:red;</c:if>">[${i}]</a>
	</c:forEach>
	<fmt:parseNumber value="${(curPage-1)/pageNum}" integerOnly="true" var="a" />
	<fmt:parseNumber value="${(totalPageNum-1)/pageNum}" integerOnly="true" var="b" />
	<c:if test="${a != b}">
		<a href="/test?page=${next}">[next]</a>
	</c:if>
</body>
</html>