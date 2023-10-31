package example.dedo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import example.dedo.model.Ambulance;

public interface  AmbDAO extends JpaRepository<Ambulance, Integer> {


}
