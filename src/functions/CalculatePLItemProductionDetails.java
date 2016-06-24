/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import db.ConnectSql;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author KGRS
 */
public class CalculatePLItemProductionDetails {

    public static void plItemsProductionCount(int itemCount, String PRODUCT_LEVEL_ITEM_CODE) {
        ResultSet resetSelectAtJobFinishedTable;
        int JobFinished_ITEM_COUNT = 0, JobFinished_ITEM_COUNT_COMPLETED;
        int JobFinished_ITEM_COUNT_COUNT = 0, JobFinished_ITEM_COUNT_COMPLETED_COUNT = 0, JobFinished_ITEM_COUNT_SUM = 0, JobFinished_ITEM_COUNT_COMPLETED_SUM = 0;
        int Q1OfITEM_COUNT, Q3OfITEM_COUNT, avarageOfITEM_COUNT, Q1PositionOfITEM_COUNT, Q3PositionOfITEM_COUNT, avaragePositionOfITEM_COUNT;
        int Q1OfITEM_COUNT_COMPLETED, Q3OfITEM_COUNT_COMPLETED, avarageOfITEM_COUNT_COMPLETED, Q1PositionOfITEM_COUNT_COMPLETED, Q3PositionOfITEM_COUNT_COMPLETED, avaragePositionOfITEM_COUNT_COMPLETED;
        try {
            ArrayList<Integer> arrayList_JobFinished_ITEM_COUNT = new ArrayList<Integer>();
            ArrayList<Integer> arrayList_JobFinished_ITEM_COUNT_COMPLETED = new ArrayList<Integer>();
            java.sql.Statement stmtSelectAtJobFinishedTable = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SelectAtJobFinishedTable = "SELECT\n"
                    + "     JobFinished.\"ITEM_COUNT\" AS JobFinished_ITEM_COUNT,\n"
                    + "     JobFinished.\"ITEM_COUNT_COMPLETED\" AS JobFinished_ITEM_COUNT_COMPLETED,\n"
                    + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" AS JobFixed_PRODUCT_LEVEL_ITEM_CODE\n"
                    + "FROM\n"
                    + "     \"dbo\".\"JobFinished\" JobFinished INNER JOIN \"dbo\".\"PLItemDifference\" PLItemDifference ON JobFinished.\"JOB_ID\" = PLItemDifference.\"JOB_ID\"\n"
                    + "     INNER JOIN \"dbo\".\"JobFixed\" JobFixed ON JobFinished.\"FIXED_JOB_ID\" = JobFixed.\"JOB_FIXED_ID\"\n"
                    + "WHERE\n"
                    + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" = '" + PRODUCT_LEVEL_ITEM_CODE + "'\n"
                    + " AND PLItemDifference.\"IS_WASTAGE\" = 'No'\n"
                    + " AND JobFinished.\"IS_COMPLETE_CANCLE\" = 'Completed' ORDER BY JobFinished.\"ITEM_COUNT\" ASC";
            resetSelectAtJobFinishedTable = stmtSelectAtJobFinishedTable.executeQuery(SelectAtJobFinishedTable);
            while (resetSelectAtJobFinishedTable.next()) {
                JobFinished_ITEM_COUNT = resetSelectAtJobFinishedTable.getInt("JobFinished_ITEM_COUNT");
                arrayList_JobFinished_ITEM_COUNT.add(JobFinished_ITEM_COUNT); //this adds an element to the list.
                JobFinished_ITEM_COUNT_COMPLETED = resetSelectAtJobFinishedTable.getInt("JobFinished_ITEM_COUNT_COMPLETED");
                arrayList_JobFinished_ITEM_COUNT_COMPLETED.add(JobFinished_ITEM_COUNT_COMPLETED); //this adds an element to the list.
                
                JobFinished_ITEM_COUNT_COUNT = JobFinished_ITEM_COUNT_COUNT + 1;
                JobFinished_ITEM_COUNT_COMPLETED_COUNT = JobFinished_ITEM_COUNT_COMPLETED_COUNT + 1;
                JobFinished_ITEM_COUNT_SUM = JobFinished_ITEM_COUNT_SUM + resetSelectAtJobFinishedTable.getInt("JobFinished_ITEM_COUNT");
                JobFinished_ITEM_COUNT_COMPLETED_SUM = JobFinished_ITEM_COUNT_COMPLETED_SUM + resetSelectAtJobFinishedTable.getInt("JobFinished_ITEM_COUNT_COMPLETED");
            }            
            Q1PositionOfITEM_COUNT = Math.round(JobFinished_ITEM_COUNT_COUNT + 1) / 4;
            Q3PositionOfITEM_COUNT = Math.round(JobFinished_ITEM_COUNT_COUNT + 1) * 3 / 4;

            Q1OfITEM_COUNT_COMPLETED = Math.round(JobFinished_ITEM_COUNT_COMPLETED_COUNT + 1) / 4;
            Q3OfITEM_COUNT_COMPLETED = Math.round(JobFinished_ITEM_COUNT_COMPLETED_COUNT + 1) * 3 / 4;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "please contact for support.");
        }
    }
}
