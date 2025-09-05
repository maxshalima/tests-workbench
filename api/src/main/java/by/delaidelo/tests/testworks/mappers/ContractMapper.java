package by.delaidelo.tests.testworks.mappers;

import org.mapstruct.Mapper;

import by.delaidelo.tests.testworks.domain.Contract;
import by.delaidelo.tests.testworks.dto.ContractDto;

@Mapper(uses = {ContractorEntityMapper.class})
public interface ContractMapper extends MappableEntity<Contract, ContractDto> {

}
