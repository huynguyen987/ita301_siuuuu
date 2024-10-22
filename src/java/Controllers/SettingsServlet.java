package Controllers;

import entities.Setting;
import DAL.SettingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/settings")  // This annotation replaces the need for web.xml mapping
public class SettingsServlet extends HttpServlet {

    private SettingDAO settingService = new SettingDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Setting> settings = settingService.getAllSettings();
        req.setAttribute("settings", settings);
        req.getRequestDispatcher("/jsp/settings-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Setting setting = settingService.getSettingById(id);
            req.setAttribute("setting", setting);
            req.getRequestDispatcher("/jsp/setting-detail.jsp").forward(req, resp);
        } else if ("deactivate".equals(action) || "activate".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            boolean status = "activate".equals(action);
            settingService.updateStatus(id, status);
            resp.sendRedirect("settings");
        }
    }
}
