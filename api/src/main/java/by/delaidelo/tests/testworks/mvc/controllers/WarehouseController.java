package by.delaidelo.tests.testworks.mvc.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.delaidelo.tests.testworks.dto.WarehouseDto;
import by.delaidelo.tests.testworks.services.WarehousesService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/warehouses")
@CrossOrigin("*")
public class WarehouseController {
    private final WarehousesService service;

    public WarehouseController(WarehousesService service) {
        this.service = service;
    }

    @GetMapping
    public Page<WarehouseDto> find(Pageable pageable) {
        return service.findWarehouses(pageable);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody WarehouseDto dto) {
        final var id = service.create(dto);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id:\\d+}")
    public void update(@PathVariable("id") Long id, @RequestBody @Valid WarehouseDto dto) {
        service.update(id, dto);
    }

    @PostMapping("/{id:\\d+}/switch")
    public void switchState(@PathVariable("id") Long id) {
        service.switchState(id);
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}

