package example.dedo.dao;

import example.dedo.model.Control;
import example.dedo.model.Infirmier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InfDAO extends JpaRepository<Infirmier, Integer> {
    @Query(value = "SELECT i.tele FROM Infirmier i where i.id_infirmier = ?1")
    public int infTele(int id);

}
