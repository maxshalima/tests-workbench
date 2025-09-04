package by.delaidelo.tests.testworks.dto;

import by.delaidelo.tests.testworks.enums.WarehouseItemGroup;
import by.delaidelo.tests.testworks.enums.WarehouseItemSubgroup;
import lombok.Data;

@Data
public class WarehouseItemTypeDto {
    private Long id;
    private String title;
    private String units;
    private String gtin;
    private WarehouseItemGroup group;
    private WarehouseItemSubgroup subgroup;
}
