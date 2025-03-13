package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Appointment;

public class DatabaseUtil {

	 private static final String URL = "jdbc:mysql://localhost:3306/helpinghands";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Jelaushekodu!1";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean saveAppointment(int userId, int day, int month, int year, String type, String time) {
        String query = "INSERT INTO appointments (calendar_id, day, month, year, type, time) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, day);
            pstmt.setInt(3, month);
            pstmt.setInt(4, year);
            pstmt.setString(5, type);
            pstmt.setString(6, time);

            pstmt.executeUpdate();
            return true; // Success
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Failure
        }
    }

    public static List<Appointment> getAppointments(int userId, int day, int month, int year) {
        String query = "SELECT * FROM appointments WHERE calendar_id = ? AND day = ? AND month = ? AND year = ?";
        List<Appointment> appointments = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, day);
            pstmt.setInt(3, month);
            pstmt.setInt(4, year);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String type = rs.getString("type");
                String time = rs.getString("time");
                appointments.add(new Appointment(day, month, year, type, time));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
    public static List<Appointment> getAppointmentsForUser(int userId) {
        String query = "SELECT * FROM appointments WHERE calendar_id = ? ORDER BY year, month, day";
        List<Appointment> appointments = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int day = rs.getInt("day");
                int month = rs.getInt("month");
                int year = rs.getInt("year");
                String type = rs.getString("type");
                String time = rs.getString("time");

                appointments.add(new Appointment(day, month, year, type, time));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
}