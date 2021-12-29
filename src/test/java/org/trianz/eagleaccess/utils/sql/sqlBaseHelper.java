package org.trianz.eagleaccess.utils.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sqlBaseHelper {

    public static Connection conn = null;
    public static ResultSet rs = null;

    public static void connectDb() {
        try {
            String url = "jdbc:sqlite:C:\\Users\\Vikas\\AppData\\Roaming\\DBeaverData\\workspace6\\.metadata\\sample-database-sqlite-1\\Chinook.db";
 //           String url = "jdbc:sqlite:C:\\Users\\vikky\\AppData\\Roaming\\DBeaverData\\workspace6\\.metadata\\sample-database-sqlite-1\\Chinook.db";
            // db parameters
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            // create a connection to the database
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void closeDbConnect() throws SQLException {
        conn.close();
    }

}
