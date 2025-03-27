package ar.edu.centro8.ps.ejemplobase2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.edu.centro8.ps.ejemplobase2.model.Auto;

public interface AutoRepository extends JpaRepository<Auto, Long> {
    
}

