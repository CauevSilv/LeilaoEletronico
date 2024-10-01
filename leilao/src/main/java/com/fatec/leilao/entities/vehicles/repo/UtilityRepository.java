package com.fatec.leilao.entities.vehicles.repo;

import com.fatec.leilao.entities.vehicles.Utility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilityRepository extends JpaRepository<Utility, Long> {
}