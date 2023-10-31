package example.dedo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="medicament")
@AllArgsConstructor
@NoArgsConstructor 
@Data 

public class Medicament {
        @Id
    	@GeneratedValue(strategy = GenerationType.AUTO)
        private  int id_medi;
    
}
