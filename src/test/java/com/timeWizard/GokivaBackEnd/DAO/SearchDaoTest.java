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


  @BeforeEach
  void setUp() throws Exception{

    usersDao = UsersDao.getInstance();
    loansDao = LoansDao.getInstance();
    searchDao = SearchDao.getInstance();
    user1= new Users("username1", "password", "firstName", "lastName");
    loan1 =  loansDao.getLoansById(100);
    loan2 = loansDao.getLoansById(230);

    loans1 = new ArrayList<>();
    loans1.add(loan1);




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
    assertEquals(new HashMap<Loans, Integer>(),searchDao.getSearchByUserName(user1.getUserName()));


  }

  @Test
  void getSearchByUserName() throws SQLException {
    search2 = searchDao.create(new Search(0, user1, loansDao.getLoansById(230)));

    HashMap<Loans, Integer> searchTest = new HashMap<>();
    searchTest.put(search1.getLoan(), search1.getTimesVisited());
    searchTest.put(search2.getLoan(), search2.getTimesVisited());


    assertTrue(searchDao.getSearchByUserName(user1.getUserName()).containsKey(search1.getLoan()));



  }

  @Test
  void updateTimesVisited() {
  }

  @Test
  void saveSearchResults() {
  }
}