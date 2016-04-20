/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import MainFiles.IndexPage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author Ravindu
 */
public class Informations extends TimerTask {

    public static void setTimeAnd_date() {
    }

    @Override
    public void run() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = format.format(new Date());
        IndexPage.LabelTime.setText(time);
    }

}
