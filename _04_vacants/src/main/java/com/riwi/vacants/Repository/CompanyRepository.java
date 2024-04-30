package com.riwi.vacants.Repository;



import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.riwi.vacants.Entity.Company;
@Repository
public interface CompanyRepository extends JpaRepository<Company, String>{


}
