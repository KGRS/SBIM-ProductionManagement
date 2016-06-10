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
import javax.swing.JButton;
import javax.swing.JOptionPane;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.ToolTipUtils;

/**
 *
 * @author KGRS
 */
public class TimerMethods extends TimerTask {

    String logDate, logTime, JOB_ALLOCATED_TIME, JOB_ALLOCATED_DATE, JOB_ID, emptyField = "";
    int ALLOCATED_TIME;
    long millisecondsjobAllocatedTime, millisecondsjobAllocatedTimeToCompare, millisecondsAllocatedTime, millisecondsShouldFinishIn, millisecondsShouldFinishInToCompare, millisecondsCurrentTime, millisecondsCurrentTimeToCompare;
    SimpleDateFormat commonTimeFormate = new SimpleDateFormat("hh:mm:ss");

    private void checkLateOngoingJobs(String logTime) {
        String query = "SELECT JOB_ID, ALLOCATED_TIME, JOB_ALLOCATED_TIME, JOB_ALLOCATED_DATE FROM JobRunning WHERE IS_LATE = 'No'";
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
                millisecondsShouldFinishIn = millisecondsjobAllocatedTime + millisecondsAllocatedTime;

//                long second = (millisecondsShouldFinishIn / 1000) % 60;
//                long minute = (millisecondsShouldFinishIn / (1000 * 60)) % 60;
//                long hour = (millisecondsShouldFinishIn / (1000 * 60 * 60)) % 24;
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
                    IndexPage.buttonJobStatus.setBackground(Color.red);
                    IndexPage.buttonJobStatus.setForeground(Color.red);
                    BalloonTip tooltipBalloon = new BalloonTip(IndexPage.buttonJobStatus, "Reserved time is exceed for '" + JOB_ID + "'. Please check.");
// Now convert this balloon tip to a tooltip, such that the tooltip shows up after 500 milliseconds and stays visible for 3000 milliseconds
                    ToolTipUtils.balloonToToolTip(tooltipBalloon, 500, 3000);
                }else if (millisecondsCurrentTimeToCompare <= millisecondsShouldFinishInToCompare) {
                    IndexPage.buttonJobStatus.setBackground(new Color(240,240,240));
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
        String query = "SELECT GETDATE() AS CurrentDateTime";
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
        String query = "SELECT JOB_ID, ALLOCATED_TIME, JOB_ALLOCATED_TIME, JOB_ALLOCATED_DATE FROM JobRunning WHERE SHOULD_FINISHED_AT = '" + emptyField + "'";
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
                millisecondsShouldFinishIn = millisecondsjobAllocatedTime + millisecondsAllocatedTime;
                long second = (millisecondsShouldFinishIn / 1000) % 60;
                long minute = (millisecondsShouldFinishIn / (1000 * 60)) % 60;
                long hour = (millisecondsShouldFinishIn / (1000 * 60 * 60)) % 24;
                timeShouldFinish = String.format("%02d:%02d:%02d", hour, minute, second);
                System.out.println(timeShouldFinish);

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
                if (millisecondsCurrentTimeToCompare > millisecondsjobAllocatedTimeToCompare) {
                    java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    String UpdateQuery = "update JobRunning set SHOULD_FINISHED_AT = '" + timeShouldFinish + "', IS_NEW_ONGOING = 'Ongoing' WHERE JOB_ID = '" + JOB_ID + "'";
                    stmt.execute(UpdateQuery);
                    stmt.close();
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
