<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.timeWizard.GokivaBackEnd.DAO.BorrowersDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
      crossorigin="anonymous">
<link rel="stylesheet" href="global.css">
<link rel="stylesheet" href="home.css">
<link rel="stylesheet" href="nav.css">
<body>
<div class="globalContainer home">
    <div class="container-fluid">
        <div class="row">
            <nav class="container-fluid navbar navbar-expand-lg navbar-light bg-kivaGreen">
                <a class="mx-4 navbar-brand text-white font-weight-bold item" href="/">GoKiva</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse rounded-bottom navbar-collapse" id="navbarSupportedContent">
                    <c:if test="${sessionScope.userName != null}">
                        <ul class="navbar-nav mr-auto ml-4">
                            <li class="nav-item">
                                <a class="nav-link text-white font-weight-bold" href="category">Category</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white font-weight-bold" href="/dataVisualization">Visualization</a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link text-white font-weight-bold" href="history">History</a>
                            </li>
                            <li>
                                <a class="nav-link text-white font-weight-bold" href="search">Search</a>
                            </li>
                        </ul>
                        <ul class="navbar-nav ml-4">
                            <li class="nav-item text-white mr-4">
                                <div class="nav-link text-white ">
                                    Hi, <c:out value="${sessionScope.userName}"/>
                                </div>
                            </li>
                            <li class="nav-item text-white mr-4">
                                <a class="nav-link text-white font-weight-bold" href="userSignOut">Sign out</a>
                            </li>
                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.userName==null}">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item text-white mr-4">
                                <a class="nav-link text-white font-weight-bold" href="userSignIn">Sign in</a>
                            </li>
                        </ul>
                    </c:if>
                </div>
            </nav>
        </div>
        <div class="row">
            <div class="desc mt-4 ml-auto mr-5">
                <div class="text text-white mr-5">Microlending</div>
                <div class="text text-white mr-5">visualized.</div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script></script>
</html>
