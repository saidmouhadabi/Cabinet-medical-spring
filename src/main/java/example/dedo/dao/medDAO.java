package example.dedo.dao;

import example.dedo.model.Control;
import example.dedo.model.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface medDAO extends JpaRepository<Medecin, Integer> {

}
