package by.delaidelo.tests.testworks.mvc.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import by.delaidelo.tests.testworks.dto.WarehouseDto;
import by.delaidelo.tests.testworks.services.WarehousesService;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
@CrossOrigin("*")
public class WarehouseController {
    private final WarehousesService service;

    public WarehouseController(WarehousesService service) {
        this.service = service;
    }

    @GetMapping
    public List<WarehouseDto> find() {
        return service.findWarehouses();
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

