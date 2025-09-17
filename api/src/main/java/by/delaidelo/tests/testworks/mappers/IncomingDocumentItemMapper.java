package by.delaidelo.tests.testworks.mappers;

import by.delaidelo.tests.testworks.domain.IncomingDocumentItem;
import by.delaidelo.tests.testworks.dto.IncomingDocumentItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IncomingDocumentItemMapper {
    @Mapping(source = "warehouseItemType.id", target = "warehouseItemTypeId")
    @Mapping(source = "warehouseItemType.title", target = "warehouseItemTypeTitle")
    IncomingDocumentItemDto toDto(IncomingDocumentItem entity);

    @Mapping(target = "warehouseItemType", ignore = true)
    @Mapping(source = "warehouseItemTypeId", target = "warehouseItemType.id")
    IncomingDocumentItem toEntity(IncomingDocumentItemDto dto);
}
