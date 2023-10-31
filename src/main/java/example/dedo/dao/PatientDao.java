package example.dedo.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import example.dedo.model.Patient;

//import java.util.UUID;


public interface PatientDao extends JpaRepository<Patient, Integer>{
   
    @Query(value = "SELECT full_name FROM patient",nativeQuery=true)
    public List<Patient> Patients();

    // @Query(value = "SELECT p FROM Patient p")
    // public List<Patient> getPatientById();

    public List <Patient> findByCinContaining(String cin);
}
