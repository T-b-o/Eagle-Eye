package EagleEyeDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EagleEyeDbConnection {
    private static Connection conn;
    private static boolean hasData = false;

    public EagleEyeDbConnection() {
    }

    static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:/home/tebohomatjele/Documents/Project/Eagle-Eye/src/EagleEyeDatabase/EagleEye");
        return conn;
    }
}
