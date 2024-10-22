/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Issue;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mituz
 */
public class IssueDAO extends DBConnection{
    
    public List<Issue> getIssuesByRequirementId(int requirementId) {
        List<Issue> issues = new ArrayList<>();
        String sql = "SELECT * FROM issue WHERE req_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, requirementId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Issue issue = new Issue();
                issue.setIssueId(rs.getInt("issue_id"));
                issue.setTitle(rs.getString("title"));
                issue.setTypeId(rs.getInt("type_id"));
                issue.setReqId(rs.getInt("req_id"));
                issue.setAssignerId(rs.getInt("assigner_id"));
                issue.setAssigneeId(rs.getInt("assignee_id"));
                issue.setDeadline(rs.getDate("deadline"));
                issue.setStatus(rs.getString("status"));
                issue.setStatusDate(rs.getDate("status_date"));
                issue.setDescription(rs.getString("description"));
                issues.add(issue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issues;
    }

    
    public List<Issue> getAllIssues() {
        List<Issue> issues = new ArrayList<>();
        String sql = "SELECT * FROM issue";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Issue issue = new Issue();
                issue.setIssueId(rs.getInt("issue_id"));
                issue.setTitle(rs.getString("title"));
                issue.setDescription(rs.getString("description"));
                issue.setStatus(rs.getString("status"));
                issue.setDeadline(rs.getDate("deadline"));
                issues.add(issue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issues;
    }
    
    public boolean addIssue(Issue issue) {
        String sql = "INSERT INTO issue (title, type_id, req_id, assigner_id, assignee_id, deadline, status, description) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, issue.getTitle());
            stmt.setInt(2, issue.getTypeId());
            stmt.setInt(3, issue.getReqId());
            stmt.setInt(4, issue.getAssignerId());
            stmt.setInt(5, issue.getAssigneeId());
            stmt.setDate(6, new java.sql.Date(issue.getDeadline().getTime()));
            stmt.setString(7, issue.getStatus());
            stmt.setString(8, issue.getDescription());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Issue getIssueById(int issueId) {
        Issue issue = null;
        String sql = "SELECT * FROM issue WHERE issue_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, issueId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    issue = new Issue();
                    issue.setIssueId(rs.getInt("issue_id"));
                    issue.setTitle(rs.getString("title"));
                    issue.setDescription(rs.getString("description"));
                    issue.setStatus(rs.getString("status"));
                    issue.setDeadline(rs.getDate("deadline"));
                    issue.setTypeId(rs.getInt("type_id"));
                    issue.setReqId(rs.getInt("req_id"));
                    issue.setAssignerId(rs.getInt("assigner_id"));
                    issue.setAssigneeId(rs.getInt("assignee_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issue;
    }
    
}
