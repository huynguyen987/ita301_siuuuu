/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.UserDAO;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author mituz
 */
public class LoginServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userDAO.getUserByUsernameAndPassword(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            // Redirect based on role
            switch (user.getRoleId()) {
                case 1:
                    // Admin
                    response.sendRedirect("user-list");
                    break;
                case 4:
                    // Admin
                    response.sendRedirect("requirement-list");
                    break;
                default:
                    response.sendRedirect("issue-list"); // For example, redirect to issue list for Staff
                    break;
            }
        } else {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
