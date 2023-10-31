package example.dedo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

// import jakarta.persistence.CascadeType;

// import org.hibernate.annotations.ManyToAny;

// import java.util.UUID;

// import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="consultation")
@AllArgsConstructor
@NoArgsConstructor 
@Data 
public class Consultation {
        @Id
    	@GeneratedValue(strategy = GenerationType.AUTO)
        private int id_cons;
        //private  int id_patient;
		@Column(nullable = false)
		private  String description;
	@Column(nullable = false)
		private  String diagnostics;
	@Column(nullable = false)
	private  String ResConsultation;
	@Column(nullable = false)
		private  String medicaments;
	@Column(nullable = false)
		private  String medic_desc;
	@Column(nullable = false)
		private  double montant;
	
        @ManyToOne
		@JoinColumn(name = "id_patient")
		private Patient patient;
        
		@OneToOne(mappedBy = "consultation")
		@NotFound(action=NotFoundAction.IGNORE)
    	private rdv rdv;

		@OneToOne(mappedBy = "consultation")
    	private Ambulance ambulance;

}
