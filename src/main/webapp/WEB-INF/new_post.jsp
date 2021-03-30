<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<style><%@include file="/scripts/style.css"%></style>
<script><%@include file="/scripts/app.js"%></script>

<!doctype html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css?family=Bentham|Playfair+Display|Raleway:400,500|Suranna|Trocchi" rel="stylesheet">

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

<div class="wrapper">
    <div class="car-text">
        <form action="<%=request.getContextPath()%>/new_post.jsp" method="post" enctype="multipart/form-data">
        <table class="new-post">
            <tr>
                <td>
                    <label for="city">City:</label>
                </td>
                <td>
                    <select name="city" id="city">
                        <c:forEach items="${cities}" var="city">
                            <option value="<c:out value="${city.id}"/>"><c:out value="${city.name}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="brand">Brand:</label>
                </td>
                <td>
                    <select name="brand" id="brand">
                        <c:forEach items="${brands}" var="brand">
                            <option value="<c:out value="${brand.id}"/>"><c:out value="${brand.name}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="model">Model:</label>
                </td>
                <td>
                    <input type="text" name="model" id="model">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="body">Body Style:</label>
                </td>
                <td>
                    <select name="body" id="body">
                        <c:forEach items="${bodies}" var="body">
                            <option value="<c:out value="${body.id}"/>"><c:out value="${body.name}"/></option>
                        </c:forEach>
                    </select><br>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="engine">Engine:</label>
                </td>
                <td>
                    <select name="engine" id="engine">
                        <c:forEach items="${engines}" var="engine">
                            <option value="<c:out value="${engine.id}"/>"><c:out value="${engine.name}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="transmission">Transmission:</label>
                </td>
                <td>
                    <select name="transmission" id="transmission">
                        <c:forEach items="${transmissions}" var="transmission">
                            <option value="<c:out value="${transmission.id}"/>"><c:out value="${transmission.name}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="hp">Horsepower:</label>
                </td>
                <td>
                    <input type="text" name="hp" id="hp">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="mileage">Mileage:</label>
                </td>
                <td>
                    <input type="text" name="mileage" id="mileage">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="year">Year:</label>
                </td>
                <td>
                    <input type="text" name="year" id="year">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="color">Color:</label>
                </td>
                <td>
                    <input type="text" name="color" id="color">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="price">Price:</label>
                </td>
                <td>
                    <input type="text" name="price" id="price">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="description">Description:</label>
                </td>
                <td>
                    <textarea id="description" name="description" rows="4" cols="50"></textarea>
                </td>
            </tr>
            <tr>
                <td>
                    Photo:
                </td>
                <td>
                    <input type="file" id="file" name="file">
                </td>
            </tr>
        </table>
            <div class="car-save">
                <button type="submit" onclick="return validatePost()">save</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>