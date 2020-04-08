<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="searchManager" class="com.timeWizard.GokivaBackEnd.intermediate.SearchManager"/>
<html>

<head>
    <title>search</title>
</head>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
      crossorigin="anonymous">
<link rel="stylesheet" href="global.css">
<link rel="stylesheet" href="nav.css">
<link rel="stylesheet" href="search.css">
<body>
<div class="globalContainer search">
    <div class=" container-fluid">
        <nav class="container-fluid navbar navbar-expand-lg navbar-light bg-kivaGreen">
            <a class="mx-4 navbar-brand text-white font-weight-bold item" href="/">GoKiva</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
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
                        <a class="nav-link text-white font-weight-bold" href="userSignIn">Sign
                            in</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="row mt-4">
            <div class="col-4">
                <h3 class="row text-monospace">Filter By</h3>
                <div class="row border border-success rounded mt-4 p-3">
                    <form class="container-fluid" action="" method="get">
                        <div class="form-group">
                            <label for="LoanId">Loan ID</label>
                            <input class="form-control" value="${param.LoanId}" name="LoanId" id="LoanId" placeholder="e.g. 233">
                        </div>

                        <div class="form-group">
                            <label for="Country">Country</label>
                            <input class="form-control" id="Country"
                                   value="${param.Country}"
                                   name="Country"
                                   placeholder="full name, e.g. Kenya">
                        </div>

                        <div class="form-group">
                            <label for="PartnerId">Partner ID</label>
                            <input class="form-control" id="PartnerId"
                                   name="PartnerId"
                                   value="${param.PartnerId}"
                                   placeholder="e.g. 100">
                        </div>

                        <div class="form-group">
                            <label for="Tag">Tag</label>
                            <input class="form-control" id="Tag"
                                   name="Tag"
                                   value="${param.Tag}"
                                   placeholder="e.g. Animals">
                        </div>

                        <label >Gender</label>
                        <div class="form-group">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio"
                                       name="Gender" id="male" value="male">
                                <label class="form-check-label" for="male">M</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio"
                                       name="Gender" id="female" value="female">
                                <label class="form-check-label" for="female">F</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio"
                                       name="Gender" id="group" value="group">
                                <label class="form-check-label" for="group">Group</label>
                            </div>
                        </div>
                        <div class="row">
                            <button type="submit" class="btn btn-outline-success ml-auto">submit</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-8">
                <div class="tableContainer">
                    <table class="table table-bordered rounded">
                        <thead>
                        <tr>
                            <th scope="col">LoanId</th>
                            <th scope="col">FundAmt</th>
                            <th scope="col">Time</th>
                            <th scope="col">DisbursedAmt</th>
                            <th scope="col">Time</th>
                            <th scope="col">PartnerId</th>
                            <th scope="col">Theme</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="list" scope="session"
                               value="${searchManager.getLoanInPage(searchObj)}"/>
                        <c:forEach items="${list}" var="item">
                            <tr>
                                <td>${item.getLoanId()}</td>
                                <td>${item.getFundAmount()}</td>
                                <td>${item.getFundTime()}</td>
                                <td>${item.getDisbursedAmount()}</td>
                                <td>${item.getFundTime()}</td>
                                <td>${item.getPartnerId()}</td>
                                <td>${item.getRegionCountry()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="buttonGrp row mt-3">
                    <form action="" class="ml-auto mr-4 ">
                        <input type="hidden" value="${param.LoanId}" name="LoanId">
                        <input type="hidden" value="${param.Country}" name="Country">
                        <input type="hidden" value="${param.PartnerId}" name="PartnerId">
                        <input type="hidden" value="${param.Tag}" name="Tag">
                        <input type="hidden" value="${param.Gender}" name="Gender">
                        <input type="hidden" value="${page-1}" name="page">
                        <button type="submit" class="btn  btn-light ml-auto mr-4 ">prev</button>
                    </form>
                    <form action="">
                        <input type="hidden" value="${param.LoanId}" name="LoanId">
                        <input type="hidden" value="${param.Country}" name="Country">
                        <input type="hidden" value="${param.PartnerId}" name="PartnerId">
                        <input type="hidden" value="${param.Tag}" name="Tag">
                        <input type="hidden" value="${param.Gender}" name="Gender">
                        <input type="hidden" value="${page+1}" name="page">
                        <button type="submit" class="btn  btn-light ">next</button>
                    </form>
                </div>
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
