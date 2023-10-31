package example.dedo.dao;

import example.dedo.model.Medecin;
import example.dedo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedecinDAO extends JpaRepository<Medecin, Integer> {
    @Query(value = "SELECT m.tele FROM Medecin m where m.id_medecin = ?1")
    public int medcTele(int id);

}
