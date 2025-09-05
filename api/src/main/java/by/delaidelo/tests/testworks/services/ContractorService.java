package by.delaidelo.tests.testworks.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.delaidelo.tests.testworks.dao.ContractorRepository;
import by.delaidelo.tests.testworks.dto.ContractorDto;
import by.delaidelo.tests.testworks.dto.SelectListItemDto;
import by.delaidelo.tests.testworks.mappers.ContractorEntityMapper;
import by.delaidelo.tests.testworks.mappers.ContractorMapper;
import jakarta.validation.constraints.NotNull;

@Service
public class ContractorService {
    private final ContractorRepository contractorRepository;
    private final ContractorMapper contractorMapper;
    private final ContractorEntityMapper entityMapper;

    public ContractorService(ContractorRepository contractorRepository, ContractorMapper contractorMapper,
            ContractorEntityMapper entityMapper) {
        this.contractorRepository = contractorRepository;
        this.contractorMapper = contractorMapper;
        this.entityMapper = entityMapper;
    }

    @Transactional(readOnly = true)
    public Page<ContractorDto> find(String query, Pageable pageable) {
        return contractorRepository.findByQuery(query, pageable)
                .map(contractorMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<SelectListItemDto> findSimple(String query) {
        return contractorRepository.findByQuery(query, Pageable.unpaged())
            .map(entityMapper::fromContractor)
            .toList();
    }

    @Transactional
    public Long create(@NotNull ContractorDto dto) {
        final var c = contractorMapper.fromDto(dto);
        contractorRepository.save(c);
        return c.getId();
    }

    @Transactional
    public void update(@NotNull Long id, @NotNull ContractorDto dto) {
        final var c = contractorRepository.findById(id).orElseThrow();
        contractorMapper.update(c, dto);
        contractorRepository.save(c);
    }

    @Transactional
    public void delete(@NotNull Long id) {
        contractorRepository.deleteById(id);
    }
}
