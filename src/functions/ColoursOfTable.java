/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import Transactions.JobStatus;
import db.ConnectSql;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ColoursOfTable extends DefaultTableCellRenderer {

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
            String jobStatus, checkingJobStatus = "Yes";
            String jobID = (String) JobStatus.tableJobs.getValueAt(row, 0).toString();
            ResultSet reset;
            Statement stmt;
            String query = "Select IS_LATE from JobRunning where IS_LATE='Yes' AND JOB_ID='" + jobID + "'";
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);
            if (reset.next()) {
                jobStatus = reset.getString("IS_LATE");
                if (jobStatus.equals(checkingJobStatus)) {
                    c.setBackground(Color.red);
                    c.setForeground(Color.white);
                }
            } else if (!reset.next()) {
                c.setBackground(Color.white);
                c.setForeground(Color.black);
            }
        } catch (Exception ex) {
            Logger.getLogger(ColoursOfTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

//    private boolean Check(String isLate) {
//        boolean check = false;
//        try {
//            Connection conn = ConnectSql.conn;
//            java.sql.Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            ResultSet rset = stmt.executeQuery("Select IS_LATE from JobRunning where IS_LATE='Yes'");
//            if (rset.next()) {
//                check = true;
//            }
//
//        } catch (Exception ex) {
//            Logger.getLogger(ColoursOfTable.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return check;
//    }
}
