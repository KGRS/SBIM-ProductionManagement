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

/**
 *
 * @author KGRS
 */
public class SendEMails {

    public void registrationEmail(String studentID) {
        final String username = "ravindu.it10005040@gmail.com";
        final String password = "ravIT10005040";
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
            query = "SELECT EMAIL, STUDENT_FIRST_NAME, STUDENT_SUR_NAME FROM students WHERE STUDENT_ID = '" + studentID + "'";
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            if (reset.next()) {
                String senderEmail = reset.getString("EMAIL");
                String firstName = reset.getString("STUDENT_FIRST_NAME");
                String surName = reset.getString("STUDENT_SUR_NAME");

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("ravindu.it10005040@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(senderEmail)); //ravindu.spsolutions@gmail.com
                message.setSubject("Registration confirmation of EDU institute.");
                message.setText("Dear '" + firstName + "''" + " " + "''" + surName + "',"
                        + "\n\nCongradulations. Your request of register to EDU institute is approved."
                        + "\nYour registration id is '" + studentID + "'."
                        + "\n"
                        + "\nThank you."
                        + "\nEDU team.");

                Transport.send(message);
                System.out.println("Done");
                JOptionPane.showMessageDialog(null, "Registration email is sent to '" + studentID + "'.");
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (SQLException ex) {
            Logger.getLogger(SendEMails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void notifyAboutLateJobsToSupervisourByEmail(String SUPERVISE_BY, String JOB_ID, String JOB_ALLOCATED_DATE, String JOB_ALLOCATED_TIME, int ALLOCATED_TIME, String PRODUCT_LEVEL_ITEM_CODE, String SHOULD_FINISHED_DATE, String SHOULD_FINISHED_AT) {
        final String BUSINESS_NAME;
        final String username = "ravindu.it10005040@gmail.com";
        final String password = "ravIT10005040";
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
                    + "     Employees.\"EMPLOYEE_CODE\" = '"+SUPERVISE_BY+"'";
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
                message.setSubject("Reserved time exceed notification of the job '" + JOB_ID + "'.");
                message.setText("Dear '" + firstName + "''" + " " + "''" + surName + "',"
                        + "\n\nThe job '" + JOB_ID + "' which supervise by you is exceed the reserved time period."
                        + "\nIt should start on '" + JOB_ALLOCATED_DATE + "' at '" + JOB_ALLOCATED_TIME + "' and should"
                        + "\nends on '" + SHOULD_FINISHED_DATE + "' at '" + SHOULD_FINISHED_AT + "'. Job is reserved only '"+ALLOCATED_TIME+"' minutes."
                        + "\nThe production item code is '" + PRODUCT_LEVEL_ITEM_CODE + "'."
                        + "\nPlease be kind to check it."
                        + "\n"
                        + "\nThank you."
                        + "\n'"+BUSINESS_NAME+"'.");

                Transport.send(message);
                System.out.println("Done");
//                String UpdateQuery = "update event_student_attendees set IS_CONFERMATION_SENT_TO_STUDENT = 'Yes' "
//                        + "where GROUP_ID = '" + groupID + "' AND STUDENT_ID = '" + studentID + "'";
//                stmtUpdate.execute(UpdateQuery);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (SQLException ex) {
            Logger.getLogger(SendEMails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void groupCreationEmailToSfatt(String groupID, String DEPARTMENT_CODE) {

        final String username = "ravindu.it10005040@gmail.com";
        final String password = "ravIT10005040";

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
                    + "     student_event_groups.`GROUP_ID` AS student_event_groups_GROUP_ID,\n"
                    + "     student_event_groups.`GROUP_NAME` AS student_event_groups_GROUP_NAME,\n"
                    + "     student_event_groups.`START_DATE` AS student_event_groups_START_DATE,\n"
                    + "     student_event_groups.`END_DATE` AS student_event_groups_END_DATE,\n"
                    + "     student_event_groups.`TIME` AS student_event_groups_TIME,\n"
                    + "     student_event_groups.`STATUS` AS student_event_groups_STATUS,\n"
                    + "     student_event_groups.`events_EVENT_CODE` AS student_event_groups_events_EVENT_CODE,\n"
                    + "     student_event_groups.`student_batch_BATCH_WITH_DEPARTMENT_CODE` AS student_event_groups_student_batch_BATCH_WITH_DEPARTMENT_CODE,\n"
                    + "     student_batch.`BATCH_WITH_DEPARTMENT_CODE` AS student_batch_BATCH_WITH_DEPARTMENT_CODE,\n"
                    + "     student_batch.`BATCH_YEAR` AS student_batch_BATCH_YEAR,\n"
                    + "     student_batch.`departments_DEPARTMENT_CODE` AS student_batch_departments_DEPARTMENT_CODE,\n"
                    + "     departments.`DEPARTMENT_NAME` AS departments_DEPARTMENT_NAME,\n"
                    + "     staff_members.`MEMBER_ID` AS staff_members_MEMBER_ID,\n"
                    + "     staff_members.`MEMBER_FIRST_NAME` AS staff_members_MEMBER_FIRST_NAME,\n"
                    + "     staff_members.`EMAIL` AS staff_members_EMAIL,\n"
                    + "     events.`EVENT_NAME` AS events_EVENT_NAME,\n"
                    + "     student_event_groups.`IS_REPORT_SENT_TO_DEPARTMENT` AS student_event_groups_IS_REPORT_SENT_TO_DEPARTMENT\n"
                    + "FROM\n"
                    + "     `student_batch` student_batch INNER JOIN `student_event_groups` student_event_groups ON student_batch.`BATCH_WITH_DEPARTMENT_CODE` = student_event_groups.`student_batch_BATCH_WITH_DEPARTMENT_CODE`\n"
                    + "     INNER JOIN `departments` departments ON student_batch.`departments_DEPARTMENT_CODE` = departments.`DEPARTMENT_CODE`\n"
                    + "     INNER JOIN `staff_members` staff_members ON departments.`DEPARTMENT_CODE` = staff_members.`departments_DEPARTMENT_CODE`\n"
                    + "     INNER JOIN `events` events ON student_event_groups.`events_EVENT_CODE` = events.`EVENT_CODE`\n"
                    + "WHERE\n"
                    + "     student_event_groups.`GROUP_ID` = '" + groupID + "' AND student_batch.`departments_DEPARTMENT_CODE` = '" + DEPARTMENT_CODE + "'";
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            while (reset.next()) {
                String senderEmail = reset.getString("staff_members_EMAIL");
                String firstName = reset.getString("staff_members_MEMBER_FIRST_NAME");
                String GROUP_NAME = reset.getString("student_event_groups_GROUP_NAME");
                String START_DATE = reset.getString("student_event_groups_START_DATE");
                String END_DATE = reset.getString("student_event_groups_END_DATE");
                String TIME = reset.getString("student_event_groups_TIME");
                String eventName = reset.getString("events_EVENT_NAME");

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("ravindu.it10005040@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(senderEmail));
                message.setSubject("A new group named '" + GROUP_NAME + "' is created.");
                message.setText("Dear '" + firstName + "',"
                        + "\n\nA new group is created which is allocate to '" + eventName + "' event which organized by EDU."
                        + "\nIt will held on '" + START_DATE + "' to '" + END_DATE + "' at '" + TIME + "'."
                        + "\nGroup ID is '" + groupID + "' and group name is '" + GROUP_NAME + "'."
                        + "\nPlease be ready to organize necessary things."
                        + "\n"
                        + "\nThank you."
                        + "\nEDU team.");

                Transport.send(message);
                System.out.println("Done");
                JOptionPane.showMessageDialog(null, "Group creation email is sent to '" + firstName + "'.");
            }

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (SQLException ex) {
            Logger.getLogger(SendEMails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
