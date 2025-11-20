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

    public static void createTableIfNotExists() {
        String createSQL = "CREATE TABLE IF NOT EXISTS " + DB_TABLE + " (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " student_no TEXT,\n"
                + " full_name TEXT,\n"
                + " nationality TEXT,\n"
                + " phone_no TEXT\n"
                + ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts sample data if table is empty
     */
    public static void insertSampleDataIfEmpty() {
        String countSQL = "SELECT COUNT(*) FROM " + DB_TABLE;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(countSQL)) {

            int cnt = rs.next() ? rs.getInt(1) : 0;
            if (cnt == 0) {
                String insertSQL = "INSERT INTO " + DB_TABLE + " (student_no, full_name, nationality, phone_no) VALUES (?,?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
                    ps.setString(1, "STU001"); ps.setString(2, "Alice Nyamirambo"); ps.setString(3, "Ugandan"); ps.setString(4, "+256701234567"); ps.executeUpdate();
                    ps.setString(1, "STU002"); ps.setString(2, "Brian Ouma"); ps.setString(3, "Kenyan"); ps.setString(4, "+254712345678"); ps.executeUpdate();
                    ps.setString(1, "STU003"); ps.setString(2, "Chloe Mensah"); ps.setString(3, "Ghanaian"); ps.setString(4, "+233201234567"); ps.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }