/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFiles;

import Transactions.ItemTransferDepartmentWise;
import Transactions.JobAllocation;
import Transactions.JobStatus;
import Transactions.MaterialRequisitionNote;
import Transactions.MaterialRequisitionNoteForProItems;
import com.jtattoo.plaf.smart.SmartLookAndFeel;
import com.jtattoo.plaf.texture.TextureLookAndFeel;
import db.ConnectSql;
import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;
import functions.Informations;
import functions.ReadConfig;
import functions.TimerMethods;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import reports.JobMoniter;

/**
 *
 * @author Ravindu
 */
public class IndexPage extends javax.swing.JFrame {

    private final String projectPath = System.getProperty("user.dir");
    public static String user = "";
    public static String employeeCode = "";
    public static String departmentCode = "";
    public static String subDepartmentCode = "";
    public static String departmentName = "";
    public static String subDepartmentName = "";
    public static String time = "";
    public static URL url = null;
    public static Date SYSTEM_DATE;
    private final String spliter = "--";

    public static Workflow workflow = null;
    public static Process subDepartment = null;
    public static OtherCost otherCost = null;
    public static ProcessExceptions processExceptions = null;
    public static ProductLevel1 productLevel1 = null;
    public static ProductLevel2 productLevel2 = null;
    public static JobFixed jobFixed = null;

    public static JobAllocation jobAllocation = null;
    public static JobStatus jobStatus = null;
    public static MaterialRequisitionNote materialRequisitionNote = null;
    public static MaterialRequisitionNoteForProItems materialRequisitionNoteForProItems = null;
    public static ItemTransferDepartmentWise ITDW = null;
    
    public static JobMoniter jobMoniter = null;

    public IndexPage() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        url = getClass().getResource("/images/image.jpg");
        initComponents();
        java.util.Timer t = new java.util.Timer();
        t.schedule(new Informations(), 0, 1000);
        LoadHeading();
        LoadSystemDate();
        LoadCompanyDetails();
        // Setting extendign state maximum..        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(projectPath + "/pictures/frameIcon/titleIcon.png"));

        LabelBackGroundPicture.setSize(screenSize);
        pnl_LeftPanel.revalidate();
        pnl_LeftPanel.repaint();
        TabMenuSettings.setVisible(false);

        String LogedUser = IndexPage.user;
        LabelUser.setText(LogedUser + spliter + employeeCode);

        String LogedUserDepartmentCode = IndexPage.departmentCode;
        String LogedUserDepartmentName = IndexPage.departmentName;
        labelDepartmentCode.setText(LogedUserDepartmentName + spliter + LogedUserDepartmentCode);

        String LogedUserSubDepartmentCode = IndexPage.subDepartmentCode;
        String LogedUserSubDepartmentName = IndexPage.subDepartmentName;
        labelSubDepartmentCode.setText(LogedUserSubDepartmentName + spliter + LogedUserSubDepartmentCode);

        Informations.setTimeAnd_date();

        ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/Logo/ClientLogo.jpg");
        LabelClientLogo.setIcon(imgThisImg);
        ImageIcon DefaultBackGround = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Basic/Default.jpg");
        LabelBackGroundPicture.setIcon(DefaultBackGround);
        dskPane_RightPanel.setBackground(new Color(255, 255, 255));
        LabelUser.requestFocus();

        java.util.Timer timerCheckLateJobs = new java.util.Timer();
        timerCheckLateJobs.schedule(new TimerMethods(), 2000, 2000); //show time in milli seconds
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        pnl_BasePanel = new javax.swing.JPanel();
        spliPne_View = new javax.swing.JSplitPane();
        pnl_LeftPanel = new javax.swing.JPanel();
        LabelDate = new javax.swing.JLabel();
        LabelTime = new javax.swing.JLabel();
        LabelUser = new javax.swing.JLabel();
        LabelDate2 = new javax.swing.JLabel();
        PanelCompanyLogo = new javax.swing.JPanel();
        LabelClientLogo = new javax.swing.JLabel();
        TextCompanyName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        ButtonClickHere = new javax.swing.JButton();
        LabelDate3 = new javax.swing.JLabel();
        labelDepartmentCode = new javax.swing.JLabel();
        labelSubDepartmentCode = new javax.swing.JLabel();
        buttonJobStatus = new javax.swing.JButton();
        dskPane_RightPanel = new javax.swing.JDesktopPane();
        LabelBackGroundPicture = new javax.swing.JLabel();
        mnBar_menuBar = new javax.swing.JMenuBar();
        TabMenuMainFiles = new javax.swing.JMenu();
        MenuWorkflow = new javax.swing.JMenuItem();
        menuFixedJobs = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuOtherCost = new javax.swing.JMenuItem();
        menuProcessExceptions = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuProductLevel1 = new javax.swing.JMenuItem();
        menuProductLevel2 = new javax.swing.JMenuItem();
        TabMenuTransactions = new javax.swing.JMenu();
        MenuCreateStudentEventGroup = new javax.swing.JMenuItem();
        menuEmployeeTree = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        arrowMenuMRN = new javax.swing.JMenu();
        menuMeterialRequisitionNote = new javax.swing.JMenuItem();
        menuMeterialRequisitionNoteForProItems = new javax.swing.JMenuItem();
        arrowMenuTransfer = new javax.swing.JMenu();
        menuTransferDepartment = new javax.swing.JMenuItem();
        TabMenuReports = new javax.swing.JMenu();
        menuItemJobMonitor = new javax.swing.JMenuItem();
        MenuReports = new javax.swing.JMenuItem();
        TabMenuTools = new javax.swing.JMenu();
        MenuNotifications = new javax.swing.JMenuItem();
        MenuPersonalization = new javax.swing.JMenu();
        MenuItemDefault = new javax.swing.JMenuItem();
        MenuItemBlank = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        SubMenuWin = new javax.swing.JMenuItem();
        SubMenuLinux = new javax.swing.JMenuItem();
        SubMenuMetal = new javax.swing.JMenuItem();
        SubMenuRome = new javax.swing.JMenuItem();
        SubMenuCreamy = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        MenuNatureTouch = new javax.swing.JMenu();
        SubMenuBlackStone = new javax.swing.JMenuItem();
        SubMenuSunSet = new javax.swing.JMenuItem();
        SubMenuGreenPlanet = new javax.swing.JMenuItem();
        SubMenuSnowWhite = new javax.swing.JMenuItem();
        SubMenuWood = new javax.swing.JMenuItem();
        SubMenuBlueOcean = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        SubMenuSpiderMan = new javax.swing.JMenuItem();
        SubMenuDarkKnight = new javax.swing.JMenuItem();
        SubMenuManOfSteel = new javax.swing.JMenuItem();
        SubMenuGreenLantern = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        SubMenuSherlockHolmes = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        SubMenuAssassinsCreed = new javax.swing.JMenuItem();
        SubMenuCrysis3 = new javax.swing.JMenuItem();
        TabMenuSettings = new javax.swing.JMenu();
        MenuAdminSettings = new javax.swing.JMenuItem();
        MenuConnectionSettings = new javax.swing.JMenuItem();
        TabMenuHelp = new javax.swing.JMenu();
        MenuAbout = new javax.swing.JMenuItem();

