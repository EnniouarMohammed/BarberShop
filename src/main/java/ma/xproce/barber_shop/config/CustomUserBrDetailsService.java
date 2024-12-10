package ma.xproce.barber_shop.config;

import ma.xproce.barber_shop.DAO.entities.UserBr;
import ma.xproce.barber_shop.DAO.repositories.UserBrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserBrDetailsService implements UserDetailsService {
    @Autowired
    private UserBrRepository userBrRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserBr user = userBrRepository.findUserBrByEmail(email);
        if(user != null) {
            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}