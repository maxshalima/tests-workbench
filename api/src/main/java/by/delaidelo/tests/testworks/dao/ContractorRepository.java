package by.delaidelo.tests.testworks.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import by.delaidelo.tests.testworks.domain.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    @Query("SELECT c FROM Contractor c WHERE c.taxNumber LIKE %:query% OR c.shortTitle ILIKE %:query%")
    Page<Contractor> findByQuery(@Param("query") String query, Pageable pageable);
}
