package com.timeWizard.GokivaBackEnd.intermediate;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataVisModel {
  String XAxis;
  String YAxis;
  private DataVisModel(){

  }
  public String getXAxis() {
    return XAxis;
  }
  public String getYAxis() {
    return YAxis;
  }
  public static DataVisModel match(ResultSet rs) throws SQLException, IllegalAccessException {
    Field[] fields= DataVisModel.class.getDeclaredFields();
    DataVisModel model=new DataVisModel();
    for(Field field:fields){
      field.set(model,rs.getString(field.getName()));
    }
    return model;
  }
}
