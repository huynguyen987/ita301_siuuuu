package Controllers;

import DAL.UserDAO;
import Models.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class UserListServlet extends HttpServlet {
     UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser != null && currentUser.getRoleId() == 1) { // Admin access only
            List<User> users = userDAO.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("userList.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); // Redirect to login if unauthorized
        }
    }
}
