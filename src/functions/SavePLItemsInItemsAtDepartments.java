/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import db.ConnectSql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author KGRS
 */
public class SavePLItemsInItemsAtDepartments {

    public static void savePLItem(String PLItemCode, String productLevel, String DepartmentCode) {
        float Quantity = 0, SellPrice;
//        int PriceLevel = 0;
        String UnitCode;
        ResultSet resetSelectAtItemsAtDepartmentsTable;
        try {
            java.sql.Statement stmtSelectAtItemsAtDepartmentsTable = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String selectAtItemsAtDepartmentsTable = "SELECT ItemCode, DepartmentCode FROM ItemsAtDepartments "
                    + "WHERE ItemCode = '" + PLItemCode + "' AND DepartmentCode = '" + DepartmentCode + "'";
            resetSelectAtItemsAtDepartmentsTable = stmtSelectAtItemsAtDepartmentsTable.executeQuery(selectAtItemsAtDepartmentsTable);
            if (!resetSelectAtItemsAtDepartmentsTable.next()) {
                if (productLevel.equals("1")) {
                    ResultSet resetPl1Items;
                    try {
                        java.sql.Statement stmtInsert = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        java.sql.Statement stmtplItems = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        String selectPl1Items = "SELECT\n"
                                + "     ProductLevel1.\"PL1_ITEM_CODE\" AS ProductLevel1_PL1_ITEM_CODE,\n"
                                + "     ProductLevel1.\"SellPrice\" AS ProductLevel1_SellPrice,\n"
                                + "     ProductLevel1.\"UnitCode\" AS ProductLevel1_UnitCode\n"
                                + "FROM\n"
                                + "     \"dbo\".\"ProductLevel1\" ProductLevel1\n"
                                + "WHERE ProductLevel1.\"PL1_ITEM_CODE\" = '" + PLItemCode + "'";
                        resetPl1Items = stmtplItems.executeQuery(selectPl1Items);
                        if (resetPl1Items.next()) {
                            UnitCode = resetPl1Items.getString("ProductLevel1_UnitCode");
                            SellPrice = resetPl1Items.getFloat("ProductLevel1_SellPrice");
                            String insertAtItemsAtDepartmentsTable = "INSERT INTO ItemsAtDepartments (ItemCode"
                                    + ", DepartmentCode, SellPrice, Quantity, UnitSell, PriceLevel) VALUES ('" + PLItemCode + "'"
                                    + ", '" + DepartmentCode + "', '" + SellPrice + "'"
                                    + ", '" + Quantity + "'"
                                    + ", '" + UnitCode + "', '0')";
                            stmtInsert.execute(insertAtItemsAtDepartmentsTable);
                        }
                        stmtplItems.close();
                        resetPl1Items.close();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(null, "Please contact for support.");
                    }
                } else if (productLevel.equals("2")) {
                    ResultSet resetPl2Items;
                    try {
                        java.sql.Statement stmtInsert = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        java.sql.Statement stmtplItems = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        String selectPl1Items = "SELECT\n"
                                + "     ProductLevel2.\"PL2_ITEM_CODE\" AS ProductLevel2_PL2_ITEM_CODE,\n"
                                + "     ProductLevel2.\"UnitCode\" AS ProductLevel2_UnitCode,\n"
                                + "     ProductLevel2.\"SellPrice\" AS ProductLevel2_SellPrice\n"
                                + "FROM\n"
                                + "     \"dbo\".\"ProductLevel2\" ProductLevel2\n"
                                + "WHERE\n"
                                + "     ProductLevel2.\"PL2_ITEM_CODE\" = '" + PLItemCode + "'";
                        resetPl2Items = stmtplItems.executeQuery(selectPl1Items);
                        if (resetPl2Items.next()) {
                            UnitCode = resetPl2Items.getString("ProductLevel2_UnitCode");
                            SellPrice = resetPl2Items.getFloat("ProductLevel2_SellPrice");
                            String insertAtItemsAtDepartmentsTable = "INSERT INTO ItemsAtDepartments (ItemCode"
                                    + ", DepartmentCode, SellPrice, Quantity, UnitSell, PriceLevel) VALUES ('" + PLItemCode + "'"
                                    + ", '" + DepartmentCode + "', '" + SellPrice + "'"
                                    + ", '" + Quantity + "'"
                                    + ", '" + UnitCode + "', '0')";
                            stmtInsert.execute(insertAtItemsAtDepartmentsTable);
                        }
                        stmtplItems.close();
                        resetPl2Items.close();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(null, "Please contact for support.");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SavePLItemsInItemsAtDepartments.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        }
    }
}
