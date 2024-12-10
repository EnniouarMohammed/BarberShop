package ma.xproce.barber_shop.DAO.repositories;

import jakarta.transaction.Transactional;
import ma.xproce.barber_shop.DAO.entities.UserBr;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface UserBrRepository extends JpaRepository<UserBr, Integer> {
    UserBr findUserBrByEmail(String email);
}
