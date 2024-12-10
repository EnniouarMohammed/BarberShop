package ma.xproce.barber_shop.DAO.repositories;

import jakarta.transaction.Transactional;
import ma.xproce.barber_shop.DAO.entities.Appointment;
import ma.xproce.barber_shop.DAO.entities.UserBr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Page<Appointment> findByReferenceContainsAndCreatedBy(String keyword, UserBr user, Pageable pageable);
    List<Appointment> findByCreatedBy(UserBr createdBy);
}
