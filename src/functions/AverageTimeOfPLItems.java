/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import db.ConnectSql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KGRS
 */
public class AverageTimeOfPLItems {

    public static void calculateAverageTimeOfPLItems(String productLevelItemCode, String productLevel, String departmentCode) {
        String statusOfJob = "Completed";
        double ITEM_COUNT_COMPLETED_SUM = 0, TAKEN_TIME_SUM = 0, AVERAGE_TAKEN_TIME_TO_ONE_ITEM=0, TAKEN_TIME;
        double ITEM_COUNT_COMPLETED = 0;
        double Q1PositionOf_TAKEN_TIME_CAL, Q3PositionOf_TAKEN_TIME_CAL;
        int Q1PositionOf_TAKEN_TIME, Q3PositionOf_TAKEN_TIME;
        double TAKEN_TIME_COUNT = 0;
        double distance_of_TAKEN_TIME;
        double lowerTail_of_TAKEN_TIME, upperTail_of_TAKEN_TIME;
        double Q1Of_TAKEN_TIME = 0;
        double Q3Of_TAKEN_TIME = 0;
        ResultSet resetSelectJobsAtFinished, resetSelectPLItemsAtAverageTimeTable;
        try {
            ArrayList<Double> arrayList_TAKEN_TIME = new ArrayList<Double>();
            java.sql.Statement stmtSelectJobsAtFinished = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            java.sql.Statement stmtSelectPLItemsAtAverageTimeTable = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            java.sql.Statement stmtInsertAverageTime = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            java.sql.Statement stmtUpdateAverageTime = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String selectJobsAtFinished = "SELECT TAKEN_TIME, ITEM_COUNT_COMPLETED FROM JobFinished WHERE PRODUCT_LEVEL_ITEM_CODE = '" + productLevelItemCode + "' AND IS_COMPLETE_CANCLE = '" + statusOfJob + "' ORDER BY TAKEN_TIME";
            resetSelectJobsAtFinished = stmtSelectJobsAtFinished.executeQuery(selectJobsAtFinished);
            double avarageOfITEM_COUNT_COMPLETED = CalculateAvgPLItemCompletedForAvgTime.plItemsProductionCount(productLevelItemCode);
            while (resetSelectJobsAtFinished.next()) {
                ITEM_COUNT_COMPLETED_SUM = ITEM_COUNT_COMPLETED_SUM + resetSelectJobsAtFinished.getInt("ITEM_COUNT_COMPLETED");
                TAKEN_TIME = resetSelectJobsAtFinished.getDouble("TAKEN_TIME");
                arrayList_TAKEN_TIME.add(TAKEN_TIME);
                TAKEN_TIME_COUNT = TAKEN_TIME_COUNT + 1;
                TAKEN_TIME_SUM = TAKEN_TIME_SUM + resetSelectJobsAtFinished.getDouble("TAKEN_TIME");
                ITEM_COUNT_COMPLETED = resetSelectJobsAtFinished.getDouble("ITEM_COUNT_COMPLETED");
            }

            Q1PositionOf_TAKEN_TIME_CAL = (TAKEN_TIME_COUNT + 1) / 4;
            if (Q1PositionOf_TAKEN_TIME_CAL >= 2) {
                if (Q1PositionOf_TAKEN_TIME_CAL == Math.round(Q1PositionOf_TAKEN_TIME_CAL)) {
                    Q1PositionOf_TAKEN_TIME = (int) Q1PositionOf_TAKEN_TIME_CAL;
                    Q1Of_TAKEN_TIME = arrayList_TAKEN_TIME.get(Q1PositionOf_TAKEN_TIME);
                } else if (Q1PositionOf_TAKEN_TIME_CAL != Math.round(Q1PositionOf_TAKEN_TIME_CAL)) {
                    int Q1PositionOf_TAKEN_TIME_COUNT1, Q1PositionOf_TAKEN_TIME_COUNT2;
                    double Q1Of_TAKEN_TIME_COUNT1, Q1Of_TAKEN_TIME_COUNT2;
                    Q1PositionOf_TAKEN_TIME_COUNT1 = (int) (Q1PositionOf_TAKEN_TIME_CAL);
                    Q1PositionOf_TAKEN_TIME_COUNT2 = Q1PositionOf_TAKEN_TIME_COUNT1 + 1;
                    Q1Of_TAKEN_TIME_COUNT1 = arrayList_TAKEN_TIME.get(Q1PositionOf_TAKEN_TIME_COUNT1 - 1);
                    Q1Of_TAKEN_TIME_COUNT2 = arrayList_TAKEN_TIME.get(Q1PositionOf_TAKEN_TIME_COUNT2 - 1);
                    Q1Of_TAKEN_TIME = (Q1Of_TAKEN_TIME_COUNT1 + Q1Of_TAKEN_TIME_COUNT2) / 2;
                }
                Q3PositionOf_TAKEN_TIME_CAL = (TAKEN_TIME_COUNT + 1) * 3 / 4;
                if (Q3PositionOf_TAKEN_TIME_CAL == Math.round(Q3PositionOf_TAKEN_TIME_CAL)) {
                    Q3PositionOf_TAKEN_TIME = (int) Q3PositionOf_TAKEN_TIME_CAL;
                    Q3Of_TAKEN_TIME = arrayList_TAKEN_TIME.get(Q3PositionOf_TAKEN_TIME);
                } else if (Q3PositionOf_TAKEN_TIME_CAL != Math.round(Q3PositionOf_TAKEN_TIME_CAL)) {
                    int Q3PositionOf_TAKEN_TIME_COUNT1, Q3PositionOf_TAKEN_TIME_COUNT2;
                    double Q3Of_TAKEN_TIME_COUNT1, Q3Of_TAKEN_TIME_COUNT2;
                    Q3PositionOf_TAKEN_TIME_COUNT1 = (int) (Q3PositionOf_TAKEN_TIME_CAL);
                    Q3PositionOf_TAKEN_TIME_COUNT2 = Q3PositionOf_TAKEN_TIME_COUNT1 + 1;
                    Q3Of_TAKEN_TIME_COUNT1 = arrayList_TAKEN_TIME.get(Q3PositionOf_TAKEN_TIME_COUNT1 - 1);
                    Q3Of_TAKEN_TIME_COUNT2 = arrayList_TAKEN_TIME.get(Q3PositionOf_TAKEN_TIME_COUNT2 - 1);
                    Q3Of_TAKEN_TIME = (Q3Of_TAKEN_TIME_COUNT1 + Q3Of_TAKEN_TIME_COUNT2) / 2;
                }

                distance_of_TAKEN_TIME = Q3Of_TAKEN_TIME - Q1Of_TAKEN_TIME;
                lowerTail_of_TAKEN_TIME = Q1Of_TAKEN_TIME - (1.5 * distance_of_TAKEN_TIME);
                if (lowerTail_of_TAKEN_TIME < 0) {
                    lowerTail_of_TAKEN_TIME = 0;
                }
                upperTail_of_TAKEN_TIME = Q3Of_TAKEN_TIME + (1.5 * distance_of_TAKEN_TIME);
                double avarage_Of_TAKEN_TIME = calculateFilteredAverageTimeForPLItems(productLevelItemCode, lowerTail_of_TAKEN_TIME, upperTail_of_TAKEN_TIME);
                AVERAGE_TAKEN_TIME_TO_ONE_ITEM = avarage_Of_TAKEN_TIME / avarageOfITEM_COUNT_COMPLETED;
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
                stmtSelectJobsAtFinished.close();
                stmtSelectPLItemsAtAverageTimeTable.close();
                stmtInsertAverageTime.close();
                stmtUpdateAverageTime.close();
                resetSelectJobsAtFinished.close();
                resetSelectPLItemsAtAverageTimeTable.close();

            CalculateSubDepartmentStockAtJobComplete.reduceStockOfSubDepartmentAtJobComplete(productLevel, productLevelItemCode, ITEM_COUNT_COMPLETED, departmentCode);
        } catch (SQLException ex) {
            Logger.getLogger(AverageTimeOfPLItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int getAverageSuggestTimeForPLItems(String productLevelItemCode, int itemCount) {
        ResultSet resetSelectPLItemsAtAverageTimeTable;
        int AVERAGE_TIME, calculatedAverageTime = 0;
        try {
            java.sql.Statement stmtSelectPLItemsAtAverageTimeTable = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String selectPLItemsAtAverageTimeTable = "SELECT AVERAGE_TIME FROM AverageTimeOfPLItems WHERE PRODUCT_LEVEL_ITEM_CODE = '" + productLevelItemCode + "'";
            resetSelectPLItemsAtAverageTimeTable = stmtSelectPLItemsAtAverageTimeTable.executeQuery(selectPLItemsAtAverageTimeTable);
            if (resetSelectPLItemsAtAverageTimeTable.next()) {
                AVERAGE_TIME = resetSelectPLItemsAtAverageTimeTable.getInt("AVERAGE_TIME");
                calculatedAverageTime = AVERAGE_TIME * itemCount;
            }
            stmtSelectPLItemsAtAverageTimeTable.close();
            resetSelectPLItemsAtAverageTimeTable.close();
        } catch (SQLException ex) {
            Logger.getLogger(AverageTimeOfPLItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        return calculatedAverageTime;
    }

    public static double calculateFilteredAverageTimeForPLItems(String productLevelItemCode, double lowerTail_of_TAKEN_TIME, double upperTail_of_TAKEN_TIME) {
        ResultSet resetSelectPLItemsAtAverageTimeTable;
        int AVERAGE_TIME;
        String statusOfJob = "Completed";
        double AVERAGE_TIME_COUNT = 0;
        double avaragePositionOf_TAKEN_TIME_CAL, avarage_Of_TAKEN_TIME = 0;
        int avaragePositionOf_TAKEN_TIME;
        try {
            ArrayList<Integer> arrayList_JobFinished_TAKEN_TIME_FILTERED = new ArrayList<Integer>();
            java.sql.Statement stmtSelectPLItemsAtAverageTimeTable = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String selectPLItemsAtAverageTimeTable = "SELECT TAKEN_TIME FROM JobFinished "
                    + "WHERE PRODUCT_LEVEL_ITEM_CODE = '" + productLevelItemCode + "' AND "
                    + "IS_COMPLETE_CANCLE = '" + statusOfJob + "' AND (TAKEN_TIME > " + lowerTail_of_TAKEN_TIME + " AND TAKEN_TIME < " + upperTail_of_TAKEN_TIME + ") ORDER BY TAKEN_TIME";
            resetSelectPLItemsAtAverageTimeTable = stmtSelectPLItemsAtAverageTimeTable.executeQuery(selectPLItemsAtAverageTimeTable);
            while (resetSelectPLItemsAtAverageTimeTable.next()) {
                AVERAGE_TIME = resetSelectPLItemsAtAverageTimeTable.getInt("TAKEN_TIME");
                arrayList_JobFinished_TAKEN_TIME_FILTERED.add(AVERAGE_TIME);
                AVERAGE_TIME_COUNT = AVERAGE_TIME_COUNT + 1;
            }
            avaragePositionOf_TAKEN_TIME_CAL = AVERAGE_TIME_COUNT / 2;
            if (avaragePositionOf_TAKEN_TIME_CAL > 2) {
                if (avaragePositionOf_TAKEN_TIME_CAL == Math.round(avaragePositionOf_TAKEN_TIME_CAL)) {
                    avaragePositionOf_TAKEN_TIME = (int) (avaragePositionOf_TAKEN_TIME_CAL);
                    avarage_Of_TAKEN_TIME = arrayList_JobFinished_TAKEN_TIME_FILTERED.get(avaragePositionOf_TAKEN_TIME - 1);
                } else if (avaragePositionOf_TAKEN_TIME_CAL != Math.round(avaragePositionOf_TAKEN_TIME_CAL)) {
                    int avarage_Position_TAKEN_TIME_1, avarage_Position_Of_TAKEN_TIME_2;
                    double avarageOf_TAKEN_TIME_1, avarageOf_TAKEN_TIME_2;
                    avarage_Position_TAKEN_TIME_1 = (int) (avaragePositionOf_TAKEN_TIME_CAL);
                    avarage_Position_Of_TAKEN_TIME_2 = avarage_Position_TAKEN_TIME_1 + 1;
                    avarageOf_TAKEN_TIME_1 = arrayList_JobFinished_TAKEN_TIME_FILTERED.get(avarage_Position_TAKEN_TIME_1 - 1);
                    avarageOf_TAKEN_TIME_2 = arrayList_JobFinished_TAKEN_TIME_FILTERED.get(avarage_Position_Of_TAKEN_TIME_2 - 1);
                    avarage_Of_TAKEN_TIME = (avarageOf_TAKEN_TIME_1 + avarageOf_TAKEN_TIME_2) / 2;
                }
            }
            stmtSelectPLItemsAtAverageTimeTable.close();
            resetSelectPLItemsAtAverageTimeTable.close();
        } catch (SQLException ex) {
            Logger.getLogger(AverageTimeOfPLItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        return avarage_Of_TAKEN_TIME;
    }
}
