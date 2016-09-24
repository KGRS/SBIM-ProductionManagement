/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import MainFiles.IndexPage;
import static MainFiles.IndexPage.departmentWiseStockReports;
import db.ConnectSql;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ravindu
 */
public class DepartmentWiseStockReports extends javax.swing.JInternalFrame {

    private final String select = "--Select--";
    private final String spliter = "--";
    private final String projectPath = System.getProperty("user.dir");
    private final String menuName = "Department wise stock reports";

    /**
     * Creates new form StockReports
     */
    public DepartmentWiseStockReports() {
        initComponents();
        Refresh();
        loadCategoriesToCombo();
        loadSubCategoriesToCombo();
        loadSuppliersToCombo();
        loadDepartmetns();

        this.setTitle(menuName);
        ImageIcon DefaultBackGround = new ImageIcon(projectPath + "/pictures/InternalFrameIcons/Reports/StockReports.jpg");
        labelIcon.setIcon(DefaultBackGround);

        ImageIcon internalBackGround = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/InternalFrames/GrayGradient.png");
        backgroundLabel.setIcon(internalBackGround);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        panel1 = new javax.swing.JPanel();
        btnRefresh = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        ButtonPreview = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cmbSupplier = new javax.swing.JComboBox();
        cmbMainCategory = new javax.swing.JComboBox();
        rBtnSupplier = new javax.swing.JRadioButton();
        rBtnSubCategory = new javax.swing.JRadioButton();
        rBtnItemType = new javax.swing.JRadioButton();
        cmbSubCategory = new javax.swing.JComboBox();
        comboDepartment = new javax.swing.JComboBox();
        rBtnMainCategory = new javax.swing.JRadioButton();
        CheckBoxAllItems = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        rBtnItemOrderCode = new javax.swing.JRadioButton();
        rBtnItemOrderName = new javax.swing.JRadioButton();
        labelIcon = new javax.swing.JLabel();
        backgroundLabel = new javax.swing.JLabel();

        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(1061, 542));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameIconified(evt);
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        panel1.setBackground(new java.awt.Color(255, 255, 255));
        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.TRAILING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), new java.awt.Color(153, 153, 153))); // NOI18N
        panel1.setForeground(new java.awt.Color(255, 255, 255));
        panel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel1MouseClicked(evt);
            }
        });
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRefresh.setMnemonic('d');
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        panel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 470, 80, -1));

        btnExit.setMnemonic('e');
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        btnExit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnExitKeyPressed(evt);
            }
        });
        panel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 470, 80, -1));

        ButtonPreview.setText("Preview");
        ButtonPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPreviewActionPerformed(evt);
            }
        });
        panel1.add(ButtonPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 470, 80, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbSupplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select--" }));
        cmbSupplier.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbSupplierPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cmbSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbSupplierKeyPressed(evt);
            }
        });
        jPanel1.add(cmbSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 280, 20));

        cmbMainCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select--" }));
        cmbMainCategory.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbMainCategoryPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cmbMainCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMainCategoryKeyPressed(evt);
            }
        });
        jPanel1.add(cmbMainCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 280, 20));

        rBtnSupplier.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rBtnSupplier);
        rBtnSupplier.setForeground(new java.awt.Color(102, 102, 102));
        rBtnSupplier.setText("Supplier");
        rBtnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnSupplierActionPerformed(evt);
            }
        });
        jPanel1.add(rBtnSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 120, -1));

        rBtnSubCategory.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rBtnSubCategory);
        rBtnSubCategory.setForeground(new java.awt.Color(102, 102, 102));
        rBtnSubCategory.setText("Sub category");
        rBtnSubCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnSubCategoryActionPerformed(evt);
            }
        });
        jPanel1.add(rBtnSubCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 120, -1));

        rBtnItemType.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rBtnItemType);
        rBtnItemType.setForeground(new java.awt.Color(102, 102, 102));
        rBtnItemType.setText("Department");
        rBtnItemType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnItemTypeActionPerformed(evt);
            }
        });
        jPanel1.add(rBtnItemType, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 120, -1));

        cmbSubCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select--" }));
        cmbSubCategory.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbSubCategoryPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cmbSubCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbSubCategoryKeyPressed(evt);
            }
        });
        jPanel1.add(cmbSubCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 280, 20));

        comboDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select--" }));
        comboDepartment.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboDepartmentPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        comboDepartment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboDepartmentKeyPressed(evt);
            }
        });
        jPanel1.add(comboDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 280, 20));

        rBtnMainCategory.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rBtnMainCategory);
        rBtnMainCategory.setForeground(new java.awt.Color(102, 102, 102));
        rBtnMainCategory.setText("Main category");
        rBtnMainCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnMainCategoryActionPerformed(evt);
            }
        });
        jPanel1.add(rBtnMainCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 120, -1));

        CheckBoxAllItems.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxAllItems.setForeground(new java.awt.Color(102, 102, 102));
        CheckBoxAllItems.setText("All items");
        CheckBoxAllItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxAllItemsActionPerformed(evt);
            }
        });
        jPanel1.add(CheckBoxAllItems, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 170, -1));

        panel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 490, 430));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jPanel2.setPreferredSize(new java.awt.Dimension(480, 380));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rBtnItemOrderCode.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rBtnItemOrderCode);
        rBtnItemOrderCode.setForeground(new java.awt.Color(102, 102, 102));
        rBtnItemOrderCode.setText("Item list order by code");
        rBtnItemOrderCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnItemOrderCodeActionPerformed(evt);
            }
        });
        jPanel2.add(rBtnItemOrderCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 360, -1));

        rBtnItemOrderName.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rBtnItemOrderName);
        rBtnItemOrderName.setForeground(new java.awt.Color(102, 102, 102));
        rBtnItemOrderName.setText("Item list order by name ");
        rBtnItemOrderName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnItemOrderNameActionPerformed(evt);
            }
        });
        jPanel2.add(rBtnItemOrderName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 360, -1));
        jPanel2.add(labelIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 440, 290));

        panel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 480, 430));
        panel1.add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1020, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadCategoriesToCombo() {
        try {
            java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "select * From MainCategory order by MainCategoryName";
            ResultSet rset = stmt.executeQuery(query);

            cmbMainCategory.removeAllItems();
            cmbMainCategory.insertItemAt("--Select--", 0);
            int position = 1;
            if (rset.next()) {
                do {
                    cmbMainCategory.insertItemAt(rset.getString("MainCategoryName") + "--" + rset.getString("MainCategoryCode"), position); // 
                    position++;
                } while (rset.next());
            }
            cmbMainCategory.setSelectedIndex(0);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", ERROR);
        }
    }

    private void loadSubCategoriesToCombo() {
        try {
//            String MainCategory[] = cmbMainCategory.getSelectedItem().toString().split("--");
            java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "select * From SubCategory order by SubCategoryName"; // where MainCategoryCode = '" + MainCategory[1] + "'
            ResultSet rset = stmt.executeQuery(query);

            cmbSubCategory.removeAllItems();
            cmbSubCategory.insertItemAt("--Select--", 0);
            int position = 1;
            if (rset.next()) {
                do {
                    cmbSubCategory.insertItemAt(rset.getString("SubCategoryName") + "--" + rset.getString("SubCategoryCode"), position); // 
                    position++;
                } while (rset.next());
            }
            cmbSubCategory.setSelectedIndex(0);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", ERROR);
        }
    }

    private void loadSuppliersToCombo() {
        try {
            java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "select * From Suppliers order by SupplierName";
            ResultSet rset = stmt.executeQuery(query);

            cmbSupplier.removeAllItems();
            cmbSupplier.insertItemAt("--Select--", 0);
            int position = 1;
            if (rset.next()) {
                do {
                    cmbSupplier.insertItemAt(rset.getString("SupplierName") + "--" + rset.getString("SupplierCode"), position); // 
                    position++;
                } while (rset.next());
            }
            cmbSupplier.setSelectedIndex(0);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", ERROR);
        }
    }

    private void loadDepartmetns() {
        try {
            java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "select DepartmentCode, DepartmentName From Departments order by DepartmentName";
            ResultSet rset = stmt.executeQuery(query);

            comboDepartment.removeAllItems();
            comboDepartment.insertItemAt(select, 0);
            int position = 1;
            if (rset.next()) {
                do {
                    comboDepartment.insertItemAt(rset.getString("DepartmentName") + spliter + rset.getString("DepartmentCode"), position); // 
                    position++;
                } while (rset.next());
            }
            comboDepartment.setSelectedIndex(0);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        Refresh();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void Refresh() {
        cmbMainCategory.setSelectedItem(select);
        cmbSubCategory.setSelectedItem(select);
        comboDepartment.setSelectedItem(select);
        cmbSupplier.setSelectedItem(select);

        CheckBoxAllItems.setSelected(true);
        CheckBoxAllItems.setEnabled(true);

        rBtnMainCategory.setEnabled(false);
        rBtnSubCategory.setEnabled(false);
        rBtnItemType.setEnabled(false);
        rBtnSupplier.setEnabled(false);

        cmbMainCategory.setEnabled(false);
        cmbSubCategory.setEnabled(false);
        comboDepartment.setEnabled(false);
        cmbSupplier.setEnabled(false);

        buttonGroup1.clearSelection();
//        rBtnItemOrderName.setSelected(true);
    }

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        exit();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnExitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnExitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            exit();
        }
    }//GEN-LAST:event_btnExitKeyPressed

    private void exit() {
        if (departmentWiseStockReports != null) {
            departmentWiseStockReports = null;
        }
        this.dispose();
    }

    private void panel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel1MouseClicked
        if (SwingUtilities.isRightMouseButton(evt) || evt.isControlDown()) {
            Refresh();
        }
    }//GEN-LAST:event_panel1MouseClicked

    private void cmbSupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSupplierKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String text = cmbSupplier.getSelectedItem().toString();
            if (!text.equals(select)) {
                ButtonPreview.requestFocus();
            }
        }
    }//GEN-LAST:event_cmbSupplierKeyPressed

    private void cmbSupplierPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbSupplierPopupMenuWillBecomeInvisible
        String text = cmbSupplier.getSelectedItem().toString();
        if (!text.equals(select)) {
            ButtonPreview.requestFocus();
        }
    }//GEN-LAST:event_cmbSupplierPopupMenuWillBecomeInvisible

    private void cmbMainCategoryPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbMainCategoryPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMainCategoryPopupMenuWillBecomeInvisible

    private void cmbMainCategoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMainCategoryKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMainCategoryKeyPressed

    private void rBtnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rBtnSupplierActionPerformed

    private void rBtnItemOrderCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnItemOrderCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rBtnItemOrderCodeActionPerformed

    private void rBtnItemOrderNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnItemOrderNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rBtnItemOrderNameActionPerformed

    private void rBtnSubCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnSubCategoryActionPerformed
        RefreshCombo();
        CheckBoxAllItems.setSelected(false);
        CheckBoxAllItems.setEnabled(false);
    }//GEN-LAST:event_rBtnSubCategoryActionPerformed

    private void rBtnItemTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnItemTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rBtnItemTypeActionPerformed

    private void cmbSubCategoryPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbSubCategoryPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSubCategoryPopupMenuWillBecomeInvisible

    private void cmbSubCategoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSubCategoryKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSubCategoryKeyPressed

    private void comboDepartmentPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboDepartmentPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDepartmentPopupMenuWillBecomeInvisible

    private void comboDepartmentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboDepartmentKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDepartmentKeyPressed

    private void rBtnMainCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnMainCategoryActionPerformed
        RefreshCombo();
        CheckBoxAllItems.setSelected(false);
        CheckBoxAllItems.setEnabled(false);
    }//GEN-LAST:event_rBtnMainCategoryActionPerformed

    private void CheckBoxAllItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxAllItemsActionPerformed
        if (CheckBoxAllItems.isSelected()) {
            cmbMainCategory.setSelectedItem(select);
            cmbSubCategory.setSelectedItem(select);
            comboDepartment.setSelectedItem(select);
            cmbSupplier.setSelectedItem(select);

            cmbMainCategory.setEnabled(false);
            cmbSubCategory.setEnabled(false);
            comboDepartment.setEnabled(false);
            cmbSupplier.setEnabled(false);

//            rBtnMainCategory.setSelected(false);
            rBtnMainCategory.setEnabled(false);
            rBtnSubCategory.setEnabled(false);
            rBtnItemType.setEnabled(false);
            rBtnSupplier.setEnabled(false);

        } else if (!CheckBoxAllItems.isSelected()) {
            cmbMainCategory.setSelectedItem(select);
            cmbSubCategory.setSelectedItem(select);
            comboDepartment.setSelectedItem(select);
            cmbSupplier.setSelectedItem(select);

            cmbMainCategory.setEnabled(true);
            cmbSubCategory.setEnabled(true);
            comboDepartment.setEnabled(true);
            cmbSupplier.setEnabled(true);

            rBtnMainCategory.setEnabled(true);
            rBtnSubCategory.setEnabled(true);
            rBtnItemType.setEnabled(true);
            rBtnSupplier.setEnabled(true);
        }
    }//GEN-LAST:event_CheckBoxAllItemsActionPerformed

    private void RefreshCombo() {
        cmbMainCategory.setSelectedItem(select);
        cmbSubCategory.setSelectedItem(select);
        comboDepartment.setSelectedItem(select);
        cmbSupplier.setSelectedItem(select);
    }

    private void ButtonPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPreviewActionPerformed
