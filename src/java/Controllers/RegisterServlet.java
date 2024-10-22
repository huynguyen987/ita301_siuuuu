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

/**
 *
 * @author mituz
 */
public class RegisterServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String mobile = request.getParameter("mobile");

        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setUserName(userName);
        newUser.setEmail(email);
        newUser.setPassword(password);  // In production, hash the password
        newUser.setMobile(mobile);
        newUser.setRoleId(2); // Set default role for registered user
        newUser.setStatus("active");

        if (userDAO.registerUser(newUser)) {
            response.sendRedirect("login.jsp"); // Redirect to login on success
        } else {
            request.setAttribute("errorMessage", "Registration failed");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}