package com.timeWizard.GokivaFrontEnd;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MyErrorController implements ErrorController {
  @RequestMapping("/error")
  public ModelAndView handleError(@RequestParam(required = false)  String errorInfo) {
    //do something like logging
    ModelAndView mav= new ModelAndView("error");
    mav.addObject("errorInfo",errorInfo);
    return mav;
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}
