package example.dedo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import example.dedo.model.Control;


public interface ControlDAO extends JpaRepository<Control, Integer> {
    
    
}
