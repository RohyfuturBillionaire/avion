package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Dbconn {

    public static Connection getConnection() {
        String host ="localhost";
        String port = "5432";
        String dbname = "airplane";
        String user = "postgres";
        String password = "Goldroger12";

        String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbname;

        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}