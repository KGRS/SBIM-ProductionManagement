/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFiles;

import static MainFiles.IndexPage.jobFixed;
import db.ConnectSql;
import functions.AverageTimeOfPLItems;
import functions.DocNumGenerator;
import functions.SavePLItemsInItemsAtDepartments;
import functions.ValidateFields;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ravindu
 */
public class JobFixed extends javax.swing.JInternalFrame {

    private final String select = "--Select--";
    private final DefaultTableModel model_categoryTable;
    private final String spliter = "--";
    private final String menuName = "Fixed Job/ Process";
    private DocNumGenerator AutoID;
    String Code = "", Name = "", productLevel = "", productLevelItemCode = "", productLevelItemName = "", remarks = "", workFlowCode, workFlowName, subDepartment, DepartmentCode, subDepartmentCode, subDepartmentName;
    int itemCount = 1, allocateTime = 1, employeeCount = 1, stepNumber = 1;

    public JobFixed() {
        initComponents();

        buttonGroup1.add(rBtnCode);
        buttonGroup1.add(rBtnName);
        rBtnCode.setSelected(true);
        textFixedJobCode.requestFocus();
        model_categoryTable = (DefaultTableModel) tableViewDetails.getModel();
        panel1.setToolTipText("Press right mouse click to refresh.");
        this.setTitle(menuName);
        buttonEmailSendTo.setVisible(false);

        getItemLevel();
        loadAllJobsToTable();
        loadWorkFlowsToCombo();
        loadSubDepartmentsToCombo();
    }

    private void loadAllJobsToTable() {
        try {
            ResultSet reset;
            Statement stmt;
            String query;
            int rowCount = 0;
            RefreshTable();

            query = "SELECT JOB_FIXED_ID, JOB_FIXED_NAME, PRODUCT_LEVEL, PRODUCT_LEVEL_ITEM_CODE FROM JobFixed ORDER BY WORK_FLOW_CODE, STEP_NUMBER";
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            while (reset.next()) {
                model_categoryTable.addRow(new Object[model_categoryTable.getColumnCount()]);
                tableViewDetails.setValueAt(reset.getString("JOB_FIXED_ID"), rowCount, 0);
                tableViewDetails.setValueAt(reset.getString("JOB_FIXED_NAME"), rowCount, 1);
                tableViewDetails.setValueAt(reset.getString("PRODUCT_LEVEL"), rowCount, 2);
                tableViewDetails.setValueAt(reset.getString("PRODUCT_LEVEL_ITEM_CODE"), rowCount, 3);
                rowCount++;
            }
            reset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }
    }

    private void loadWorkFlowsToCombo() {
        try {
            java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT WORK_FLOW_CODE, WORK_FLOW_NAME FROM Workflow ORDER BY WORK_FLOW_NAME";
            ResultSet rset = stmt.executeQuery(query);

            cmbWorkflow.removeAllItems();
            cmbWorkflow.insertItemAt(select, 0);
            int position = 1;
            if (rset.next()) {
                do {
                    cmbWorkflow.insertItemAt(rset.getString("WORK_FLOW_NAME") + spliter + rset.getString("WORK_FLOW_CODE"), position); // 
                    position++;
                } while (rset.next());
            }
            cmbWorkflow.setSelectedIndex(0);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }
    }

    private void ProductLevelItem(int level) {
        if (level == 1) {
            try {
                java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String query = "SELECT PL1_ITEM_CODE, PL1_ITEM_NAME FROM ProductLevel1 ORDER BY PL1_ITEM_NAME";
                ResultSet rset = stmt.executeQuery(query);

                cmbProductLevelItem.removeAllItems();
                cmbProductLevelItem.insertItemAt(select, 0);
                int position = 1;
                if (rset.next()) {
                    do {
                        cmbProductLevelItem.insertItemAt(rset.getString("PL1_ITEM_NAME") + spliter + rset.getString("PL1_ITEM_CODE"), position); // 
                        position++;
                    } while (rset.next());
                }
                cmbProductLevelItem.setSelectedIndex(0);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            }
        } else if (level == 2) {
            try {
                java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String query = "SELECT PL2_ITEM_CODE, PL2_ITEM_NAME FROM ProductLevel2 ORDER BY PL2_ITEM_NAME";
                ResultSet rset = stmt.executeQuery(query);

                cmbProductLevelItem.removeAllItems();
                cmbProductLevelItem.insertItemAt(select, 0);
                int position = 1;
                if (rset.next()) {
                    do {
                        cmbProductLevelItem.insertItemAt(rset.getString("PL2_ITEM_NAME") + spliter + rset.getString("PL2_ITEM_CODE"), position); // 
                        position++;
                    } while (rset.next());
                }
                cmbProductLevelItem.setSelectedIndex(0);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            }
        }

    }

