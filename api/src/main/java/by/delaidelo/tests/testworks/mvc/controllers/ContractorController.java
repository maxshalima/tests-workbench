package by.delaidelo.tests.testworks.mvc.controllers;

import by.delaidelo.tests.testworks.dto.ContractorDto;
import by.delaidelo.tests.testworks.dto.SelectListItemDto;
import by.delaidelo.tests.testworks.services.ContractorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/contractors")
public class ContractorController {
    private final ContractorService service;

    public ContractorController(ContractorService contractorService) {
        this.service = contractorService;
    }

    @GetMapping
    public Page<ContractorDto> find(@RequestParam(defaultValue = "", value = "query") String query, Pageable pageable) {
        return service.find(query, pageable);
    }

    /**
     * Return no more than 20 records
     * @param query
     * @return
     */
    @GetMapping("/simple")
    public List<SelectListItemDto> findSimple(@RequestParam(defaultValue = "") String query) {
        return service.findSimple(query);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid ContractorDto dto) {
        final var id = service.create(dto);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id:\\d+}")
    public void update(@PathVariable Long id, @RequestBody ContractorDto dto) {
        service.update(id, dto);
    }
    
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
    @GetMapping("/{id:\\d+}")
    public ContractorDto findById(@PathVariable Long id) {
        return service.findById(id);
    }
    
}
