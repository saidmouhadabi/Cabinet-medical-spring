package example.dedo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="Medecin")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id_medecin;
    @Column(nullable = false)
    private String Full_name;
    @Column(nullable = false)
    private int tele;
    @Column(nullable = false)
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_amb", referencedColumnName = "id_amb")
    @NotFound(action= NotFoundAction.IGNORE)
    private Ambulance ambulance;
    @Column(nullable = false)
    private String etat;

}
