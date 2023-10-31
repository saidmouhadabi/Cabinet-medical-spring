package example.dedo.model;

import java.sql.Date;

// import java.util.Calendar;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

// import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="rdv")
@AllArgsConstructor
@NoArgsConstructor  
@Data 

public class rdv {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rdv", unique = true, nullable = false)
    private  int id_rdv;
    // @Temporal(TemporalType.DATE)
    private  Date date_rdv;
    private  String horaire;
    private  String type_rdv;


    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cons")
    @JsonIgnore
    @JsonSetter
    @NotFound(action=NotFoundAction.IGNORE)
    private Consultation consultation;
    @OneToOne(mappedBy = "rdve")
    private Control control;
     
    
}
