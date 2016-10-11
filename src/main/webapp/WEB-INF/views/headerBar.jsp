<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="user" value="${SPRING_SECURITY_CONTEXT.authentication.principal}"/>

<div class="align-right">
    <sec:authorize access="isAnonymous()">
        <a href="/user/signup">회원가입</a> &nbsp;
        <a href="/user/signin">로그인</a>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <span style="color: #ffffff">로그인 계정: ${user.email}</span> <span style="color: #ffffff">&nbsp;|&nbsp;</span>
        <a href="http://<%=request.getServerName()%>:8082/login.jsp" target="_blank">Im-memory 데이터베이스(H2) 관리</a> <span style="color: #ffffff">&nbsp;|&nbsp;</span>
        <a href="/user/signout">로그아웃</a>
    </sec:authorize>
</div>