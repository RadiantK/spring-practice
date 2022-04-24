<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register"/></title>
</head>
<body>
	
	<h2><spring:message code="member.info"/></h2>
	<!-- form:form의 method속성 기본값은 post action속성 기본값은 현재 URL 
	id는 기본값이 command가 되는데 커맨드 객체의 이름이 다르면 ModelAttribute=""로 설정한다. -->
	<form:form action="step3" modelAttribute="registerRequest">
		<p>
			<label><spring:message code="email"/>:<br/>
				<!-- id, name은 path 설정하고 value는 path로 지정한 커맨드 객체의 프로퍼티값이 됨 -->
				<form:input path="email"/>
				<!-- 에러메시지를 출력할 프로퍼티 이름을 지정 -->
				<form:errors path="email"/>
			</label>
		</p>
		<p>
			<label><spring:message code="name"/>:<br/>
				<form:input path="name"/>
				<form:errors path="name" />
			</label>
		</p>
		<p>
			<label><spring:message code="password"/>:<br/>
				<form:password path="password"/>
				<form:errors path="password" />
			</label>
		</p>
		<p>
			<label><spring:message code="password.confirm"/>:<br/>
				<form:password path="confirmPassword"/>
				<form:errors path="confirmPassword"/>
			</label>
		</p>
		<input type="submit" value="<spring:message code="register.btn"/>" />
	</form:form>
	
<%-- 
	<h2>회원 정보 입력</h2>
	<form action="step3" method="post">
		<p>
			<label>이메일:<br/>
				<input type="text" name="email" id="email" value="${registerRequest.email}" />
			</label>
		</p>
		<p>
			<label>이름:<br/>
				<input type="text" name="name" id="name" value="${registerRequest.name}" />
			</label>
		</p>
		<p>
			<label>비밀번호:<br/>
				<input type="password" name="password" id="password" />
			</label>
		</p>
		<p>
			<label>비밀번호 확인:<br/>
				<input type="password" name="confirmPassword" id="confirmPassword" />
			</label>
		</p>
		<input type="submit" value="가입 완료" />
	</form>
 --%>
	
</body>
</html>