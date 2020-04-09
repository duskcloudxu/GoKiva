package com.timeWizard.GokivaBackEnd.intermediate;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataVisManager {
  public List<DataVisModel> getData(String XAxis, String YAxis)
      throws SQLException, IllegalAccessException {
    String queryStr=null;
    if(XAxis.equals("Country")){
      if(YAxis.equals("Loan")){
        queryStr="SELECT RegionCountry XAXIS, COUNT(*) YAXIS FROM Loans NATURAL JOIN LoanThemes GROUP BY RegionCountry HAVING  YAXIS>20 ORDER BY YAXIS DESC";
      }
      else if(YAxis.equals("Dollar")){
        queryStr="SELECT RegionCountry XAXIS, SUM(FundAmount) YAXIS FROM Loans NATURAL JOIN LoanThemes GROUP BY RegionCountry HAVING  YAXIS>20000 ORDER BY YAXIS DESC";
      }
    }
    else{
      if(YAxis.equals("Loan")){
        queryStr="SELECT Theme XAXIS, COUNT(*) YAXIS FROM Loans NATURAL JOIN LoanThemes GROUP BY Theme ORDER BY YAXIS DESC";
      }
      else if(YAxis.equals("Dollar")){
        queryStr="SELECT Theme XAXIS, SUM(FundAmount) YAXIS FROM Loans NATURAL JOIN LoanThemes GROUP BY Theme ORDER BY YAXIS DESC";
      }
    }
    ConnectionManager cm=new ConnectionManager();
    ResultSet rs=cm.execQuery(queryStr);
    List<DataVisModel>resList=new ArrayList<>();
    while(rs.next()){
      resList.add(DataVisModel.match(rs));
    }
    return resList;
  }
}
