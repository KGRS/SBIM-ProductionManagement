/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import db.ConnectSql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KGRS
 */
public class AverageTimeOfPLItems {

    public void calculateAverageTimeOfPLItems(String productLevelItemCode) {
        String statusOfJob = "Completed";
        float ITEM_COUNT_COMPLETED_SUM=0, TAKEN_TIME_SUM=0, AVERAGE_TAKEN_TIME_TO_ONE_ITEM=0;
        ResultSet resetSelectJobsAtFinished;
        try {
            java.sql.Statement stmtSelectJobsAtFinished = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String selectJobsAtFinished = "SELECT TAKEN_TIME, ITEM_COUNT_COMPLETED FROM JobFinished WHERE PRODUCT_LEVEL_ITEM_CODE = '"+productLevelItemCode+"' AND IS_COMPLETE_CANCLE = '"+statusOfJob+"'";
            resetSelectJobsAtFinished = stmtSelectJobsAtFinished.executeQuery(selectJobsAtFinished);
            while(resetSelectJobsAtFinished.next()){
                ITEM_COUNT_COMPLETED_SUM = ITEM_COUNT_COMPLETED_SUM + resetSelectJobsAtFinished.getFloat("ITEM_COUNT_COMPLETED");
                TAKEN_TIME_SUM = TAKEN_TIME_SUM + resetSelectJobsAtFinished.getFloat("TAKEN_TIME");
                AVERAGE_TAKEN_TIME_TO_ONE_ITEM = TAKEN_TIME_SUM/ITEM_COUNT_COMPLETED_SUM;
            }
            System.out.println(AVERAGE_TAKEN_TIME_TO_ONE_ITEM);
        } catch (SQLException ex) {
            Logger.getLogger(AverageTimeOfPLItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
