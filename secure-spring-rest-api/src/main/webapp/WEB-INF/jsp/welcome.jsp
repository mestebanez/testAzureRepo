<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="web" uri="http://web.com"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>

</head>
<body>
    <c:out value="${key}"></c:out>
    <c:if test = "${key=='jasmine'}">
        <web:jasmine title="${key}"/>
        <iframe src="https://www.w3schools.com"></iframe>
    </c:if>
    <c:if test = "${key!='jasmine'}">
        <web:jasmine title="${key}"/>
    </c:if>

    <c:forEach items="${attLst}" var="entry">

        <b>from session <c:out value="${entry}"></c:out></b>

    </c:forEach>



    <form:form action="/"
          method="post">
        <input type="submit"
               value="Win Money!"/>
        <input type="submit"
               value="Log out" />
    </form:form>

</body>

</html>