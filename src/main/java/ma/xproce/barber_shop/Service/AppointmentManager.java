package ma.xproce.barber_shop.Service;

import ma.xproce.barber_shop.DAO.entities.Appointment;
import ma.xproce.barber_shop.DAO.entities.UserBr;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AppointmentManager {
    Appointment addAppointment(Appointment appointment);

    Appointment updateAppointment(Appointment appointment);

    boolean deleteAppointmentById(Integer id);

    Appointment findAppointmentById(int id);

    List<Appointment> getAllAppointments();
    List<String> getBookedDates();

    public Page<Appointment> searchAppointments(String keyword, UserBr user, int page, int taille);

}
