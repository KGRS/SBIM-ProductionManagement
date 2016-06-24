/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import MainFiles.IndexPage;
import db.ConnectSql;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.ToolTipUtils;
import reports.JobMoniter;
import static MainFiles.IndexPage.jobMoniter;

/**
 *
 * @author KGRS
 */
public class TimerMethods extends TimerTask {

    String logDate, logTime, JOB_ALLOCATED_TIME, JOB_ALLOCATED_DATE, JOB_ID, emptyField = "", SUPERVISE_BY, PRODUCT_LEVEL_ITEM_CODE, SHOULD_FINISHED_DATE, SHOULD_FINISHED_AT, printingText, query;
    int ALLOCATED_TIME;
    long millisecondsjobAllocatedTime, millisecondsjobAllocatedTimeToCompare, millisecondsAllocatedTime, millisecondsShouldFinishIn, millisecondsShouldFinishInToCompare, millisecondsCurrentTime, millisecondsCurrentTimeToCompare, millisecondsCalculatedAllocatedTime;
    SimpleDateFormat commonTimeFormate = new SimpleDateFormat("hh:mm:ss");
    String LogedUserDepartmentCode = IndexPage.departmentCode;
    String LogedUserSubDepartmentCode = IndexPage.subDepartmentCode;
    String TimerRunsAccordingTo = ReadConfig.timerRunsAccordingTo;

