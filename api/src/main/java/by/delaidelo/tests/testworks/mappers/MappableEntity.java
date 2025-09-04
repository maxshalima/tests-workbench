package by.delaidelo.tests.testworks.mappers;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import by.delaidelo.tests.testworks.domain.AbstractEntity;

public interface MappableEntity<K extends AbstractEntity, T> {
    T toDto(K entity);

    @Mapping(target = "id", ignore = true)
    K fromDto(T dto);

    @InheritConfiguration
    void update(@MappingTarget K entity, T dto);
}
