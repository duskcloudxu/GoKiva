package com.timeWizard.GokivaBackEnd.DAO;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.timeWizard.GokivaBackEnd.model.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;

class UsersDaoTest {
  UsersDao usersDao;

  @Before
  void setUp() throws NoSuchAlgorithmException, InvalidKeySpecException {
    usersDao = UsersDao.getInstance();
    String hashPassword = usersDao.hashPassword("password");
    Users user1= new Users("username", "password", "firstName", "lastName");
    Users user2= new Users("username", hashPassword, "firstName", "lastName");

  }

  @Test
  void getInstance() {
  }

  @Test
  void create() {
//    usersDao.create(new Users());

  }

  @Test
  void delete() {
  }

  @Test
  void getUsersByUserName() {
  }

  @Test
  void authenticatePassword() {
    assertTrue(usersDao.authenticatePassword("");)
  }

  @Test
  void hashPassword() {
  }

  @Test
  void createAccount() {

  }
}