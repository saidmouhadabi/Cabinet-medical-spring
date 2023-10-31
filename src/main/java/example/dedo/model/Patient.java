package example.dedo.model;

import java.util.ArrayList;
import java.util.Collection;

// import java.util.UUID;

// import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="patient")
@AllArgsConstructor
@NoArgsConstructor 
@Data 
public class Patient {
        @Id
    	@GeneratedValue(strategy = GenerationType.AUTO)
        private  int id_patient;
		@Column(nullable = false)
		private  String full_name;
	@Column(nullable = false)
	private  String cin;
		@Column(nullable = false)
		private  String email;
	@Column(nullable = false)
		private  String adresse;
	@Column(nullable = false)
		private  String date_naiss;
	@Column(nullable = false)
		private  int age;
	@Column(nullable = false)
		private  int tele;
		private  String securite_medicale;
	@Column(nullable = false)
		private  String sexe;
		//private  String photo;
		@OneToMany(mappedBy = "patient",fetch=FetchType.LAZY)
        private Collection<Consultation> Consultation=new ArrayList<>();
        
		@OneToMany(mappedBy = "patient",fetch=FetchType.LAZY)
        private Collection<rdv> rdvs=new ArrayList<>();
	@OneToMany(mappedBy = "patient",fetch=FetchType.LAZY)
	private Collection<Control> control=new ArrayList<>();
        
        
        
}