        jMenuItem6.setText("jMenuItem6");

        jMenu5.setText("jMenu5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnl_BasePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));

        spliPne_View.setDividerLocation(150);

        pnl_LeftPanel.setBackground(new java.awt.Color(255, 255, 255));
        pnl_LeftPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));
        pnl_LeftPanel.setEnabled(false);
        pnl_LeftPanel.setPreferredSize(new java.awt.Dimension(200, 366));

        LabelDate.setForeground(new java.awt.Color(102, 102, 102));
        LabelDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelDate.setText("Date");
        LabelDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        LabelDate.setMaximumSize(new java.awt.Dimension(34, 25));

        LabelTime.setForeground(new java.awt.Color(102, 102, 102));
        LabelTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTime.setText("Time");
        LabelTime.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        LabelTime.setMaximumSize(new java.awt.Dimension(34, 25));
        LabelTime.setMinimumSize(new java.awt.Dimension(25, 16));

        LabelUser.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        LabelUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelUser.setText("User");
        LabelUser.setMaximumSize(new java.awt.Dimension(34, 25));

        LabelDate2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        LabelDate2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelDate2.setText("Welcome");
        LabelDate2.setMaximumSize(new java.awt.Dimension(34, 25));

        PanelCompanyLogo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        javax.swing.GroupLayout PanelCompanyLogoLayout = new javax.swing.GroupLayout(PanelCompanyLogo);
        PanelCompanyLogo.setLayout(PanelCompanyLogoLayout);
        PanelCompanyLogoLayout.setHorizontalGroup(
            PanelCompanyLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelClientLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
        );
        PanelCompanyLogoLayout.setVerticalGroup(
            PanelCompanyLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelClientLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        TextCompanyName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextCompanyName.setText("Company name");
        TextCompanyName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TextCompanyName.setEnabled(false);

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" Hi... need any help?");

        ButtonClickHere.setText("Click here");
        ButtonClickHere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonClickHereActionPerformed(evt);
            }
        });

        LabelDate3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        LabelDate3.setForeground(new java.awt.Color(102, 102, 102));
        LabelDate3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelDate3.setText("You are at");
        LabelDate3.setMaximumSize(new java.awt.Dimension(34, 25));

        labelDepartmentCode.setForeground(new java.awt.Color(102, 102, 102));
        labelDepartmentCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDepartmentCode.setText("Department");
        labelDepartmentCode.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        labelDepartmentCode.setMaximumSize(new java.awt.Dimension(34, 25));

        labelSubDepartmentCode.setForeground(new java.awt.Color(102, 102, 102));
        labelSubDepartmentCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSubDepartmentCode.setText("Sub department");
        labelSubDepartmentCode.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        labelSubDepartmentCode.setMaximumSize(new java.awt.Dimension(34, 25));

        buttonJobStatus.setText("Job status");
        buttonJobStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonJobStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_LeftPanelLayout = new javax.swing.GroupLayout(pnl_LeftPanel);
        pnl_LeftPanel.setLayout(pnl_LeftPanelLayout);
        pnl_LeftPanelLayout.setHorizontalGroup(
            pnl_LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_LeftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LabelDate2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LabelDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LabelTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelCompanyLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextCompanyName)
                    .addComponent(ButtonClickHere, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LabelDate3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelDepartmentCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSubDepartmentCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonJobStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnl_LeftPanelLayout.setVerticalGroup(
            pnl_LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_LeftPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonClickHere)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonJobStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addComponent(LabelDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(LabelDate3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDepartmentCode, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSubDepartmentCode, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LabelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelTime, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(PanelCompanyLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        spliPne_View.setLeftComponent(pnl_LeftPanel);

        dskPane_RightPanel.setBackground(new java.awt.Color(204, 204, 204));
        dskPane_RightPanel.setForeground(new java.awt.Color(204, 204, 204));

        LabelBackGroundPicture.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelBackGroundPicture.setLabelFor(LabelBackGroundPicture);
        LabelBackGroundPicture.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        dskPane_RightPanel.add(LabelBackGroundPicture);
        LabelBackGroundPicture.setBounds(0, 0, 940, 660);

        spliPne_View.setRightComponent(dskPane_RightPanel);

        javax.swing.GroupLayout pnl_BasePanelLayout = new javax.swing.GroupLayout(pnl_BasePanel);
        pnl_BasePanel.setLayout(pnl_BasePanelLayout);
        pnl_BasePanelLayout.setHorizontalGroup(
            pnl_BasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spliPne_View, javax.swing.GroupLayout.DEFAULT_SIZE, 1093, Short.MAX_VALUE)
        );
        pnl_BasePanelLayout.setVerticalGroup(
            pnl_BasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spliPne_View, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
        );

        TabMenuMainFiles.setMnemonic('f');
        TabMenuMainFiles.setText("Data Files");
        TabMenuMainFiles.setPreferredSize(new java.awt.Dimension(80, 19));
        TabMenuMainFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TabMenuMainFilesActionPerformed(evt);
            }
        });

        MenuWorkflow.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        MenuWorkflow.setText("Workflow");
        MenuWorkflow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuWorkflowActionPerformed(evt);
            }
        });
        TabMenuMainFiles.add(MenuWorkflow);

        menuFixedJobs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.CTRL_MASK));
        menuFixedJobs.setText("Fixed jobs/ Process");
        menuFixedJobs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFixedJobsActionPerformed(evt);
            }
        });
        TabMenuMainFiles.add(menuFixedJobs);
        TabMenuMainFiles.add(jSeparator1);

        menuOtherCost.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuOtherCost.setText("Other cost");
        menuOtherCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOtherCostActionPerformed(evt);
            }
        });
        TabMenuMainFiles.add(menuOtherCost);

        menuProcessExceptions.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        menuProcessExceptions.setText("Process exceptions");
        menuProcessExceptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProcessExceptionsActionPerformed(evt);
            }
        });
        TabMenuMainFiles.add(menuProcessExceptions);
        TabMenuMainFiles.add(jSeparator2);

        menuProductLevel1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        menuProductLevel1.setText("Product level 1");
        menuProductLevel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProductLevel1ActionPerformed(evt);
            }
        });
        TabMenuMainFiles.add(menuProductLevel1);

        menuProductLevel2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        menuProductLevel2.setText("Product level 2");
        menuProductLevel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProductLevel2ActionPerformed(evt);
            }
        });
        TabMenuMainFiles.add(menuProductLevel2);

        mnBar_menuBar.add(TabMenuMainFiles);

        TabMenuTransactions.setMnemonic('t');
        TabMenuTransactions.setText("Transaction");
        TabMenuTransactions.setMaximumSize(new java.awt.Dimension(100, 32767));
        TabMenuTransactions.setPreferredSize(new java.awt.Dimension(80, 19));

        MenuCreateStudentEventGroup.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.ALT_MASK));
        MenuCreateStudentEventGroup.setText("Job allocation");
        MenuCreateStudentEventGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCreateStudentEventGroupActionPerformed(evt);
            }
        });
        TabMenuTransactions.add(MenuCreateStudentEventGroup);

        menuEmployeeTree.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        menuEmployeeTree.setText("Job status");
        menuEmployeeTree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEmployeeTreeActionPerformed(evt);
            }
        });
        TabMenuTransactions.add(menuEmployeeTree);
        TabMenuTransactions.add(jSeparator5);

        arrowMenuMRN.setText("MRN from Departments");

        menuMeterialRequisitionNote.setText("Material Requisition Note");
        menuMeterialRequisitionNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMeterialRequisitionNoteActionPerformed(evt);
            }
        });
        arrowMenuMRN.add(menuMeterialRequisitionNote);

        menuMeterialRequisitionNoteForProItems.setText("Material Requisition Note for Production Items");
        menuMeterialRequisitionNoteForProItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMeterialRequisitionNoteForProItemsActionPerformed(evt);
            }
        });
        arrowMenuMRN.add(menuMeterialRequisitionNoteForProItems);

        TabMenuTransactions.add(arrowMenuMRN);

        arrowMenuTransfer.setText("Item Transfer");

        menuTransferDepartment.setText("Item Transfer (Department wise)");
        menuTransferDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTransferDepartmentActionPerformed(evt);
            }
        });
        arrowMenuTransfer.add(menuTransferDepartment);

        TabMenuTransactions.add(arrowMenuTransfer);

        mnBar_menuBar.add(TabMenuTransactions);

        TabMenuReports.setText("Reports");
        TabMenuReports.setPreferredSize(new java.awt.Dimension(80, 19));

        menuItemJobMonitor.setText("Job monitor");
        menuItemJobMonitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemJobMonitorActionPerformed(evt);
            }
        });
        TabMenuReports.add(menuItemJobMonitor);

        MenuReports.setText("Reports");
        MenuReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuReportsActionPerformed(evt);
            }
        });
        TabMenuReports.add(MenuReports);

        mnBar_menuBar.add(TabMenuReports);

        TabMenuTools.setText("Tools");
        TabMenuTools.setMaximumSize(new java.awt.Dimension(70, 32767));
        TabMenuTools.setPreferredSize(new java.awt.Dimension(80, 19));

        MenuNotifications.setText("Notifications");
        MenuNotifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuNotificationsActionPerformed(evt);
            }
        });
        TabMenuTools.add(MenuNotifications);

        MenuPersonalization.setText("Personalization");

        MenuItemDefault.setText("Default");
        MenuItemDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemDefaultActionPerformed(evt);
            }
        });
        MenuPersonalization.add(MenuItemDefault);

        MenuItemBlank.setText("Blank");
        MenuItemBlank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemBlankActionPerformed(evt);
            }
        });
        MenuPersonalization.add(MenuItemBlank);
        MenuPersonalization.add(jSeparator9);

        SubMenuWin.setText("Windows Look");
        SubMenuWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuWinActionPerformed(evt);
            }
        });
        MenuPersonalization.add(SubMenuWin);

        SubMenuLinux.setText("Linux Look");
        SubMenuLinux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuLinuxActionPerformed(evt);
            }
        });
        MenuPersonalization.add(SubMenuLinux);

        SubMenuMetal.setText("Metal");
        SubMenuMetal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuMetalActionPerformed(evt);
            }
        });
        MenuPersonalization.add(SubMenuMetal);

        SubMenuRome.setText("Rome");
        SubMenuRome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuRomeActionPerformed(evt);
            }
        });
        MenuPersonalization.add(SubMenuRome);

        SubMenuCreamy.setText("Creamy");
        SubMenuCreamy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuCreamyActionPerformed(evt);
            }
        });
        MenuPersonalization.add(SubMenuCreamy);
        MenuPersonalization.add(jSeparator3);

        MenuNatureTouch.setText("Nature Touch");

        SubMenuBlackStone.setText("Black Stone");
        SubMenuBlackStone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuBlackStoneActionPerformed(evt);
            }
        });
        MenuNatureTouch.add(SubMenuBlackStone);

        SubMenuSunSet.setText("Sun Set");
        SubMenuSunSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuSunSetActionPerformed(evt);
            }
        });
        MenuNatureTouch.add(SubMenuSunSet);

        SubMenuGreenPlanet.setText("Green Planet");
        SubMenuGreenPlanet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuGreenPlanetActionPerformed(evt);
            }
        });
        MenuNatureTouch.add(SubMenuGreenPlanet);

        SubMenuSnowWhite.setText("Snow White");
        SubMenuSnowWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuSnowWhiteActionPerformed(evt);
            }
        });
        MenuNatureTouch.add(SubMenuSnowWhite);

        SubMenuWood.setText("Wood");
        SubMenuWood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuWoodActionPerformed(evt);
            }
        });
        MenuNatureTouch.add(SubMenuWood);

        SubMenuBlueOcean.setText("Blue Ocean");
        SubMenuBlueOcean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuBlueOceanActionPerformed(evt);
            }
        });
        MenuNatureTouch.add(SubMenuBlueOcean);

        MenuPersonalization.add(MenuNatureTouch);

        jMenu1.setText("Movie Touch");

        SubMenuSpiderMan.setText("Spider Man");
        SubMenuSpiderMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuSpiderManActionPerformed(evt);
            }
        });
        jMenu1.add(SubMenuSpiderMan);

        SubMenuDarkKnight.setText("Dark Knight");
        SubMenuDarkKnight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuDarkKnightActionPerformed(evt);
            }
        });
        jMenu1.add(SubMenuDarkKnight);

        SubMenuManOfSteel.setText("Man Of Steel");
        SubMenuManOfSteel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuManOfSteelActionPerformed(evt);
            }
        });
        jMenu1.add(SubMenuManOfSteel);

        SubMenuGreenLantern.setText("Green Lantern");
        SubMenuGreenLantern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuGreenLanternActionPerformed(evt);
            }
        });
        jMenu1.add(SubMenuGreenLantern);
        jMenu1.add(jSeparator7);

        SubMenuSherlockHolmes.setText("Sherlock Holmes");
        SubMenuSherlockHolmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuSherlockHolmesActionPerformed(evt);
            }
        });
        jMenu1.add(SubMenuSherlockHolmes);

        MenuPersonalization.add(jMenu1);

        jMenu2.setText("Game Touch");

        SubMenuAssassinsCreed.setText("Assassin's Creed");
        SubMenuAssassinsCreed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuAssassinsCreedActionPerformed(evt);
            }
        });
        jMenu2.add(SubMenuAssassinsCreed);

        SubMenuCrysis3.setText("Crysis 3");
        SubMenuCrysis3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubMenuCrysis3ActionPerformed(evt);
            }
        });
        jMenu2.add(SubMenuCrysis3);

        MenuPersonalization.add(jMenu2);

        TabMenuTools.add(MenuPersonalization);

        mnBar_menuBar.add(TabMenuTools);

        TabMenuSettings.setText("Settings");
        TabMenuSettings.setMaximumSize(new java.awt.Dimension(80, 32767));

        MenuAdminSettings.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        MenuAdminSettings.setText("Administrator Settings");
        MenuAdminSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAdminSettingsActionPerformed(evt);
            }
        });
        TabMenuSettings.add(MenuAdminSettings);

        MenuConnectionSettings.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        MenuConnectionSettings.setText("Connection Settings");
        MenuConnectionSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuConnectionSettingsActionPerformed(evt);
            }
        });
        TabMenuSettings.add(MenuConnectionSettings);

        mnBar_menuBar.add(TabMenuSettings);

        TabMenuHelp.setText("Help");
        TabMenuHelp.setMaximumSize(new java.awt.Dimension(70, 32767));

        MenuAbout.setText("About");
        TabMenuHelp.add(MenuAbout);

        mnBar_menuBar.add(TabMenuHelp);

        setJMenuBar(mnBar_menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1097, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnl_BasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 667, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnl_BasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoadSystemDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String DATE = ReadConfig.Date;

        if (DATE.equals("MANUAL")) {
            try {
                Statement stmt;
                stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rset = stmt.executeQuery("SELECT sysDate FROM SystemDate");
                if (rset.next()) {
                    LabelDate.setText(sdf.format(rset.getDate("sysDate")));
                }
                rset.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            }
        } else if (DATE.equals("SQL")) {
            try {
                Statement stmt = ConnectSql.conn.createStatement();
                ResultSet rset = stmt.executeQuery("Select getdate() as date");
                if (rset.next()) {
                    String SQLdate = rset.getString("date");
                    String SplSqlDate[] = SQLdate.split(" ");
                    LabelDate.setText(SplSqlDate[0]);

                    String Query = "update SystemDate set sysDate =  '" + LabelDate.getText() + "'";
                    stmt.execute(Query);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please contact for support.");
            }
        }
    }

    private void LoadCompanyDetails() {
        try {
            Statement stmt;
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rset = stmt.executeQuery("SELECT Companyname FROM ClientCompany");
            if (rset.next()) {
                TextCompanyName.setText(rset.getString("Companyname"));
            }
            rset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }
    }

    private void LoadHeading() {
        try {
            Statement stmt;
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rset = stmt.executeQuery("SELECT MODULE_NAME, VERSION, BUSINESS_NAME FROM Modules where MODULE_CODE = '3'");
            if (rset.next()) {
                String MODULE_NAME = rset.getString("MODULE_NAME");
                String VERSION = rset.getString("VERSION");
                String BUSINESS_NAME = rset.getString("BUSINESS_NAME");

                String heading = MODULE_NAME + " " + VERSION + " - " + BUSINESS_NAME;
                this.setTitle(heading);
            }
            rset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }
    }

private void TabMenuMainFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TabMenuMainFilesActionPerformed

}//GEN-LAST:event_TabMenuMainFilesActionPerformed

    private void SubMenuCreamyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuCreamyActionPerformed
