package example.dedo.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="control")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Control {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id_control;
    @Column(nullable = false)
    private String Resultat_Control;
    @Column(nullable = false)
    private String Description;
    @Column(nullable = false)
    private  String diagnostics;
    @Column(nullable = false)
    private  String medicaments;
    @Column(nullable = false)
    private  String medic_desc;
    @Column(nullable = false)
    private  double montant;
    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;
    @OneToOne(mappedBy = "control")
    private Ambulance ambulance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rdv", referencedColumnName = "id_rdv")
    private rdv rdve;


}
