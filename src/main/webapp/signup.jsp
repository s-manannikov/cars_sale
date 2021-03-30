<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
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
    <a href="<%=request.getContextPath()%>/new_post.jsp" title="Services">New Post</a>
    <a href="<%=request.getContextPath()%>/login.jsp" title="Sign in">Sign in</a>
    <a href="<%=request.getContextPath()%>/signup.jsp" title="Sign up">Sign up</a>
</div>

<div class="wrapper">
    <div class="car-text">
        <form action="<%=request.getContextPath()%>/signup" method="post">
            <table class="login">
                <tr>
                    <td>
                        <label for="name">Name:</label>
                    </td>
                    <td>
                        <input type="text" name="name" id="name">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="email">Email:</label>
                    </td>
                    <td>
                        <input type="text" name="email" id="email">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password">Password:</label>
                    </td>
                    <td>
                        <input type="password" name="password" id="password">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="phone">Phone:</label>
                    </td>
                    <td>
                        <input type="text" name="phone" id="phone">
                    </td>
                </tr>
            </table>
            <div class="car-save">
                <button type="submit" onclick="return validateReg()">sign up</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>