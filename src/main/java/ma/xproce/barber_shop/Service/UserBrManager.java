package ma.xproce.barber_shop.Service;

import ma.xproce.barber_shop.DAO.entities.UserBr;
import ma.xproce.barber_shop.DTO.RegistrationDto;
import org.springframework.stereotype.Component;

@Component
public interface UserBrManager {
    void addUser(RegistrationDto registrationDto);

    UserBr findUserBrByEmail(String email);
}
