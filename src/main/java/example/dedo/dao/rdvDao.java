package example.dedo.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
// import java.util.Collection;
import java.util.List;

// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
// import org.hibernate.mapping.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import example.dedo.model.Patient;
import example.dedo.model.rdv;


public interface rdvDao extends JpaRepository<rdv, Integer> {
    
    @Query(value = "SELECT r.date_rdv FROM rdv r")
    public List<Date> dates();

    @Query(value = "SELECT r from rdv r where r.date_rdv= ?1 and r.horaire= ?2 and r.id_rdv not in ?3")
    public List<rdv> checkDATE(Date data, String horaire,int id);

    @Query(value = "SELECT r from rdv r where r.date_rdv=CURRENT_DATE ")
    public List<rdv> todayRDV();
    
    @Query(value = "select p from Patient p where p.full_name=?1")
    public Patient findPatinetByFullName(String fullName);

}
