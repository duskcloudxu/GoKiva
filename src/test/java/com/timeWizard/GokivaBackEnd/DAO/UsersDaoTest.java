package com.timeWizard.GokivaBackEnd.DAO;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import com.timeWizard.GokivaBackEnd.model.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class UsersDaoTest {
  UsersDao usersDao;
  Users user1;
  Users user2;
  String hashPassword;

  @Before
  void setUp() throws SQLException,  NoSuchAlgorithmException, InvalidKeySpecException {


  }

  @Test
  void create() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
    usersDao = UsersDao.getInstance();
    hashPassword = usersDao.hashPassword("username2", "password");
    System.err.println(hashPassword);
    user1= new Users("username1", "password", "firstName", "lastName");
    user2= new Users("username2", hashPassword, "firstName", "lastName");
    Users user1_DB = usersDao.create(user1);
    Users user2b_db = usersDao.create(user2);
    assertEquals(usersDao.getUsersByUserName(user1.getUserName()).getUserName(), user1.getUserName());

  }

  @Test
  void delete() throws SQLException,  NoSuchAlgorithmException, InvalidKeySpecException, NullPointerException, SQLException{
    usersDao = UsersDao.getInstance();
    hashPassword = usersDao.hashPassword("username2", "password");
    System.err.println(hashPassword);
    user1= new Users("username1", "password", "firstName", "lastName");
    user2= new Users("username2", hashPassword, "firstName", "lastName");
    usersDao.delete(user1);
    System.err.println(user1.getUserName());
    assertNull(usersDao.getUsersByUserName(user1.getUserName()));
  }


  @Test
  void hashPassword() {
  }

  @Test
  void createAccount() {

  }
}