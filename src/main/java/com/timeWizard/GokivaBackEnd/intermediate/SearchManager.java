package com.timeWizard.GokivaBackEnd.intermediate;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchManager {
  public List<FrontEndLoanModel> getLoanInPage(Integer page)
      throws SQLException, IllegalAccessException {
    List<FrontEndLoanModel>res= new ArrayList<>();
    ConnectionManager cm=new ConnectionManager();
    ResultSet rs= cm.execQuery("SELECT * FROM Loans LIMIT 50 OFFSET "+ page * 50);
    while(rs.next()){
      FrontEndLoanModel fm=FrontEndLoanModel.match(rs);
      res.add(fm);
    }
    return res;

  }
}
