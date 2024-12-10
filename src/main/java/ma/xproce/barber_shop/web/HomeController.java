package ma.xproce.barber_shop.web;

import jakarta.servlet.http.HttpSession;
import ma.xproce.barber_shop.DAO.entities.UserBr;
import ma.xproce.barber_shop.Service.UserBrManager;
import ma.xproce.barber_shop.config.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    UserBrManager userBrManager;

    @GetMapping("/index")
    public String home(Model model, HttpSession session){
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            UserBr user = userBrManager.findUserBrByEmail(username);
            session.setAttribute("email", user.getEmail());
            session.setAttribute("fullName", user.getFullName());
            return "index";
        }

        return "index";
    }
}
