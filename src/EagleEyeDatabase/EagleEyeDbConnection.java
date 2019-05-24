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
        final String dbUrl = "jdbc:sqlserver://TBO-PC:1433;databaseName=EagleEyeDatabase;user=sa;password=MySqlS3rv3r";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(dbUrl);
        return conn;
    }
}
