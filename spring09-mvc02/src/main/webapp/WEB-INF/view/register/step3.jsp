<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<!-- 스프링MVC는 커맨드 객체의 첫글자를 소문자로 바꾼 클래스 이름과 속성 이름을 사용해서
	커맨드 객체를 뷰에 전달한다. -->
	<p><strong>${registerRequest.name}님</strong>회원 가입을 완료했습니다.</p>
	<p><a href="<c:url value='/main' />" >[첫 화면으로 이동]</a></p>
</body>
</html>