/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Location;
import model.Preference;

/**
 *
 * @author User
 */
public class PreferenceDAOImpl implements PreferenceDAO{

    @Override
    public int createPreference(Preference preference) {
            try (Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO preference (category_id, user_id) "
                     + "VALUES (?, ?)")) {
            pstmt.setInt(1, preference.getCategoryId());
            pstmt.setInt(2, preference.getUserId());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }}

    @Override
    public Preference getPreferenceByUserId(int userId) {
            try (Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM preference WHERE userId = ?")) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Preference(
                            rs.getInt("id"),
                            rs.getInt("category_id"),
                            rs.getInt("user_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or throw an exception as needed
        }finally {
        }
        return null;
    }

    @Override
    public int createPreference(int categoryId, int userId) {
            try (Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO preference (category_id, user_id) "
                     + "VALUES (?, ?)")) {
            pstmt.setInt(1, categoryId);
            pstmt.setInt(2, userId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }}}
    
