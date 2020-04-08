package com.timeWizard.GokivaBackEnd.intermediate;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FrontEndLoanModel {
  private String LoanId;
  private String Description;
  private String PostedTime;
  private String FundTime;
  private String DisbursedTime;
  private String FundAmount;
  private String DisbursedAmount;
  private String RegionCountry;
  private String PartnerId;
  private String ThemeId;
  private FrontEndLoanModel(){
  }

  public String getLoanId() {
    return LoanId;
  }

  public String getDescription() {
    return Description;
  }

  public String getPostedTime() {
    return PostedTime;
  }

  public String getFundTime() {
    return FundTime;
  }

  public String getDisbursedTime() {
    return DisbursedTime;
  }

  public String getFundAmount() {
    return FundAmount;
  }

  public String getDisbursedAmount() {
    return DisbursedAmount;
  }

  public String getRegionCountry() {
    return RegionCountry;
  }

  public String getPartnerId() {
    return PartnerId;
  }

  public String getThemeId() {
    return ThemeId;
  }

  public static FrontEndLoanModel match(ResultSet rs) throws SQLException, IllegalAccessException {
    Field[] fields=FrontEndLoanModel.class.getDeclaredFields();
    FrontEndLoanModel model=new FrontEndLoanModel();
    for(Field field:fields){
      field.set(model,rs.getString(field.getName()));
    }
    return model;
  }
}
