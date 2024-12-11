package ma.xproce.barber_shop.DAO.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty()
    private String fullName;

    @NotEmpty()
    private String email;

    @NotEmpty()
    private String barber;

    @NotEmpty()
    private String service;

    @NotNull
    private LocalDateTime date;

    @NotEmpty()
    private String message;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", nullable = false)
    private UserBr createdBy;

    @NotEmpty()
    private String reference;

    public void generateReference() {
        String prefix = "#";
        Random random = new Random();
        int randomNumber = random.nextInt(999999);
        this.reference = prefix + String.format("%07d", randomNumber);
    }
}
