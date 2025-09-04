package by.delaidelo.tests.testworks.mvc.controllers;

import by.delaidelo.tests.testworks.dto.WarehouseItemTypeDto;
import by.delaidelo.tests.testworks.services.WarehouseItemTypeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item-types")
@CrossOrigin("*")
public class WarehouseItemTypeController {
    private final WarehouseItemTypeService service;

    public WarehouseItemTypeController(WarehouseItemTypeService service) {
        this.service = service;
    }

    @GetMapping
    public Page<WarehouseItemTypeDto> find(@RequestParam(defaultValue = "", value = "query") String query,
                                           @RequestParam(required = false, value = "pageSize") Integer pageSize,
                                           @RequestParam(required = false, value = "pageNumber") Integer pageNumber) {
        return service.find(query, pageSize, pageNumber);
    }

    @GetMapping("/{id:\\d+}")
    public WarehouseItemTypeDto findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid WarehouseItemTypeDto dto) {
        final var id = service.create(dto);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody @Valid WarehouseItemTypeDto dto) {
        service.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
