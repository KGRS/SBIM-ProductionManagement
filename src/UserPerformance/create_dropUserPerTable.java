/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserPerformance;

import db.ConnectSql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author LuckyLak
 */
public class create_dropUserPerTable {

    public void createUserViseItemComplete() {

        try {
            Statement stmt;
            String query;

            query = "SELECT\n"
                    + "     EmployeesAtFinishedJob.\"EMPLOYEE_CODE\" AS EmployeesAtFinishedJob_EMPLOYEE_CODE,\n"
                    + "     JobFinished.\"JOB_ID\" AS JobFinished_JOB_ID,\n"
                    + "     JobFinished.\"FIXED_JOB_ID\" AS JobFinished_FIXED_JOB_ID,\n"
                    + "     JobFinished.\"TAKEN_TIME\" AS JobFinished_TAKEN_TIME,\n"
                    + "     JobFinished.\"ITEM_COUNT_COMPLETED\" AS JobFinished_ITEM_COUNT_COMPLETED\n"
                    + "INTO \n"
                    + "	 \"dbo\".\"UsersVsCompleteItems\"\n"
                    + "FROM\n"
                    + "     \"dbo\".\"EmployeesAtFinishedJob\" EmployeesAtFinishedJob INNER JOIN \"dbo\".\"JobFinished\" JobFinished ON EmployeesAtFinishedJob.\"JOB_ID\" = JobFinished.\"JOB_ID\"";

            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.execute(query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        }
    }

    public void deleteUserViseItemComplete() {
        try {
            Statement stmt;
            String query;

            query = "DROP TABLE [dbo].[UsersVsCompleteItems]";

            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.execute(query);
            System.out.println("run deleteUserViseItemComplete");
        } catch (SQLException ex) {
            Logger.getLogger(calcUserPerformance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
