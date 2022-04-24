<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register"/></title>
</head>
<body>
	<!-- 스프링MVC는 커맨드 객체의 첫글자를 소문자로 바꾼 클래스 이름과 속성 이름을 사용해서
	커맨드 객체를 뷰에 전달한다. -->
	<p>
		<spring:message code="register.done">
			<spring:argument value="${registerRequest.name}"/>
			<spring:argument value="${registerRequest.email}"/>
		</spring:message>
	</p>
	<p><a href="<c:url value='/main' />" >[<spring:message code="go.main"/>]</a></p>
</body>
</html>