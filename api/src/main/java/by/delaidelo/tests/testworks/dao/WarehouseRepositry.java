package by.delaidelo.tests.testworks.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import by.delaidelo.tests.testworks.domain.Warehouse;

public interface WarehouseRepositry extends JpaRepository<Warehouse, Long> {
} 
