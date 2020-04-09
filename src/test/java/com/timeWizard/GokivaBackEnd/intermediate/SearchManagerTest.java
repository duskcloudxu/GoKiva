package com.timeWizard.GokivaBackEnd.intermediate;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;
import org.junit.Test;

public class SearchManagerTest {

  @Test
  public void getLoanInPage() throws SQLException, IllegalAccessException {
    SearchManager sm=new SearchManager();
    List<FrontEndLoanModel>res=sm.getLoanInPage(0);
    System.out.println();
  }
}
