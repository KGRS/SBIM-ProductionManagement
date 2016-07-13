/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import db.ConnectSql;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import reports.JobDetailedScreen;

public class ColoursOfTableOfJobDetailedScreen2 extends DefaultTableCellRenderer {

    @Override
    public synchronized Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        Component c
                = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus,
                        row, column);
        try {
            String jobStatus, checkingJobStatus = "Yes", notCheckingJobStatus = "No";
            String FIXED_JOB_ID = (String) JobDetailedScreen.tableFixedJobProcess.getValueAt(row, 0).toString();
            ResultSet reset;
            Statement stmt;
            String query = "SELECT\n"
                    + "     JobRunning.\"FIXED_JOB_ID\" AS JobRunning_FIXED_JOB_ID,\n"
                    + "     JobRunning.\"IS_LATE\" AS JobRunning_IS_LATE,\n"
                    + "     JobRunning.\"IS_NEW_ONGOING\" AS JobRunning_IS_NEW_ONGOING\n"
                    + "FROM\n"
                    + "     \"dbo\".\"JobRunning\" JobRunning\n"
                    + "WHERE\n"
//                    + "     JobRunning.\"IS_LATE\" = 'Yes'\n"
                    + " JobRunning.\"FIXED_JOB_ID\" = '"+FIXED_JOB_ID+"'\n"
                    + " AND JobRunning.\"IS_NEW_ONGOING\" = 'Ongoing'";
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);
            if (reset.next()) {
                jobStatus = reset.getString("JobRunning_IS_LATE");
                if (jobStatus.equals(checkingJobStatus)) {
                    c.setBackground(Color.red);
                    c.setForeground(Color.white);
                }
                else if (jobStatus.equals(notCheckingJobStatus)) {
                    c.setBackground(Color.green);
                    c.setForeground(Color.white);
                }
            } else if (!reset.next()) {
                c.setBackground(Color.white);
                c.setForeground(Color.black);
            }
        } catch (Exception ex) {
            Logger.getLogger(ColoursOfTableOfJobDetailedScreen2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
}
