package ma.xproce.barber_shop.DAO.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Entity
@Data
@Table(name = "userbr")
@NoArgsConstructor
@AllArgsConstructor
public class UserBr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty()
    private String fullName;

    @NotEmpty()
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @NotEmpty()
    private String phoneNumber;

    @NotEmpty()
    private String password;

}
