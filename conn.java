package electricity.billing.system;

import java.sql.*;

public class conn {

    Connection c;
    Statement s;
    conn() {
        try {
            c = DriverManager.getConnection("jdbc:mysql:///ebs", "root", "root");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}