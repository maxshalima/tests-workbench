package by.delaidelo.tests.testworks.mappers;

import org.mapstruct.Mapper;

import by.delaidelo.tests.testworks.domain.Contractor;
import by.delaidelo.tests.testworks.dto.ContractorDto;

@Mapper
public interface ContractorMapper extends MappableEntity<Contractor, ContractorDto> {

}
