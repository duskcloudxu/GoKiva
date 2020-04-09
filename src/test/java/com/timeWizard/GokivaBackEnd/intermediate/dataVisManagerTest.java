package com.timeWizard.GokivaBackEnd.intermediate;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;
import org.junit.Test;

public class dataVisManagerTest {

  @Test
  public void getData() throws SQLException, IllegalAccessException {
    DataVisManager dm=new DataVisManager();
    List<DataVisModel>res= dm.getData("Country","Loan");
    assertTrue(res.size()>0);
  }
}
