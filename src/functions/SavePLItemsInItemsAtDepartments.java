/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import db.ConnectSql;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author KGRS
 */
public class SavePLItemsInItemsAtDepartments {

    public static void savePLItem(String PL1ItemCode, String productLevel) {
        float Quantity = 0, PriceLevel = 0, SellPrice;
        String UnitCode, DepartmentCode;
        if (productLevel.equals("1")) {
            ResultSet resetPl1Items;
            try {
                java.sql.Statement stmtInsert = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                java.sql.Statement stmtplItems = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String selectPl1Items = "SELECT\n"
                        + "     ProductLevel1.\"UnitCode\" AS ProductLevel1_UnitCode,\n"
                        + "     ProductLevel1.\"SellPrice\" AS ProductLevel1_SellPrice,\n"
                        + "     ProductLevel1.\"PL1_ITEM_CODE\" AS ProductLevel1_PL1_ITEM_CODE,\n"
                        + "     JobFixed.\"SUB_DEPARTMENT_CODE\" AS JobFixed_SUB_DEPARTMENT_CODE,\n"
                        + "     SubDepartments.\"DepartmentCode\" AS SubDepartments_DepartmentCode\n"
                        + "FROM\n"
                        + "     \"dbo\".\"JobFixed\" JobFixed INNER JOIN \"dbo\".\"ProductLevel1\" ProductLevel1 ON JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" = ProductLevel1.\"PL1_ITEM_CODE\"\n"
                        + "     INNER JOIN \"dbo\".\"SubDepartments\" SubDepartments ON JobFixed.\"SUB_DEPARTMENT_CODE\" = SubDepartments.\"SUB_DEPARTMENT_CODE\"\n"
                        + "WHERE\n"
                        + "     ProductLevel1.\"PL1_ITEM_CODE\" = '" + PL1ItemCode + "'";
                resetPl1Items = stmtplItems.executeQuery(selectPl1Items);
                if (resetPl1Items.next()) {
                    UnitCode = resetPl1Items.getString("ProductLevel1_UnitCode");
                    SellPrice = resetPl1Items.getFloat("ProductLevel1_SellPrice");
                    DepartmentCode = resetPl1Items.getString("SubDepartments_DepartmentCode");
                    String insertAtItemsAtDepartmentsTable = "INSERT INTO ItemsAtDepartments (ItemCode"
                            + ", DepartmentCode, SellPrice, Quantity, UnitSell, PriceLevel) VALUES ('" + PL1ItemCode + "'"
                            + ", '" + DepartmentCode + "', '" + SellPrice + "'"
                            + ", '" + Quantity + "'"
                            + ", '" + UnitCode + "', '" + PriceLevel + "')";
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
                        + "     JobFixed.\"SUB_DEPARTMENT_CODE\" AS JobFixed_SUB_DEPARTMENT_CODE,\n"
                        + "     SubDepartments.\"DepartmentCode\" AS SubDepartments_DepartmentCode,\n"
                        + "     ProductLevel2.\"PL2_ITEM_CODE\" AS ProductLevel2_PL2_ITEM_CODE,\n"
                        + "     ProductLevel2.\"UnitCode\" AS ProductLevel2_UnitCode,\n"
                        + "     ProductLevel2.\"SellPrice\" AS ProductLevel2_SellPrice\n"
                        + "FROM\n"
                        + "     \"dbo\".\"JobFixed\" JobFixed INNER JOIN \"dbo\".\"SubDepartments\" SubDepartments ON JobFixed.\"SUB_DEPARTMENT_CODE\" = SubDepartments.\"SUB_DEPARTMENT_CODE\"\n"
                        + "     INNER JOIN \"dbo\".\"ProductLevel2\" ProductLevel2 ON JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" = ProductLevel2.\"PL2_ITEM_CODE\"\n"
                        + "WHERE\n"
                        + "     ProductLevel2.\"PL2_ITEM_CODE\" = '" + PL1ItemCode + "'";
                resetPl2Items = stmtplItems.executeQuery(selectPl1Items);
                if (resetPl2Items.next()) {
                    UnitCode = resetPl2Items.getString("ProductLevel2_UnitCode");
                    SellPrice = resetPl2Items.getFloat("ProductLevel2_SellPrice");
                    DepartmentCode = resetPl2Items.getString("SubDepartments_DepartmentCode");
                    String insertAtItemsAtDepartmentsTable = "INSERT INTO ItemsAtDepartments (ItemCode"
                            + ", DepartmentCode, SellPrice, Quantity, UnitSell, PriceLevel) VALUES ('" + PL1ItemCode + "'"
                            + ", '" + DepartmentCode + "', '" + SellPrice + "'"
                            + ", '" + Quantity + "'"
                            + ", '" + UnitCode + "', '" + PriceLevel + "')";
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
}
