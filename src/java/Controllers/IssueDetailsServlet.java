/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.IssueDAO;
import Models.Issue;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author mituz
 */
public class IssueDetailsServlet extends HttpServlet {
    IssueDAO issueDAO = new IssueDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the issue ID from the request parameters
        int issueId = Integer.parseInt(request.getParameter("issueId"));
        
        // Fetch the issue details using the DAO
        Issue issue = issueDAO.getIssueById(issueId);
        
        // Check if issue exists
        if (issue != null) {
            // Set the issue as a request attribute and forward to the JSP
            request.setAttribute("issue", issue);
            request.getRequestDispatcher("issueDetails.jsp").forward(request, response);
        } else {
            // Redirect to issue list if the issue is not found
            response.sendRedirect("issue-list");
        }
    }
}