/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserPerformance;

import db.ConnectSql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;

/**
 *
 * @author LuckyLak
 */
public class GetLowerBound {

    public double getLowerB(String GetFixedJobIds) {

        //get mean from db
        double Mean_TimePerItem = 0;
        try {
            ResultSet reset;
            Statement stmt;
            String query;

            query = "SELECT AVG(TIME_PER_ONE_ITEM)\n"
                    + "  FROM dbo.UsersVsCompleteItems\n"
                    + "  WHERE JobFinished_FIXED_JOB_ID = '" + GetFixedJobIds + "'";

            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            if (reset.next()) {
                String wd = reset.getString(1);
                Mean_TimePerItem = Double.parseDouble(wd);
            }

            reset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        }

        //get item Count from db
        double n_Count = 0;
        try {
            ResultSet reset;
            Statement stmt;
            String query;

            query = "SELECT COUNT(TIME_PER_ONE_ITEM)\n"
                    + "  FROM dbo.UsersVsCompleteItems\n"
                    + "  WHERE JobFinished_FIXED_JOB_ID = '" + GetFixedJobIds + "'";

            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            if (reset.next()) {
                String wd = reset.getString(1);
                n_Count = Double.parseDouble(wd);
            }

            reset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        }

        //get item Standed deviation from db
        double st_Dev = 0;
        try {
            ResultSet reset;
            Statement stmt;
            String query;

            query = "SELECT STDEV(TIME_PER_ONE_ITEM)\n"
                    + "  FROM dbo.UsersVsCompleteItems\n"
                    + "  WHERE JobFinished_FIXED_JOB_ID = '" + GetFixedJobIds + "'";

            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            if (reset.next()) {
                st_Dev = reset.getDouble(1);
            }

            reset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        }

        //get alfa
        Double alfa = 0.9;
        System.out.println("Confidence Interval for Fixed Job : " + alfa*100 + "%");

        // Calculate 90% confidence interval
        double S_Mean_TimePerItem = roundTwoDecimals(Mean_TimePerItem);

        double ci = calcMeanCI(n_Count, st_Dev, alfa);
        System.out.println("Mean for Fixed Job : " + S_Mean_TimePerItem);
        
        double S_lower =  (Mean_TimePerItem - ci);
        double lower = roundTwoDecimals(S_lower);
        
        double S_upper =  (Mean_TimePerItem + ci);
        double upper = roundTwoDecimals(S_upper);
        
        System.out.println("lower Limit for Fixed Job : " + lower);
        return lower;
    }
    
    
        public double getUpperB(String GetFixedJobIds) {

        //get mean from db
        double Mean_TimePerItem = 0;
        try {
            ResultSet reset;
            Statement stmt;
            String query;

            query = "SELECT AVG(TIME_PER_ONE_ITEM)\n"
                    + "  FROM dbo.UsersVsCompleteItems\n"
                    + "  WHERE JobFinished_FIXED_JOB_ID = '" + GetFixedJobIds + "'";

            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            if (reset.next()) {
                String wd = reset.getString(1);
                Mean_TimePerItem = Double.parseDouble(wd);
            }

            reset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        }

        //get item Count from db
        double n_Count = 0;
        try {
            ResultSet reset;
            Statement stmt;
            String query;

            query = "SELECT COUNT(TIME_PER_ONE_ITEM)\n"
                    + "  FROM dbo.UsersVsCompleteItems\n"
                    + "  WHERE JobFinished_FIXED_JOB_ID = '" + GetFixedJobIds + "'";

            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            if (reset.next()) {
                String wd = reset.getString(1);
                n_Count = Double.parseDouble(wd);
            }

            reset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        }

        //get item Standed deviation from db
        double st_Dev = 0;
        try {
            ResultSet reset;
            Statement stmt;
            String query;

            query = "SELECT STDEV(TIME_PER_ONE_ITEM)\n"
                    + "  FROM dbo.UsersVsCompleteItems\n"
                    + "  WHERE JobFinished_FIXED_JOB_ID = '" + GetFixedJobIds + "'";

            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            if (reset.next()) {
                st_Dev = reset.getDouble(1);
            }

            reset.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Please contact for support.");
        }

        //get alfa
        Double alfa = 0.9;
        System.out.println("Confidence Interval for Fixed Job : " + alfa*100 + "%");

        // Calculate 90% confidence interval
        double S_Mean_TimePerItem = roundTwoDecimals(Mean_TimePerItem);

        double ci = calcMeanCI(n_Count, st_Dev, alfa);
        System.out.println("Mean for Fixed Job : " + S_Mean_TimePerItem);
        
        double S_lower =  (Mean_TimePerItem - ci);
        double lower = roundTwoDecimals(S_lower);
        
        double S_upper =  (Mean_TimePerItem + ci);
        double upper = roundTwoDecimals(S_upper);
        
        System.out.println("Upper Limit for Fixed Job : " + upper);
        return upper;
    }
   
    
    
    public double roundTwoDecimals(double f) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(f));
    }

    private static double calcMeanCI(double n, double StandardDeviation, double alfa) {
        try {
            // Create T Distribution with N-1 degrees of freedom
            TDistribution tDist = new TDistribution(n - 1);
            // Calculate critical value
            double critVal = tDist.inverseCumulativeProbability(1.0 - (1 - alfa) / 2);
            // Calculate confidence interval
            return  (critVal * StandardDeviation / Math.sqrt(n));
        } catch (MathIllegalArgumentException e) {
            return  Double.NaN;
        }
    }

}
