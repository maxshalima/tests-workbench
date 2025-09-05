package by.delaidelo.tests.testworks.mappers;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import by.delaidelo.tests.testworks.dao.ContractRepository;
import by.delaidelo.tests.testworks.domain.Contract;
import by.delaidelo.tests.testworks.dto.SelectListItemDto;

@Component
public class ContractEntityMapper {
    private final ContractRepository contractRepository;

    public ContractEntityMapper(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public SelectListItemDto fromContract(Contract contract) {
        if (Objects.isNull(contract))
            return null;
        final var dtd = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return new SelectListItemDto(contract.getId(), contract.getContractNumber() + " от " + dtd.format(contract.getContractDate()));
    }

    public Contract toContract(SelectListItemDto dto) {
        return Optional.ofNullable(dto)
                .map(SelectListItemDto::id)
                .flatMap(contractRepository::findById)
                .orElse(null);
    }
}
