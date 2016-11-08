<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글쓰기</h1>
	<hr/>
	<form enctype="multipart/form-data" method="post" action="<c:url value="/board/doWriteBoard"/>">
		<label for="subject">제목</label>
		<input type="text" id="subject" name="subject"/><br/><br/>
		
		<label for="content" style="vertical-align: top;">내용</label>
		<textarea style="height: 300px; width: 300px;" id="content" name="content"></textarea>
		
		<input type="hidden" id="userId" name="userId" value="${sessionScope._USER_.userId}"/><br/>
		
		<input type="file" name="fileUpload" /><br/>
		<input type="submit" value="등록" style="margin-left: 250px;">
		<input type="button" value="목록보기" onclick="location.href='<c:url value="/board"/>'" />
	</form>
	
</body>
</html>