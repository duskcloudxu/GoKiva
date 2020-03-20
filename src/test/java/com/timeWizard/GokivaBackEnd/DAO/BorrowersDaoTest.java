package com.timeWizard.GokivaBackEnd.DAO;

import static org.junit.Assert.*;

import java.sql.SQLException;
import org.junit.Test;

public class BorrowersDaoTest {

  @Test
  public void getBorrowersById() throws SQLException {
    BorrowersDao borrowersDao=BorrowersDao.getInstance();
    assertEquals(borrowersDao.getBorrowersById(182).toString(),"Borrowers{BorrowerId=182, Gender='male'}"); ;
  }
}
