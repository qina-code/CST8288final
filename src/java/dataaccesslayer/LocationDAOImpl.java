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
import model.User;

/**
 *
 * @author User
 */
public class LocationDAOImpl implements LocationDAO{

    @Override
    public int createLocation(Location location) {
                try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("INSERT INTO location (city, postal_code. user_id) "
                     + "VALUES (?, ?,?)")) {
            pstmt.setString(1, location.getCity());
            pstmt.setString(2, location.getPostal());
            pstmt.setInt(3, location.getUserId());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        
    }

    @Override
    public Location getLocationByUserId(int userId) {
                try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM location WHERE userId = ?")) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Location(
                            rs.getInt("id"),
                            rs.getString("city"),
                            rs.getString("postal_code"),
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
    public int createLocation(String city, String postal,int userId) {
                        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("INSERT INTO location (city, postal_code, user_id) "
                     + "VALUES (?, ?,?)")) {
            pstmt.setString(1, city);
            pstmt.setString(2, postal);
            pstmt.setInt(3, userId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }}
    
}
