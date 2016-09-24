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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LuckyLak
 */
public class PreformanceCalculation {

    ArrayList<String> arrayList_GetEmpIds = new ArrayList<String>();
    ArrayList<String> arrayList_GetFixedJobIds = new ArrayList<String>();

    double lowerLimit = 0;
    GetLowerBound getLB = new GetLowerBound();

    public void GetCalcPreformance() {
        GetEmpIds();
    }

    //Get the No of Employees in the System and insert to the array
    public void GetEmpIds() {
        try {
            String S_GetEmpIds;
            ResultSet reset;
            Statement stmt;
            String query;

            query = "SELECT  Distinct EmployeesAtFinishedJob_EMPLOYEE_CODE\n"
                    + "FROM dbo.UsersVsCompleteItems";
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            while (reset.next()) {
                S_GetEmpIds = reset.getString("EmployeesAtFinishedJob_EMPLOYEE_CODE");
                arrayList_GetEmpIds.add(S_GetEmpIds);
            }
            reset.close();
        } catch (SQLException ex) {
            Logger.getLogger(PreformanceCalculation.class.getName()).log(Level.SEVERE, null, ex);
        }

        GetFixedJobIds();
    }

    //Get the no of fixed jobs in the system and insert to the array
    public void GetFixedJobIds() {
        try {
            String S_GetFixedJobIds;
            ResultSet reset;
            Statement stmt;
            String query;

            query = "SELECT  Distinct JobFinished_FIXED_JOB_ID\n"
                    + "FROM dbo.UsersVsCompleteItems";
            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            while (reset.next()) {
                S_GetFixedJobIds = reset.getString("JobFinished_FIXED_JOB_ID");
                arrayList_GetFixedJobIds.add(S_GetFixedJobIds);
            }
            reset.close();
        } catch (SQLException ex) {
            Logger.getLogger(PreformanceCalculation.class.getName()).log(Level.SEVERE, null, ex);
        }

        calcPreformance();

    }

    //Calculate all employees vs all fixed jobs preformance
    public void calcPreformance() {

        for (int GetEmpIds_Count = 0; GetEmpIds_Count < arrayList_GetEmpIds.size(); GetEmpIds_Count++) {
            for (int GetFixedJobIds_Count = 0; GetFixedJobIds_Count < arrayList_GetFixedJobIds.size(); GetFixedJobIds_Count++) {
                CalcPreform(GetEmpIds_Count, GetFixedJobIds_Count);
            }
        }
        create_dropUserPerTable u_table = new create_dropUserPerTable();
        System.out.println("Completed the Preformance Calculation");
        u_table.deleteUserViseItemComplete();
    }

