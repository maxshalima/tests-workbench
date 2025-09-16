package by.delaidelo.tests.testworks.dao;

import by.delaidelo.tests.testworks.domain.IncomingDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomingDocumentRepository extends JpaRepository<IncomingDocument, Long> {
    Page<IncomingDocument> findByDocumentNumberContainingIgnoreCase(String query, Pageable pageable);
} 
