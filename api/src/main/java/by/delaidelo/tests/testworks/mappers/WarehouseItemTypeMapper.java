package by.delaidelo.tests.testworks.mappers;

import org.mapstruct.Mapper;

import by.delaidelo.tests.testworks.domain.WarehouseItemType;
import by.delaidelo.tests.testworks.dto.WarehouseItemTypeDto;

@Mapper
public interface WarehouseItemTypeMapper extends MappableEntity<WarehouseItemType, WarehouseItemTypeDto> {
    
}