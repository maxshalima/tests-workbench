package by.delaidelo.tests.testworks.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import by.delaidelo.tests.testworks.domain.WarehouseOperation;

public interface WarehouseOperationRepository extends JpaRepository<WarehouseOperation, Long> {
    List<WarehouseOperation> findBySourceId(Long sourceId);
    List<WarehouseOperation> findByReferenceId(Long referenceId);
}
