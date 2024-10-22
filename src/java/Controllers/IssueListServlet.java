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
import java.util.List;

/**
 *
 * @author mituz
 */
public class IssueListServlet extends HttpServlet {
    IssueDAO issueDAO = new IssueDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all issues from the database
        List<Issue> issues = issueDAO.getAllIssues();
        // Pass the list of issues to the JSP
        request.setAttribute("issues", issues);
        // Forward to the issue list JSP page
        request.getRequestDispatcher("issueList.jsp").forward(request, response);
    }
}
