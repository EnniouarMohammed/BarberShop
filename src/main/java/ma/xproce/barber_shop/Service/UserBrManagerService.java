package ma.xproce.barber_shop.Service;

import ma.xproce.barber_shop.DAO.entities.UserBr;
import ma.xproce.barber_shop.DAO.repositories.UserBrRepository;
import ma.xproce.barber_shop.DTO.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserBrManagerService implements UserBrManager{
    @Autowired
    private UserBrRepository userBrRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void addUser(RegistrationDto registrationDto) {
        UserBr user = new UserBr();
        user.setFullName(registrationDto.getFullName());
        user.setPhoneNumber(registrationDto.getPhoneNumber());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        userBrRepository.save(user);
    }

    @Override
    public UserBr findUserBrByEmail(String email) {
        try{
            return userBrRepository.findUserBrByEmail(email);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
