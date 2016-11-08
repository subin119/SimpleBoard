<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${board.subject}</h1><br/>
	글쓴이 : ${board.user.userNickName}
	<hr/>
	${board.content}<br/><br/>
	조회수 : ${board.hit}
	추천수 : ${board.recommend}<br/><br/>
	
	<c:choose>
		<c:when test="${not empty board.displayFileName }">
			<a href="<c:url value="/board/doDownLoad/${board.boardId}"/>">${board.displayFileName}</a>
		</c:when>
		<c:otherwise>
			파일이 없습니다!
		</c:otherwise>
	</c:choose>
	
	<input type="button" value="목록보기" onclick="location.href='<c:url value="/board"/>'" />
	<input	type="button"  value="삭제" onclick="location.href='<c:url value="/deleteBoard/${board.boardId}"/>'"/>
</body>
</html>