    //Calculate one employee vs fixed job user preformance
    public void CalcPreform(int GetEmpIds_Count, int GetFixedJobIds_Count) {
        double T_sum = 0;
        double I_sum = 0;
        String T_sum_S = null;
        String I_sum_S = null;

        System.out.println("EmpID : " + arrayList_GetEmpIds.get(GetEmpIds_Count) + ", FixJobID : " +"\n"
                + arrayList_GetFixedJobIds.get(GetFixedJobIds_Count));

        //Get lower limit value for the remove unnesseccery user perforance from the calulateion
        lowerLimit = getLB.getLowerB(arrayList_GetFixedJobIds.get(GetFixedJobIds_Count));

        //Get Sum of taken time
        try {
            ResultSet reset;
            Statement stmt;
            String query;

            query = "SELECT SUM(JobFinished_TAKEN_TIME)\n"
                    + "  FROM dbo.UsersVsCompleteItems\n"
                    + "  WHERE EmployeesAtFinishedJob_EMPLOYEE_CODE = '" + arrayList_GetEmpIds.get(GetEmpIds_Count) + "' \n"
                    + "and JobFinished_FIXED_JOB_ID = '" + arrayList_GetFixedJobIds.get(GetFixedJobIds_Count) + "' and TIME_PER_ONE_ITEM > '" + lowerLimit + "'";

            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            if (reset.next()) {
                String wd = reset.getString(1);
                T_sum_S = wd;
            }

            if (T_sum_S != null) {
                T_sum = Double.parseDouble(T_sum_S);
            } else {
                T_sum = 0;
            }

            reset.close();
        } catch (SQLException ex) {
            Logger.getLogger(PreformanceCalculation.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Get Sum of complete
        try {
            ResultSet reset;
            Statement stmt;
            String query;

            query = "SELECT SUM(JobFinished_ITEM_COUNT_COMPLETED)\n"
                    + "  FROM dbo.UsersVsCompleteItems\n"
                    + "  WHERE EmployeesAtFinishedJob_EMPLOYEE_CODE = '" + arrayList_GetEmpIds.get(GetEmpIds_Count) + "' and JobFinished_FIXED_JOB_ID = '" + arrayList_GetFixedJobIds.get(GetFixedJobIds_Count) + "' and TIME_PER_ONE_ITEM > '" + lowerLimit + "'";

            stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reset = stmt.executeQuery(query);

            if (reset.next()) {
                String wd = reset.getString(1);
                I_sum_S = wd;
            }

            if (I_sum_S != null) {
                I_sum = Double.parseDouble(I_sum_S);
            } else {
                I_sum = 0;
            }

            reset.close();
        } catch (SQLException ex) {
            Logger.getLogger(PreformanceCalculation.class.getName()).log(Level.SEVERE, null, ex);
        }

        double d_mean;
        if (T_sum != 0 || I_sum != 0) {
            //Calculate mean
            d_mean = T_sum / I_sum;
        } else {
            //Set lower limit value as default value
            d_mean = lowerLimit;
        }

        float mean = (float) d_mean;
        InsertUserProformancs(GetEmpIds_Count, GetFixedJobIds_Count, mean);
    }

    public float roundTwoDecimals(float f) {
        // Round value to two decimal points
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Float.valueOf(twoDForm.format(f));
    }

    public void InsertUserProformancs(int GetEmpIds_Count, int GetFixedJobIds_Count, float smean) {
        // Insert values to Employee preformance db

        float Calculate_Preformance_Value = roundTwoDecimals(smean);

        try {
            //check values are exsist
            java.sql.Statement stmt = ConnectSql.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "select EMPLOYEE_CODE,AVERAGE_TIME_TO_COMPLETE From EmployeePerformance WHERE EMPLOYEE_CODE = '" + arrayList_GetEmpIds.get(GetEmpIds_Count) + "' and FIXED_JOB_ID = '" + arrayList_GetFixedJobIds.get(GetFixedJobIds_Count) + "'";
            ResultSet rset = stmt.executeQuery(query);

            //update values if old value exsist
            if (rset.next()) {
                double Old_Performance_Value = rset.getDouble("AVERAGE_TIME_TO_COMPLETE");
                System.out.println("Old Preformance Value : " + Old_Performance_Value);

                //Get old preformance value and recalculte new value
                //Give 75% weightage to old value and 25% weightage to current calculate value
                //And calculate final preformance value
                double f_mean = ((Old_Performance_Value * 0.75) + (Calculate_Preformance_Value * 0.25));
                float Final_Performance_Value = roundTwoDecimals((float) f_mean);

                String UpdateQuery = "UPDATE [dbo].[EmployeePerformance]\n"
                        + "   SET [AVERAGE_TIME_TO_COMPLETE] = '" + Final_Performance_Value + "'\n"
                        + " WHERE EMPLOYEE_CODE = '" + arrayList_GetEmpIds.get(GetEmpIds_Count) + "' and FIXED_JOB_ID = '" + arrayList_GetFixedJobIds.get(GetFixedJobIds_Count) + "'";
                stmt.execute(UpdateQuery);

                System.out.println("New Preformance Value : " + Final_Performance_Value + " Updated Successfuly \n");

                //insert new values
            } else if (!rset.next()) {
                String UpdateQuery = "INSERT INTO [dbo].[EmployeePerformance]\n"
                        + "           ([EMPLOYEE_CODE]\n"
                        + "           ,[FIXED_JOB_ID]\n"
                        + "           ,[AVERAGE_TIME_TO_COMPLETE])\n"
                        + "     VALUES\n"
                        + "           ('" + arrayList_GetEmpIds.get(GetEmpIds_Count) + "'\n"
                        + "           ,'" + arrayList_GetFixedJobIds.get(GetFixedJobIds_Count) + "'\n"
                        + "           ,'" + Calculate_Preformance_Value + "')";
                stmt.execute(UpdateQuery);
                System.out.println("Preformance Value : " + Calculate_Preformance_Value + " Inserted Successfuly \n");

            }
            rset.close();

        } catch (SQLException ex) {
            Logger.getLogger(PreformanceCalculation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
