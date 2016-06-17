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
    public static void calculateAverageTimeOfPLItems(String productLevelItemCode) {
        String statusOfJob = "Completed";
        int ITEM_COUNT_COMPLETED_SUM = 0, TAKEN_TIME_SUM = 0, AVERAGE_TAKEN_TIME_TO_ONE_ITEM = 0;
        ResultSet resetSelectJobsAtFinished, resetSelectPLItemsAtAverageTimeTable;
        try {
            java.sql.Statement stmtSelectJobsAtFinished = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            java.sql.Statement stmtSelectPLItemsAtAverageTimeTable = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            java.sql.Statement stmtInsertAverageTime = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            java.sql.Statement stmtUpdateAverageTime = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String selectJobsAtFinished = "SELECT TAKEN_TIME, ITEM_COUNT_COMPLETED FROM JobFinished WHERE PRODUCT_LEVEL_ITEM_CODE = '" + productLevelItemCode + "' AND IS_COMPLETE_CANCLE = '" + statusOfJob + "'";
            resetSelectJobsAtFinished = stmtSelectJobsAtFinished.executeQuery(selectJobsAtFinished);
            while (resetSelectJobsAtFinished.next()) {
                ITEM_COUNT_COMPLETED_SUM = ITEM_COUNT_COMPLETED_SUM + resetSelectJobsAtFinished.getInt("ITEM_COUNT_COMPLETED");
                TAKEN_TIME_SUM = TAKEN_TIME_SUM + resetSelectJobsAtFinished.getInt("TAKEN_TIME");
                AVERAGE_TAKEN_TIME_TO_ONE_ITEM = TAKEN_TIME_SUM / ITEM_COUNT_COMPLETED_SUM;
            }
            String selectPLItemsAtAverageTimeTable = "SELECT PRODUCT_LEVEL_ITEM_CODE FROM AverageTimeOfPLItems WHERE PRODUCT_LEVEL_ITEM_CODE = '" + productLevelItemCode + "'";
            resetSelectPLItemsAtAverageTimeTable = stmtSelectPLItemsAtAverageTimeTable.executeQuery(selectPLItemsAtAverageTimeTable);
            if (resetSelectPLItemsAtAverageTimeTable.next()) {
                String updatePLItemsAtAverageTimeTable = "UPDATE AverageTimeOfPLItems SET AVERAGE_TIME = '" + AVERAGE_TAKEN_TIME_TO_ONE_ITEM + "', TOTAL_ITEM_COUNT = '" + ITEM_COUNT_COMPLETED_SUM + "' WHERE PRODUCT_LEVEL_ITEM_CODE = '" + productLevelItemCode + "'";
                stmtUpdateAverageTime.execute(updatePLItemsAtAverageTimeTable);
            } else if (!resetSelectPLItemsAtAverageTimeTable.next()) {
                String insertPLItemsAtAverageTimeTable = "INSERT INTO AverageTimeOfPLItems (PRODUCT_LEVEL_ITEM_CODE, AVERAGE_TIME, TOTAL_ITEM_COUNT) VALUES ('" + productLevelItemCode + "', '" + AVERAGE_TAKEN_TIME_TO_ONE_ITEM + "', '" + ITEM_COUNT_COMPLETED_SUM + "')";
                stmtInsertAverageTime.execute(insertPLItemsAtAverageTimeTable);
            }
//            System.out.println(AVERAGE_TAKEN_TIME_TO_ONE_ITEM);
            stmtSelectJobsAtFinished.close();
            stmtSelectPLItemsAtAverageTimeTable.close();
            stmtInsertAverageTime.close();
            stmtUpdateAverageTime.close();
            resetSelectJobsAtFinished.close();
            resetSelectPLItemsAtAverageTimeTable.close();
        } catch (SQLException ex) {
            Logger.getLogger(AverageTimeOfPLItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
