/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import static MainFiles.IndexPage.jobDetailedScreen;
import db.ConnectSql;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static reports.JobDetailedScreen.tableStaticsOfItemCountCompleted;

/**
 *
 * @author KGRS
 */
public class CalculateAvgPLItemCompletedForAvgTime {

    public static double roundThreeDecimals(double d) {
        DecimalFormat noDForm = new DecimalFormat("#.###");
        return Double.valueOf(noDForm.format(d));
    }

    public static double plItemsProductionCount(String PRODUCT_LEVEL_ITEM_CODE) {
        ResultSet resetSelectAtJobFinishedTable, resetSelectAtJobFinishedTableCompleted;
        double Q1PositionOfITEM_COUNT_CAL, Q3PositionOfITEM_COUNT_CAL, Q1PositionOfITEM_COUNT_COMPLETED_CAL, Q3PositionOfITEM_COUNT_COMPLETED_CAL;
        int JobFinished_ITEM_COUNT, JobFinished_ITEM_COUNT_COMPLETED;
        double distance_of_ITEM_COUNT;
        double lowerTail_of_ITEM_COUNT = 0, upperTail_of_ITEM_COUNT = 0;
        double distance_of_ITEM_COUNT_COMPLETED;
        double lowerTail_of_ITEM_COUNT_COMPLETED, upperTail_of_ITEM_COUNT_COMPLETED;
        double JobFinished_ITEM_COUNT_COUNT = 0;
        double JobFinished_ITEM_COUNT_COMPLETED_COUNT = 0;
        int JobFinished_ITEM_COUNT_SUM = 0, JobFinished_ITEM_COUNT_COMPLETED_SUM = 0;
        double Q1OfITEM_COUNT = 0;
        double Q3OfITEM_COUNT = 0;
        int Q1PositionOfITEM_COUNT, Q3PositionOfITEM_COUNT;
        double Q1OfITEM_COUNT_COMPLETED = 0, Q3OfITEM_COUNT_COMPLETED = 0;
        double avarageOfITEM_COUNT_COMPLETED = 0;
        int Q1PositionOfITEM_COUNT_COMPLETED, Q3PositionOfITEM_COUNT_COMPLETED;
        try {
            ArrayList<Integer> arrayList_JobFinished_ITEM_COUNT = new ArrayList<Integer>();
            ArrayList<Integer> arrayList_JobFinished_ITEM_COUNT_COMPLETED = new ArrayList<Integer>();
            java.sql.Statement stmtSelectAtJobFinishedTableItemCount = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            java.sql.Statement stmtSelectAtJobFinishedTableItemCountCompleted = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SelectAtJobFinishedTableItemCount = "SELECT\n"
                    + "     JobFinished.\"ITEM_COUNT\" AS JobFinished_ITEM_COUNT,\n"
                    + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" AS JobFixed_PRODUCT_LEVEL_ITEM_CODE\n"
                    + "FROM\n"
                    + "     \"dbo\".\"JobFinished\" JobFinished INNER JOIN \"dbo\".\"PLItemDifference\" PLItemDifference ON JobFinished.\"JOB_ID\" = PLItemDifference.\"JOB_ID\"\n"
                    + "     INNER JOIN \"dbo\".\"JobFixed\" JobFixed ON JobFinished.\"FIXED_JOB_ID\" = JobFixed.\"JOB_FIXED_ID\"\n"
                    + "WHERE\n"
                    + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" = '" + PRODUCT_LEVEL_ITEM_CODE + "'\n"
                    + " AND PLItemDifference.\"IS_WASTAGE\" = 'No'\n"
                    + " AND JobFinished.\"IS_COMPLETE_CANCLE\" = 'Completed' ORDER BY JobFinished.\"ITEM_COUNT\" ASC";
            resetSelectAtJobFinishedTable = stmtSelectAtJobFinishedTableItemCount.executeQuery(SelectAtJobFinishedTableItemCount);

            String SelectAtJobFinishedTableItemCountCompleted = "SELECT\n"
                    + "     JobFinished.\"ITEM_COUNT_COMPLETED\" AS JobFinished_ITEM_COUNT_COMPLETED,\n"
                    + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" AS JobFixed_PRODUCT_LEVEL_ITEM_CODE\n"
                    + "FROM\n"
                    + "     \"dbo\".\"JobFinished\" JobFinished INNER JOIN \"dbo\".\"PLItemDifference\" PLItemDifference ON JobFinished.\"JOB_ID\" = PLItemDifference.\"JOB_ID\"\n"
                    + "     INNER JOIN \"dbo\".\"JobFixed\" JobFixed ON JobFinished.\"FIXED_JOB_ID\" = JobFixed.\"JOB_FIXED_ID\"\n"
                    + "WHERE\n"
                    + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" = '" + PRODUCT_LEVEL_ITEM_CODE + "'\n"
                    + " AND PLItemDifference.\"IS_WASTAGE\" = 'No'\n"
                    + " AND JobFinished.\"IS_COMPLETE_CANCLE\" = 'Completed' ORDER BY JobFinished.\"ITEM_COUNT_COMPLETED\" ASC";
            resetSelectAtJobFinishedTableCompleted = stmtSelectAtJobFinishedTableItemCountCompleted.executeQuery(SelectAtJobFinishedTableItemCountCompleted);
            while (resetSelectAtJobFinishedTable.next() && resetSelectAtJobFinishedTableCompleted.next()) {
                JobFinished_ITEM_COUNT = resetSelectAtJobFinishedTable.getInt("JobFinished_ITEM_COUNT");
                arrayList_JobFinished_ITEM_COUNT.add(JobFinished_ITEM_COUNT); //this adds an element to the list.

                JobFinished_ITEM_COUNT_COMPLETED = resetSelectAtJobFinishedTableCompleted.getInt("JobFinished_ITEM_COUNT_COMPLETED");
                arrayList_JobFinished_ITEM_COUNT_COMPLETED.add(JobFinished_ITEM_COUNT_COMPLETED); //this adds an element to the list.

                JobFinished_ITEM_COUNT_COUNT = JobFinished_ITEM_COUNT_COUNT + 1;
                JobFinished_ITEM_COUNT_COMPLETED_COUNT = JobFinished_ITEM_COUNT_COMPLETED_COUNT + 1;
                JobFinished_ITEM_COUNT_SUM = JobFinished_ITEM_COUNT_SUM + resetSelectAtJobFinishedTable.getInt("JobFinished_ITEM_COUNT");
                JobFinished_ITEM_COUNT_COMPLETED_SUM = JobFinished_ITEM_COUNT_COMPLETED_SUM + resetSelectAtJobFinishedTableCompleted.getInt("JobFinished_ITEM_COUNT_COMPLETED");
            }

            Q1PositionOfITEM_COUNT_CAL = (JobFinished_ITEM_COUNT_COUNT + 1) / 4;
            if (Q1PositionOfITEM_COUNT_CAL >= 2) {
                if (Q1PositionOfITEM_COUNT_CAL == Math.round(Q1PositionOfITEM_COUNT_CAL)) {
                    Q1PositionOfITEM_COUNT = (int) (Q1PositionOfITEM_COUNT_CAL);
                    Q1OfITEM_COUNT = arrayList_JobFinished_ITEM_COUNT.get(Q1PositionOfITEM_COUNT);
                } else if (Q1PositionOfITEM_COUNT_CAL != Math.round(Q1PositionOfITEM_COUNT_CAL)) {
                    int Q1PositionOfITEM_COUNT1, Q1PositionOfITEM_COUNT2;
                    double Q1OfITEM_COUNT1, Q1OfITEM_COUNT2;
                    Q1PositionOfITEM_COUNT1 = (int) (Q1PositionOfITEM_COUNT_CAL);
                    Q1PositionOfITEM_COUNT2 = Q1PositionOfITEM_COUNT1 + 1;
                    Q1OfITEM_COUNT1 = arrayList_JobFinished_ITEM_COUNT.get(Q1PositionOfITEM_COUNT1 - 1);
                    Q1OfITEM_COUNT2 = arrayList_JobFinished_ITEM_COUNT.get(Q1PositionOfITEM_COUNT2 - 1);
                    Q1OfITEM_COUNT = (Q1OfITEM_COUNT1 + Q1OfITEM_COUNT2) / 2;
                }
                Q3PositionOfITEM_COUNT_CAL = (JobFinished_ITEM_COUNT_COUNT + 1) * 3 / 4;
                if (Q3PositionOfITEM_COUNT_CAL == Math.round(Q3PositionOfITEM_COUNT_CAL)) {
                    Q3PositionOfITEM_COUNT = (int) Q3PositionOfITEM_COUNT_CAL;
                    Q3OfITEM_COUNT = arrayList_JobFinished_ITEM_COUNT.get(Q3PositionOfITEM_COUNT);
                } else if (Q3PositionOfITEM_COUNT_CAL != Math.round(Q3PositionOfITEM_COUNT_CAL)) {
                    int Q3PositionOfITEM_COUNT1, Q3PositionOfITEM_COUNT2;
                    double Q3OfITEM_COUNT1, Q3OfITEM_COUNT2;
                    Q3PositionOfITEM_COUNT1 = (int) (Q3PositionOfITEM_COUNT_CAL);
                    Q3PositionOfITEM_COUNT2 = Q3PositionOfITEM_COUNT1 + 1;
                    Q3OfITEM_COUNT1 = arrayList_JobFinished_ITEM_COUNT.get(Q3PositionOfITEM_COUNT1 - 1);
                    Q3OfITEM_COUNT2 = arrayList_JobFinished_ITEM_COUNT.get(Q3PositionOfITEM_COUNT2 - 1);
                    Q3OfITEM_COUNT = (Q3OfITEM_COUNT1 + Q3OfITEM_COUNT2) / 2;
                }

                distance_of_ITEM_COUNT = Q3OfITEM_COUNT - Q1OfITEM_COUNT;
                lowerTail_of_ITEM_COUNT = Q1OfITEM_COUNT - (1.5 * distance_of_ITEM_COUNT);
                if (lowerTail_of_ITEM_COUNT < 0) {
                    lowerTail_of_ITEM_COUNT = 0;
                }
                upperTail_of_ITEM_COUNT = Q3OfITEM_COUNT + (1.5 * distance_of_ITEM_COUNT);
            }

/////////////////////////////////////////////////////////////////////////////            
            Q1PositionOfITEM_COUNT_COMPLETED_CAL = (JobFinished_ITEM_COUNT_COMPLETED_COUNT + 1) / 4;
            if (Q1PositionOfITEM_COUNT_COMPLETED_CAL >= 2) {
                if (Q1PositionOfITEM_COUNT_COMPLETED_CAL == Math.round(Q1PositionOfITEM_COUNT_COMPLETED_CAL)) {
                    Q1PositionOfITEM_COUNT_COMPLETED = (int) (Q1PositionOfITEM_COUNT_COMPLETED_CAL);
                    Q1OfITEM_COUNT_COMPLETED = arrayList_JobFinished_ITEM_COUNT_COMPLETED.get(Q1PositionOfITEM_COUNT_COMPLETED - 1);
                } else if (Q1PositionOfITEM_COUNT_COMPLETED_CAL != Math.round(Q1PositionOfITEM_COUNT_COMPLETED_CAL)) {
                    int Q1PositionOfITEM_COUNT_COMPLETED_1, Q1PositionOfITEM_COUNT_COMPLETED_2;
                    double Q1OfITEM_COUNT_COMPLETED_1, Q1OfITEM_COUNT_COMPLETED_2;
                    Q1PositionOfITEM_COUNT_COMPLETED_1 = (int) (Q1PositionOfITEM_COUNT_COMPLETED_CAL);
                    Q1PositionOfITEM_COUNT_COMPLETED_2 = Q1PositionOfITEM_COUNT_COMPLETED_1 + 1;
                    Q1OfITEM_COUNT_COMPLETED_1 = arrayList_JobFinished_ITEM_COUNT_COMPLETED.get(Q1PositionOfITEM_COUNT_COMPLETED_1 - 1);
                    Q1OfITEM_COUNT_COMPLETED_2 = arrayList_JobFinished_ITEM_COUNT_COMPLETED.get(Q1PositionOfITEM_COUNT_COMPLETED_2 - 1);
                    Q1OfITEM_COUNT_COMPLETED = (Q1OfITEM_COUNT_COMPLETED_1 + Q1OfITEM_COUNT_COMPLETED_2) / 2;
                }

                Q3PositionOfITEM_COUNT_COMPLETED_CAL = (JobFinished_ITEM_COUNT_COMPLETED_COUNT + 1) * 3 / 4;
                if (Q3PositionOfITEM_COUNT_COMPLETED_CAL == Math.round(Q3PositionOfITEM_COUNT_COMPLETED_CAL)) {
                    Q3PositionOfITEM_COUNT_COMPLETED = (int) (Q3PositionOfITEM_COUNT_COMPLETED_CAL);
                    Q3OfITEM_COUNT_COMPLETED = arrayList_JobFinished_ITEM_COUNT_COMPLETED.get(Q3PositionOfITEM_COUNT_COMPLETED - 1);
                } else if (Q3PositionOfITEM_COUNT_COMPLETED_CAL != Math.round(Q3PositionOfITEM_COUNT_COMPLETED_CAL)) {
                    int Q3PositionOfITEM_COUNT_COMPLETED_1, Q3PositionOfITEM_COUNT_COMPLETED_2;
                    double Q3OfITEM_COUNT_COMPLETED_1, Q3OfITEM_COUNT_COMPLETED_2;
                    Q3PositionOfITEM_COUNT_COMPLETED_1 = (int) (Q3PositionOfITEM_COUNT_COMPLETED_CAL);
                    Q3PositionOfITEM_COUNT_COMPLETED_2 = Q3PositionOfITEM_COUNT_COMPLETED_1 + 1;
                    Q3OfITEM_COUNT_COMPLETED_1 = arrayList_JobFinished_ITEM_COUNT_COMPLETED.get(Q3PositionOfITEM_COUNT_COMPLETED_1 - 1);
                    Q3OfITEM_COUNT_COMPLETED_2 = arrayList_JobFinished_ITEM_COUNT_COMPLETED.get(Q3PositionOfITEM_COUNT_COMPLETED_2 - 1);
                    Q3OfITEM_COUNT_COMPLETED = (Q3OfITEM_COUNT_COMPLETED_1 + Q3OfITEM_COUNT_COMPLETED_2) / 2;
                }
                distance_of_ITEM_COUNT_COMPLETED = Q3OfITEM_COUNT_COMPLETED - Q1OfITEM_COUNT_COMPLETED;
                lowerTail_of_ITEM_COUNT_COMPLETED = Q1OfITEM_COUNT_COMPLETED - (1.5 * distance_of_ITEM_COUNT_COMPLETED);
                if (lowerTail_of_ITEM_COUNT_COMPLETED < 0) {
                    lowerTail_of_ITEM_COUNT_COMPLETED = 0;
                }
                upperTail_of_ITEM_COUNT_COMPLETED = Q3OfITEM_COUNT_COMPLETED + (1.5 * distance_of_ITEM_COUNT_COMPLETED);
                avarageOfITEM_COUNT_COMPLETED = calculateFilteredAverageToReturnAvGOfItemCompleted(PRODUCT_LEVEL_ITEM_CODE, lowerTail_of_ITEM_COUNT, upperTail_of_ITEM_COUNT, lowerTail_of_ITEM_COUNT_COMPLETED, upperTail_of_ITEM_COUNT_COMPLETED);

                if (jobDetailedScreen != null) {
                    DefaultTableModel model_tableStaticsOfItemCountCompleted = (DefaultTableModel) tableStaticsOfItemCountCompleted.getModel();
                    model_tableStaticsOfItemCountCompleted.addRow(new Object[]{PRODUCT_LEVEL_ITEM_CODE, Q1OfITEM_COUNT_COMPLETED, avarageOfITEM_COUNT_COMPLETED, Q3OfITEM_COUNT_COMPLETED, lowerTail_of_ITEM_COUNT_COMPLETED, upperTail_of_ITEM_COUNT_COMPLETED, distance_of_ITEM_COUNT_COMPLETED, JobFinished_ITEM_COUNT_COMPLETED_COUNT});
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        }
        return avarageOfITEM_COUNT_COMPLETED;
    }

    private static double calculateFilteredAverageToReturnAvGOfItemCompleted(String PRODUCT_LEVEL_ITEM_CODE, double lowerTail_of_ITEM_COUNT, double upperTail_of_ITEM_COUNT, double lowerTail_of_ITEM_COUNT_COMPLETED, double upperTail_of_ITEM_COUNT_COMPLETED) {
        ResultSet resetSelectAtJobFinishedTableFILTERED_ITEM_COUNT, resetSelectAtJobFinishedTableFILTERED_ITEM_COUNT_COMPLETED;
        int JobFinished_ITEM_COUNT, JobFinished_ITEM_COUNT_COMPLETED;
        double JobFinished_ITEM_COUNT_COUNT = 0, JobFinished_ITEM_COUNT_COMPLETED_COUNT = 0;
        double avaragePositionOfITEM_COUNT_CAL, avaragePositionOfITEM_COUNT_COMPLETED_CAL;
        int avaragePositionOfITEM_COUNT, avaragePositionOfITEM_COUNT_COMPLETED;
        double avarageOfITEM_COUNT = 0, avarageOfITEM_COUNT_COMPLETED = 0;
        try {
            ArrayList<Integer> arrayList_JobFinished_ITEM_COUNT_FILTERED = new ArrayList<Integer>();
            ArrayList<Integer> arrayList_JobFinished_ITEM_COUNT_COMPLETED_FILTERED = new ArrayList<Integer>();
            java.sql.Statement stmtSelectAtJobFinishedTableFILTERED_ITEM_COUNT = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            java.sql.Statement stmtSelectAtJobFinishedTableFILTERED_ITEM_COUNT_COMPLETED_FILTERED = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SelectAtJobFinishedTableFILTERED_ITEM_COUNT = "SELECT\n"
                    + "     JobFinished.\"ITEM_COUNT\" AS JobFinished_ITEM_COUNT,\n"
                    + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" AS JobFixed_PRODUCT_LEVEL_ITEM_CODE\n"
                    + "FROM\n"
                    + "     \"dbo\".\"JobFinished\" JobFinished INNER JOIN \"dbo\".\"PLItemDifference\" PLItemDifference ON JobFinished.\"JOB_ID\" = PLItemDifference.\"JOB_ID\"\n"
                    + "     INNER JOIN \"dbo\".\"JobFixed\" JobFixed ON JobFinished.\"FIXED_JOB_ID\" = JobFixed.\"JOB_FIXED_ID\"\n"
                    + "WHERE\n"
                    + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" = '" + PRODUCT_LEVEL_ITEM_CODE + "'\n"
                    + " AND PLItemDifference.\"IS_WASTAGE\" = 'No'\n"
                    + " AND JobFinished.\"IS_COMPLETE_CANCLE\" = 'Completed'"
                    + " AND (JobFinished.\"ITEM_COUNT\" >= " + lowerTail_of_ITEM_COUNT + " AND JobFinished.\"ITEM_COUNT\" <= " + upperTail_of_ITEM_COUNT + ")"
                    + " ORDER BY JobFinished.\"ITEM_COUNT\" ASC";
            resetSelectAtJobFinishedTableFILTERED_ITEM_COUNT = stmtSelectAtJobFinishedTableFILTERED_ITEM_COUNT.executeQuery(SelectAtJobFinishedTableFILTERED_ITEM_COUNT);

            String SelectAtJobFinishedTableFILTERED_ITEM_COUNT_COMPLETED_FILTERED = "SELECT\n"
                    + "     JobFinished.\"ITEM_COUNT_COMPLETED\" AS JobFinished_ITEM_COUNT_COMPLETED,\n"
                    + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" AS JobFixed_PRODUCT_LEVEL_ITEM_CODE\n"
                    + "FROM\n"
                    + "     \"dbo\".\"JobFinished\" JobFinished INNER JOIN \"dbo\".\"PLItemDifference\" PLItemDifference ON JobFinished.\"JOB_ID\" = PLItemDifference.\"JOB_ID\"\n"
                    + "     INNER JOIN \"dbo\".\"JobFixed\" JobFixed ON JobFinished.\"FIXED_JOB_ID\" = JobFixed.\"JOB_FIXED_ID\"\n"
                    + "WHERE\n"
                    + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" = '" + PRODUCT_LEVEL_ITEM_CODE + "'\n"
                    + " AND PLItemDifference.\"IS_WASTAGE\" = 'No'\n"
                    + " AND JobFinished.\"IS_COMPLETE_CANCLE\" = 'Completed'"
                    + " AND (JobFinished.\"ITEM_COUNT_COMPLETED\" >= " + lowerTail_of_ITEM_COUNT_COMPLETED + " AND JobFinished.\"ITEM_COUNT_COMPLETED\" <= " + upperTail_of_ITEM_COUNT_COMPLETED + ")"
                    + " ORDER BY JobFinished.\"ITEM_COUNT_COMPLETED\" ASC";
            resetSelectAtJobFinishedTableFILTERED_ITEM_COUNT_COMPLETED = stmtSelectAtJobFinishedTableFILTERED_ITEM_COUNT_COMPLETED_FILTERED.executeQuery(SelectAtJobFinishedTableFILTERED_ITEM_COUNT_COMPLETED_FILTERED);
            while (resetSelectAtJobFinishedTableFILTERED_ITEM_COUNT.next() && resetSelectAtJobFinishedTableFILTERED_ITEM_COUNT_COMPLETED.next()) {
                JobFinished_ITEM_COUNT = resetSelectAtJobFinishedTableFILTERED_ITEM_COUNT.getInt("JobFinished_ITEM_COUNT");
                arrayList_JobFinished_ITEM_COUNT_FILTERED.add(JobFinished_ITEM_COUNT);
                JobFinished_ITEM_COUNT_COUNT = JobFinished_ITEM_COUNT_COUNT + 1;

                JobFinished_ITEM_COUNT_COMPLETED = resetSelectAtJobFinishedTableFILTERED_ITEM_COUNT_COMPLETED.getInt("JobFinished_ITEM_COUNT_COMPLETED");
                arrayList_JobFinished_ITEM_COUNT_COMPLETED_FILTERED.add(JobFinished_ITEM_COUNT_COMPLETED);
                JobFinished_ITEM_COUNT_COMPLETED_COUNT = JobFinished_ITEM_COUNT_COMPLETED_COUNT + 1;
            }
            avaragePositionOfITEM_COUNT_CAL = JobFinished_ITEM_COUNT_COUNT / 2;
            if (avaragePositionOfITEM_COUNT_CAL >= 2) {
                if (avaragePositionOfITEM_COUNT_CAL == Math.round(avaragePositionOfITEM_COUNT_CAL)) {
                    avaragePositionOfITEM_COUNT = (int) (avaragePositionOfITEM_COUNT_CAL);
                    avarageOfITEM_COUNT = arrayList_JobFinished_ITEM_COUNT_FILTERED.get(avaragePositionOfITEM_COUNT - 1);
                } else if (avaragePositionOfITEM_COUNT_CAL != Math.round(avaragePositionOfITEM_COUNT_CAL)) {
                    int avarage_Position_OfITEM_COUNT_1, avarage_Position_OfITEM_COUNT_2;
                    double avarageOfITEM_COUNT_1, avarageOfITEM_COUNT_2;
                    avarage_Position_OfITEM_COUNT_1 = (int) (avaragePositionOfITEM_COUNT_CAL);
                    avarage_Position_OfITEM_COUNT_2 = avarage_Position_OfITEM_COUNT_1 + 1;
                    avarageOfITEM_COUNT_1 = arrayList_JobFinished_ITEM_COUNT_FILTERED.get(avarage_Position_OfITEM_COUNT_1 - 1);
                    avarageOfITEM_COUNT_2 = arrayList_JobFinished_ITEM_COUNT_FILTERED.get(avarage_Position_OfITEM_COUNT_2 - 1);
                    avarageOfITEM_COUNT = (avarageOfITEM_COUNT_1 + avarageOfITEM_COUNT_2) / 2;
                }
            }
            avaragePositionOfITEM_COUNT_COMPLETED_CAL = JobFinished_ITEM_COUNT_COMPLETED_COUNT / 2;
            if (avaragePositionOfITEM_COUNT_COMPLETED_CAL >= 2) {
                if (avaragePositionOfITEM_COUNT_COMPLETED_CAL == Math.round(avaragePositionOfITEM_COUNT_COMPLETED_CAL)) {
                    avaragePositionOfITEM_COUNT_COMPLETED = (int) (avaragePositionOfITEM_COUNT_COMPLETED_CAL);
                    avarageOfITEM_COUNT_COMPLETED = arrayList_JobFinished_ITEM_COUNT_COMPLETED_FILTERED.get(avaragePositionOfITEM_COUNT_COMPLETED - 1);
                } else if (avaragePositionOfITEM_COUNT_COMPLETED_CAL != Math.round(avaragePositionOfITEM_COUNT_COMPLETED_CAL)) {
                    int avarage_Position_OfITEM_COUNT_COMPLETED_1, avarage_Position_OfITEM_COUNT_COMPLETED_2;
                    double avarageOfITEM_COUNT_COMPLETED_1, avarageOfITEM_COUNT_COMPLETED_2;
                    avarage_Position_OfITEM_COUNT_COMPLETED_1 = (int) (avaragePositionOfITEM_COUNT_CAL);
                    avarage_Position_OfITEM_COUNT_COMPLETED_2 = avarage_Position_OfITEM_COUNT_COMPLETED_1 + 1;
                    avarageOfITEM_COUNT_COMPLETED_1 = arrayList_JobFinished_ITEM_COUNT_COMPLETED_FILTERED.get(avarage_Position_OfITEM_COUNT_COMPLETED_1 - 1);
                    avarageOfITEM_COUNT_COMPLETED_2 = arrayList_JobFinished_ITEM_COUNT_COMPLETED_FILTERED.get(avarage_Position_OfITEM_COUNT_COMPLETED_2 - 1);
                    avarageOfITEM_COUNT_COMPLETED = (avarageOfITEM_COUNT_COMPLETED_1 + avarageOfITEM_COUNT_COMPLETED_2) / 2;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        }
        return avarageOfITEM_COUNT_COMPLETED;
    }
}
