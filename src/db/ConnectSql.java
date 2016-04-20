/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import functions.ReadConfig;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import pool.ConnectionFactory;

/**
 *
 * @author Ravindu
 */

public class ConnectSql {

    public static Connection conn;

    public static boolean conect() {
        try {
            ConnectionFactory conF = new ConnectionFactory(ReadConfig.db_driver, ReadConfig.db_url, ReadConfig.db_username, ReadConfig.db_password, 5);
//            ConnectionFactory conF = new ConnectionFactory("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://192.168.1.10:1433;databaseName= PMS", "sa", "sa", 5);
            conn = conF.connect();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Connect to database is failed.", "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Connect to database is failed.", "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
            return false;
        }
        return true;
    }
}
