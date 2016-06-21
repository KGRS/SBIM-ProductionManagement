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

/**
 *
 * @author KGRS
 */
public class CalculateSubDepartmentStockAtJobComplete {

    public static void reduceStockOfSubDepartmentAtJobComplete(String productLevel, String productLevelItemCode, float itemCompleted, String DepartmentCode) {
        ResultSet resetSelectAtItemsAtDepartmentsTable, resetSelectPLAtItemsAtDepartmentsTable;
        String rowItemCode, rowItemUnitSell;
        float rowItemQuantity, calculateRowItemQuantity, calculatePLItemQuantity, rowItemSellPrice, rowItemExsistQuantity, PLItemExsistQuantity, sumOfcalculateRowItemQuantityAndRowItemExsistQuantity, minuseOfCalculateRowItemQuantity;
        int PriceLevel = 0;
        try {
            java.sql.Statement stmtSelectAtItemsAtDepartmentsTable = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            java.sql.Statement stmtInsertAtItemsAtDepartmentsTable = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            java.sql.Statement stmtUpdateAtItemsAtDepartments = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (productLevel.equals("1")) {
                ResultSet resetSelectAtProductLevel1;
                java.sql.Statement stmtSelectAtProductLevel1 = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String selectAtProductLevel1 = "SELECT\n"
                        + "     ProductLevel1.\"PL1_ITEM_CODE\" AS ProductLevel1_PL1_ITEM_CODE,\n"
                        + "     ProductLevel1RawItems.\"PL1_ITEM_QUANTITY\" AS ProductLevel1RawItems_PL1_ITEM_QUANTITY,\n"
                        + "     ProductLevel1RawItems.\"ItemCode\" AS ProductLevel1RawItems_ItemCode,\n"
                        + "     ProductLevel1RawItems.\"QUANTITY\" AS ProductLevel1RawItems_QUANTITY,\n"
                        + "     Items.\"SellPrice\" AS Items_SellPrice,\n"
                        + "     Items.\"UnitSell\" AS Items_UnitSell\n"
                        + "FROM\n"
                        + "     \"dbo\".\"ProductLevel1RawItems\" ProductLevel1RawItems INNER JOIN \"dbo\".\"ProductLevel1\" ProductLevel1 ON ProductLevel1RawItems.\"PL1_ITEM_CODE\" = ProductLevel1.\"PL1_ITEM_CODE\"\n"
                        + "     INNER JOIN \"dbo\".\"Items\" Items ON ProductLevel1RawItems.\"ItemCode\" = Items.\"ItemCode\"\n"
                        + "WHERE\n"
                        + "     ProductLevel1.\"PL1_ITEM_CODE\" = '" + productLevelItemCode + "'";
                resetSelectAtProductLevel1 = stmtSelectAtProductLevel1.executeQuery(selectAtProductLevel1);
                while (resetSelectAtProductLevel1.next()) {
                    rowItemCode = resetSelectAtProductLevel1.getString("ProductLevel1RawItems_ItemCode");
                    rowItemQuantity = resetSelectAtProductLevel1.getFloat("ProductLevel1RawItems_QUANTITY");
                    calculateRowItemQuantity = rowItemQuantity * itemCompleted;
                    rowItemSellPrice = resetSelectAtProductLevel1.getFloat("Items_SellPrice");
                    rowItemUnitSell = resetSelectAtProductLevel1.getString("Items_UnitSell");
                    minuseOfCalculateRowItemQuantity = calculateRowItemQuantity * (-1);

                    String selectAtItemsAtDepartmentsTable = "SELECT ItemCode, DepartmentCode, Quantity FROM ItemsAtDepartments WHERE ItemCode = '" + rowItemCode + "' AND DepartmentCode = '" + DepartmentCode + "'";
                    resetSelectAtItemsAtDepartmentsTable = stmtSelectAtItemsAtDepartmentsTable.executeQuery(selectAtItemsAtDepartmentsTable);
                    if (resetSelectAtItemsAtDepartmentsTable.next()) {
                        rowItemExsistQuantity = resetSelectAtItemsAtDepartmentsTable.getFloat("Quantity");
                        sumOfcalculateRowItemQuantityAndRowItemExsistQuantity = rowItemExsistQuantity - calculateRowItemQuantity;
                        String updateAtItemsAtDepartments = "UPDATE ItemsAtDepartments SET Quantity = '" + sumOfcalculateRowItemQuantityAndRowItemExsistQuantity + "' WHERE ItemCode = '" + rowItemCode + "' AND DepartmentCode = '" + DepartmentCode + "'";
                        stmtUpdateAtItemsAtDepartments.execute(updateAtItemsAtDepartments);
                    } else if (!resetSelectAtItemsAtDepartmentsTable.next()) {
                        String insertAtItemsAtDepartmentsTable = "INSERT INTO ItemsAtDepartments (ItemCode"
                                + ", DepartmentCode, SellPrice, Quantity, UnitSell, PriceLevel) VALUES ('" + rowItemCode + "'"
                                + ", '" + DepartmentCode + "', '" + rowItemSellPrice + "', '" + minuseOfCalculateRowItemQuantity + "'"
                                + ", '" + rowItemUnitSell + "', '" + PriceLevel + "')";
                        stmtInsertAtItemsAtDepartmentsTable.execute(insertAtItemsAtDepartmentsTable);
                    }
                }
                String selectPL1AtItemsAtDepartmentsTable = "SELECT Quantity FROM ItemsAtDepartments WHERE ItemCode = '" + productLevelItemCode + "' AND DepartmentCode = '" + DepartmentCode + "'";
                resetSelectPLAtItemsAtDepartmentsTable = stmtSelectAtItemsAtDepartmentsTable.executeQuery(selectPL1AtItemsAtDepartmentsTable);
                if(resetSelectPLAtItemsAtDepartmentsTable.next()){
                    PLItemExsistQuantity = resetSelectPLAtItemsAtDepartmentsTable.getFloat("Quantity");
                    calculatePLItemQuantity = PLItemExsistQuantity + itemCompleted;
                    String updatePL1AtItemsAtDepartments = "UPDATE ItemsAtDepartments SET Quantity = '" + calculatePLItemQuantity + "' WHERE ItemCode = '" + productLevelItemCode + "' AND DepartmentCode = '" + DepartmentCode + "'";
                    stmtUpdateAtItemsAtDepartments.execute(updatePL1AtItemsAtDepartments);
                }
            } else if (productLevel.equals("2")) {
                ResultSet resetSelectAtProductLevel2;
                java.sql.Statement stmtSelectAtProductLevel2 = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String selectAtProductLevel2 = "SELECT\n"
                        + "     ProductLevel2.\"PL2_ITEM_CODE\" AS ProductLevel2_PL2_ITEM_CODE,\n"
                        + "     ProductLevel2RawItems.\"PL2_ITEM_QUANTITY\" AS ProductLevel2RawItems_PL2_ITEM_QUANTITY,\n"
                        + "     ProductLevel2RawItems.\"PL1_ITEM_CODE\" AS ProductLevel2RawItems_PL1_ITEM_CODE,\n"
                        + "     ProductLevel2RawItems.\"PL1_ITEM_QUANTITY\" AS ProductLevel2RawItems_PL1_ITEM_QUANTITY,\n"
                        + "     ProductLevel2.\"SellPrice\" AS ProductLevel2_SellPrice,\n"
                        + "     ProductLevel2.\"UnitCode\" AS ProductLevel2_UnitCode,\n"
                        + "     ProductLevel1.\"UnitCode\" AS ProductLevel1_UnitCode,\n"
                        + "     ProductLevel1.\"SellPrice\" AS ProductLevel1_SellPrice\n"
                        + "FROM\n"
                        + "     \"dbo\".\"ProductLevel2RawItems\" ProductLevel2RawItems INNER JOIN \"dbo\".\"ProductLevel2\" ProductLevel2 ON ProductLevel2RawItems.\"PL2_ITEM_CODE\" = ProductLevel2.\"PL2_ITEM_CODE\"\n"
                        + "     INNER JOIN \"dbo\".\"ProductLevel1\" ProductLevel1 ON ProductLevel2RawItems.\"PL1_ITEM_CODE\" = ProductLevel1.\"PL1_ITEM_CODE\"\n"
                        + "WHERE\n"
                        + "     ProductLevel2.\"PL2_ITEM_CODE\" = '" + productLevelItemCode + "'";
                resetSelectAtProductLevel2 = stmtSelectAtProductLevel2.executeQuery(selectAtProductLevel2);
                while (resetSelectAtProductLevel2.next()) {
                    rowItemCode = resetSelectAtProductLevel2.getString("ProductLevel2RawItems_PL1_ITEM_CODE");
                    rowItemQuantity = resetSelectAtProductLevel2.getFloat("ProductLevel2RawItems_PL1_ITEM_QUANTITY");
                    calculateRowItemQuantity = rowItemQuantity * itemCompleted;
                    rowItemSellPrice = resetSelectAtProductLevel2.getFloat("ProductLevel1_SellPrice");
                    rowItemUnitSell = resetSelectAtProductLevel2.getString("ProductLevel1_UnitCode");
                    minuseOfCalculateRowItemQuantity = calculateRowItemQuantity * (-1);

                    String selectAtItemsAtDepartmentsTable = "SELECT ItemCode, DepartmentCode, Quantity FROM ItemsAtDepartments WHERE ItemCode = '" + rowItemCode + "' AND DepartmentCode = '" + DepartmentCode + "'";
                    resetSelectAtItemsAtDepartmentsTable = stmtSelectAtItemsAtDepartmentsTable.executeQuery(selectAtItemsAtDepartmentsTable);
                    if (resetSelectAtItemsAtDepartmentsTable.next()) {
                        rowItemExsistQuantity = resetSelectAtItemsAtDepartmentsTable.getFloat("Quantity");
                        sumOfcalculateRowItemQuantityAndRowItemExsistQuantity = rowItemExsistQuantity - calculateRowItemQuantity;
                        String updateAtItemsAtDepartments = "UPDATE ItemsAtDepartments SET Quantity = '" + sumOfcalculateRowItemQuantityAndRowItemExsistQuantity + "' WHERE ItemCode = '" + rowItemCode + "' AND DepartmentCode = '" + DepartmentCode + "'";
                        stmtUpdateAtItemsAtDepartments.execute(updateAtItemsAtDepartments);
                    } else if (!resetSelectAtItemsAtDepartmentsTable.next()) {
                        String insertAtItemsAtDepartmentsTable = "INSERT INTO ItemsAtDepartments (ItemCode"
                                + ", DepartmentCode, SellPrice, Quantity, UnitSell, PriceLevel) VALUES ('" + rowItemCode + "'"
                                + ", '" + DepartmentCode + "', '" + rowItemSellPrice + "', '" + minuseOfCalculateRowItemQuantity + "'"
                                + ", '" + rowItemUnitSell + "', '" + PriceLevel + "')";
                        stmtInsertAtItemsAtDepartmentsTable.execute(insertAtItemsAtDepartmentsTable);
                    }
                }
                String selectPL2AtItemsAtDepartmentsTable = "SELECT Quantity FROM ItemsAtDepartments WHERE ItemCode = '" + productLevelItemCode + "' AND DepartmentCode = '" + DepartmentCode + "'";
                resetSelectPLAtItemsAtDepartmentsTable = stmtSelectAtItemsAtDepartmentsTable.executeQuery(selectPL2AtItemsAtDepartmentsTable);
                if(resetSelectPLAtItemsAtDepartmentsTable.next()){
                    PLItemExsistQuantity = resetSelectPLAtItemsAtDepartmentsTable.getFloat("Quantity");
                    calculatePLItemQuantity = PLItemExsistQuantity + itemCompleted;
                    String updatePL2AtItemsAtDepartments = "UPDATE ItemsAtDepartments SET Quantity = '" + calculatePLItemQuantity + "' WHERE ItemCode = '" + productLevelItemCode + "' AND DepartmentCode = '" + DepartmentCode + "'";
                    stmtUpdateAtItemsAtDepartments.execute(updatePL2AtItemsAtDepartments);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CalculateSubDepartmentStockAtJobComplete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
