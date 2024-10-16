package com.example.AppMutant.repository;

import com.example.AppMutant.model.Dna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DnaRepository extends JpaRepository<Dna, String> {
    long countByIsMutant(boolean isMutant);
}
