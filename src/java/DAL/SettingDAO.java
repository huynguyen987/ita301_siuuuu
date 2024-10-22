
package DAL;



import entities.Setting;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SettingDAO {

    private final String DB_URL = "jdbc:mysql://localhost:3306/mpms";
    private final String USER = "root";
    private final String PASS = "12345678";

    public List<Setting> getAllSettings() {
        List<Setting> settings = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM setting";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Setting setting = new Setting(
                        rs.getInt("setting_id"),
                        rs.getString("name"),
                        rs.getString("value"),
                        rs.getString("type"),
                        rs.getInt("priority"),
                        rs.getBoolean("status"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at"),
                        rs.getInt("created_by_id"),
                        rs.getTimestamp("updated_at"),
                        rs.getInt("updated_by_id")
                );
                settings.add(setting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return settings;
    }

    public Setting getSettingById(int id) {
        Setting setting = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM setting WHERE setting_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                setting = new Setting(
                        rs.getInt("setting_id"),
                        rs.getString("name"),
                        rs.getString("value"),
                        rs.getString("type"),
                        rs.getInt("priority"),
                        rs.getBoolean("status"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at"),
                        rs.getInt("created_by_id"),
                        rs.getTimestamp("updated_at"),
                        rs.getInt("updated_by_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setting;
    }

    public void updateStatus(int id, boolean status) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "UPDATE setting SET status = ? WHERE setting_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void saveOrUpdateSetting(Setting setting) {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
        String sql;
        if (setting.getSettingId() > 0) {
            sql = "UPDATE setting SET name = ?, value = ?, type = ?, priority = ?, status = ?, description = ? WHERE setting_id = ?";
        } else {
            sql = "INSERT INTO setting (name, value, type, priority, status, description) VALUES (?, ?, ?, ?, ?, ?)";
        }
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, setting.getName());
        stmt.setString(2, setting.getValue());
        stmt.setString(3, setting.getType());
        stmt.setInt(4, setting.getPriority());
        stmt.setBoolean(5, setting.getStatus());
        stmt.setString(6, setting.getDescription());

        if (setting.getSettingId() > 0) {
            stmt.setInt(7, setting.getSettingId());
        }

        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}

