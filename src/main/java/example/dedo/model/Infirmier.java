package example.dedo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="Infirmier")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Infirmier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id_infirmier;
    @Column(nullable = false)
    private String Full_name;
    @Column(nullable = false)
    private Long tele;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String etat ;
    @OneToOne(cascade = CascadeType.ALL)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "id_amb", referencedColumnName = "id_amb")
    private Ambulance ambulance;

}
