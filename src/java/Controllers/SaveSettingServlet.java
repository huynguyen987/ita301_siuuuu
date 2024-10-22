package Controllers;

import entities.Setting;
import DAL.SettingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/save-setting")
public class SaveSettingServlet extends HttpServlet {

    private SettingDAO settingService = new SettingDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        String value = req.getParameter("value");
        int priority = Integer.parseInt(req.getParameter("priority"));
        boolean status = Boolean.parseBoolean(req.getParameter("status"));
        String description = req.getParameter("description");

        Setting setting = new Setting();
        setting.setName(name);
        setting.setType(type);
        setting.setValue(value);
        setting.setPriority(priority);
        setting.setStatus(status);
        setting.setDescription(description);

        settingService.saveOrUpdateSetting(setting);

        resp.sendRedirect("settings");
    }
}