    private void loadSubDepartmentsToCombo() {
        try {
            java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "select SUB_DEPARTMENT_CODE, SUB_DEPARTMENT_NAME From SubDepartments order by SUB_DEPARTMENT_NAME";
            ResultSet rset = stmt.executeQuery(query);

            comboSubDepartment.removeAllItems();
            comboSubDepartment.insertItemAt(select, 0);
            int position = 1;
            if (rset.next()) {
                do {
                    comboSubDepartment.insertItemAt(rset.getString("SUB_DEPARTMENT_NAME") + spliter + rset.getString("SUB_DEPARTMENT_CODE"), position);
                    position++;
                } while (rset.next());
            }
            comboSubDepartment.setSelectedIndex(0);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", ERROR);
        }
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
        panel1 = new javax.swing.JPanel();
        lbl_category = new javax.swing.JLabel();
        textFixedJobCode = new javax.swing.JTextField();
        textFixedJobName = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lbl_description = new javax.swing.JLabel();
        lbl_subAccount = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        rBtnCode = new javax.swing.JRadioButton();
        rBtnName = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableViewDetails = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        cmbProductLevel = new javax.swing.JComboBox();
        lbl_category1 = new javax.swing.JLabel();
        lbl_description1 = new javax.swing.JLabel();
        cmbProductLevelItem = new javax.swing.JComboBox();
        lbl_description2 = new javax.swing.JLabel();
        formatedTextAllocatedTime = new javax.swing.JFormattedTextField();
        lbl_description4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaRemarks = new javax.swing.JTextArea();
        lbl_description3 = new javax.swing.JLabel();
        spinnerItemCount = new javax.swing.JSpinner();
        lbl_description5 = new javax.swing.JLabel();
        spinnerEmpCount = new javax.swing.JSpinner();
        lbl_category2 = new javax.swing.JLabel();
        cmbWorkflow = new javax.swing.JComboBox();
        lbl_description6 = new javax.swing.JLabel();
        comboSubDepartment = new javax.swing.JComboBox();
        buttonGetSuggestTime = new javax.swing.JButton();
        buttonEmailSendTo = new javax.swing.JButton();
        lbl_category3 = new javax.swing.JLabel();
        spinnerStepNumber = new javax.swing.JSpinner();

        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(1000, 533));
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

