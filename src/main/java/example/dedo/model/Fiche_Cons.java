package example.dedo.model;



// import java.util.UUID;

// import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="fiche_cons")
@AllArgsConstructor
@NoArgsConstructor 
@Data 
public class Fiche_Cons {

    @Id
    	@GeneratedValue(strategy = GenerationType.AUTO)
        private  int id_fiche;
    
}
