package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAOImpl extends UserDAO {

    @Override
    public User getUserByEmail(String email) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user WHERE email = ?")) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("type"),
                            rs.getBoolean("subscribed")
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
    public int createUser(User user) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("INSERT INTO user (name, email, password, type, subscribed) "
                     + "VALUES (?, ?, ?, ?, ?)")) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getType());
            pstmt.setBoolean(5, user.getSubscribed());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or throw an exception as needed
            return 0;
        }
    }

    @Override
    public List<User> getSubscribedUsers() {
        List<User> subscribedUsers = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user WHERE subscribed = ?")) {
            pstmt.setBoolean(1, true);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    subscribedUsers.add(new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("type"),
                            rs.getBoolean("subscribed")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or throw an exception as needed
        }
        return subscribedUsers;
    }
}

