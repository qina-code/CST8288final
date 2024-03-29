/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

/**
 *
 * @author Jiaying Qiu
 */
public class UserDAOImpl extends UserDAO {

    @Override
    public User getUserByEmail(String email) {
        Connection connection = DBConnection.getConnection();
        System.out.println(connection);
        try {
            //prepare query
            PreparedStatement pstmt = connection.prepareStatement("Select * from user where email = ? ");
            pstmt.setString(1, email);
            //execute and get resultset
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Email: " + rs.getString("email") + "Password " + rs.getString("password"));

                //return new User(rs.getString("name"), rs.getString("email"), rs.getString("password"),rs.getString("type"),rs.getBoolean("subscribed"));
                return new User(
                        rs.getInt("id"), // Retrieve and use the user ID
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("type"),
                        rs.getBoolean("subscribed")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public int createUser(User user) {
        Connection connection = DBConnection.getConnection();
        try {
            //prepare query
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO user (name, email, password, type, subscribed) "
                    + "VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getType());
            pstmt.setBoolean(5, user.getSubscribed());
            //execute
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
