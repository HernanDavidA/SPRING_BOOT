package com.riwi.vacants.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.vacants.Entity.Vacant;

@Repository
public interface VacantRepository extends JpaRepository<Vacant, Long>{

}
