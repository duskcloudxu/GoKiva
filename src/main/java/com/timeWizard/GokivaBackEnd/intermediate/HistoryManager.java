package com.timeWizard.GokivaBackEnd.intermediate;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoryManager {

    public List<FrontEndSearchModel> getHistoryInPage(Map<String, String> historyObj)
            throws SQLException, IllegalAccessException {
        List<FrontEndSearchModel> res = new ArrayList<>();
        ConnectionManager cm = new ConnectionManager();
        int page = Integer.parseInt(historyObj.getOrDefault("page", "0"));
        String UserName = historyObj.getOrDefault("userName", "");
        if (page < 0) {
            page = 0;
        }
        String queryStr = "SELECT * FROM Search WHERE UserName = ";
        queryStr += "'" + UserName + "'" + " LIMIT 50 OFFSET " + page * 50;
        System.out.println(queryStr);
        ResultSet rs = cm.execQuery(queryStr);
        while (rs.next()) {
            FrontEndSearchModel fm = FrontEndSearchModel.match(rs);
            res.add(fm);
        }
        return res;
    }
}
