package MainFiles;

import db.ConnectSql;
/**
 *
 * @author Ravindu
 */
public class Main {

    public static String user;
    public static StringBuilder sb;
    public static boolean conn_ok;
    public static String path = "";

    public Main() {
        CheckLogger();
    }

    public static void main(String[] args) {

        if (args.length > 0) {
            path = args[0];
        }
        conn_ok = ConnectSql.conect();

        if (!conn_ok) {
            System.exit(0);
        } else {
            Main main = new Main();
        }
    }

    private void CheckLogger() {
        path = System.getProperty("user.dir");
        LoginPage login = new LoginPage();
        login.setVisible(true);
    }
}
