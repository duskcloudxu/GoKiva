package com.timeWizard.GokivaFrontEnd;

import javax.servlet.http.HttpServletRequest;

public class Utils {
  public static int getErrorCode(HttpServletRequest httpRequest) {
    return (Integer) httpRequest
        .getAttribute("javax.servlet.error.status_code");
  }
}
