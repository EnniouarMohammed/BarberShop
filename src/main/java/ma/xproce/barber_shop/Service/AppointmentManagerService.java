package ma.xproce.barber_shop.Service;

import ma.xproce.barber_shop.DAO.entities.Appointment;
import ma.xproce.barber_shop.DAO.entities.UserBr;
import ma.xproce.barber_shop.DAO.repositories.AppointmentRepository;
import ma.xproce.barber_shop.DAO.repositories.UserBrRepository;
import ma.xproce.barber_shop.config.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

import java.util.List;

@Service
public class AppointmentManagerService implements AppointmentManager {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserBrRepository userBrRepository;

    private UserBr getCurrentUser() {
        String email = SecurityUtil.getSessionUser();
        return userBrRepository.findUserBrByEmail(email);
    }

    @Override
    public Appointment addAppointment(Appointment appointment) {
        try {
            UserBr user = getCurrentUser();
            appointment.setCreatedBy(user);
            return appointmentRepository.save(appointment);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        try {
            UserBr user = getCurrentUser();
            if (appointment.getCreatedBy().equals(user)) {
                return appointmentRepository.save(appointment);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public boolean deleteAppointmentById(Integer id) {
        try {
            UserBr user = getCurrentUser();
            Appointment appointment = appointmentRepository.findById(id).orElse(null);
            if (appointment != null && appointment.getCreatedBy().equals(user)) {
                appointmentRepository.deleteById(id);
                return true;
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return false;
    }

    @Override
    public Appointment findAppointmentById(int id) {
        try {
            UserBr user = getCurrentUser();
            Appointment appointment = appointmentRepository.findById(id).orElse(null);
            if (appointment != null && appointment.getCreatedBy().equals(user)) {
                return appointment;
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        try {
            UserBr user = getCurrentUser();
            return appointmentRepository.findByCreatedBy(user);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
    public List<String> getBookedDates() {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<String> bookedDates = new ArrayList<>();

        for (Appointment appointment : appointments) {
            bookedDates.add(appointment.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }

        return bookedDates;
    }

    @Override
    public Page<Appointment> searchAppointments(String keyword, UserBr user, int page, int taille) {
        return appointmentRepository.findByReferenceContainsAndCreatedBy(keyword, user, PageRequest.of(page, taille));
    }
}
