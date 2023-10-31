package example.dedo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import example.dedo.model.Consultation;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ConsDAO extends JpaRepository<Consultation, Integer> {
    @Query(value = "select c from Consultation c , Patient  p where p.id_patient=c.patient.id_patient and p.cin =?1 ")
    public List<Consultation> findConsultationByCin(String cin);
    
}