//        try {
//            SyntheticaWhiteVisionLookAndFeel feel = new SyntheticaWhiteVisionLookAndFeel();
//            setThisLookandFeel(feel);
//        } catch (ParseException ex) {
//            Logger.getLogger(IndexPage.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Properties props = new Properties();
        SmartLookAndFeel.setCurrentTheme(props);
        try {
            // select the Look and Feel //
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Basic/Creamy.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
//            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.mcwin lookandfeel");
//            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }


    }//GEN-LAST:event_SubMenuCreamyActionPerformed

    private void SubMenuMetalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuMetalActionPerformed
//        QuaquaLookAndFeel feel = new QuaquaLookAndFeel();
//        setThisLookandFeel(feel);
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Metal.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(204, 204, 204));
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
    }//GEN-LAST:event_SubMenuMetalActionPerformed

    private void SubMenuGreenPlanetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuGreenPlanetActionPerformed
//        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel
        UIManager.removeAuxiliaryLookAndFeel(null);
        UIManager.removePropertyChangeListener(null);
        try {
            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green", "INSERT YOUR LICENSE KEY HERE", "my company");
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/GreenPlanet.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(0, 102, 0));

        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }
    }//GEN-LAST:event_SubMenuGreenPlanetActionPerformed

    private void SubMenuWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuWinActionPerformed
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Basic/WindowsLook.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(255, 255, 255));
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_SubMenuWinActionPerformed

    private void MenuReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuReportsActionPerformed
        JOptionPane.showMessageDialog(this, "This form is still under developing.\n We "
                + "promise to you to provide this feature as soon as possiable.");
    }//GEN-LAST:event_MenuReportsActionPerformed

    private void menuProductLevel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProductLevel2ActionPerformed
        if (productLevel2 != null) {
            if (!productLevel2.isVisible()) {
                productLevel2.setVisible(true);
            }
        } else {
            productLevel2 = new ProductLevel2();
            dskPane_RightPanel.add(productLevel2);
            productLevel2.setVisible(true);
        }
    }//GEN-LAST:event_menuProductLevel2ActionPerformed

    private void SubMenuWoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuWoodActionPerformed
        try {
            UIManager.setLookAndFeel(new SyntheticaClassyLookAndFeel());
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Wood.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(102, 51, 0));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_SubMenuWoodActionPerformed

    private void SubMenuBlackStoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuBlackStoneActionPerformed
