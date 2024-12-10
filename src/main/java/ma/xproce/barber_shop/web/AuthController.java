package ma.xproce.barber_shop.web;

import jakarta.validation.Valid;
import ma.xproce.barber_shop.DAO.entities.UserBr;
import ma.xproce.barber_shop.DTO.RegistrationDto;
import ma.xproce.barber_shop.Service.UserBrManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    UserBrManager userBrManager;

    /**********Register************/
    @GetMapping("/auth-sign-up")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "auth-sign-up";
    }

    @PostMapping("/auth-sign-up")
    public String register(@Valid @ModelAttribute("user")RegistrationDto user, Model model, BindingResult bindingResult) {

        UserBr existingUserEmail = userBrManager.findUserBrByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()){
            return "redirect:/auth-sign-up?alreadyExists";
        }

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            return "auth-sign-up";
        }

        userBrManager.addUser(user);
        return "redirect:/auth-sign-in?success";
    }

    /************Login************/
    @GetMapping("/auth-sign-in")
    public String loginPage() {
        return "auth-sign-in";
    }
}
