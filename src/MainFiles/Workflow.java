/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFiles;

import static MainFiles.IndexPage.workflow;
import db.ConnectSql;
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
public class Workflow extends javax.swing.JInternalFrame {

    private final String select = "--Select--";
    private final DefaultTableModel model_categoryTable;
    private final String spliter = "--";
    private final String menuName = "Workflow";

    public Workflow() {
        initComponents();

        buttonGroup1.add(rBtnCode);
        buttonGroup1.add(rBtnName);
        rBtnCode.setSelected(true);
        textWorkflowfCode.requestFocus();
        model_categoryTable = (DefaultTableModel) tableViewDetails.getModel();
        panel1.setToolTipText("Press right mouse click to refresh.");
        this.setTitle(menuName);

        loadProductionlvl2ItemToCombo();
        LoadCategories();
//        loadBranchesToCombo();
//        loadTypes();
    }

    private void LoadCategories() {
        try {
            ResultSet reset;
            Statement stmt;
            String query;
            int rowCount = 0;
            query = "SELECT * FROM Workflow ORDER BY WORK_FLOW_NAME";
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            while (reset.next()) {
                model_categoryTable.addRow(new Object[model_categoryTable.getColumnCount()]);
                tableViewDetails.setValueAt(reset.getString("WORK_FLOW_CODE"), rowCount, 0);
                tableViewDetails.setValueAt(reset.getString("WORK_FLOW_NAME"), rowCount, 1);
                tableViewDetails.setValueAt(reset.getString("PL2_ITEM_CODE"), rowCount, 2);
                rowCount++;
            }
            reset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }
    }

    private void loadProductionlvl2ItemToCombo() {
        try {
            java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "select * From ProductLevel2 order by PL2_ITEM_NAME";
            ResultSet rset = stmt.executeQuery(query);

            cmbProductLevel2Item.removeAllItems();
            cmbProductLevel2Item.insertItemAt("--Select--", 0);
            int position = 1;
            if (rset.next()) {
                do {
                    cmbProductLevel2Item.insertItemAt(rset.getString("PL2_ITEM_NAME") + "--" + rset.getString("PL2_ITEM_CODE"), position); // 
                    position++;
                } while (rset.next());
            }
            cmbProductLevel2Item.setSelectedIndex(0);

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
        //        JPanel panel1 = new JPanel() {
            //        @Override
            //        protected void paintComponent(Graphics grphcs) {
                //            super.paintComponent(grphcs);
                //            Color color1 = Color.white;
                //            Color color2 = Color.GREEN;
                //            Graphics2D g2d = (Graphics2D) grphcs;
                //            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    //                    RenderingHints.VALUE_ANTIALIAS_ON);
                //            GradientPaint gp = new GradientPaint(0, 274,
                    //                    color1.brighter().brighter().brighter(), getWidth(), getHeight(),
                    //                    color2.darker());
                //            g2d.setPaint(gp);
                //            g2d.fillRect(0, 274, getWidth(), getHeight());
                //        }
            //    };
        lbl_category = new javax.swing.JLabel();
        textWorkflowfCode = new javax.swing.JTextField();
        textWorkflowName = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lbl_description = new javax.swing.JLabel();
        lbl_subAccount = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        rBtnCode = new javax.swing.JRadioButton();
        rBtnName = new javax.swing.JRadioButton();
        lbl_accountType = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableViewDetails = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        cmbProductLevel2Item = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaRemarks = new javax.swing.JTextArea();

        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(883, 358));
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
        lbl_category.setText("Workflow code");
        panel1.add(lbl_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 110, 20));

        textWorkflowfCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textWorkflowfCodeFocusGained(evt);
            }
        });
        textWorkflowfCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textWorkflowfCodeKeyReleased(evt);
            }
        });
        panel1.add(textWorkflowfCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 170, 20));

        textWorkflowName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textWorkflowNameFocusGained(evt);
            }
        });
        textWorkflowName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textWorkflowNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textWorkflowNameKeyReleased(evt);
            }
        });
        panel1.add(textWorkflowName, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, 210, 20));

        btnSave.setMnemonic('s');
        btnSave.setText("Save");
        btnSave.setActionCommand("Delete");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        panel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 290, 80, -1));

        btnDelete.setMnemonic('d');
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        panel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 290, 80, -1));

        lbl_description.setForeground(new java.awt.Color(102, 102, 102));
        lbl_description.setText("Workflow name");
        panel1.add(lbl_description, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 110, 20));

        lbl_subAccount.setForeground(new java.awt.Color(102, 102, 102));
        lbl_subAccount.setText("Search workflow by");
        panel1.add(lbl_subAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, 20));

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
        panel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 290, 80, -1));

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

        lbl_accountType.setForeground(new java.awt.Color(102, 102, 102));
        lbl_accountType.setText("Remarks");
        panel1.add(lbl_accountType, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 110, 20));

        tableViewDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Product level 2 item"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
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

        panel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 250));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        panel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 170, -1));
        panel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, 350, -1));

        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Product level 2 item");
        panel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 114, 20));

        cmbProductLevel2Item.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select--" }));
        cmbProductLevel2Item.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbProductLevel2ItemPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cmbProductLevel2Item.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbProductLevel2ItemKeyPressed(evt);
            }
        });
        panel1.add(cmbProductLevel2Item, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, 210, 20));

        textAreaRemarks.setColumns(20);
        textAreaRemarks.setRows(5);
        jScrollPane2.setViewportView(textAreaRemarks);

        panel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 180, 210, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textWorkflowfCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textWorkflowfCodeFocusGained
        textWorkflowfCode.selectAll();
    }//GEN-LAST:event_textWorkflowfCodeFocusGained



    private void textWorkflowfCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textWorkflowfCodeKeyReleased
        ValidateFields.CheckForCodes(textWorkflowfCode);
    }//GEN-LAST:event_textWorkflowfCodeKeyReleased

    private void textWorkflowNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textWorkflowNameFocusGained
        textWorkflowName.selectAll();
    }//GEN-LAST:event_textWorkflowNameFocusGained

    private void textWorkflowNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textWorkflowNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String text = textWorkflowName.getText();
            if (!text.isEmpty()) {
                cmbProductLevel2Item.requestFocus();
            }
        }
    }//GEN-LAST:event_textWorkflowNameKeyPressed

    private void textWorkflowNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textWorkflowNameKeyReleased
        ValidateFields.CheckForOtherFields(textWorkflowName);
    }//GEN-LAST:event_textWorkflowNameKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        CheckBeforeSave();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void CheckBeforeSave() {
        String workFlowCode = textWorkflowfCode.getText();
        String workFlowName = textWorkflowName.getText();
        String textArea = textAreaRemarks.getText();
        String ProductionlevelItem[] = cmbProductLevel2Item.getSelectedItem().toString().split("--");
        if (!workFlowCode.isEmpty() && !workFlowName.isEmpty() && !textArea.equals(select) && !ProductionlevelItem[1].equals(select)) {
            try {
                java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String query = "select WORK_FLOW_CODE From Workflow where WORK_FLOW_CODE = '" + workFlowCode + "'";
                ResultSet rset = stmt.executeQuery(query);

                if (rset.next()) {
                    int x = JOptionPane.showConfirmDialog(this, "Are you sure to change the '" + workFlowCode + "' Workflow details?", "Update Workflow?", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        String UpdateQuery = "update Workflow set WORK_FLOW_NAME = '" + workFlowName + "'"
                                + ", REMARKS = '" + textArea + "', PL2_ITEM_CODE = '" + ProductionlevelItem[1] + "' where WORK_FLOW_CODE = '" + workFlowCode + "'";
                        stmt.execute(UpdateQuery);
                        JOptionPane.showMessageDialog(this, "Workflow details are updated.");
                        Refresh();
                    } else if (x == JOptionPane.NO_OPTION) {
                        textWorkflowfCode.requestFocus();
                    }

                } else if (!rset.next()) {
                    String UpdateQuery = "insert into Workflow (WORK_FLOW_CODE, WORK_FLOW_NAME"
                            + ", REMARKS, PL2_ITEM_CODE) values ( '" + workFlowCode + "','" + workFlowName + "', '" + textArea + "', '" + ProductionlevelItem[1] + "') ";
                    stmt.execute(UpdateQuery);
                    JOptionPane.showMessageDialog(this, "New workflow is saved.");
                    Refresh();
                }
                rset.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            }
        } else if (workFlowCode.isEmpty() || workFlowName.isEmpty() || textArea.equals(select)) {
            JOptionPane.showMessageDialog(this, "Please fill all fields before save.", "Empty fields", JOptionPane.OK_OPTION);
            textWorkflowfCode.requestFocus();
        }
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        CheckBeforeDelete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void CheckBeforeDelete() {
        String CategoryCode = textWorkflowfCode.getText();
        if (!CategoryCode.isEmpty()) {
            try {
                java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String query = "select DepartmentCode From Items where DepartmentCode = '" + CategoryCode + "'";
                ResultSet rset = stmt.executeQuery(query);

                if (rset.next()) {
                    JOptionPane.showMessageDialog(this, "This department is already used. Can't delete.", "Can't delete", JOptionPane.ERROR_MESSAGE);
                } else if (!rset.next()) {
                    DeleteDepartment();
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            }
        } else if (CategoryCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please insert a valid department code before delete.", "Empty department code", JOptionPane.OK_OPTION);
            textWorkflowfCode.requestFocus();
        }
    }

    private void DeleteDepartment() {
        String Code = textWorkflowfCode.getText();
        int x = JOptionPane.showConfirmDialog(this, "Are you sure To delete this?", "Delete department?", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            try {
                java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                java.sql.Statement Checkstmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                String Checkquery = "select DepartmentCode From Departments where DepartmentCode = '" + Code + "'";
                ResultSet Checkrset = Checkstmt.executeQuery(Checkquery);

                if (Checkrset.next()) {
                    String query = "delete From Departments where DepartmentCode = '" + Code + "'";
                    stmt.execute(query);
                    JOptionPane.showMessageDialog(this, "Department is deleted.");
                    Refresh();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid department code. Please insert a valid department code.", "Invalid department code", JOptionPane.OK_OPTION);
                    textWorkflowfCode.requestFocus();
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            }

        } else if (x == JOptionPane.NO_OPTION) {
            textWorkflowfCode.requestFocus();
        }
    }

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
        String Code, Name, Remark = "", ProdlvlItem, WorkFlowName = "", PL2_ITEM_NAME="";

        Code = tableViewDetails.getValueAt(tableViewDetails.getSelectedRow(), 0).toString();
        Name = tableViewDetails.getValueAt(tableViewDetails.getSelectedRow(), 1).toString();
        //Remark = tableViewDetails.getValueAt(tableViewDetails.getSelectedRow(), 2).toString();
        ProdlvlItem = tableViewDetails.getValueAt(tableViewDetails.getSelectedRow(), 2).toString();

        try {
            ResultSet reset;
            Statement stmt;
            String query;
            query = "SELECT\n"
                    + "     Workflow.\"WORK_FLOW_CODE\" AS Workflow_WORK_FLOW_CODE,\n"
                    + "     Workflow.\"WORK_FLOW_NAME\" AS Workflow_WORK_FLOW_NAME,\n"
                    + "     Workflow.\"PL2_ITEM_CODE\" AS Workflow_PL2_ITEM_CODE,\n"
                    + "     Workflow.\"REMARKS\" AS Workflow_REMARKS,\n"
                    + "     ProductLevel2.\"PL2_ITEM_NAME\" AS ProductLevel2_PL2_ITEM_NAME\n"
                    + "FROM\n"
                    + "     \"dbo\".\"ProductLevel2\" ProductLevel2 INNER JOIN \"dbo\".\"Workflow\" Workflow ON ProductLevel2.\"PL2_ITEM_CODE\" = Workflow.\"PL2_ITEM_CODE\"\n"
                    + "WHERE Workflow.\"WORK_FLOW_CODE\" = '"+Code+"'";
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            if (reset.next()) {
                PL2_ITEM_NAME = reset.getString("ProductLevel2_PL2_ITEM_NAME");
                Remark = reset.getString("Workflow_REMARKS");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }

        textWorkflowfCode.setText(Code);
        textWorkflowName.setText(Name);
        cmbProductLevel2Item.setSelectedItem(PL2_ITEM_NAME + "--" + ProdlvlItem);
        textAreaRemarks.setText(Remark);
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
                query = "SELECT * FROM Workflow WHERE WORK_FLOW_CODE LIKE '" + CategoryCode + "%'";
            } else {
                query = "SELECT * FROM Workflow  WHERE WORK_FLOW_CODE LIKE '" + CategoryCode + "%'";
            }
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            while (reset.next()) {

                model_categoryTable.addRow(new Object[model_categoryTable.getColumnCount()]);
                tableViewDetails.setValueAt(reset.getString("WORK_FLOW_CODE"), rowCount, 0);
                tableViewDetails.setValueAt(reset.getString("WORK_FLOW_NAME"), rowCount, 1);
                tableViewDetails.setValueAt(reset.getString("PL2_ITEM_CODE"), rowCount, 2);
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
                query = "SELECT * FROM Workflow WHERE WORK_FLOW_NAME LIKE '%" + CategoryName + "%'";
            } else {
                query = "SELECT * FROM Workflow  WHERE WORK_FLOW_NAME LIKE '%" + CategoryName + "%'";
            }
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            while (reset.next()) {

                model_categoryTable.addRow(new Object[model_categoryTable.getColumnCount()]);
                tableViewDetails.setValueAt(reset.getString("WORK_FLOW_CODE"), rowCount, 0);
                tableViewDetails.setValueAt(reset.getString("WORK_FLOW_NAME"), rowCount, 1);
                tableViewDetails.setValueAt(reset.getString("PL2_ITEM_CODE"), rowCount, 2);
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
        int x = evt.getX();
        int y = evt.getY();
        System.out.println(x + "," + y);
    }//GEN-LAST:event_panel1MouseClicked

    private void cmbProductLevel2ItemPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbProductLevel2ItemPopupMenuWillBecomeInvisible
        if (cmbProductLevel2Item.getSelectedItem().equals(select)) {
            cmbProductLevel2Item.requestFocus();
        } else {
            textAreaRemarks.requestFocus();
        }
    }//GEN-LAST:event_cmbProductLevel2ItemPopupMenuWillBecomeInvisible

    private void cmbProductLevel2ItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbProductLevel2ItemKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cmbProductLevel2Item.getSelectedItem().equals(select)) {
                cmbProductLevel2Item.requestFocus();
            } else {
                textAreaRemarks.requestFocus();
            }
        }
    }//GEN-LAST:event_cmbProductLevel2ItemKeyPressed

    private void formInternalFrameIconified(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameIconified
        workflow.toFront();
    }//GEN-LAST:event_formInternalFrameIconified

    private void Refresh() {
        RefreshTableAndLoadAgain();
        textWorkflowfCode.setText("");
        textWorkflowName.setText("");
        textAreaRemarks.setText("");
        txtSearch.setText("");
        cmbProductLevel2Item.setSelectedIndex(0);
    }

    private void RefreshTableAndLoadAgain() {
        try {
            int row = model_categoryTable.getRowCount();
            for (int j = 0; j < row; j++) {
                model_categoryTable.removeRow(0);
            }
            LoadCategories();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbProductLevel2Item;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_accountType;
    private javax.swing.JLabel lbl_category;
    private javax.swing.JLabel lbl_description;
    private javax.swing.JLabel lbl_subAccount;
    private javax.swing.JPanel panel1;
    private javax.swing.JRadioButton rBtnCode;
    private javax.swing.JRadioButton rBtnName;
    private javax.swing.JTable tableViewDetails;
    private javax.swing.JTextArea textAreaRemarks;
    private javax.swing.JTextField textWorkflowName;
    private javax.swing.JTextField textWorkflowfCode;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    private void exit() {
        if (workflow != null) {
            workflow = null;
        }
        this.dispose();
    }

}
