<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dataVisualization</title>
</head>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
      crossorigin="anonymous">
<link rel="stylesheet" href="global.css">
<link rel="stylesheet" href="nav.css">
<link rel="stylesheet" href="dataV.css">
<body>
<div class="globalContainer">

    <div class=" container-fluid dataV">
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
            </div>
        </nav>
        <div class="row m-4">
            <div class="col-3   ">
                <div class="row">
                    <form class="p-3 container border border-success rounded" action=""
                          method="get">
                        <label class="font-weight-bold">X-AXIS</label>
                        <div class="form-group">
                            <div class="custom-control custom-checkbox">
                                <input type="radio" class="custom-control-input" name="XAxis"
                                ${param.XAxis==null?"checked":""}
                                ${param.XAxis.equals("Country")?"checked":""}
                                       value="Country" id="Country">
                                <label class="custom-control-label" for="Country">Country</label>
                            </div>
                            <div class="custom-control custom-checkbox">
                                <input type="radio" class="custom-control-input" name="XAxis"

                                ${param.XAxis.equals("Theme")?"checked":""}
                                       value="Theme" id="Theme">
                                <label class="custom-control-label" for="Theme">Theme</label>
                            </div>
                        </div>
                        <label class="font-weight-bold">Y-AXIS</label>
                        <div class="form-group">
                            <div class="custom-control custom-checkbox">
                                <input type="radio" class="custom-control-input" name="YAxis"
                                ${param.YAxis==null?"checked":""}
                                ${param.YAxis.equals("Loan")?"checked":""}
                                       value="Loan" id="LoanNum">
                                <label class="custom-control-label" for="LoanNum">Num of
                                    Loans</label>
                            </div>
                            <div class="custom-control custom-checkbox">
                                <input type="radio" class="custom-control-input" name="YAxis"
                                ${param.YAxis.equals("Dollar")?"checked":""}
                                       value="Dollar" id="TotalAmt">
                                <label class="custom-control-label" for="TotalAmt">Dollar
                                    Amount</label>
                            </div>
                        </div>
                        <div class="row">
                            <button type="submit" class="btn btn-outline-success ml-auto">submit
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-9 chartContainer" id="chartContainer">

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
<script src="https://cdn.jsdelivr.net/npm/echarts@4.7.0/dist/echarts.common.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/echarts@4.7.0/dist/echarts.common.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/echarts@4.7.0/dist/echarts.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/echarts@4.7.0/dist/echarts.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/echarts@4.7.0/dist/echarts.simple.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/echarts@4.7.0/dist/echarts.simple.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/echarts@4.7.0/dist/echarts-en.common.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/echarts@4.7.0/dist/echarts-en.common.min.js"></script>
<script type="text/javascript">


  var dom = document.getElementById("chartContainer");
  var myChart = echarts.init(dom);
  var app = {};
  option = null;
  option = {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
        label: {
          show: true
        }
      }
    },
    xAxis: {
      type: 'category',
      data: [
        <c:forEach items="${list}" var="item" varStatus="status">
        "${item.XAxis}"
        <c:if test="${!status.last}">
        ,
        </c:if>
        </c:forEach>
      ]
    },
    yAxis: {
      type: 'value'
    },
    series: [{
      data: [
        <c:forEach items="${list}" var="item" varStatus="status">
        ${item.YAxis}
        <c:if test="${!status.last}">
        ,
        </c:if>
        </c:forEach>
      ],
      type: 'bar',
      color: "#639a67",
      backgroundStyle: {
        color: 'rgba(220, 220, 220, 0.8)'
      }
    }],
    dataZoom: [
      {
        type: 'slider',
        show: true,
        start: 0,
        end: 10,
        handleSize: 10
      },
      {
        type: 'inside',
        start: 0,
        end: 100
      },
      {
        type: 'slider',
        show: true,
        yAxisIndex: 0,
        filterMode: 'empty',
        width: 12,
        height: '70%',
        handleSize: 8,
        showDataShadow: false,
        left: '93%'
      }
    ]
  };
  ;
  if (option && typeof option === "object") {
    myChart.setOption(option, true);
  }
</script>
</html>
