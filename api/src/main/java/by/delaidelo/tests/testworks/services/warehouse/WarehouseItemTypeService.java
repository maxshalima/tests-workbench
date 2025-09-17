package by.delaidelo.tests.testworks.services.warehouse;

import by.delaidelo.tests.testworks.dao.WarehouseItemTypeRepository;
import by.delaidelo.tests.testworks.dto.SelectListItemDto;
import by.delaidelo.tests.testworks.dto.WarehouseItemTypeDto;
import by.delaidelo.tests.testworks.mappers.WarehouseItemTypeEntityMapper;
import by.delaidelo.tests.testworks.mappers.WarehouseItemTypeMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseItemTypeService {
    private final WarehouseItemTypeRepository repository;
    private final WarehouseItemTypeMapper mapper;

    private final WarehouseItemTypeEntityMapper entityMapper;

    public WarehouseItemTypeService(WarehouseItemTypeRepository repository, WarehouseItemTypeMapper mapper, WarehouseItemTypeEntityMapper warehouseItemTypeEntityMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.entityMapper = warehouseItemTypeEntityMapper;
    }

    @Transactional(readOnly = true)
    public WarehouseItemTypeDto findById(@NotNull Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Page<WarehouseItemTypeDto> find(String query, Pageable pageable) {
        return repository.findByTitleContainsIgnoreCase(Optional.ofNullable(query).orElse(""), pageable)
                .map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<SelectListItemDto> findSimple(String query) {
        return repository.findByTitleContainsIgnoreCase(query, Pageable.ofSize(20))
                .map(entityMapper::fromWarehouseItemType)
                .toList();
    }

    @Transactional
    public Long create(@NotNull WarehouseItemTypeDto dto) {
        final var item = mapper.fromDto(dto);
        repository.save(item);
        return item.getId();
    }

    @Transactional
    public void update(@NotNull Long id, @NotNull WarehouseItemTypeDto dto) {
        final var item = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        mapper.update(item, dto);
        repository.save(item);
    }

    @Transactional
    public void deleteById(@NotNull Long id) {
        repository.deleteById(id);
    }
}