    private void checkLateOngoingJobs(String logTime) {
        if (TimerRunsAccordingTo.equals("Department")) {
            query = "SELECT\n"
                    + "     JobRunning.\"JOB_ID\" AS JobRunning_JOB_ID,\n"
                    + "     JobRunning.\"JOB_ALLOCATED_DATE\" AS JobRunning_JOB_ALLOCATED_DATE,\n"
                    + "     JobRunning.\"JOB_ALLOCATED_TIME\" AS JobRunning_JOB_ALLOCATED_TIME,\n"
                    + "     JobRunning.\"ALLOCATED_TIME\" AS JobRunning_ALLOCATED_TIME,\n"
                    + "     JobRunning.\"SUPERVISE_BY\" AS JobRunning_SUPERVISE_BY,\n"
                    + "     JobRunning.\"SHOULD_FINISHED_DATE\" AS JobRunning_SHOULD_FINISHED_DATE,\n"
                    + "     JobRunning.\"SHOULD_FINISHED_AT\" AS JobRunning_SHOULD_FINISHED_AT,\n"
                    + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" AS JobFixed_PRODUCT_LEVEL_ITEM_CODE,\n"
                    + "     JobFixed.\"SUB_DEPARTMENT_CODE\" AS JobFixed_SUB_DEPARTMENT_CODE,\n"
                    + "     SubDepartments.\"DepartmentCode\" AS SubDepartments_DepartmentCode,\n"
                    + "     JobRunning.\"IS_LATE\" AS JobRunning_IS_LATE\n"
                    + "FROM\n"
                    + "     \"dbo\".\"JobFixed\" JobFixed INNER JOIN \"dbo\".\"JobRunning\" JobRunning ON JobFixed.\"JOB_FIXED_ID\" = JobRunning.\"FIXED_JOB_ID\"\n"
                    + "     INNER JOIN \"dbo\".\"SubDepartments\" SubDepartments ON JobFixed.\"SUB_DEPARTMENT_CODE\" = SubDepartments.\"SUB_DEPARTMENT_CODE\"\n"
                    + "WHERE JobRunning.\"IS_LATE\" = 'No'\n"
                    + "AND SubDepartments.\"DepartmentCode\" = '" + LogedUserDepartmentCode + "'";
        } else if (TimerRunsAccordingTo.equals("Sub Department")) {
            query = "SELECT\n"
                    + "     JobRunning.\"JOB_ID\" AS JobRunning_JOB_ID,\n"
                    + "     JobRunning.\"JOB_ALLOCATED_DATE\" AS JobRunning_JOB_ALLOCATED_DATE,\n"
                    + "     JobRunning.\"JOB_ALLOCATED_TIME\" AS JobRunning_JOB_ALLOCATED_TIME,\n"
                    + "     JobRunning.\"ALLOCATED_TIME\" AS JobRunning_ALLOCATED_TIME,\n"
                    + "     JobRunning.\"SUPERVISE_BY\" AS JobRunning_SUPERVISE_BY,\n"
                    + "     JobRunning.\"SHOULD_FINISHED_DATE\" AS JobRunning_SHOULD_FINISHED_DATE,\n"
                    + "     JobRunning.\"SHOULD_FINISHED_AT\" AS JobRunning_SHOULD_FINISHED_AT,\n"
                    + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" AS JobFixed_PRODUCT_LEVEL_ITEM_CODE,\n"
                    + "     JobFixed.\"SUB_DEPARTMENT_CODE\" AS JobFixed_SUB_DEPARTMENT_CODE,\n"
                    + "     SubDepartments.\"DepartmentCode\" AS SubDepartments_DepartmentCode,\n"
                    + "     JobRunning.\"IS_LATE\" AS JobRunning_IS_LATE\n"
                    + "FROM\n"
                    + "     \"dbo\".\"JobFixed\" JobFixed INNER JOIN \"dbo\".\"JobRunning\" JobRunning ON JobFixed.\"JOB_FIXED_ID\" = JobRunning.\"FIXED_JOB_ID\"\n"
                    + "     INNER JOIN \"dbo\".\"SubDepartments\" SubDepartments ON JobFixed.\"SUB_DEPARTMENT_CODE\" = SubDepartments.\"SUB_DEPARTMENT_CODE\"\n"
                    + "WHERE JobRunning.\"IS_LATE\" = 'No'\n"
                    + "AND JobFixed.\"SUB_DEPARTMENT_CODE\" = '" + LogedUserSubDepartmentCode + "'";
        } else if (TimerRunsAccordingTo.equals("All")) {
            query = "SELECT\n"
                    + "     JobRunning.\"JOB_ID\" AS JobRunning_JOB_ID,\n"
                    + "     JobRunning.\"JOB_ALLOCATED_DATE\" AS JobRunning_JOB_ALLOCATED_DATE,\n"
                    + "     JobRunning.\"JOB_ALLOCATED_TIME\" AS JobRunning_JOB_ALLOCATED_TIME,\n"
                    + "     JobRunning.\"ALLOCATED_TIME\" AS JobRunning_ALLOCATED_TIME,\n"
                    + "     JobRunning.\"SUPERVISE_BY\" AS JobRunning_SUPERVISE_BY,\n"
                    + "     JobRunning.\"SHOULD_FINISHED_DATE\" AS JobRunning_SHOULD_FINISHED_DATE,\n"
                    + "     JobRunning.\"SHOULD_FINISHED_AT\" AS JobRunning_SHOULD_FINISHED_AT,\n"
                    + "     JobFixed.\"PRODUCT_LEVEL_ITEM_CODE\" AS JobFixed_PRODUCT_LEVEL_ITEM_CODE,\n"
                    + "     JobFixed.\"SUB_DEPARTMENT_CODE\" AS JobFixed_SUB_DEPARTMENT_CODE,\n"
                    + "     SubDepartments.\"DepartmentCode\" AS SubDepartments_DepartmentCode,\n"
                    + "     JobRunning.\"IS_LATE\" AS JobRunning_IS_LATE\n"
                    + "FROM\n"
                    + "     \"dbo\".\"JobFixed\" JobFixed INNER JOIN \"dbo\".\"JobRunning\" JobRunning ON JobFixed.\"JOB_FIXED_ID\" = JobRunning.\"FIXED_JOB_ID\"\n"
                    + "     INNER JOIN \"dbo\".\"SubDepartments\" SubDepartments ON JobFixed.\"SUB_DEPARTMENT_CODE\" = SubDepartments.\"SUB_DEPARTMENT_CODE\"\n"
                    + "WHERE JobRunning.\"IS_LATE\" = 'No'";
        }
        try {
            Statement statement = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next()) {
                JOB_ID = resultset.getString("JobRunning_JOB_ID");
                ALLOCATED_TIME = resultset.getInt("JobRunning_ALLOCATED_TIME");
                JOB_ALLOCATED_TIME = resultset.getString("JobRunning_JOB_ALLOCATED_TIME");
                JOB_ALLOCATED_DATE = resultset.getString("JobRunning_JOB_ALLOCATED_DATE");
                SUPERVISE_BY = resultset.getString("JobRunning_SUPERVISE_BY");
                PRODUCT_LEVEL_ITEM_CODE = resultset.getString("JobFixed_PRODUCT_LEVEL_ITEM_CODE");
                SHOULD_FINISHED_DATE = resultset.getString("JobRunning_SHOULD_FINISHED_DATE");
                SHOULD_FINISHED_AT = resultset.getString("JobRunning_SHOULD_FINISHED_AT");

                Calendar caljobAllocatedTime = Calendar.getInstance();
                caljobAllocatedTime.setTime(commonTimeFormate.parse(JOB_ALLOCATED_TIME));
                millisecondsjobAllocatedTime = caljobAllocatedTime.getTimeInMillis();

                Calendar calcurrentTime = Calendar.getInstance();
                calcurrentTime.setTime(commonTimeFormate.parse(logTime));
                millisecondsCurrentTime = calcurrentTime.getTimeInMillis();

                millisecondsAllocatedTime = ALLOCATED_TIME * 60 * 1000;
                millisecondsShouldFinishIn = millisecondsjobAllocatedTime + millisecondsAllocatedTime;
//
//                timeShouldFinish = String.format("%02d:%02d:%02d", hour, minute, second);
//                System.out.println(timeShouldFinish);
//                Date date = new Date(logEvent.timeSTamp);
//                DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//                String dateFormatted = formatter.format(date);
                if (millisecondsCurrentTime < 0) {
                    millisecondsCurrentTimeToCompare = millisecondsCurrentTime * (-1);
                } else if (millisecondsCurrentTime >= 0) {
                    millisecondsCurrentTimeToCompare = millisecondsCurrentTime;
                }
                if (millisecondsShouldFinishIn < 0) {
                    millisecondsShouldFinishInToCompare = millisecondsShouldFinishIn * (-1);
                } else if (millisecondsShouldFinishIn >= 0) {
                    millisecondsShouldFinishInToCompare = millisecondsShouldFinishIn;
                }
                if (millisecondsCurrentTimeToCompare > millisecondsShouldFinishInToCompare) {
                    java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    String UpdateQuery = "update JobRunning set IS_LATE = 'Yes' WHERE JOB_ID = '" + JOB_ID + "'";
                    stmt.execute(UpdateQuery);
                    stmt.close();
                    SendEMails sendEMails = new SendEMails();
                    String returnDetails = sendEMails.notifyAboutLateJobsToSupervisourByEmail(SUPERVISE_BY, JOB_ID, JOB_ALLOCATED_DATE, JOB_ALLOCATED_TIME, ALLOCATED_TIME, PRODUCT_LEVEL_ITEM_CODE, SHOULD_FINISHED_DATE, SHOULD_FINISHED_AT);
                    if (jobMoniter != null) {
                        JobMoniter.textAreaJobMonitor.setForeground(Color.red);
                        JobMoniter.textAreaJobMonitor.insert(returnDetails, 0);
                    }
                    IndexPage.buttonJobStatus.setBackground(Color.red);
                    IndexPage.buttonJobStatus.setForeground(Color.red);
                    BalloonTip tooltipBalloon = new BalloonTip(IndexPage.buttonJobStatus, "Reserved time is exceed for '" + JOB_ID + "'. Please check.");
// Now convert this balloon tip to a tooltip, such that the tooltip shows up after 500 milliseconds and stays visible for 3000 milliseconds
                    ToolTipUtils.balloonToToolTip(tooltipBalloon, 500, 3000);
                } else if (millisecondsCurrentTimeToCompare <= millisecondsShouldFinishInToCompare) {
                    IndexPage.buttonJobStatus.setBackground(new Color(240, 240, 240));
                    IndexPage.buttonJobStatus.setForeground(Color.black);
                }
            }
            statement.close();
            resultset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        } catch (ParseException ex) {
            Logger.getLogger(TimerMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        query = "SELECT GETDATE() AS CurrentDateTime";
        try {
            Statement statement = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultset = statement.executeQuery(query);

            if (resultset.next()) {
                logDate = resultset.getString("CurrentDateTime").split(" ")[0];
                logTime = resultset.getString("CurrentDateTime").split(" ")[1];
                logTime = logTime.split("\\.")[0];
                System.out.println(logTime);
                checkLateOngoingJobs(logTime);
                updateJobRunningStatus(logTime);
                if (jobMoniter != null) {
                    printingText = "Checking time = " + logTime + "\n";
                    JobMoniter.textAreaJobMonitor.setForeground(Color.black);
                    JobMoniter.textAreaJobMonitor.insert(printingText, 0);
                }
            }
            statement.close();
            resultset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        }
    }

    private void updateJobRunningStatus(String logTime) {
        String timeShouldFinish;
        query = "SELECT JOB_ID, ALLOCATED_TIME, JOB_ALLOCATED_TIME, JOB_ALLOCATED_DATE FROM JobRunning WHERE SHOULD_FINISHED_AT = '" + emptyField + "'";
        try {
            Statement statement = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next()) {
                JOB_ID = resultset.getString("JOB_ID");
                ALLOCATED_TIME = resultset.getInt("ALLOCATED_TIME");
                JOB_ALLOCATED_TIME = resultset.getString("JOB_ALLOCATED_TIME");
                JOB_ALLOCATED_DATE = resultset.getString("JOB_ALLOCATED_DATE");

                Calendar caljobAllocatedTime = Calendar.getInstance();
                caljobAllocatedTime.setTime(commonTimeFormate.parse(JOB_ALLOCATED_TIME));
                millisecondsjobAllocatedTime = caljobAllocatedTime.getTimeInMillis();

                Calendar calcurrentTime = Calendar.getInstance();
                calcurrentTime.setTime(commonTimeFormate.parse(logTime));
                millisecondsCurrentTime = calcurrentTime.getTimeInMillis();

                millisecondsAllocatedTime = ALLOCATED_TIME * 60 * 1000;
//                if (millisecondsjobAllocatedTime < 0) {
//                    millisecondsCalculatedAllocatedTime = millisecondsAllocatedTime * (-1);
//                }else if (millisecondsjobAllocatedTime >= 0) {
//                    millisecondsCalculatedAllocatedTime = millisecondsAllocatedTime;
//                }
//                millisecondsShouldFinishIn = millisecondsjobAllocatedTime + millisecondsCalculatedAllocatedTime;                

                if (millisecondsCurrentTime < 0) {
                    millisecondsCurrentTimeToCompare = millisecondsCurrentTime * (-1);
                } else if (millisecondsCurrentTime >= 0) {
                    millisecondsCurrentTimeToCompare = millisecondsCurrentTime;
                }
                if (millisecondsjobAllocatedTime < 0) {
                    millisecondsjobAllocatedTimeToCompare = millisecondsjobAllocatedTime * (-1);
                } else if (millisecondsjobAllocatedTime >= 0) {
                    millisecondsjobAllocatedTimeToCompare = millisecondsjobAllocatedTime;
                }
                millisecondsShouldFinishIn = millisecondsjobAllocatedTimeToCompare + millisecondsAllocatedTime;
                long second = (millisecondsShouldFinishIn / 1000) % 60;
                long minute = (millisecondsShouldFinishIn / (1000 * 60)) % 60;
                long hour = (millisecondsShouldFinishIn / (1000 * 60 * 60)) % 24;
                timeShouldFinish = String.format("%02d:%02d:%02d", hour, minute, second);
                System.out.println(timeShouldFinish);

                if (millisecondsCurrentTimeToCompare > millisecondsjobAllocatedTimeToCompare) {
                    java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    String UpdateQuery = "update JobRunning set SHOULD_FINISHED_AT = '" + timeShouldFinish + "', IS_NEW_ONGOING = 'Ongoing' WHERE JOB_ID = '" + JOB_ID + "'";
                    stmt.execute(UpdateQuery);
                    stmt.close();
                    if (jobMoniter != null) {
                        printingText = "Should finish time of the " + JOB_ID + " is updated as " + timeShouldFinish + ".\n";
                        JobMoniter.textAreaJobMonitor.setForeground(Color.black);
                        JobMoniter.textAreaJobMonitor.insert(printingText, 0);
                    }
                }
            }
            statement.close();
            resultset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        } catch (ParseException ex) {
            Logger.getLogger(TimerMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
