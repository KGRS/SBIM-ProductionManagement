/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import java.applet.Applet;
import java.awt.*;
/**
 *
 * @author KGRS
 */
public class BaloonPopups extends Applet{
    
    //-----------------------------------------------------------------
   //  Draws a set of balloons on strings.
   //-----------------------------------------------------------------
   @Override
   public void paint (Graphics page)
   {
      setBackground (Color.white);

      // draw the strings
      page.setColor (Color.black);
      page.drawLine (45, 95, 100, 300);
      page.drawLine (90, 100, 100, 300);
      page.drawLine (60, 100, 100, 300);
      page.drawLine (122, 85, 100, 300);
      page.drawLine (145, 115, 100, 300);

      // draw the balloons
      page.setColor (Color.blue);
      page.fillOval (20, 30, 50, 65);
      page.setColor (Color.yellow);
      page.fillOval (70, 40, 40, 60);
      page.setColor (Color.red);
      page.fillOval (40, 50, 40, 55);
      page.setColor (Color.green);
      page.fillOval (100, 30, 45, 55);
      page.setColor (Color.cyan);
      page.fillOval (120, 55, 50, 60);
   }
    
}
