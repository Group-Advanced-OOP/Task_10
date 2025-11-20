import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {




    private static final String DB_URL = "jdbc:sqlite:students.db";
    private static final String DB_TABLE = "Students";

    /**
     * Returns a connection to the SQLite database
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
