/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import static MainFiles.IndexPage.jobDetailedScreen;
import db.ConnectSql;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DigitalForce™
 */
public class JobDetailedScreen extends javax.swing.JInternalFrame {

    private final String select = "--Select--";
    private final DefaultTableModel model_tableFixedJobProcess, model_tableWorkFlow;
    private final String spliter = "--";
    private final String menuName = "Job detailed screen";
    String departmentCode, subDepartmentCode, rankForGenerate, designationCode, designationName, workFlowCode;
    int rank, rowCountWorkFlow, rowCountFixJobs, selectedRowOfWorkFlow, selectedRowCountOfWorkFlow;

    public JobDetailedScreen() {
        initComponents();
        model_tableFixedJobProcess = (DefaultTableModel) tableFixedJobProcess.getModel();
        model_tableWorkFlow = (DefaultTableModel) tableWorkFlow.getModel();
        panel1.setToolTipText("Press right mouse click to refresh.");
        this.setTitle(menuName);
        LoadWorkFlow();
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
        btnRefresh = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDesignationRank = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableWorkFlow = new javax.swing.JTable();
        textSecondTableItemCount = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableFixedJobProcess = new javax.swing.JTable();

        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(901, 654));
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
        panel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 590, 80, -1));

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
        panel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 590, 80, -1));

        tableDesignationRank.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rank code", "Rank level", "Designation code", "Designation name", "Department", "Sub department"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDesignationRank.getTableHeader().setReorderingAllowed(false);
        tableDesignationRank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDesignationRankMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDesignationRank);
        if (tableDesignationRank.getColumnModel().getColumnCount() > 0) {
            tableDesignationRank.getColumnModel().getColumn(1).setPreferredWidth(20);
        }

        panel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 840, 220));

        tableWorkFlow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Workflow code", "Workflow name", "PL item 2 code"
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
        tableWorkFlow.getTableHeader().setReorderingAllowed(false);
        tableWorkFlow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableWorkFlowMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableWorkFlow);

        panel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 400, 310));

        textSecondTableItemCount.setEditable(false);
        textSecondTableItemCount.setText("0");
        panel1.add(textSecondTableItemCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 70, -1));

        tableFixedJobProcess.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fixed job/Process code", "Fixed job/Process name", "PL item 1 code", "Step number"
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
        jScrollPane3.setViewportView(tableFixedJobProcess);

        panel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 410, 310));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoadWorkFlow() {
        try {
            model_tableWorkFlow.setRowCount(0);
            ResultSet reset;
            Statement stmt;
            String query;
            int rowCount = 0;
            query = "SELECT [WORK_FLOW_CODE]\n"
                    + "      ,[WORK_FLOW_NAME]\n"
                    + "      ,[PL2_ITEM_CODE]\n"
                    + "      ,[REMARKS]\n"
                    + "  FROM [Workflow] ORDER BY WORK_FLOW_CODE";
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            while (reset.next()) {
                model_tableWorkFlow.addRow(new Object[model_tableWorkFlow.getColumnCount()]);
                tableWorkFlow.setValueAt(reset.getString("WORK_FLOW_CODE"), rowCount, 0);
                tableWorkFlow.setValueAt(reset.getString("WORK_FLOW_NAME"), rowCount, 1);
                tableWorkFlow.setValueAt(reset.getString("PL2_ITEM_CODE"), rowCount, 2);
                rowCount++;
            }
            reset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }
    }

    private void LoadJobFixed(String workFlowCode) {
        try {
            model_tableFixedJobProcess.setRowCount(0);
            ResultSet reset;
            Statement stmt;
            String query;
            int rowCount = 0;
            query = "SELECT [JOB_FIXED_ID]\n"
                    + "      ,[JOB_FIXED_NAME]\n"
                    + "      ,[PRODUCT_LEVEL_ITEM_CODE]\n"
                    + "      ,[STEP_NUMBER]\n"
                    + "  FROM [JobFixed] WHERE WORK_FLOW_CODE = '" + workFlowCode + "' ORDER BY STEP_NUMBER ASC";
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            while (reset.next()) {
                model_tableFixedJobProcess.addRow(new Object[model_tableFixedJobProcess.getColumnCount()]);
                tableFixedJobProcess.setValueAt(reset.getString("JOB_FIXED_ID"), rowCount, 0);
                tableFixedJobProcess.setValueAt(reset.getString("JOB_FIXED_NAME"), rowCount, 1);
                tableFixedJobProcess.setValueAt(reset.getString("PRODUCT_LEVEL_ITEM_CODE"), rowCount, 2);
                tableFixedJobProcess.setValueAt(reset.getString("STEP_NUMBER"), rowCount, 3);
                rowCount++;
            }
            reset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }
    }    
    
    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        Refresh();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        exit();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnExitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnExitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            exit();
        }
    }//GEN-LAST:event_btnExitKeyPressed

    private void exit() {
        if (jobDetailedScreen != null) {
            jobDetailedScreen = null;
        }
        this.dispose();
    }

    private void tableDesignationRankMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesignationRankMouseClicked
        
    }//GEN-LAST:event_tableDesignationRankMouseClicked

    private void panel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel1MouseClicked
        if (SwingUtilities.isRightMouseButton(evt) || evt.isControlDown()) {
            Refresh();
        }
    }//GEN-LAST:event_panel1MouseClicked

    private void formInternalFrameIconified(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameIconified
        jobDetailedScreen.toFront();
    }//GEN-LAST:event_formInternalFrameIconified

    private void tableWorkFlowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableWorkFlowMouseClicked
        selectedRowCountOfWorkFlow = tableWorkFlow.getSelectedRowCount();
        if (selectedRowCountOfWorkFlow == 1) {
            selectedRowOfWorkFlow = tableWorkFlow.getSelectedRow();
            workFlowCode = tableWorkFlow.getValueAt(selectedRowOfWorkFlow, 0).toString();
            LoadJobFixed(workFlowCode);
        }
    }//GEN-LAST:event_tableWorkFlowMouseClicked

    private void countItemsInSecondTable() {
        textSecondTableItemCount.setText(model_tableFixedJobProcess.getRowCount() + "");
    }

    protected Object[] CheckItemAlreadyAdded(String ItemFromFirstTable) {
        int rowCount = model_tableFixedJobProcess.getRowCount();
        Object[] data = new Object[2];
        data[0] = false;
        data[1] = -1;

        for (int i = 0; i < rowCount; i++) {
            String ItemAtSecondTable = model_tableFixedJobProcess.getValueAt(i, 0).toString();
            if (ItemFromFirstTable.equals(ItemAtSecondTable)) {
                data[0] = true;
                data[1] = i;
            }
        }
        return data;
    }

    private void Refresh() {
        RefreshTableAndLoadAgain();
        LoadWorkFlow();
        countItemsInSecondTable();
    }

    private void RefreshTableAndLoadAgain() {
        try {
            model_tableFixedJobProcess.setRowCount(0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnRefresh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panel1;
    private javax.swing.JTable tableDesignationRank;
    private javax.swing.JTable tableFixedJobProcess;
    private javax.swing.JTable tableWorkFlow;
    private javax.swing.JTextField textSecondTableItemCount;
    // End of variables declaration//GEN-END:variables
}
