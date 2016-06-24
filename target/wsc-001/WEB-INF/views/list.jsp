<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<%@ include file="headerBar.jsp" %>

<h2>회원 리스트</h2><br/><br/>
<table style="border:1px solid black">
    <tr>
        <td></td>
        <td>이름</td>
        <td>이메일</td>
        <td>나이</td>
        <td></td>
    </tr>
    <c:forEach var = "u" varStatus = "varStatus" items="${users}">
        <tr>
            <td>${varStatus.count}</td>
            <td>${u.name}</td>
            <td>${u.email}</td>
            <td>${u.age}</td>
            <td>
                <c:if test="${u.id == user.id}">
                <a href="/user/edit?id=${u.id}">수정</a>
                </c:if>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a href="/user/delete?id=${u.id}">삭제</a>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>