package com.timeWizard.GokivaFrontEnd;

import com.timeWizard.GokivaBackEnd.DAO.BorrowersDao;
import com.timeWizard.GokivaBackEnd.DAO.UsersDao;
import com.timeWizard.GokivaBackEnd.intermediate.DataVisManager;
import com.timeWizard.GokivaBackEnd.intermediate.DataVisModel;
import com.timeWizard.GokivaBackEnd.model.Users;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties.Xa;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

  @RequestMapping(method = RequestMethod.GET, value = "/test", produces = "application/json")
  public ResponseEntity<String> test() throws SQLException {
    BorrowersDao borrowersDao = BorrowersDao.getInstance();

    return new ResponseEntity<String>(borrowersDao.getBorrowersById(183).toString(), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/index")
  public ModelAndView index() {
    ModelAndView mav = new ModelAndView("index");
    System.out.println("index Arrive!");
    return mav;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/template")
  public ModelAndView template() {
    ModelAndView mav = new ModelAndView("template");
    return mav;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/userSignIn")
  public ModelAndView userSignIn() {
    ModelAndView mav = new ModelAndView("userSignIn");
    return mav;
  }
  @RequestMapping(method = RequestMethod.POST, value = "/userAuth", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ModelAndView userAuth(@RequestBody MultiValueMap<String, String> payload, HttpSession session)
      throws NoSuchAlgorithmException, SQLException, InvalidKeySpecException {
    UsersDao ud=UsersDao.getInstance();
    String userName= String.valueOf(payload.getFirst("userName"));
    String password= String.valueOf(payload.getFirst("password"));
    if(ud.authenticatePassword(userName,password)){
      session.setAttribute("userName",userName);
      return new ModelAndView("redirect:/");
    }
    ModelAndView mav=new ModelAndView("error");
    mav.addObject("errorInfo","Wrong password or username");
    return mav;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/userRegister")
  public ModelAndView userRegister() {
    ModelAndView mav = new ModelAndView("userRegister");
    return mav;
  }

  @RequestMapping(value="/userRegister",
      method=RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ModelAndView userRegisterPost(@RequestBody MultiValueMap<String, String> payload, HttpSession session)
      throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
    UsersDao ud=UsersDao.getInstance();
    String userName= String.valueOf(payload.getFirst("userName"));
    String password= String.valueOf(payload.getFirst("password"));
    String retypePassword= String.valueOf(payload.getFirst("retypePassword"));
    String firstName=String.valueOf(payload.getFirst("firstName"));
    String lastName=String.valueOf(payload.getFirst("lastName"));
    if(ud.isUserExist(userName)){
      ModelAndView mav = new ModelAndView("error");
      mav.addObject("errorInfo","UserName already exists");
      return mav;
    }
    else if(!password.equals(retypePassword)){
      ModelAndView mav = new ModelAndView("error");
      mav.addObject("errorInfo","retype password does not match");
      return mav;
    }
    Users user=ud.createAccount(userName,password,retypePassword,firstName,lastName);
    session.setAttribute("userName",user.getUserName());
    ModelAndView mav = new ModelAndView("redirect:/");
    return mav;
  }


  @RequestMapping(method = RequestMethod.GET, value = "/category")
  public ModelAndView category() {
    ModelAndView mav = new ModelAndView("category");
    return mav;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/history")
  public ModelAndView history(@RequestParam Map<String, String> allRequestParams, HttpSession session) {
    if(session.getAttribute("userName")==null){
      return new ModelAndView("error","errorInfo","Please Login First");
    }
    int page = Integer.parseInt(allRequestParams.getOrDefault("page", "0"));
    if (page < 0) {
      page = 0;
    }
    Map<String, String> copyParams = new HashMap<>(allRequestParams);
    List<String> toBeDeleted = new ArrayList<>();
    for (String key : copyParams.keySet()) {
      String val = copyParams.get(key);
      if (val.length() == 0) {
        toBeDeleted.add(key);
      }
    }
    for (String key : toBeDeleted) {
      allRequestParams.remove(key);
    }
    ModelAndView mav = new ModelAndView("history");
    mav.addObject("page", page);
    mav.addObject("historyObj", allRequestParams);
    return mav;

  }

  @RequestMapping(method = RequestMethod.GET, value = "/search")
  public ModelAndView search(@RequestParam Map<String, String> allRequestParams, HttpSession session) {
    if(session.getAttribute("userName")==null){
      return new ModelAndView("error","errorInfo","Please Login First");
    }
    int page = Integer.parseInt(allRequestParams.getOrDefault("page", "0"));
    if (page < 0) {
      page = 0;
    }
    Map<String, String> copyParams = new HashMap<>(allRequestParams);
    List<String> toBeDeleted = new ArrayList<>();
    for (String key : copyParams.keySet()) {
      String val = copyParams.get(key);
      if (val.length() == 0) {
        toBeDeleted.add(key);
      }
    }
    for (String key : toBeDeleted) {
      allRequestParams.remove(key);
    }
    ModelAndView mav = new ModelAndView("search");
    mav.addObject("page", page);
    mav.addObject("searchObj", allRequestParams);
    return mav;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/dataVisualization")
  public ModelAndView dataVisualization(@RequestParam Map<String, String> allRequestParams ,HttpSession session)
      throws SQLException, IllegalAccessException {
    if(session.getAttribute("userName")==null){
      return new ModelAndView("error","errorInfo","Please Login First");
    }
    String XAxis="Country";
    String YAxis="Loan";
    if(allRequestParams.containsKey("XAxis"))XAxis=allRequestParams.get("XAxis");
    if(allRequestParams.containsKey("YAxis"))YAxis=allRequestParams.get("YAxis");
    DataVisManager dm=new DataVisManager();
    List<DataVisModel>list= dm.getData(XAxis,YAxis);
    ModelAndView mav = new ModelAndView("dataVisualization");
    mav.addObject("list", list);
    return mav;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/userSignOut")
  public ModelAndView signOut(HttpSession session) {
    session.setAttribute("userName",null);
    return  new ModelAndView("redirect:/");
  }


}
