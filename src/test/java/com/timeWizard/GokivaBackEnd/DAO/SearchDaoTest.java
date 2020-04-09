package com.timeWizard.GokivaBackEnd.DAO;

import com.timeWizard.GokivaBackEnd.model.Loans;
import com.timeWizard.GokivaBackEnd.model.Search;
import com.timeWizard.GokivaBackEnd.model.Users;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SearchDaoTest {
  UsersDao usersDao;
  Users user1;
  Users user2;

  LoansDao loansDao;
  SearchDao searchDao;
  Search search1;
  Search search2;
  Loans loan1;
  Loans loan2;

  ArrayList<Loans>  loans1;
  ArrayList<Loans>  loans2;


  @BeforeEach
  void setUp() throws Exception{

    usersDao = UsersDao.getInstance();
    loansDao = LoansDao.getInstance();
    searchDao = SearchDao.getInstance();
    user1= new Users("username1", "password", "firstName", "lastName");
    user2= new Users("username2", "password", "firstName", "lastName");

    loan1 =  loansDao.getLoansById(100);
    loan2 = loansDao.getLoansById(230);
    search1 = new Search(1, user1, loan1);

    loans1 = new ArrayList<>();
    loans1.add(loan1);
    loans2 = new ArrayList<>();
    loans2.add(loan2);





  }

  @Test
  void saveSearchResultsTest() throws SQLException {
    HashMap<Loans, Integer> search = searchDao.saveSearchResults(user1, loans1);
    assertEquals(searchDao.getSearchByUserName(user1.getUserName()), search);

  }

  @Test
  void delete() throws SQLException {
    searchDao.delete(search1);
    assertNull(searchDao.getSearchByLoanId(100));


  }

  @Test
  void getSearchByUserName() throws SQLException {
    HashMap<Loans, Integer> search = searchDao.saveSearchResults(user2, loans2);
    HashMap<Loans, Integer> searchTest = new HashMap<>();
    assertEquals(search, searchDao.getSearchByUserName(user2.getUserName()));


  }

  @Test
  void updateTimesVisited() {
  }

  @Test
  void saveSearchResults() {
  }
}