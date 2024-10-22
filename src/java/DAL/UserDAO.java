package DAL;

import Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DBConnection {

    public boolean registerUser(User user) {
        String sql = "INSERT INTO user (full_name, user_name, email, password, mobile, role_id, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection(); // Get the connection from DBConnection
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getMobile());
            stmt.setInt(6, user.getRoleId());
            stmt.setString(7, user.getStatus());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // Method to get a user by username and password
    public User getUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM user WHERE user_name = ? AND password = ?";
        try (Connection con = getConnection(); // Get the connection from DBConnection
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setFullName(rs.getString("full_name"));
                    user.setUserName(rs.getString("user_name"));
                    user.setEmail(rs.getString("email"));
                    user.setRoleId(rs.getInt("role_id"));
                    user.setStatus(rs.getString("status"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to get all users (Admin-only functionality)
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection con = getConnection(); // Get the connection from DBConnection
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setUserName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setRoleId(rs.getInt("role_id"));
                user.setStatus(rs.getString("status"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Method to get user by ID (for User Details view)
    public User getUserById(int userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try (Connection con = getConnection(); // Get the connection from DBConnection
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setFullName(rs.getString("full_name"));
                    user.setUserName(rs.getString("user_name"));
                    user.setEmail(rs.getString("email"));
                    user.setRoleId(rs.getInt("role_id"));
                    user.setStatus(rs.getString("status"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
