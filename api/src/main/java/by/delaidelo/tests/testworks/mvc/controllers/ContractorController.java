package by.delaidelo.tests.testworks.mvc.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.delaidelo.tests.testworks.dto.ContractorDto;
import by.delaidelo.tests.testworks.dto.SelectListItemDto;
import by.delaidelo.tests.testworks.services.ContractorService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/contractors")
@CrossOrigin("*")
public class ContractorController {
    private final ContractorService contractorService;

    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @GetMapping
    public Page<ContractorDto> find(@RequestParam(defaultValue = "", value = "query") String query, Pageable pageable) {
        return contractorService.find(query, pageable);
    }

    /**
     * Return no more than 20 records
     * @param query
     * @return
     */
    @GetMapping("/simple")
    public List<SelectListItemDto> findSimple(@RequestParam(defaultValue = "") String query) {
        return contractorService.findSimple(query);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid ContractorDto dto) {
        final var id = contractorService.create(dto);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id:\\d+}")
    public void update(@PathVariable Long id, @RequestBody ContractorDto dto) {
        contractorService.update(id, dto);
    }
    
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Long id) {
        contractorService.delete(id);
    }
    
    @GetMapping("/{id:\\d+}")
    public ContractorDto findById(@PathVariable Long id) {
        return contractorService.findById(id);
    }
    
}