//        try {
//            UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
//            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/DarkNight.jpg");
//            LabelBackGroundPicture.setIcon(imgThisImg);
//            dskPane_RightPanel.setBackground(Color.BLACK);
//        } catch (Exception e) { //
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }

        try {
            //here you can put the selected theme class name in JTattoo
//            UIManager.setLookAndFeel(new  TextureLookAndFeel());
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/DarkNight.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(Color.BLACK);

        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IndexPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IndexPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SubMenuBlackStoneActionPerformed

    private void SubMenuSunSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuSunSetActionPerformed
        try {
            UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/SunSet.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(Color.ORANGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_SubMenuSunSetActionPerformed

    private void SubMenuSnowWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuSnowWhiteActionPerformed
        try {
            UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/SnowWhite.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(Color.WHITE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_SubMenuSnowWhiteActionPerformed

    private void SubMenuBlueOceanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuBlueOceanActionPerformed
        try {
            UIManager.setLookAndFeel(new SyntheticaBlueMoonLookAndFeel());
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/BlueOcean.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(0, 0, 91));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_SubMenuBlueOceanActionPerformed

    private void SubMenuLinuxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuLinuxActionPerformed
//        try {
//            UIManager.setLookAndFeel(
//                    ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }

        try {
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Basic/LinuxLook.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (InstantiationException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (IllegalAccessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_SubMenuLinuxActionPerformed

    private void SubMenuSpiderManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuSpiderManActionPerformed
        try {
//            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green", "INSERT YOUR LICENSE KEY HERE", "my company");
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Movie Touch/SpiderMan.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(0, 0, 0));

        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }
    }//GEN-LAST:event_SubMenuSpiderManActionPerformed

    private void SubMenuRomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuRomeActionPerformed
        try {
            //here you can put the selected theme class name in JTattoo
//            com.jtattoo.plaf.texture.TextureLookAndFeel.setTheme("Green", "INSERT YOUR LICENSE KEY HERE", "Dark Knight");
            UIManager.setLookAndFeel(new TextureLookAndFeel());
            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Historical/Rome.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(255, 204, 153));

        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        }
    }//GEN-LAST:event_SubMenuRomeActionPerformed

    private void SubMenuDarkKnightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuDarkKnightActionPerformed
        try {
//            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Black", "INSERT YOUR LICENSE KEY HERE", "Dark Knight");
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Movie Touch/DarkKnight.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(0, 0, 0));

        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }
    }//GEN-LAST:event_SubMenuDarkKnightActionPerformed

    private void SubMenuManOfSteelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuManOfSteelActionPerformed
        try {

            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Movie Touch/ManOfSteel.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(0, 0, 20));

        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }
    }//GEN-LAST:event_SubMenuManOfSteelActionPerformed

    private void SubMenuSherlockHolmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuSherlockHolmesActionPerformed
        UIManager.removeAuxiliaryLookAndFeel(null);
        UIManager.removePropertyChangeListener(null);
        try {
            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Black", "INSERT YOUR LICENSE KEY HERE", "Dark Knight");
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Movie Touch/SherlockHolmes.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(0, 0, 0));

        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }

