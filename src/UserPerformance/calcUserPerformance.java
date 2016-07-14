/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserPerformance;
/**
 *
 * @author LuckyLak
 */
public class calcUserPerformance {
    
    public void mainRunCalc(){
        create_dropUserPerTable u_table = new create_dropUserPerTable();
        u_table.createUserViseItemComplete();
        
        PreformanceCalculation calcPre = new PreformanceCalculation();
        calcPre.GetCalcPreformance();   
    }
    
}
