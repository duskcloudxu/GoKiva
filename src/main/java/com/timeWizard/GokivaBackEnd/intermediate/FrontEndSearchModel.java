package com.timeWizard.GokivaBackEnd.intermediate;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class FrontEndSearchModel {
    protected String VisitId;
    protected String UserName;
    protected String LoanId;
    protected String TimeVisited;
    protected String Category;
    protected String Country;
    protected String PartnerId;
    private FrontEndSearchModel(){
    }



    public static FrontEndSearchModel match(ResultSet rs) throws SQLException, IllegalAccessException {
        Field[] fields=FrontEndSearchModel.class.getDeclaredFields();
        FrontEndSearchModel model=new FrontEndSearchModel();
        for(Field field:fields){
            field.set(model,rs.getString(field.getName()));
        }
        return model;
    }


    public String getVisitId() {
        return VisitId;
    }

    public String getUserName() {
        return UserName;
    }

    public String getLoanId() {
        return LoanId;
    }

    public String getTimeVisited() {
        return TimeVisited;
    }

    public String getCategory() {
        return Category;
    }

    public String getCountry() {
        return Country;
    }

    public String getPartnerId() {
        return PartnerId;
    }
}
