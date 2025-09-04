package by.delaidelo.tests.testworks.domain;

import by.delaidelo.tests.testworks.enums.WarehouseItemGroup;
import by.delaidelo.tests.testworks.enums.WarehouseItemSubgroup;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "warehouse_item_types")
@Getter
@Setter
public class WarehouseItemType extends AbstractEntity {

    @Column
    private String title;

    @Column(length = 10)
    private String units;
    
    @Column(length = 32)
    private String gtin;
    
    @Column(name = "item_group", nullable = false)
    @Enumerated(EnumType.STRING)
    private WarehouseItemGroup group;
    
    @Column(name = "item_subgroup")
    @Enumerated(EnumType.STRING)
    private WarehouseItemSubgroup subgroup;
}
