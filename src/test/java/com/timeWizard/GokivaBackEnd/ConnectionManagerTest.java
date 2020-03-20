package com.timeWizard.GokivaBackEnd;

import static org.junit.Assert.*;

import java.sql.SQLException;
import org.junit.Test;

public class ConnectionManagerTest {

  @Test
  public void getConnection() throws SQLException {
    ConnectionManager connectionManager=new ConnectionManager();
    var connection=connectionManager.getConnection();
    return ;

  }
}
