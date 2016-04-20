/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import MainFiles.Main;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ReadConfig {

    public static String db_driver;
    public static String db_url;
    public static String db_username;
    public static String db_password;

    public static String EnableSellPricePresentage;
    public static String BarCodeImageType;
    public static String Date;

    static {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(Main.path + "Config\\Config.properties"));

            String driver = prop.getProperty("db_driver");
            String ip = prop.getProperty("db_ip");
            String dbName = prop.getProperty("db_name");
            db_username = prop.getProperty("db_username");
            db_password = prop.getProperty("db_password");

            if (db_password.equals("")) {
                db_password = "RavSam$#@!";
            }

            //------------------------------------------------------------------
            EnableSellPricePresentage = prop.getProperty("EnableSellPricePresentage");
            BarCodeImageType = prop.getProperty("BarCodeImageType");

            if (driver.equals("SQL2000")) {
                db_driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
                db_url = "jdbc:microsoft:sqlserver://" + ip + ":1433;DatabaseName=" + dbName;
            } else if (driver.equals("MSSQL")) {
                db_driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                db_url = "jdbc:sqlserver://" + ip + ":1433;databaseName=" + dbName;
            } else if (driver.equals("MySQL")) {
                db_driver = "com.mysql.jdbc.Driver";
                db_url = "jdbc:mysql://" + ip + ":3306/" + dbName;
            }

            Date = prop.getProperty("DateType");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Can't read cofig files");
            Logger.getLogger(ReadConfig.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }
}
