package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class HealthProfileDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/signup";
    private static final String USER = "root";
    private static final String PASSWORD = "satadafannum";

    /**
     * Saves the health profile information to the database.
     *
     * @param userId                The ID of the user.
     * @param firstName             The first name of the user.
     * @param lastName              The last name of the user.
     * @param phone                 The phone number of the user.
     * @param email                 The email address of the user.
     * @param dateOfBirth           The date of birth of the user.
     * @param gender                The gender of the user.
     * @param emergencyContactName  The name of the emergency contact.
     * @param emergencyContactPhone The phone number of the emergency contact.
     * @param emergencyContactEmail The email address of the emergency contact.
     * @param otherDisease          Other diseases entered by the user.
     * @param otherAllergy          Other allergies entered by the user.
     * @return true if the profile is saved successfully, false otherwise.
     */
    public boolean saveHealthProfile(int userId, String firstName, String lastName, String phone, String email,
                                     String dateOfBirth, String gender, String emergencyContactName,
                                     String emergencyContactPhone, String emergencyContactEmail,
                                     String otherDisease, String otherAllergy) {
        String sql = "INSERT INTO signup.health_profile (user_id, first_name, last_name, phone, email, date_of_birth, gender, " +
                     "emergency_contact_name, emergency_contact_phone, emergency_contact_email, other_disease, other_allergy) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
            pstmt.setString(6, dateOfBirth);
            pstmt.setString(7, gender);
            pstmt.setString(8, emergencyContactName);
            pstmt.setString(9, emergencyContactPhone);
            pstmt.setString(10, emergencyContactEmail);
            pstmt.setString(11, otherDisease);
            pstmt.setString(12, otherAllergy);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Return true if the profile is saved successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurs
        }
    }

    /**
     * Saves the selected diagnoses for the user.
     *
     * @param userId            The ID of the user.
     * @param diagnosisIds      A list of diagnosis IDs selected by the user.
     * @return true if the diagnoses are saved successfully, false otherwise.
     */
    public boolean saveUserDiagnoses(int userId, List<Integer> diagnosisIds) {
        String sql = "INSERT INTO signup.user_diagnoses (ID, diagnoses_id) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int diagnosisId : diagnosisIds) {
                pstmt.setInt(1, userId);
                pstmt.setInt(2, diagnosisId);
                pstmt.addBatch();
            }

            int[] rowsAffected = pstmt.executeBatch();
            return rowsAffected.length > 0; // Return true if at least one diagnosis is saved
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurs
        }
    }

    /**
     * Saves the selected allergies for the user.
     *
     * @param userId        The ID of the user.
     * @param allergyIds    A list of allergy IDs selected by the user.
     * @return true if the allergies are saved successfully, false otherwise.
     */
    public boolean saveUserAllergies(int userId, List<Integer> allergyIds) {
        String sql = "INSERT INTO signup.user_allergies (userid, allergy_id) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int allergyId : allergyIds) {
                pstmt.setInt(1, userId);
                pstmt.setInt(2, allergyId);
                pstmt.addBatch();
            }

            int[] rowsAffected = pstmt.executeBatch();
            return rowsAffected.length > 0; // Return true if at least one allergy is saved
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurs
        }
    }
}