/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import static MainFiles.IndexPage.jobMoniter;
import db.ConnectSql;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import reports.JobMoniter;

/**
 *
 * @author KGRS
 */
public class SendEMails {

//    public void registrationEmail(String studentID) {
//        final String username = "ravindu.it10005040@gmail.com";
//        final String password = "ravIT10005040";
//        Properties props = new Properties();
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//        try {
//            ResultSet reset;
//            Statement stmt;
//            String query;
//            query = "SELECT EMAIL, STUDENT_FIRST_NAME, STUDENT_SUR_NAME FROM students WHERE STUDENT_ID = '" + studentID + "'";
//            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            reset = stmt.executeQuery(query);
//
//            if (reset.next()) {
//                String senderEmail = reset.getString("EMAIL");
//                String firstName = reset.getString("STUDENT_FIRST_NAME");
//                String surName = reset.getString("STUDENT_SUR_NAME");
//
//                Message message = new MimeMessage(session);
//                message.setFrom(new InternetAddress("ravindu.it10005040@gmail.com"));
//                message.setRecipients(Message.RecipientType.TO,
//                        InternetAddress.parse(senderEmail)); //ravindu.spsolutions@gmail.com
//                message.setSubject("Registration confirmation of EDU institute.");
//                message.setText("Dear '" + firstName + "''" + " " + "''" + surName + "',"
//                        + "\n\nCongradulations. Your request of register to EDU institute is approved."
//                        + "\nYour registration id is '" + studentID + "'."
//                        + "\n"
//                        + "\nThank you."
//                        + "\nEDU team.");
//
//                Transport.send(message);
//                System.out.println("Done");
//                JOptionPane.showMessageDialog(null, "Registration email is sent to '" + studentID + "'.");
//            }
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException ex) {
//            Logger.getLogger(SendEMails.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public static String notifyAboutLateJobsToSupervisourByEmail(String SUPERVISE_BY, String JOB_ID, String JOB_ALLOCATED_DATE, String JOB_ALLOCATED_TIME, int ALLOCATED_TIME, String PRODUCT_LEVEL_ITEM_CODE, String SHOULD_FINISHED_DATE, String SHOULD_FINISHED_AT) {
        final String BUSINESS_NAME, space = " ";
        final String username = "ravindu.it10005040@gmail.com";
        final String password = "ravIT10005040";
        final String spliter = "--";
        String returnDetails = "", printingText, printingErrorText, getExceptionTotext;
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            ResultSet reset;
            Statement stmt;
            String query;
            query = "SELECT\n"
                    + "     Employees.\"FIRST_NAME\" AS Employees_FIRST_NAME,\n"
                    + "     Employees.\"SUR_NAME\" AS Employees_SUR_NAME,\n"
                    + "     Employees.\"EMAIL\" AS Employees_EMAIL,\n"
                    + "     Employees.\"EMPLOYEE_CODE\" AS Employees_EMPLOYEE_CODE,\n"
                    + "     Modules.\"BUSINESS_NAME\" AS Modules_BUSINESS_NAME\n"
                    + "FROM\n"
                    + "     \"dbo\".\"Employees\" Employees,\n"
                    + "     \"dbo\".\"Modules\" Modules\n"
                    + "WHERE\n"
                    + "     Employees.\"EMPLOYEE_CODE\" = '" + SUPERVISE_BY + "'";
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            if (reset.next()) {
                String senderEmail = reset.getString("Employees_EMAIL");
                String firstName = reset.getString("Employees_FIRST_NAME");
                String surName = reset.getString("Employees_SUR_NAME");
                BUSINESS_NAME = reset.getString("Modules_BUSINESS_NAME");
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("ravindu.it10005040@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(senderEmail));
                message.setSubject("Reserved time exceed notification of the job " + JOB_ID + ".");
                message.setText("Dear " + firstName + "" + " " + "" + surName + ","
                        + "\n\nThe job " + JOB_ID + " which supervise by you is exceed the reserved time period."
                        + "\nIt should start on " + JOB_ALLOCATED_DATE + " at " + JOB_ALLOCATED_TIME + " and should"
                        + "\nends on " + SHOULD_FINISHED_DATE + " at " + SHOULD_FINISHED_AT + ". Job is reserved only '" + ALLOCATED_TIME + "' minutes."
                        + "\nThe production item code is " + PRODUCT_LEVEL_ITEM_CODE + "."
                        + "\nPlease be kind to check this."
                        + "\n"
                        + "\nThank you."
                        + "\n" + BUSINESS_NAME + ".");

                Transport.send(message);
                System.out.println("Done");
                printingText = "Notification email of the late job " + JOB_ID + " is sent to";
                returnDetails = printingText + space + firstName + space + surName + spliter + SUPERVISE_BY;
            }
        } catch (MessagingException e) {
//            throw new RuntimeException(e);
            if (jobMoniter != null) {
                getExceptionTotext = String.valueOf(e);
                printingErrorText = "Notification email of the late job " + JOB_ID + " is fail to sent.\n"+getExceptionTotext+"\n";
                JobMoniter.textAreaJobMonitor.setForeground(Color.magenta);
                JobMoniter.textAreaJobMonitor.insert(printingErrorText, 0);
            }else if(jobMoniter == null){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendEMails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnDetails;
    }

    public void notifyAboutWastageOfCompleteJobsToSupervisourByEmail(String SUPERVISE_BY, String JOB_ID, int plItemDifference, int itemCount, int itemCompleted, String PRODUCT_LEVEL_ITEM_CODE) {
        final String BUSINESS_NAME, space = " ";
        final String username = "ravindu.it10005040@gmail.com";
        final String password = "ravIT10005040";
        final String spliter = "--";
        String printingText, printingErrorText, getExceptionTotext;
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            ResultSet reset;
            Statement stmt;
            String query;
            query = "SELECT\n"
                    + "     Employees.\"FIRST_NAME\" AS Employees_FIRST_NAME,\n"
                    + "     Employees.\"SUR_NAME\" AS Employees_SUR_NAME,\n"
                    + "     Employees.\"EMAIL\" AS Employees_EMAIL,\n"
                    + "     Employees.\"EMPLOYEE_CODE\" AS Employees_EMPLOYEE_CODE,\n"
                    + "     Modules.\"BUSINESS_NAME\" AS Modules_BUSINESS_NAME\n"
                    + "FROM\n"
                    + "     \"dbo\".\"Employees\" Employees,\n"
                    + "     \"dbo\".\"Modules\" Modules\n"
                    + "WHERE\n"
                    + "     Employees.\"EMPLOYEE_CODE\" = '" + SUPERVISE_BY + "'";
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            if (reset.next()) {
                String senderEmail = reset.getString("Employees_EMAIL");
                String firstName = reset.getString("Employees_FIRST_NAME");
                String surName = reset.getString("Employees_SUR_NAME");
                BUSINESS_NAME = reset.getString("Modules_BUSINESS_NAME");
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("ravindu.it10005040@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(senderEmail));
                message.setSubject("Row item wastage occured in the job " + JOB_ID + ".");
                message.setText("Dear " + firstName + "" + " " + "" + surName + ","
                        + "\n\nAt the job '" + JOB_ID + "' which supervise by you is occured a row item wastage."
                        + "\nThe number of production items should complete is '" + itemCount + "'."
                        + "\nBut the job completed only '" + itemCompleted + "'. Difference is '" + plItemDifference + "'"
                        + "\nThe production item code is " + PRODUCT_LEVEL_ITEM_CODE + "."
                        + "\nPlease be kind to check this."
                        + "\n"
                        + "\nThank you."
                        + "\n" + BUSINESS_NAME + ".");

                Transport.send(message);
                System.out.println("Done");
                printingText = "Wastage occured in " + JOB_ID + ". Production item code is " + PRODUCT_LEVEL_ITEM_CODE + ".\nNotification email of the wastage is sent to" + space + firstName + space + surName + spliter + SUPERVISE_BY;
                if (jobMoniter != null) {
                    JobMoniter.textAreaJobMonitor.setForeground(Color.red);
                    JobMoniter.textAreaJobMonitor.insert(printingText, 0);
                }
            }
        } catch (MessagingException e) {
//            throw new RuntimeException(e);
            if (jobMoniter != null) {
                getExceptionTotext = String.valueOf(e);
                printingErrorText = "Notification email of the late job " + JOB_ID + " is fail to sent.\n"+getExceptionTotext+"\n";
                JobMoniter.textAreaJobMonitor.setForeground(Color.magenta);
                JobMoniter.textAreaJobMonitor.insert(printingErrorText, 0);
            }else if(jobMoniter == null){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendEMails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