//        if (!rBtnItemOrderName.isSelected() || !rBtnItemOrderCode.isSelected() || !rBtnItemMinium.isSelected() || !rBtnItemMaximum.isSelected() || !rBtnItemZeroQtyName.isSelected() || !rBtnItemZeroQtyCode.isSelected() || !rBtnItemNotVisible.isSelected()) {
//            JOptionPane.showMessageDialog(this, "please select a report.", "Not seleted", JOptionPane.OK_OPTION);
//        } else 
        if (rBtnItemOrderName.isSelected() || rBtnItemOrderCode.isSelected()) {
            PrintStockReport();
        }
    }//GEN-LAST:event_ButtonPreviewActionPerformed

    private void formInternalFrameIconified(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameIconified
        departmentWiseStockReports.toFront();
    }//GEN-LAST:event_formInternalFrameIconified

    private void PrintStockReport() {
        Connection sqlcon = ConnectSql.conn;
//        String Date = IndexPage.LabelDate.getText();
        String departmentID[] = comboDepartment.getSelectedItem().toString().split(spliter);
        String PathToLogo = projectPath + "/pictures/Logo/ClientLogo.jpg";
        if (rBtnItemOrderName.isSelected()) {
            try {
                Map map = new HashMap();
                map.put("User", IndexPage.user.trim());
                map.put("Logo", PathToLogo);
                map.put("Code", departmentID[1]);

                String Report = "Item list order by name - Department wise.jrxml";
                JasperDesign jasperdesigns = JRXmlLoader.load(projectPath + "/Reports/Stock Reports/All items/" + Report);
                JasperReport jasperreport = JasperCompileManager.compileReport(jasperdesigns);
                JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, map, sqlcon);
                JasperViewer.viewReport(jasperprint, false);
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            }
        } else if (rBtnItemOrderCode.isSelected()) {
            try {
                Map map = new HashMap();
                map.put("User", IndexPage.user.trim());
                map.put("Logo", PathToLogo);
                map.put("Code", departmentID[1]);

                String Report = "Item list order by code - Department wise.jrxml";
                JasperDesign jasperdesigns = JRXmlLoader.load(projectPath + "/Reports/Stock Reports/All items/" + Report);
                JasperReport jasperreport = JasperCompileManager.compileReport(jasperdesigns);
                JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, map, sqlcon);
                JasperViewer.viewReport(jasperprint, false);
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            }
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonPreview;
    private javax.swing.JCheckBox CheckBoxAllItems;
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnRefresh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cmbMainCategory;
    private javax.swing.JComboBox cmbSubCategory;
    private javax.swing.JComboBox cmbSupplier;
    private javax.swing.JComboBox comboDepartment;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelIcon;
    private javax.swing.JPanel panel1;
    private javax.swing.JRadioButton rBtnItemOrderCode;
    private javax.swing.JRadioButton rBtnItemOrderName;
    private javax.swing.JRadioButton rBtnItemType;
    private javax.swing.JRadioButton rBtnMainCategory;
    private javax.swing.JRadioButton rBtnSubCategory;
    private javax.swing.JRadioButton rBtnSupplier;
    // End of variables declaration//GEN-END:variables
}
