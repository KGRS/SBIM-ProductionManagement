/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import db.ConnectSql;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author KGRS
 */
public class CalculatePLItemDifference {
    public static void PLItemDifference(String JobRunning_SUPERVISE_BY, String jobID, int plItemDifference, String IS_WASTAGE, int itemCount, String PRODUCT_LEVEL_ITEM_CODE, int itemCompleted) {
        String IS_RETURN = "No";
        try {
            java.sql.Statement stmtInsertAtPLItemDifferenceTable = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);            
            String insertAtPLItemDifferenceTable = "INSERT INTO PLItemDifference (JOB_ID, CHANGE_ITEM_COUNT, IS_WASTAGE"
                    + ", IS_RETURN) VALUES ('" + jobID + "', '" + plItemDifference + "', '" + IS_WASTAGE + "'"
                    + ", '" + IS_RETURN + "')";
            stmtInsertAtPLItemDifferenceTable.execute(insertAtPLItemDifferenceTable);
            SendEMails sendEMails = new SendEMails();
            sendEMails.notifyAboutWastageOfCompleteJobsToSupervisourByEmail(JobRunning_SUPERVISE_BY, jobID, plItemDifference, itemCount, itemCompleted, PRODUCT_LEVEL_ITEM_CODE);
            stmtInsertAtPLItemDifferenceTable.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "please contact for support.");
        }
    }
}
