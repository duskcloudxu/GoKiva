# GoKiva​ :evergreen_tree:

Gokiva is a website for presenting [data provided by Kiva](https://www.kaggle.com/kiva/data-science-for-good-kiva-crowdfunding) in various query condition and visualizing data distributions as bar chart to give users a direct insight of this dataset of over 300k records.

## Introduction​ :page_with_curl:

This is group final proeject of our data system course. We decide to build a website based on kiva dataset on gaggle, and develop data sorting, filtering and visualization features. This project was implemented under `springboot` framework with `gradle` as build environment manager, and we use `mysql` as the database and `JDBC` as intermediate library between backend and database. For the front end, according to the requirement of our instructor, we use `jsp` to produce `HTML` code, and used library `JSTL` for sake of syntax convenience.

## workflow diagram​ :world_map:

![img](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdxyig1akyj30u010j7b0.jpg)

## implementation detail​ :books:

### backend data model

As the requirement of our course, we use DAO pattern to establish the connection relationship between backend and database. It is the lowest layer of backend and used by class of higher layer, e.g. DataVisManager, to **fetch data** in the mysql database.

### "frontend" data processing

In development, we notice that the DAO class required by Spec cannot fulfill our need to get data from mysql in desired form. Therefore we implemented the class `SearchManager` and class `DataVisManager` to get data via multiple DAO classes, and transform those data into the form we want.

Here the quote of frontend means that it is not a independent frontend like React or Vue application, which are common nowadays. Since we are supposed to use jsp, there is no easy way to build an independent frontend that make query to backend and fetch data. In our project, we use `JSTL` to provide some *syntax sugar*, and utilize intermediate class like `SearchManager` to provide data according to the *parameters* passed by URL(supported by `Springboot` controller module )

```jsp
 <table class="table table-bordered rounded">
                        <thead>
                        <tr>
                            <th scope="col">LoanId</th>
                            <th scope="col">FundAmt</th>
                            <th scope="col">Time</th>
                            <th scope="col">DisbursedAmt</th>
                            <th scope="col">PartnerId</th>
                            <th scope="col">Region</th>
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
                                <td>${item.getPartnerId()}</td>
                                <td>${item.getRegionCountry()}</td>
                                <td>${item.getTheme()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
```

## preview​ :eyeglasses:

- home page(before sign in)

![image-20200418005802120](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdxz70xx0vj31r20u0u10.jpg)

- sign in

![image-20200418010016313](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdxz94631wj31r20u0di2.jpg)

- Home page (after sign in)

  ![image-20200418010054892](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdxz9xe9lsj31r20u0x6s.jpg)

- Query loans by Category

  ![image-20200418010128335](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdxzadpr8lj31r40u0b29.jpg)

- Search page

  ![image-20200418010204146](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdxzaz9g5qj31r60u0gsp.jpg)

- Data visualization page

  ![image-20200418010301367](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdxzbz8l6kj31r20u0whu.jpg)

## Other libraries used in jsp via CDN :hammer:

- [BootStrap4](https://getbootstrap.com/docs/4.0/getting-started/introduction/)
- [ECharts](https://www.echartsjs.com/examples/en/)

- [JQuery](https://jquery.com/)

