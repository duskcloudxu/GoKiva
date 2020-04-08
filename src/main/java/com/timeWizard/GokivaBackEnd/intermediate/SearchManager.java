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
    String Tag = searchObj.getOrDefault("Tag", null);
    if (page < 0) {
      page = 0;
    }
    String queryStr = "SELECT * FROM Loans ";
    List<String> queryClauses = new ArrayList<>();
    if (LoanId != null) {
      queryClauses.add("LoanId=" + LoanId);
    }
    if (Country != null) {
      queryClauses.add("RegionCountry LIKE " + "'%" + Country + "%'");
    }
    if (PartnerId != null) {
      queryClauses.add("PartnerId LIKE " + "'%" + PartnerId + "%'");
    }
    if (queryClauses.size() != 0) {
      queryStr += "WHERE ";
    }
    for (int i = 0; i < queryClauses.size(); i++) {
      if (i != queryClauses.size() - 1) {
        queryStr += queryClauses.get(i);
        queryStr += " And ";
      } else {
        queryStr += queryClauses.get(i);
      }
    }
    queryStr += " LIMIT 50 OFFSET " + page * 50;
    ResultSet rs = cm.execQuery(queryStr);
    while (rs.next()) {
      FrontEndLoanModel fm = FrontEndLoanModel.match(rs);
      res.add(fm);
    }
    return res;
  }
}
