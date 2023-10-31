package example.dedo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Ambulance")
@AllArgsConstructor
@NoArgsConstructor 
@Data 

public class Ambulance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_amb;
    //private  int id_patient;
    private  String localisation;
    @Column(nullable = false)
    private  String hopital;
    @Column(nullable = false)
    private  String etat;

    @OneToOne
    @Cascade({ CascadeType.ALL })
    @JoinColumn(name = "id_cons", referencedColumnName="id_cons")
    @NotFound(action=NotFoundAction.IGNORE)
    private Consultation consultation;
    @OneToOne(mappedBy = "ambulance")
    private Infirmier infirmier;
    @OneToOne(mappedBy = "ambulance")
    private Medecin medecin;
    @OneToOne
    @Cascade({ CascadeType.ALL })
    @JoinColumn(name = "id_control", referencedColumnName="id_control")
    @NotFound(action=NotFoundAction.IGNORE)
    private Control control;

}
