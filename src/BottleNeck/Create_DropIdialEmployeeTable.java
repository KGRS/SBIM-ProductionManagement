/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BottleNeck;

import db.ConnectSql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author LuckyLak
 */
public class Create_DropIdialEmployeeTable {

    public void createIdialEmpTable() {

        try {
            Statement stmt;
            String query;

            query = "SELECT\n"
                    + "     Employees.\"EMPLOYEE_CODE\" AS Employees_EMPLOYEE_CODE\n"
                    + "INTO\n"
                    + "	\"dbo\".\"IDIAL_EMP\"\n"
                    + "FROM\n"
                    + "     \"dbo\".\"JobRunning\" JobRunning INNER JOIN \"dbo\".\"EmployeesAtRunningJob\" EmployeesAtRunningJob ON JobRunning.\"JOB_ID\" = EmployeesAtRunningJob.\"JOB_ID\"\n"
                    + "     INNER JOIN \"dbo\".\"Employees\" Employees ON EmployeesAtRunningJob.\"EMPLOYEE_CODE\" != Employees.\"EMPLOYEE_CODE\"";

            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.execute(query);
            System.out.println("Run createIdialEmpTable");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        }
    }

    public void deleteIdialEmpTable() {
        try {
            Statement stmt;
            String query;

            query = "DROP TABLE [dbo].[IDIAL_EMP]";

            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.execute(query);
            System.out.println("Run deleteIdialEmpTable");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        }
    }
}
