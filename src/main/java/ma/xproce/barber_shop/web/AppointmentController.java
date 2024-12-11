package ma.xproce.barber_shop.web;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ma.xproce.barber_shop.DAO.entities.Appointment;
import ma.xproce.barber_shop.DAO.entities.UserBr;
import ma.xproce.barber_shop.Service.AppointmentManager;
import ma.xproce.barber_shop.Service.UserBrManager;
import ma.xproce.barber_shop.config.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppointmentController {
    @Autowired
    UserBrManager userBrManager;
    @Autowired
    AppointmentManager appointmentManager;

    @GetMapping("/make-appointment")
    public String showMakeAppointmentPage(Model model, HttpSession session) {
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            UserBr user = userBrManager.findUserBrByEmail(username);
            session.setAttribute("email", user.getEmail());
            session.setAttribute("fullName", user.getFullName());

            List<String> bookedDates = appointmentManager.getBookedDates();
            model.addAttribute("bookedDates", bookedDates);

            return "make-appointment";
        }

        return "make-appointment";
    }

    @GetMapping("/list-appointments")
    public String ListAppointments(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "taille", defaultValue = "6") int taille,
                           @RequestParam(name = "search", defaultValue = "") String keyword) {
        String username = SecurityUtil.getSessionUser();
        UserBr user = userBrManager.findUserBrByEmail(username);

        Page<Appointment> appointments = appointmentManager.searchAppointments(keyword, user, page, taille);
        model.addAttribute("listAppointments", appointments.getContent());
        int[] pages = new int[appointments.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "list-appointments";
    }

    @PostMapping("/make-appointment")
    public String addAppointment(Model model, @Valid Appointment appointment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            return "make-appointment";
        }
        appointment.generateReference();
        appointmentManager.addAppointment(appointment);

        return "redirect:list-appointments";
    }

    @GetMapping("/edit-appointment/{id}")
    public String editAppointment(@PathVariable("id") int id, Model model) {
        Appointment appointment = appointmentManager.findAppointmentById(id);
        model.addAttribute("appointment", appointment);

        return "/edit-appointment";
    }

    @PostMapping("/edit-appointment/{id}")
    public String updateAppointment(@PathVariable("id") int id, Appointment appointment) {
        System.out.println(appointment);
        appointmentManager.updateAppointment(appointment);
        return "redirect:/list-appointments";
    }

    @GetMapping("/deleteAppointment")
    public String deleteAppointment(Model model, @RequestParam(name = "id") Integer id) {
        if (appointmentManager.deleteAppointmentById(id)) {
            return "redirect:list-appointments";
        } else {
            return "/page-error";
        }
    }
}
