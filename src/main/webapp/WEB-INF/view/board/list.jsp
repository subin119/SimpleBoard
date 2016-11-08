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
	
	<h1>게시판</h1>
	<hr/>
	<table>
		<tr style="text-align: center;">
			<td>번호</td>
			<td>제목</td>
			<td>닉네임</td>
			<td>작성일</td>
			<td>조회수</td>
			<td>추천수</td>			
		</tr>
		<c:choose>
			<%-- IF --%>
			<c:when test="${not empty boardList.boardList}">
				<c:forEach items="${boardList.boardList}" var="board">
					<tr style="text-align: center;">
						<td>${board.boardId}</td>
						<td><a href="<c:url value="/board/detailBoard/${board.boardId}"/>">${board.subject}</a>
							<c:choose>
								<c:when test="${not empty board.displayFileName }">
									(${board.displayFileName})
								</c:when>
								<c:otherwise>
									(파일x)
								</c:otherwise>
							</c:choose>
						</td>
						<td>${board.user.userNickName}</td>
						<td>${board.createdDate}</td>
						<td>${board.hit}</td>			
						<td>${board.recommend}</td>			
					</tr>
				</c:forEach>
			</c:when>
			<%-- ELSE --%>
			<c:otherwise>
				<tr>
					<td colspan="6">조회된 데이터가 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<c:if test="${not empty sessionScope._USER_}"><br/>
	<input type="button" value="글쓰기" onclick="location.href='<c:url value="/board/writeBoard" />'"/>
	</c:if>
</body>
</html>