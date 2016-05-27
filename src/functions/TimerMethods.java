/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import db.ConnectSql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author KGRS
 */
public class TimerMethods {

    String logDate, logTime, JOB_ALLOCATED_TIME, JOB_ALLOCATED_DATE;
    int ALLOCATED_TIME;
    long millisecondsjobAllocatedTime, millisecondsAllocatedTime, millisecondsAllocatedFinishingTime, millisecondsCurrentTime;
    SimpleDateFormat commonTimeFormate = new SimpleDateFormat("hh:mm:ss");

    public void loadDateTime() {
        String query = "SELECT GETDATE() AS CurrentDateTime";
        try {
            Statement statement = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultset = statement.executeQuery(query);

            if (resultset.next()) {
                logDate = resultset.getString("CurrentDateTime").split(" ")[0];
                logTime = resultset.getString("CurrentDateTime").split(" ")[1];
                logTime = logTime.split("\\.")[0];
                checkLateOngoingJobs(logTime);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        }
    }

    public String checkLateOngoingJobs(String logTime) {
        String timeShouldFinish = "";
        String query = "SELECT ALLOCATED_TIME, JOB_ALLOCATED_TIME, JOB_ALLOCATED_DATE FROM JobRunning WHERE JOB_ID = ''";
        try {
            Statement statement = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultset = statement.executeQuery(query);

            if (resultset.next()) {
                ALLOCATED_TIME = resultset.getInt("ALLOCATED_TIME");
                JOB_ALLOCATED_TIME = resultset.getString("JOB_ALLOCATED_TIME");
                JOB_ALLOCATED_DATE = resultset.getString("JOB_ALLOCATED_DATE");

                Calendar caljobAllocatedTime = Calendar.getInstance();
                caljobAllocatedTime.setTime(commonTimeFormate.parse(JOB_ALLOCATED_TIME));
                millisecondsjobAllocatedTime = caljobAllocatedTime.getTimeInMillis();
                
                Calendar calcurrentTime = Calendar.getInstance();
                calcurrentTime.setTime(commonTimeFormate.parse(JOB_ALLOCATED_TIME));
                millisecondsCurrentTime = calcurrentTime.getTimeInMillis();

                millisecondsAllocatedTime = ALLOCATED_TIME * 60 * 1000;
                millisecondsAllocatedFinishingTime = millisecondsjobAllocatedTime + millisecondsAllocatedTime;

//                Date date = new Date(logEvent.timeSTamp);
//                DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//                String dateFormatted = formatter.format(date);
                
                if(millisecondsCurrentTime < millisecondsAllocatedFinishingTime){
                    
                }
                
                long second = (millisecondsAllocatedFinishingTime / 1000) % 60;
                long minute = (millisecondsAllocatedFinishingTime / (1000 * 60)) % 60;
                long hour = (millisecondsAllocatedFinishingTime / (1000 * 60 * 60)) % 24;

                timeShouldFinish = String.format("%02d:%02d:%02d", hour, minute, second);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        } catch (ParseException ex) {
            Logger.getLogger(TimerMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timeShouldFinish;

    }

}
