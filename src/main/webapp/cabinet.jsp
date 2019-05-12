<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="internationalization.messages"/>

<html lang="${language}" style="background-color: #000000;">
    <head>
        <title><fmt:message key="cabinet_page.title"/></title>
    </head>
    <body style="background-color: rgba(255,255,255,0);">
        <div><jsp:include page="menuBar.jsp"></jsp:include></div>
        <div style="background-image: url(&quot;assets/img/1348229547604-e1432042866949.jpg&quot;);height: 650px;background-repeat: round;background-size: cover;">
                <h1>Personal Cabinet</h1>
                    <button onclick="location.href='/cabinet/profile?id=${sessionScope.loggedInUser.id}'"><fmt:message key="cabinet_page.profile_bn"/></button>
                    <button onclick="location.href='/cabinet/settings?id=${sessionScope.loggedInUser.id}'"><fmt:message key="cabinet_page.changepass_bn"/></button>
                    <button onclick="location.href='/cabinet/usercards?login=${sessionScope.loggedInUser.login}'"><fmt:message key="cabinet_page.mycards_bn"/></button>
        </div>
        <div><jsp:include page="footer.jsp"></jsp:include></div>
    </body>
</html>
