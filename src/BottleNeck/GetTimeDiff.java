/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BottleNeck;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author LuckyLak
 */
public class GetTimeDiff {

    public int getTimeDiff(String endTime) {
        
        System.out.println("\n");
        String dateStop = endTime;
        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat iformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println("Current Time : " + dateFormat.format(date));
        String d = dateFormat.format(date);

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = iformat.parse(d);
            d2 = iformat.parse(dateStop);

            long diff = d2.getTime() - d1.getTime();
            long diffMinutes = diff / (60 * 1000);

            System.out.println(diffMinutes + " minutes ");
            
            return (int) diffMinutes;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        
        
    }
}