//        try {
//            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }//GEN-LAST:event_SubMenuSherlockHolmesActionPerformed

    private void SubMenuCrysis3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuCrysis3ActionPerformed
        UIManager.removeAuxiliaryLookAndFeel(null);
        UIManager.removePropertyChangeListener(null);
        try {
            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Red", "INSERT YOUR LICENSE KEY HERE", "Dark Knight");
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Game Touch/Crysis3.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(102, 0, 0));

        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }
    }//GEN-LAST:event_SubMenuCrysis3ActionPerformed

    private void SubMenuAssassinsCreedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuAssassinsCreedActionPerformed
        try {
            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Black", "INSERT YOUR LICENSE KEY HERE", "Dark Knight");
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Game Touch/AssassinsCreed.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(0, 0, 0));

        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }
    }//GEN-LAST:event_SubMenuAssassinsCreedActionPerformed

    private void SubMenuGreenLanternActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubMenuGreenLanternActionPerformed
        try {
            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green", "INSERT YOUR LICENSE KEY HERE", "my company");
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Movie Touch/GreenLantern.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(0, 102, 0));

        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }
    }//GEN-LAST:event_SubMenuGreenLanternActionPerformed

    private void menuItemJobMonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemJobMonitorActionPerformed
        if (jobMoniter != null) {
            if (!jobMoniter.isVisible()) {
                jobMoniter.setVisible(true);
            }
        } else {
            jobMoniter = new JobMoniter();
            dskPane_RightPanel.add(jobMoniter);
            jobMoniter.setVisible(true);
        }
    }//GEN-LAST:event_menuItemJobMonitorActionPerformed

    private void MenuAdminSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAdminSettingsActionPerformed
        JOptionPane.showMessageDialog(this, "This form is still under developing.\n We "
                + "promise to you to provide this feature as soon as possiable.");


    }//GEN-LAST:event_MenuAdminSettingsActionPerformed

    private void MenuConnectionSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuConnectionSettingsActionPerformed
//        AdministratorSettings AdminSettings = new AdministratorSettings();
//        dskPane_RightPanel.add(AdminSettings);
//        dskPane_RightPanel.repaint();
//        dskPane_RightPanel.revalidate();
//        AdminSettings.setVisible(true);
    }//GEN-LAST:event_MenuConnectionSettingsActionPerformed

    private void ButtonClickHereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonClickHereActionPerformed
        try {
            File foler = new File(projectPath + "/Video Tutorials/"); // path to the directory to be opened
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }
            desktop.open(foler);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Please contact for support.");
        }

    }//GEN-LAST:event_ButtonClickHereActionPerformed

    private void MenuWorkflowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuWorkflowActionPerformed
        if (workflow != null) {
            if (!workflow.isVisible()) {
                workflow.setVisible(true);
            }
        } else {
            workflow = new Workflow();
            dskPane_RightPanel.add(workflow);
            workflow.setVisible(true);
        }
    }//GEN-LAST:event_MenuWorkflowActionPerformed

    private void MenuItemDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemDefaultActionPerformed
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/Basic/Default.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(255, 255, 255));
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_MenuItemDefaultActionPerformed

    private void MenuNotificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuNotificationsActionPerformed
        JOptionPane.showMessageDialog(this, "This form is still under developing.\n We "
                + "promise to you to provide this feature as soon as possiable.");
    }//GEN-LAST:event_MenuNotificationsActionPerformed

    private void menuOtherCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOtherCostActionPerformed
        if (otherCost != null) {
            if (!otherCost.isVisible()) {
                otherCost.setVisible(true);
            }
        } else {
            otherCost = new OtherCost();
            dskPane_RightPanel.add(otherCost);
            otherCost.setVisible(true);
        }

    }//GEN-LAST:event_menuOtherCostActionPerformed

    private void MenuItemBlankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemBlankActionPerformed
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            ImageIcon imgThisImg = new ImageIcon(projectPath + "/pictures/DefaultBackgrounds/loginBackground.jpg");
            LabelBackGroundPicture.setIcon(imgThisImg);
            dskPane_RightPanel.setBackground(new Color(255, 255, 255));
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_MenuItemBlankActionPerformed

    private void menuProductLevel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProductLevel1ActionPerformed
        if (productLevel1 != null) {
            if (!productLevel1.isVisible()) {
                productLevel1.setVisible(true);
            }
        } else {
            productLevel1 = new ProductLevel1();
            dskPane_RightPanel.add(productLevel1);
            productLevel1.setVisible(true);
        }
    }//GEN-LAST:event_menuProductLevel1ActionPerformed

    private void menuProcessExceptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProcessExceptionsActionPerformed
        if (processExceptions != null) {
            if (!processExceptions.isVisible()) {
                processExceptions.setVisible(true);
            }
        } else {
            processExceptions = new ProcessExceptions();
            dskPane_RightPanel.add(processExceptions);
            processExceptions.setVisible(true);
        }
    }//GEN-LAST:event_menuProcessExceptionsActionPerformed

    private void menuFixedJobsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFixedJobsActionPerformed
        if (jobFixed != null) {
            if (!jobFixed.isVisible()) {
                jobFixed.setVisible(true);
            }
        } else {
            jobFixed = new JobFixed();
            dskPane_RightPanel.add(jobFixed);
            jobFixed.setVisible(true);
        }
    }//GEN-LAST:event_menuFixedJobsActionPerformed

    private void buttonJobStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonJobStatusActionPerformed
        if (jobStatus != null) {
            if (!jobStatus.isVisible()) {
                jobStatus.setVisible(true);
            }
        } else {
            jobStatus = new JobStatus();
            dskPane_RightPanel.add(jobStatus);
            jobStatus.setVisible(true);
        }
    }//GEN-LAST:event_buttonJobStatusActionPerformed

    private void menuMeterialRequisitionNoteForProItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMeterialRequisitionNoteForProItemsActionPerformed
        if (materialRequisitionNoteForProItems != null) {
            if (!materialRequisitionNoteForProItems.isVisible()) {
                materialRequisitionNoteForProItems.setVisible(true);
            }
        } else {
            materialRequisitionNoteForProItems = new MaterialRequisitionNoteForProItems();
            dskPane_RightPanel.add(materialRequisitionNoteForProItems);
            materialRequisitionNoteForProItems.setVisible(true);
        }
    }//GEN-LAST:event_menuMeterialRequisitionNoteForProItemsActionPerformed

    private void menuMeterialRequisitionNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMeterialRequisitionNoteActionPerformed
        if (materialRequisitionNote != null) {
            if (!materialRequisitionNote.isVisible()) {
                materialRequisitionNote.setVisible(true);
            }
        } else {
            materialRequisitionNote = new MaterialRequisitionNote();
            dskPane_RightPanel.add(materialRequisitionNote);
            materialRequisitionNote.setVisible(true);
        }
    }//GEN-LAST:event_menuMeterialRequisitionNoteActionPerformed

    private void menuEmployeeTreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEmployeeTreeActionPerformed
        if (jobStatus != null) {
            if (!jobStatus.isVisible()) {
                jobStatus.setVisible(true);
            }
        } else {
            jobStatus = new JobStatus();
            dskPane_RightPanel.add(jobStatus);
            jobStatus.setVisible(true);
        }
    }//GEN-LAST:event_menuEmployeeTreeActionPerformed

    private void MenuCreateStudentEventGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCreateStudentEventGroupActionPerformed
        if (jobAllocation != null) {
            if (!jobAllocation.isVisible()) {
                jobAllocation.setVisible(true);
            }
        } else {
            jobAllocation = new JobAllocation();
            dskPane_RightPanel.add(jobAllocation);
            jobAllocation.setVisible(true);
        }
    }//GEN-LAST:event_MenuCreateStudentEventGroupActionPerformed

    private void menuTransferDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTransferDepartmentActionPerformed
        if (ITDW != null) {
            if (!ITDW.isVisible()) {
                ITDW.setVisible(true);
            }
        } else {
            ITDW = new ItemTransferDepartmentWise();
            dskPane_RightPanel.add(ITDW);
            ITDW.setVisible(true);
        }
    }//GEN-LAST:event_menuTransferDepartmentActionPerformed

    public static JMenu getOpenWindowMenuItem() {
        return TabMenuSettings;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IndexPage().setVisible(true);
            }
        });
    }

    public JMenu getOpenMenuWindowMenu() {
        return TabMenuSettings;
    }

    public static JDesktopPane getDesktopPane() {
        return dskPane_RightPanel;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonClickHere;
    private javax.swing.JLabel LabelBackGroundPicture;
    private javax.swing.JLabel LabelClientLogo;
    public static javax.swing.JLabel LabelDate;
    private javax.swing.JLabel LabelDate2;
    private javax.swing.JLabel LabelDate3;
    public static javax.swing.JLabel LabelTime;
    public static javax.swing.JLabel LabelUser;
    private javax.swing.JMenuItem MenuAbout;
    private javax.swing.JMenuItem MenuAdminSettings;
    private javax.swing.JMenuItem MenuConnectionSettings;
    private javax.swing.JMenuItem MenuCreateStudentEventGroup;
    private javax.swing.JMenuItem MenuItemBlank;
    private javax.swing.JMenuItem MenuItemDefault;
    private javax.swing.JMenu MenuNatureTouch;
    private javax.swing.JMenuItem MenuNotifications;
    public static javax.swing.JMenu MenuPersonalization;
    public static javax.swing.JMenuItem MenuReports;
    private javax.swing.JMenuItem MenuWorkflow;
    private javax.swing.JPanel PanelCompanyLogo;
    private javax.swing.JMenuItem SubMenuAssassinsCreed;
    private javax.swing.JMenuItem SubMenuBlackStone;
    private javax.swing.JMenuItem SubMenuBlueOcean;
    private javax.swing.JMenuItem SubMenuCreamy;
    private javax.swing.JMenuItem SubMenuCrysis3;
    private javax.swing.JMenuItem SubMenuDarkKnight;
    private javax.swing.JMenuItem SubMenuGreenLantern;
    private javax.swing.JMenuItem SubMenuGreenPlanet;
    private javax.swing.JMenuItem SubMenuLinux;
    private javax.swing.JMenuItem SubMenuManOfSteel;
    private javax.swing.JMenuItem SubMenuMetal;
    private javax.swing.JMenuItem SubMenuRome;
    private javax.swing.JMenuItem SubMenuSherlockHolmes;
    private javax.swing.JMenuItem SubMenuSnowWhite;
    private javax.swing.JMenuItem SubMenuSpiderMan;
    private javax.swing.JMenuItem SubMenuSunSet;
    private javax.swing.JMenuItem SubMenuWin;
    private javax.swing.JMenuItem SubMenuWood;
    private javax.swing.JMenu TabMenuHelp;
    public static javax.swing.JMenu TabMenuMainFiles;
    public static javax.swing.JMenu TabMenuReports;
    private static javax.swing.JMenu TabMenuSettings;
    public static javax.swing.JMenu TabMenuTools;
    public static javax.swing.JMenu TabMenuTransactions;
    public static javax.swing.JTextField TextCompanyName;
    private javax.swing.JMenu arrowMenuMRN;
    private javax.swing.JMenu arrowMenuTransfer;
    public static javax.swing.JButton buttonJobStatus;
    public static javax.swing.JDesktopPane dskPane_RightPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    public static javax.swing.JLabel labelDepartmentCode;
    public static javax.swing.JLabel labelSubDepartmentCode;
    private javax.swing.JMenuItem menuEmployeeTree;
    private javax.swing.JMenuItem menuFixedJobs;
    private javax.swing.JMenuItem menuItemJobMonitor;
    private javax.swing.JMenuItem menuMeterialRequisitionNote;
    private javax.swing.JMenuItem menuMeterialRequisitionNoteForProItems;
    private javax.swing.JMenuItem menuOtherCost;
    private javax.swing.JMenuItem menuProcessExceptions;
    private javax.swing.JMenuItem menuProductLevel1;
    public static javax.swing.JMenuItem menuProductLevel2;
    private javax.swing.JMenuItem menuTransferDepartment;
    private javax.swing.JMenuBar mnBar_menuBar;
    private javax.swing.JPanel pnl_BasePanel;
    private javax.swing.JPanel pnl_LeftPanel;
    private javax.swing.JSplitPane spliPne_View;
    // End of variables declaration//GEN-END:variables

}
