package functions;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class ValidateFields {

    private static boolean checkInt(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void validateInt(JTextField txt, int length) {
        try {
            String s = txt.getText();
            while (!checkInt(s)) {
                String s1 = s.substring(0, s.length() - 1);
                s = s1;
                txt.setText(s1);
            }
            if (s.length() > length) {
                String s1 = s.substring(0, length);
                txt.setText(s1);
            }
        } catch (Exception e) {
        }
    }

    public static void validateInt(JTextField txt) {
        try {
            String s = txt.getText();
            while (!checkInt(s)) {
                String s1 = s.substring(0, s.length() - 1);
                s = s1;
                txt.setText(s1);
            }

        } catch (Exception e) {
        }
    }

    public static boolean validateEmail(String s) {
        return s.contains("@") && s.contains(".");
    }

    public static void validateTextfieldWithSize(JTextField field, int size) {
        String text = field.getText();
        int length = text.length();

        if (length > size) {
            JOptionPane.showMessageDialog(field, "<html>Maximum charcters has reached <br> on this Field");
            String s = text.substring(0, size);
            field.setText(s);

        }
    }

    public static boolean CheckForOtherFields(JTextField field) {
        String text_ = field.getText();
        boolean b = false;

        if (text_.contains(";") || text_.contains("'") || text_.contains("/") || text_.contains("--") || text_.contains("<") || text_.contains(">") || text_.contains(",")) {
            JOptionPane.showMessageDialog(field, "<html>Following Characters are not alowed in this field  <br><b>;</b> , <b>'</b> ,<b> \\</b> ,<b> -</b> , <b><</b> , <b>></b>, <b>,</b>");
            String substring = text_.substring(0, text_.length() - 1);
            field.setText(substring);

        } else {
            b = true;
        }
        return b;
    }

    public static boolean CheckForAreas(JTextArea Area) {
        String text_ = Area.getText();
        boolean b = false;

        if (text_.contains(";") || text_.contains("'") || text_.contains("/") || text_.contains("--") || text_.contains("<") || text_.contains(">") || text_.contains(",")) {
            JOptionPane.showMessageDialog(Area, "<html>Following Characters are not alowed in this field  <br><b>;</b> , <b>'</b> ,<b> \\</b> ,<b> -</b> , <b><</b> , <b>></b>, <b>,</b>");
            String substring = text_.substring(0, text_.length() - 1);
            Area.setText(substring);

        } else {
            b = true;
        }
        return b;
    }
    
    public static boolean CheckForCodes(JTextField field) {
        String text_ = field.getText();
        boolean b = false;

        if (text_.contains(";") || text_.contains("'") || text_.contains("/") || text_.contains("--") || text_.contains("<") || text_.contains(">") || text_.contains(",") || text_.contains(" ")) {
            JOptionPane.showMessageDialog(field, "<html>Following Characters are not alowed in this field  <br><b>;</b> , <b>'</b> ,<b> /</b> ,<b> --</b> , <b><</b> , <b>></b>, <b>,</b>");
            String substring = text_.substring(0, text_.length() - 1);
            field.setText(substring);

        } else {
            b = true;
        }
        return b;
    }
    
    public static boolean CheckForItemCodes(JTextField field) {
        String text_ = field.getText();
//        String BarCodeUnsupport = "\";
        boolean b = false;

        if (text_.contains(";") || text_.contains("'") || text_.contains("/") || text_.contains("--") || text_.contains("<") || text_.contains(">") || text_.contains(",") || text_.contains(" ") || text_.contains("\\")) {
            JOptionPane.showMessageDialog(field, "<html>Following Characters are not alowed in this field  <br><b>;</b> , <b>'</b> ,<b> /</b> ,<b> --</b> , <b><</b> , <b>></b>, <b>,</b>,<b> \\</b>");
            String substring = text_.substring(0, text_.length() - 1);
            field.setText(substring);

        } else {
            b = true;
        }
        return b;
    }
    
    /**
     * Given
     * <code>JTextField</code>'s character size Will be limited.
     *
     * @param field given text field.
     * @param maxiumSizeOfTextField maximum size to characters to be in text
     * field.
     */
    public static void validateTextFieldSize(JTextField field, int maxiumSizeOfTextField) {
        String text = field.getText();
        String fix = "";
        int length = text.length();
        if (!(length <= maxiumSizeOfTextField) ){
            fix = text.substring(0, maxiumSizeOfTextField+1);
        }else{
            fix = text;
        }
        
        field.setText(fix);
        
    }
}
