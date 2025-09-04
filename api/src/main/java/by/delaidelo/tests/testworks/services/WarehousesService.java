package by.delaidelo.tests.testworks.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.delaidelo.tests.testworks.dao.WarehouseRepositry;
import by.delaidelo.tests.testworks.dto.WarehouseDto;
import by.delaidelo.tests.testworks.mappers.WarehouseMapper;
import jakarta.validation.constraints.NotNull;

@Service
public class WarehousesService {
    private final WarehouseRepositry warehouseRepositry;
    private final WarehouseMapper mapper;
    public WarehousesService(WarehouseRepositry warehouseRepositry, WarehouseMapper mapper) {
        this.warehouseRepositry = warehouseRepositry;
        this.mapper = mapper;
    }

    public List<WarehouseDto> findWarehouses() {
        return warehouseRepositry.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional
    public Long create(WarehouseDto dto) {
        final var w = mapper.fromDto(dto);
        warehouseRepositry.save(w);
        return w.getId();
    }

    @Transactional
    public void update(@NotNull Long id, @NotNull WarehouseDto dto) {
        final var warehouse = warehouseRepositry.findById(id).orElseThrow();
        mapper.update(warehouse, dto);
        warehouseRepositry.save(warehouse);
    }

    @Transactional
    public void switchState(@NotNull Long id) {
        final var warehouse = warehouseRepositry.findById(id).orElseThrow();
        warehouse.setEnabled(!warehouse.isEnabled());
    }

    @Transactional
    public void delete(@NotNull Long id) {
        final var warehouse = warehouseRepositry.findById(id).orElseThrow();
        warehouseRepositry.delete(warehouse);
    }
}