        lbl_category.setForeground(new java.awt.Color(102, 102, 102));
        lbl_category.setText("Product level *");
        panel1.add(lbl_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 100, 20));

        textFixedJobCode.setEditable(false);
        textFixedJobCode.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        textFixedJobCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textFixedJobCodeFocusGained(evt);
            }
        });
        textFixedJobCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFixedJobCodeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFixedJobCodeKeyReleased(evt);
            }
        });
        panel1.add(textFixedJobCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 120, 20));

        textFixedJobName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textFixedJobNameFocusGained(evt);
            }
        });
        textFixedJobName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFixedJobNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFixedJobNameKeyReleased(evt);
            }
        });
        panel1.add(textFixedJobName, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 270, 20));

        btnSave.setMnemonic('s');
        btnSave.setText("Save");
        btnSave.setActionCommand("Delete");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        panel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 460, 80, -1));

        btnDelete.setMnemonic('d');
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        panel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 460, 80, -1));

        lbl_description.setForeground(new java.awt.Color(102, 102, 102));
        lbl_description.setText("Remarks");
        panel1.add(lbl_description, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, 100, 20));

        lbl_subAccount.setForeground(new java.awt.Color(102, 102, 102));
        lbl_subAccount.setText("Search fixed job/ Process by");
        panel1.add(lbl_subAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 20));

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
        panel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 460, 80, -1));

        rBtnCode.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rBtnCode);
        rBtnCode.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rBtnCode.setText("Code");
        rBtnCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnCodeActionPerformed(evt);
            }
        });
        panel1.add(rBtnCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 60, -1));

        rBtnName.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rBtnName);
        rBtnName.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rBtnName.setText("Name");
        rBtnName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnNameActionPerformed(evt);
            }
        });
        rBtnName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rBtnNameKeyPressed(evt);
            }
        });
        panel1.add(rBtnName, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 60, -1));

        tableViewDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fixed job/ Process code", "Fixed job/ Process name", "Product level", "Product level item code"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableViewDetails.getTableHeader().setReorderingAllowed(false);
        tableViewDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableViewDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableViewDetails);
        if (tableViewDetails.getColumnModel().getColumnCount() > 0) {
            tableViewDetails.getColumnModel().getColumn(2).setPreferredWidth(30);
            tableViewDetails.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        panel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 380));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        panel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 170, -1));
        panel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 438, 430, -1));

        cmbProductLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2" }));
        cmbProductLevel.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbProductLevelPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        panel1.add(cmbProductLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 140, 60, -1));

        lbl_category1.setForeground(new java.awt.Color(102, 102, 102));
        lbl_category1.setText("Step No *");
        panel1.add(lbl_category1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 60, 20));

        lbl_description1.setForeground(new java.awt.Color(102, 102, 102));
        lbl_description1.setText("Fixed job/ Process name *");
        panel1.add(lbl_description1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 150, 20));

        cmbProductLevelItem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select--" }));
        cmbProductLevelItem.setToolTipText("");
        panel1.add(cmbProductLevelItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 180, 270, -1));

        lbl_description2.setForeground(new java.awt.Color(102, 102, 102));
        lbl_description2.setText("Item count *");
        panel1.add(lbl_description2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, 130, 20));

        formatedTextAllocatedTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        formatedTextAllocatedTime.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        formatedTextAllocatedTime.setText("30");
        panel1.add(formatedTextAllocatedTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 260, 60, -1));

        lbl_description4.setForeground(new java.awt.Color(102, 102, 102));
        lbl_description4.setText("Employee count *");
        panel1.add(lbl_description4, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 220, 100, 20));

        textAreaRemarks.setColumns(20);
        textAreaRemarks.setRows(5);
        jScrollPane2.setViewportView(textAreaRemarks);

        panel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 340, 270, 70));

        lbl_description3.setForeground(new java.awt.Color(102, 102, 102));
        lbl_description3.setText("Product level item *");
        panel1.add(lbl_description3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 140, 20));

        spinnerItemCount.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        panel1.add(spinnerItemCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 220, 60, -1));

        lbl_description5.setForeground(new java.awt.Color(102, 102, 102));
        lbl_description5.setText("Allocated time (minutes) *");
        panel1.add(lbl_description5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 140, 20));

        spinnerEmpCount.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        panel1.add(spinnerEmpCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 220, 60, -1));

        lbl_category2.setForeground(new java.awt.Color(102, 102, 102));
        lbl_category2.setText("Workflow *");
        panel1.add(lbl_category2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 110, 20));

        cmbWorkflow.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select--" }));
        panel1.add(cmbWorkflow, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 270, -1));

        lbl_description6.setForeground(new java.awt.Color(102, 102, 102));
        lbl_description6.setText("Sub department *");
        panel1.add(lbl_description6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 130, 20));

        comboSubDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select--" }));
        panel1.add(comboSubDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 300, 270, -1));

        buttonGetSuggestTime.setText("Get suggest time");
        buttonGetSuggestTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGetSuggestTimeActionPerformed(evt);
            }
        });
        panel1.add(buttonGetSuggestTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 260, 160, -1));

        buttonEmailSendTo.setText("Email send to");
        panel1.add(buttonEmailSendTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, 120, -1));

        lbl_category3.setForeground(new java.awt.Color(102, 102, 102));
        lbl_category3.setText("Fixed job/ Process code *");
        panel1.add(lbl_category3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 150, 20));

        spinnerStepNumber.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        panel1.add(spinnerStepNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(879, 60, 60, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFixedJobCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFixedJobCodeFocusGained
        textFixedJobCode.selectAll();
    }//GEN-LAST:event_textFixedJobCodeFocusGained

    private void textFixedJobCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFixedJobCodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String text = textFixedJobCode.getText();
            if (!text.isEmpty()) {
                textFixedJobName.requestFocus();
            }
        }
    }//GEN-LAST:event_textFixedJobCodeKeyPressed

    private void textFixedJobCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFixedJobCodeKeyReleased
        ValidateFields.CheckForCodes(textFixedJobCode);
    }//GEN-LAST:event_textFixedJobCodeKeyReleased

    private void textFixedJobNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFixedJobNameFocusGained
        textFixedJobName.selectAll();
    }//GEN-LAST:event_textFixedJobNameFocusGained

    private void textFixedJobNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFixedJobNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String text = textFixedJobName.getText();
            if (!text.isEmpty()) {
                btnSave.requestFocus();
            }
        }
    }//GEN-LAST:event_textFixedJobNameKeyPressed

    private void textFixedJobNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFixedJobNameKeyReleased
        ValidateFields.CheckForOtherFields(textFixedJobName);
    }//GEN-LAST:event_textFixedJobNameKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        CheckBeforeSave();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void CheckBeforeSave() {
        Code = textFixedJobCode.getText();
        stepNumber = Integer.parseInt(spinnerStepNumber.getValue().toString());
        Name = textFixedJobName.getText();
        productLevel = cmbProductLevel.getSelectedItem().toString();
        productLevelItemCode = cmbProductLevelItem.getSelectedItem().toString();
        workFlowCode = cmbWorkflow.getSelectedItem().toString();
        subDepartment = comboSubDepartment.getSelectedItem().toString();
        String productLevelItemCodeInArray[] = cmbProductLevelItem.getSelectedItem().toString().split(spliter);
        String workFlowCodeInArray[] = cmbWorkflow.getSelectedItem().toString().split(spliter);
        String subDepartmentCodeInArray[] = comboSubDepartment.getSelectedItem().toString().split(spliter);
        itemCount = Integer.parseInt(spinnerItemCount.getValue().toString());
        allocateTime = Integer.parseInt(formatedTextAllocatedTime.getText());
        employeeCount = Integer.parseInt(spinnerEmpCount.getValue().toString());
        remarks = textAreaRemarks.getText();

        if (!Name.isEmpty() && !productLevelItemCode.equals(select) && !workFlowCode.equals(select) && !subDepartment.equals(select)) {
            try {
                java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String query = "select JOB_FIXED_ID From JobFixed where JOB_FIXED_ID = '" + Code + "'";
                ResultSet rset = stmt.executeQuery(query);

                if (rset.next()) {
                    int x = JOptionPane.showConfirmDialog(this, "Are you sure to change the '" + Code + "' details?", "Update fixed job?", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        String UpdateQuery = "UPDATE [JobFixed]\n"
                                + "   SET [JOB_FIXED_NAME] = '" + Name + "'\n"
                                //                                + "      ,[WORK_FLOW_CODE] = '" + workFlowCodeInArray[1] + "'\n"
                                //                                + "      ,[PRODUCT_LEVEL] = '" + productLevel + "'\n"
                                                                + "      ,[PRODUCT_LEVEL_ITEM_CODE] = '" + productLevelItemCodeInArray[1] + "'\n"
                                + "      ,[ITEM_COUNT] = '" + itemCount + "'\n"
                                + "      ,[ALLOCATED_TIME] = '" + allocateTime + "'\n"
                                + "      ,[EMPLOYEE_COUNT] = '" + employeeCount + "'\n"
                                                                + "      ,[SUB_DEPARTMENT_CODE] = '" + subDepartmentCodeInArray[1] + "'\n"
                                + "      ,[REMARKS] = '" + remarks + "'\n"
                                + "      ,[STEP_NUMBER] = '" + stepNumber + "'\n"
                                + " WHERE JOB_FIXED_ID = '" + Code + "'";
                        stmt.execute(UpdateQuery);
                        JOptionPane.showMessageDialog(this, "'" + menuName + "' details are updated.");
                        Refresh();
                    } else if (x == JOptionPane.NO_OPTION) {
                        textFixedJobCode.requestFocus();
                    }

                } else if (!rset.next()) {
                    AutoID = new DocNumGenerator();
                    AutoID.methodNumGen(workFlowCodeInArray[1] + spliter + "FJOB");
                    Code = AutoID.getDocChar() + AutoID.getDocNumber();
                    textFixedJobCode.setText(Code);
                    Code = textFixedJobCode.getText();
                    String InsertQuery = "INSERT INTO [JobFixed]\n"
                            + "           ([JOB_FIXED_ID]\n"
                            + "           ,[JOB_FIXED_NAME]\n"
                            + "           ,[WORK_FLOW_CODE]\n"
                            + "           ,[PRODUCT_LEVEL]\n"
                            + "           ,[PRODUCT_LEVEL_ITEM_CODE]\n"
                            + "           ,[ITEM_COUNT]\n"
                            + "           ,[ALLOCATED_TIME]\n"
                            + "           ,[EMPLOYEE_COUNT]\n"
                            + "           ,[SUB_DEPARTMENT_CODE]\n"
                            + "           ,[REMARKS]\n"
                            + "           ,[STEP_NUMBER])\n"
                            + "     VALUES\n"
                            + "           ('" + Code + "'\n"
                            + "           ,'" + Name + "'\n"
                            + "           ,'" + workFlowCodeInArray[1] + "'\n"
                            + "           ,'" + productLevel + "'\n"
                            + "           ,'" + productLevelItemCodeInArray[1] + "'\n"
                            + "           ,'" + itemCount + "'\n"
                            + "           ,'" + allocateTime + "'\n"
                            + "           ,'" + employeeCount + "'\n"
                            + "           ,'" + subDepartmentCodeInArray[1] + "'\n"
                            + "           ,'" + remarks + "'\n"
                            + "           ,'" + stepNumber + "')";
                    stmt.execute(InsertQuery);
                    ResultSet resetAtItemsAtDepartments;
                    java.sql.Statement stmtAtDepartmentsTable = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    String selectAtItemsAtDepartmentsTable = "SELECT DepartmentCode FROM SubDepartments WHERE SUB_DEPARTMENT_CODE = '" + subDepartmentCodeInArray[1] + "'";
                    resetAtItemsAtDepartments = stmtAtDepartmentsTable.executeQuery(selectAtItemsAtDepartmentsTable);
                    if (resetAtItemsAtDepartments.next()) {
                        DepartmentCode = resetAtItemsAtDepartments.getString("DepartmentCode");
                        SavePLItemsInItemsAtDepartments.savePLItem(productLevelItemCodeInArray[1], productLevel, DepartmentCode);
                    }
                    JOptionPane.showMessageDialog(this, "New '" + menuName + "' is saved.");
                    Refresh();
                    stmtAtDepartmentsTable.close();
                    resetAtItemsAtDepartments.close();
                }
                rset.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            }
        } else if (Name.isEmpty() || productLevelItemCode.equals(select) || workFlowCode.equals(select) || subDepartment.equals(select)) {
            JOptionPane.showMessageDialog(this, "Please fill all fields before save.", "Empty fields", JOptionPane.OK_OPTION);
            textFixedJobName.requestFocus();
        }
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
//        CheckBeforeDelete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        exit();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnExitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnExitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            exit();
        }
    }//GEN-LAST:event_btnExitKeyPressed

    private void rBtnCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnCodeActionPerformed
        if (rBtnCode.isSelected()) {
            txtSearch.requestFocus();
            txtSearch.selectAll();
        }
    }//GEN-LAST:event_rBtnCodeActionPerformed

    private void rBtnNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnNameActionPerformed
        if (rBtnName.isSelected()) {
            txtSearch.requestFocus();
            txtSearch.selectAll();
        }
    }//GEN-LAST:event_rBtnNameActionPerformed

    private void rBtnNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rBtnNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            rBtnCode.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            rBtnName.setSelected(true);
            btnSave.requestFocus();
        }
    }//GEN-LAST:event_rBtnNameKeyPressed

    private void tableViewDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableViewDetailsMouseClicked
        Code = tableViewDetails.getValueAt(tableViewDetails.getSelectedRow(), 0).toString();
        Name = tableViewDetails.getValueAt(tableViewDetails.getSelectedRow(), 1).toString();
        productLevel = tableViewDetails.getValueAt(tableViewDetails.getSelectedRow(), 2).toString();
        productLevelItemCode = tableViewDetails.getValueAt(tableViewDetails.getSelectedRow(), 3).toString();
        textFixedJobCode.setText(Code);
        textFixedJobName.setText(Name);
        cmbProductLevel.setSelectedItem(productLevel);

        if (productLevel.equals("1")) {
            try {
                ResultSet reset;
                Statement stmt;
                String query;
                query = "SELECT\n"
                        + "     JobFixed.\"JOB_FIXED_ID\" AS JobFixed_JOB_FIXED_ID,\n"
                        + "     JobFixed.\"JOB_FIXED_NAME\" AS JobFixed_JOB_FIXED_NAME,\n"
                        + "     JobFixed.\"PRODUCT_LEVEL\" AS JobFixed_PRODUCT_LEVEL,\n"
                        + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" AS JobFixed_PRODUCT_LEVEL_ITEM_CODE,\n"
                        + "     JobFixed.\"ITEM_COUNT\" AS JobFixed_ITEM_COUNT,\n"
                        + "     JobFixed.\"ALLOCATED_TIME\" AS JobFixed_ALLOCATED_TIME,\n"
                        + "     JobFixed.\"EMPLOYEE_COUNT\" AS JobFixed_EMPLOYEE_COUNT,\n"
                        + "     JobFixed.\"REMARKS\" AS JobFixed_REMARKS,\n"
                        + "     JobFixed.\"STEP_NUMBER\" AS JobFixed_STEP_NUMBER,\n"
                        + "     ProductLevel1.\"PL1_ITEM_NAME\" AS ProductLevel1_PL1_ITEM_NAME,\n"
                        + "     ProductLevel1.\"PL1_ITEM_PRINT_NAME\" AS ProductLevel1_PL1_ITEM_PRINT_NAME,\n"
                        + "     ProductLevel1.\"UnitCode\" AS ProductLevel1_UnitCode,\n"
                        + "     ProductLevel1.\"VISIBILITY\" AS ProductLevel1_VISIBILITY,\n"
                        + "     JobFixed.\"WORK_FLOW_CODE\" AS JobFixed_WORK_FLOW_CODE,\n"
                        + "     Workflow.\"WORK_FLOW_NAME\" AS Workflow_WORK_FLOW_NAME,\n"
                        + "     JobFixed.\"SUB_DEPARTMENT_CODE\" AS JobFixed_SUB_DEPARTMENT_CODE,\n"
                        + "     SubDepartments.\"SUB_DEPARTMENT_NAME\" AS SubDepartments_SUB_DEPARTMENT_NAME\n"
                        + "FROM\n"
                        + "     \"dbo\".\"ProductLevel1\" ProductLevel1 INNER JOIN \"dbo\".\"JobFixed\" JobFixed ON ProductLevel1.\"PL1_ITEM_CODE\" = JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\"\n"
                        + "     INNER JOIN \"dbo\".\"Workflow\" Workflow ON JobFixed.\"WORK_FLOW_CODE\" = Workflow.\"WORK_FLOW_CODE\"\n"
                        + "     INNER JOIN \"dbo\".\"SubDepartments\" SubDepartments ON JobFixed.\"SUB_DEPARTMENT_CODE\" = SubDepartments.\"SUB_DEPARTMENT_CODE\"\n"
                        + "WHERE\n"
                        + "     JobFixed.\"JOB_FIXED_ID\" = '" + Code + "'";

                stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                reset = stmt.executeQuery(query);

                if (reset.next()) {
                    productLevelItemName = reset.getString("ProductLevel1_PL1_ITEM_NAME");
                    itemCount = reset.getInt("JobFixed_ITEM_COUNT");
                    allocateTime = reset.getInt("JobFixed_ALLOCATED_TIME");
                    employeeCount = reset.getInt("JobFixed_EMPLOYEE_COUNT");
                    remarks = reset.getString("JobFixed_REMARKS");
                    workFlowCode = reset.getString("JobFixed_WORK_FLOW_CODE");
                    workFlowName = reset.getString("Workflow_WORK_FLOW_NAME");
                    stepNumber = reset.getInt("JobFixed_STEP_NUMBER");
                    subDepartmentCode = reset.getString("JobFixed_SUB_DEPARTMENT_CODE");
                    subDepartmentName = reset.getString("SubDepartments_SUB_DEPARTMENT_NAME");
                    getItemLevel();

                    cmbWorkflow.setSelectedItem(workFlowName + spliter + workFlowCode);
                    cmbProductLevelItem.setSelectedItem((productLevelItemName) + spliter + (productLevelItemCode));
                    spinnerItemCount.setValue(itemCount);
                    formatedTextAllocatedTime.setText(String.valueOf(allocateTime));
                    spinnerEmpCount.setValue(employeeCount);
                    spinnerStepNumber.setValue(stepNumber);
                    textAreaRemarks.setText(remarks);
                    comboSubDepartment.setSelectedItem(subDepartmentName + spliter + subDepartmentCode);
                }
                reset.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            }
        } else if (productLevel.equals("2")) {
            try {
                ResultSet reset;
                Statement stmt;
                String query;

                query = "SELECT\n"
                        + "     ProductLevel2.\"PL2_ITEM_NAME\" AS ProductLevel2_PL2_ITEM_NAME,\n"
                        + "     ProductLevel2.\"PL2_ITEM_PRINT_NAME\" AS ProductLevel2_PL2_ITEM_PRINT_NAME,\n"
                        + "     ProductLevel2.\"UnitCode\" AS ProductLevel2_UnitCode,\n"
                        + "     ProductLevel2.\"VISIBILITY\" AS ProductLevel2_VISIBILITY,\n"
                        + "     JobFixed.\"JOB_FIXED_ID\" AS JobFixed_JOB_FIXED_ID,\n"
                        + "     JobFixed.\"JOB_FIXED_NAME\" AS JobFixed_JOB_FIXED_NAME,\n"
                        + "     JobFixed.\"PRODUCT_LEVEL\" AS JobFixed_PRODUCT_LEVEL,\n"
                        + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" AS JobFixed_PRODUCT_LEVEL_ITEM_CODE,\n"
                        + "     JobFixed.\"ITEM_COUNT\" AS JobFixed_ITEM_COUNT,\n"
                        + "     JobFixed.\"ALLOCATED_TIME\" AS JobFixed_ALLOCATED_TIME,\n"
                        + "     JobFixed.\"EMPLOYEE_COUNT\" AS JobFixed_EMPLOYEE_COUNT,\n"
                        + "     JobFixed.\"REMARKS\" AS JobFixed_REMARKS,\n"
                        + "     JobFixed.\"STEP_NUMBER\" AS JobFixed_STEP_NUMBER,\n"
                        + "     JobFixed.\"WORK_FLOW_CODE\" AS JobFixed_WORK_FLOW_CODE,\n"
                        + "     Workflow.\"WORK_FLOW_NAME\" AS Workflow_WORK_FLOW_NAME,\n"
                        + "     JobFixed.\"SUB_DEPARTMENT_CODE\" AS JobFixed_SUB_DEPARTMENT_CODE,\n"
                        + "     SubDepartments.\"SUB_DEPARTMENT_NAME\" AS SubDepartments_SUB_DEPARTMENT_NAME\n"
                        + "FROM\n"
                        + "     \"dbo\".\"ProductLevel2\" ProductLevel2 INNER JOIN \"dbo\".\"JobFixed\" JobFixed ON ProductLevel2.\"PL2_ITEM_CODE\" = JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\"\n"
                        + "     INNER JOIN \"dbo\".\"Workflow\" Workflow ON JobFixed.\"WORK_FLOW_CODE\" = Workflow.\"WORK_FLOW_CODE\"\n"
                        + "     INNER JOIN \"dbo\".\"SubDepartments\" SubDepartments ON JobFixed.\"SUB_DEPARTMENT_CODE\" = SubDepartments.\"SUB_DEPARTMENT_CODE\"\n"
                        + "WHERE\n"
                        + "     JobFixed.\"JOB_FIXED_ID\" = '"+Code+"'";

                stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                reset = stmt.executeQuery(query);

                if (reset.next()) {
                    productLevelItemName = reset.getString("ProductLevel2_PL2_ITEM_NAME");
                    itemCount = reset.getInt("JobFixed_ITEM_COUNT");
                    allocateTime = reset.getInt("JobFixed_ALLOCATED_TIME");
                    employeeCount = reset.getInt("JobFixed_EMPLOYEE_COUNT");
                    remarks = reset.getString("JobFixed_REMARKS");
                    workFlowCode = reset.getString("JobFixed_WORK_FLOW_CODE");
                    workFlowName = reset.getString("Workflow_WORK_FLOW_NAME");
                    stepNumber = reset.getInt("JobFixed_STEP_NUMBER");
                    subDepartmentCode = reset.getString("JobFixed_SUB_DEPARTMENT_CODE");
                    subDepartmentName = reset.getString("SubDepartments_SUB_DEPARTMENT_NAME");
                    getItemLevel();

                    cmbWorkflow.setSelectedItem(workFlowName + spliter + workFlowCode);
                    cmbProductLevelItem.setSelectedItem((productLevelItemName) + "--" + (productLevelItemCode));
                    spinnerItemCount.setValue(itemCount);
                    formatedTextAllocatedTime.setText(String.valueOf(allocateTime));
                    spinnerEmpCount.setValue(employeeCount);
                    spinnerStepNumber.setValue(stepNumber);
                    textAreaRemarks.setText(remarks);
                    comboSubDepartment.setSelectedItem(subDepartmentName + spliter + subDepartmentCode);
                }
                reset.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            }
        }
    }//GEN-LAST:event_tableViewDetailsMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (rBtnCode.isSelected()) {
            SearchCategoryByCode(txtSearch.getText());
        } else if (rBtnName.isSelected()) {
            SearchCategoryByName(txtSearch.getText());
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void SearchCategoryByCode(String CategoryCode) {
        try {
            ResultSet reset;
            Statement stmt;
            String query;
            int rowCount = 0;
            RefreshTable();

            if (!CategoryCode.equals("")) {
                query = "SELECT JOB_FIXED_ID, JOB_FIXED_NAME, PRODUCT_LEVEL, PRODUCT_LEVEL_ITEM_CODE FROM JobFixed WHERE JOB_FIXED_ID LIKE '" + CategoryCode + "%'";
            } else {
                query = "SELECT JOB_FIXED_ID, JOB_FIXED_NAME, PRODUCT_LEVEL, PRODUCT_LEVEL_ITEM_CODE FROM JobFixed WHERE JOB_FIXED_ID LIKE '" + CategoryCode + "%'";
            }
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            while (reset.next()) {
                model_categoryTable.addRow(new Object[model_categoryTable.getColumnCount()]);
                tableViewDetails.setValueAt(reset.getString("JOB_FIXED_ID"), rowCount, 0);
                tableViewDetails.setValueAt(reset.getString("JOB_FIXED_NAME"), rowCount, 1);
                tableViewDetails.setValueAt(reset.getString("PRODUCT_LEVEL"), rowCount, 2);
                tableViewDetails.setValueAt(reset.getString("PRODUCT_LEVEL_ITEM_CODE"), rowCount, 3);
                rowCount++;
            }
            reset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }
    }

    private void SearchCategoryByName(String CategoryName) {
        try {
            ResultSet reset;
            Statement stmt;
            String query;
            int rowCount = 0;
            RefreshTable();

            if (!CategoryName.equals("")) {
                query = "SELECT JOB_FIXED_ID, JOB_FIXED_NAME, PRODUCT_LEVEL, PRODUCT_LEVEL_ITEM_CODE FROM JobFixed WHERE JOB_FIXED_ID LIKE '%" + CategoryName + "%'";
            } else {
                query = "SELECT JOB_FIXED_ID, JOB_FIXED_NAME, PRODUCT_LEVEL, PRODUCT_LEVEL_ITEM_CODE FROM JobFixed WHERE JOB_FIXED_ID LIKE '%" + CategoryName + "%'";
            }
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            while (reset.next()) {
                model_categoryTable.addRow(new Object[model_categoryTable.getColumnCount()]);
                tableViewDetails.setValueAt(reset.getString("JOB_FIXED_ID"), rowCount, 0);
                tableViewDetails.setValueAt(reset.getString("JOB_FIXED_NAME"), rowCount, 1);
                tableViewDetails.setValueAt(reset.getString("PRODUCT_LEVEL"), rowCount, 2);
                tableViewDetails.setValueAt(reset.getString("PRODUCT_LEVEL_ITEM_CODE"), rowCount, 3);
                rowCount++;
            }
            reset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }
    }

    private void RefreshTable() {
        try {
            int row = model_categoryTable.getRowCount();
            for (int j = 0; j < row; j++) {
                model_categoryTable.removeRow(0);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }
    }

    private void panel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel1MouseClicked
        if (SwingUtilities.isRightMouseButton(evt) || evt.isControlDown()) {
            Refresh();
        }
    }//GEN-LAST:event_panel1MouseClicked

    private void formInternalFrameIconified(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameIconified
        jobFixed.toFront();
    }//GEN-LAST:event_formInternalFrameIconified

    private void cmbProductLevelPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbProductLevelPopupMenuWillBecomeInvisible
        getItemLevel();
    }//GEN-LAST:event_cmbProductLevelPopupMenuWillBecomeInvisible

    private void buttonGetSuggestTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGetSuggestTimeActionPerformed
        productLevelItemCode = cmbProductLevelItem.getSelectedItem().toString();
        itemCount = Integer.parseInt(spinnerItemCount.getValue().toString());
        if (!productLevelItemCode.equals(select)) {
            String productLevelItemCodeInArray[] = cmbProductLevelItem.getSelectedItem().toString().split(spliter);
            int suggestTime = AverageTimeOfPLItems.getAverageSuggestTimeForPLItems(productLevelItemCodeInArray[1], itemCount);
            formatedTextAllocatedTime.setText(String.valueOf(suggestTime));
        }
    }//GEN-LAST:event_buttonGetSuggestTimeActionPerformed

    private void getItemLevel() {
        int level = Integer.parseInt(cmbProductLevel.getSelectedItem().toString());
        ProductLevelItem(level);
    }

    private void Refresh() {
        RefreshTableAndLoadAgain();
        textFixedJobCode.setText("");
        textFixedJobName.setText("");
        cmbProductLevel.setSelectedIndex(0);
        cmbProductLevelItem.setSelectedIndex(0);
        cmbWorkflow.setSelectedIndex(0);
        spinnerEmpCount.setValue(1);
        formatedTextAllocatedTime.setText("30");
        spinnerItemCount.setValue(1);
        textAreaRemarks.setText("");
        txtSearch.setText("");
        comboSubDepartment.setSelectedIndex(0);
        getItemLevel();
        loadAllJobsToTable();
        loadWorkFlowsToCombo();
    }

    private void RefreshTableAndLoadAgain() {
        try {
            int row = model_categoryTable.getRowCount();
            for (int j = 0; j < row; j++) {
                model_categoryTable.removeRow(0);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton buttonEmailSendTo;
    private javax.swing.JButton buttonGetSuggestTime;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbProductLevel;
    private javax.swing.JComboBox cmbProductLevelItem;
    private javax.swing.JComboBox cmbWorkflow;
    private javax.swing.JComboBox comboSubDepartment;
    private javax.swing.JFormattedTextField formatedTextAllocatedTime;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_category;
    private javax.swing.JLabel lbl_category1;
    private javax.swing.JLabel lbl_category2;
    private javax.swing.JLabel lbl_category3;
    private javax.swing.JLabel lbl_description;
    private javax.swing.JLabel lbl_description1;
    private javax.swing.JLabel lbl_description2;
    private javax.swing.JLabel lbl_description3;
    private javax.swing.JLabel lbl_description4;
    private javax.swing.JLabel lbl_description5;
    private javax.swing.JLabel lbl_description6;
    private javax.swing.JLabel lbl_subAccount;
    private javax.swing.JPanel panel1;
    private javax.swing.JRadioButton rBtnCode;
    private javax.swing.JRadioButton rBtnName;
    private javax.swing.JSpinner spinnerEmpCount;
    private javax.swing.JSpinner spinnerItemCount;
    private javax.swing.JSpinner spinnerStepNumber;
    private javax.swing.JTable tableViewDetails;
    private javax.swing.JTextArea textAreaRemarks;
    private javax.swing.JTextField textFixedJobCode;
    private javax.swing.JTextField textFixedJobName;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    private void exit() {
        if (jobFixed != null) {
            jobFixed = null;
        }
        this.dispose();
    }

}
