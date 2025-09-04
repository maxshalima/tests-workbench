package by.delaidelo.tests.testworks.mappers;

import org.mapstruct.Mapper;

import by.delaidelo.tests.testworks.domain.Warehouse;
import by.delaidelo.tests.testworks.dto.WarehouseDto;

@Mapper
public interface WarehouseMapper extends MappableEntity<Warehouse, WarehouseDto> {
}
