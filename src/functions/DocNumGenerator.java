/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import db.ConnectSql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocNumGenerator {

    private int DocNumber;
    private String DocChr, mode;

    public void methodNumGen(String docType) {
        try {
            java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rset = stmt.executeQuery("Select * from SystemAutoCodes where DocType='" + docType + "'");

            if (!rset.next()) {
                String query = "Insert into SystemAutoCodes (AutoCode,AutoStartChr,DocType) Values ('1001','" + docType + "','" + docType + "')";
                stmt.execute(query);
                DocNumber = 1000;
                DocChr = docType;
            } else {
                DocNumber = rset.getInt("AutoCode");
                DocChr = rset.getString("AutoStartChr");
                rset.updateInt("AutoCode", (DocNumber + 1));
                rset.updateRow();
            }
            rset.close();
        } catch (SQLException ex) {
            Logger.getLogger(DocNumGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDocChar() {
        return DocChr;
    }

    public String getMode() {
        return mode;
    }

    public int getDocNumber() {
        return DocNumber;
    }
}
