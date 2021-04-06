<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<style><%@include file="/scripts/style.css"%></style>
<script><%@include file="/scripts/app.js"%></script>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Bentham|Playfair+Display|Raleway:400,500|Suranna|Trocchi" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <title>Cars Sale</title>
</head>

<body>
<div class="rc_nav" id="centered_nav">
    <a href="<%=request.getContextPath()%>/index.jsp" title="Home">Home</a>
    <a href="<%=request.getContextPath()%>/new_post.jsp" title="New Post">New Post</a>
    <%if (request.getSession().getAttribute("user") == null) {%>
    <a href="<%=request.getContextPath()%>/login.jsp" title="Sign in">Sign in</a>
    <a href="<%=request.getContextPath()%>/signup.jsp" title="Sign up">Sign up</a>
    <%} else {%>
    <a href="<%=request.getContextPath()%>/index.jsp?id=3" title="My Posts">My Posts</a>
    <a href="<%=request.getContextPath()%>/auth" title="Sign out">Sign out</a>
    <% } %>

</div>

<br>
<table class="filters">
    <tr>
        <td>
            <label for="search">Select search filter:</label>
        </td>
        <td>
            <select onChange="window.location.href=this.value" id="search" name="search">
                <option hidden>choose filter</option>
                <option value="<%=request.getContextPath()%>/index.jsp?id=0">all posts</option>
                <option value="<%=request.getContextPath()%>/index.jsp?id=2">yesterday's posts</option>
                <option value="<%=request.getContextPath()%>/index.jsp?id=1">posts with photo</option>
            </select>
        </td>
        <td>
        </td>
        <td>
            <label for="brand-search">Search by brand:</label>
        </td>
        <td>
            <select onChange="window.location.href=this.value" id="brand-search">
                <option hidden>select brand</option>
                <c:forEach items="${brands}" var="brand">
                <option value="<%=request.getContextPath()%>/index.jsp?brand=<c:out value="${brand.name}"/>"><c:out value="${brand.name}"/></option>
                </c:forEach>
            </select>
        </td>
    </tr>
</table>

<c:forEach items="${posts}" var="post">
<div class="wrapper">
    <div class="car-img">
        <a href="<c:url value='/download?id=${post.photo.id}'/>">
            <img src='<c:url value="/download?id=${post.photo.id}"/>'
                 width="300px"
                 height="200px" alt="<c:out value="${car.brand.name}"/>"/>
        </a>
    </div>
    <div class="car-info">
        <div class="car-text">
            <h1><c:out value="${post.car.brand.name}"/> <c:out value="${post.car.model}"/> (<c:out value="${post.car.year}"/>)</h1>
            <h2><c:out value="${post.city.name}"/></h2>
            <p>
                Body Style: <c:out value="${post.car.body.name}"/><br>
                Engine: <c:out value="${post.car.engine.name}"/><br>
                Transmission: <c:out value="${post.car.transmission.name}"/><br>
                Horsepower: <c:out value="${post.car.hp}"/><br>
                Year: <c:out value="${post.car.year}"/><br>
                Mileage: <c:out value="${post.car.mileage}"/> km<br>
                Color: <c:out value="${post.car.color}"/><br>
                <c:set var="date" value="${post.created}"/>
                Date: <fmt:formatDate type="both" value="${date}"/><br>
                Phone: <c:out value="${post.user.phone}"/><br><br>
                <c:out value="${post.description}"/>
            </p>
        </div>
        <div class="car-price-btn">
            <p><span><c:out value="${post.car.price}"/> </span>$</p>
            <c:if test="${post.status == 1}">
                <div class="car-sold">
                <button>sold</button>
                </div>
            </c:if>
            <c:if test="${sessionScope.user.id == post.user.id && post.status == 0}">
                <a href="<%=request.getContextPath()%>/sold?id=<c:out value="${post.car.id}"/>">
                    <button>sold</button>
                </a>
            </c:if>
        </div>
    </div>
</div>
</c:forEach>
</body>
</html>