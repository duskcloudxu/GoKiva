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
  void delete() throws NoSuchAlgorithmException, InvalidKeySpecException, NullPointerException, SQLException{
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
  void hashPassword() throws NoSuchAlgorithmException, InvalidKeySpecException, NullPointerException, SQLException {
    usersDao = UsersDao.getInstance();
    String testPassword = usersDao.hashPassword("username3", "password");
    Users user3= new Users("username3", testPassword, "firstName", "lastName");
    usersDao.createAccount("username3", "password", "password",
        "firstName", "lastName");
    assertEquals(testPassword, usersDao.getUsersByUserName(user3.getUserName()).getPassword());



  }

  @Test
  void createAccount() throws NoSuchAlgorithmException, InvalidKeySpecException, NullPointerException, SQLException {
    usersDao = UsersDao.getInstance();
    String hashed = usersDao.hashPassword("username1", "password1");
    usersDao.createAccount("username1", "password1", "password1",
        "firstName1", "lastName1");
    assertEquals("username1", usersDao.getUsersByUserName("username1").getUserName());
    assertEquals(hashed, usersDao.getUsersByUserName("username1").getPassword());
    assertEquals("firstName1", usersDao.getUsersByUserName("username1").getFirstName());
    assertEquals("lastName1", usersDao.getUsersByUserName("username1").getLastName());


  }
}