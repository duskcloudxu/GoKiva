package com.timeWizard.GokivaBackEnd.intermediate;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchManager {

  public List<FrontEndLoanModel> getLoanInPage(Map<String, String> searchObj)
      throws SQLException, IllegalAccessException {
    List<FrontEndLoanModel> res = new ArrayList<>();
    ConnectionManager cm = new ConnectionManager();
    int page = Integer.parseInt(searchObj.getOrDefault("page", "0"));
    String LoanId = searchObj.getOrDefault("LoanId", null);
    String Country = searchObj.getOrDefault("Country", null);
    String PartnerId = searchObj.getOrDefault("PartnerId", null);
    String Category = searchObj.getOrDefault("Category", null);
    String SortBy=searchObj.getOrDefault("SortBy",null);
    String UserName = searchObj.getOrDefault("userName", null);
    if (page < 0) {
      page = 0;
    }
    String insertQueryStr = "INSERT INTO Search(LoanId, Country, PartnerId, Category, UserName)" +
            " VALUES(";
    //add UserName before all other column attributes for inserting values
    String queryStr = "SELECT * FROM Loans NATURAL JOIN LoanThemes ";
    List<String> queryClauses = new ArrayList<>();
    if (LoanId != null) {
      queryClauses.add("LoanId=" + LoanId);
      insertQueryStr += LoanId + ",";
    }
    else{
      insertQueryStr += "null" + ",";
    }
    if (Country != null) {
      queryClauses.add("RegionCountry LIKE " + "'%" + Country + "%'");
      insertQueryStr += "'" + Country + "'" + ",";
    }
    else{
      insertQueryStr += "null" + ",";
    }

    if (PartnerId != null) {
      queryClauses.add("PartnerId LIKE " + "'%" + PartnerId + "%'");
      insertQueryStr += PartnerId + ",";
    }
    else {
      insertQueryStr += "null" + ",";
    }
    if (Category != null) {
      queryClauses.add("Theme LIKE " + "'%" + Category + "%'");
      insertQueryStr += "'" + Category + "'" + ",";
    }
    else{
      insertQueryStr += "null" + ",";
    }
    if (queryClauses.size() != 0) {
      queryStr += "WHERE ";
    }
    //Add to the insert statement
      insertQueryStr += "'" + UserName +"')";

    for (int i = 0; i < queryClauses.size(); i++) {
      if (i != queryClauses.size() - 1) {
        queryStr += queryClauses.get(i);
        queryStr += " And ";
      } else {
        queryStr += queryClauses.get(i);
      }
    }

    if(SortBy!=null)queryStr+=" ORDER BY "+SortBy;
    queryStr += " LIMIT 50 OFFSET " + page * 50;
    ResultSet rs = cm.execQuery(queryStr);
    System.out.println(queryStr);
    System.out.println(insertQueryStr);

    while (rs.next()) {
      FrontEndLoanModel fm = FrontEndLoanModel.match(rs);
      res.add(fm);
    }
    //add insert statement
    if(queryClauses.size() != 0){
      cm.execQueryInsert(insertQueryStr);
    }
    return res;
  }
}

