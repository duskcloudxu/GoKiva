package com.timeWizard.GokivaFrontEnd;

import com.timeWizard.GokivaBackEnd.DAO.BorrowersDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping(method = RequestMethod.GET, value = "/test", produces = "application/json")
    public ResponseEntity<String> test() throws SQLException {
        BorrowersDao borrowersDao=BorrowersDao.getInstance();

        return new ResponseEntity<String>(borrowersDao.getBorrowersById(183).toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/index" )
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index");
        System.out.println("index Arrive!");
        return mav;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/template" )
    public ModelAndView template(){
        ModelAndView mav = new ModelAndView("template");
        return mav;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/userSignIn" )
    public ModelAndView userSignIn(){
        ModelAndView mav = new ModelAndView("userSignIn");
        return mav;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/userRegister" )
    public ModelAndView userRegister(){
        ModelAndView mav = new ModelAndView("userRegister");
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/category" )
    public ModelAndView category(){
        ModelAndView mav = new ModelAndView("category");
        return mav;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/history" )
    public ModelAndView history(){
        ModelAndView mav = new ModelAndView("history");
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search" )
    public ModelAndView search(@RequestParam Map<String,String> allRequestParams, ModelMap model){
        int page=Integer.parseInt(allRequestParams.getOrDefault("page","0"));
        if(page < 0)page=0;
        Map<String,String> copyParams=new HashMap<>(allRequestParams);
        List<String>toBeDeleted=new ArrayList<>();
        for(String key:copyParams.keySet()){
            String val=copyParams.get(key);
            if(val.length()==0){
              toBeDeleted.add(key);
            }
        }
        for(String key:toBeDeleted){
            allRequestParams.remove(key);
        }
        ModelAndView mav = new ModelAndView("search");
        mav.addObject("page",page);
        mav.addObject("searchObj",allRequestParams);
        return mav;
    }


}
