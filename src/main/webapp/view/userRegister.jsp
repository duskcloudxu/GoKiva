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
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse rounded-bottom navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto ml-4">
                        <li class="nav-item">
                            <a class="nav-link text-white font-weight-bold" href="category">Category</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white font-weight-bold" href="#">Regions</a>
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
                            <a class="nav-link text-white font-weight-bold" href="userSignIn">Sign in</a>
                        </li>
                    </ul>
                </div>
            </nav>


            <span class="border border-secondary">
            <div class="d-flex justify-content-center">

                <form action="${pageContext.request.contextPath}/userRegister" class="form-signin" method="post">
                    <div class="form-group">
                        <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
                    </div>
                    <div class="form-group">
                        <label for="userName" class="sr-only">User Name</label>
                        <input type="userName" id="userName" class="form-control" placeholder="User Name" required="" autofocus="">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="sr-only">Password</label>
                        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
                    </div>
                    <div class="form-group">
                        <label for="retypedPassword" class="sr-only">Retype Password</label>
                        <input type="password" id="retypedPassword" class="form-control" placeholder="Retype Password" required="">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="sr-only">First Name</label>
                        <input type="text" id="firstName" class="form-control" placeholder="First Name" required="">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="sr-only">Last Name</label>
                        <input type="text" id="lastName" class="form-control" placeholder="Last Name" required="">
                    </div>
                    <div class="form-group">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Create new account</button>
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
