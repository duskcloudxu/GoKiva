<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
      crossorigin="anonymous">
<link rel="stylesheet" href="global.css">
<link rel="stylesheet" href="nav.css">
<body>
<div class="globalContainer">
    <div class=" container-fluid">
        <nav class="container-fluid navbar navbar-expand-lg navbar-light bg-kivaGreen">
            <a class="mx-4 navbar-brand text-white font-weight-bold item" href="/">GoKiva</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </nav>


        <span class="border border-secondary">
            <div class="d-flex justify-content-center">
                <form class="form-signin" action="userAuth" method="post">
                    <div class="form-group">
                        <h1 class="h3 mb-3 font-weight-normal">Sign in below</h1>
                    </div>
                    <div class="form-group">
                        <label for="userName" class="sr-only">User Name</label>
                        <input type="userName"  name="userName" id="userName" class="form-control"
                               placeholder="User Name" required="" autofocus="">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="sr-only">Password</label>
                        <input type="password" name="password" id="inputPassword" class="form-control"
                               placeholder="Password" required="">
                    </div>
                    <div class="form-group">
                        <button class="btn btn-lg btn-primary btn-block"
                                type="submit">Sign in</button>
                    </div>
                    <div class="form-group">
                        <p>Or <a href="userRegister">Create New Account</a></p>
                    </div>
                </form>
            </div>
        </span>

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
