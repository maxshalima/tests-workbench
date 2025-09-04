package by.delaidelo.tests.testworks.services;

import by.delaidelo.tests.testworks.dao.WarehouseItemTypeRepository;
import by.delaidelo.tests.testworks.dto.WarehouseItemTypeDto;
import by.delaidelo.tests.testworks.mappers.WarehouseItemTypeMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class WarehouseItemTypeService {
    private final WarehouseItemTypeRepository repository;
    private final WarehouseItemTypeMapper mapper;

    public WarehouseItemTypeService(WarehouseItemTypeRepository repository, WarehouseItemTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public WarehouseItemTypeDto findById(@NotNull Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Page<WarehouseItemTypeDto> find(String query, Integer pageSize, Integer pageNumber) {
        Pageable pageable;
        if (Objects.nonNull(pageSize) && Objects.nonNull(pageNumber)) {
            pageable = Pageable.ofSize(pageSize)
                    .withPage(pageNumber);
        } else {
            pageable = Pageable.unpaged();
        }
        return repository.findByTitleContainsIgnoreCase(Optional.ofNullable(query).orElse(""), pageable)
                .map(mapper::toDto);
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
