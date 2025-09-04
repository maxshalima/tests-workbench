package by.delaidelo.tests.testworks.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import by.delaidelo.tests.testworks.domain.WarehouseItemType;

public interface WarehouseItemTypeRepository extends JpaRepository<WarehouseItemType, Long> {
    Page<WarehouseItemType> findByTitleContainsIgnoreCase(String query, Pageable pageable);

}
