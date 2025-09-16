package by.delaidelo.tests.testworks.mvc.controllers;

import by.delaidelo.tests.testworks.dto.IncomingDocumentDto;
import by.delaidelo.tests.testworks.services.incomingDocument.IncomingDocumentsService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incoming-documents")
@CrossOrigin("*")
public class IncomingDocumentController {
    private final IncomingDocumentsService service;

    public IncomingDocumentController(IncomingDocumentsService service) {
        this.service = service;
    }

    @GetMapping("/{id:\\d+}")
    public IncomingDocumentDto findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
    @GetMapping
    public Page<IncomingDocumentDto> find(@RequestParam(required = false, defaultValue = "") String query, Pageable pageable) {
        return service.findIncomingDocuments(query, pageable);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody IncomingDocumentDto dto) {
        final var id = service.create(dto);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id:\\d+}")
    public void update(@PathVariable("id") Long id, @RequestBody @Valid IncomingDocumentDto dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}

