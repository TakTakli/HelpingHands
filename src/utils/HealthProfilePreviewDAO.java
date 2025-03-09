package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HealthProfilePreviewDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/signup";
    private static final String USER = "root";
    private static final String PASSWORD = "satadafannum";

    /**
     * Fetches the health profile of the user.
     *
     * @param userId The ID of the user.
     * @return A ResultSet containing the health profile data, or null if not found.
     */
    public ResultSet getHealthProfile(int userId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM health_profile WHERE user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            // Close resources in case of an exception
            closeResources(conn, pstmt, rs);
            return null;
        }
    }

    /**
     * Fetches the diagnoses of the user.
     *
     * @param userId The ID of the user.
     * @return A list of diagnosis IDs for the user.
     */
    public List<Integer> getUserDiagnoses(int userId) {
        List<Integer> diagnosisIds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT diagnoses_id FROM user_diagnoses WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                diagnosisIds.add(rs.getInt("diagnoses_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return diagnosisIds;
    }

    /**
     * Fetches the allergies of the user.
     *
     * @param userId The ID of the user.
     * @return A list of allergy IDs for the user.
     */
    public List<Integer> getUserAllergies(int userId) {
        List<Integer> allergyIds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT allergy_id FROM user_allergies WHERE userid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                allergyIds.add(rs.getInt("allergy_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return allergyIds;
    }

    /**
     * Closes the database resources.
     *
     * @param conn The database connection.
     * @param stmt The SQL statement.
     * @param rs   The result set.
     */
    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}