package by.delaidelo.tests.testworks.enums;


public enum WarehouseItemSubgroup {
    // GOODS
    GOODS_PURCHASED(WarehouseItemGroup.GOODS),
    GOODS_ON_COMMISSION(WarehouseItemGroup.GOODS),
    PACKAGING_GOODS(WarehouseItemGroup.GOODS),
    SAFEKEEPING(WarehouseItemGroup.GOODS),
    
    // Materials
    SPARE_PARTS(WarehouseItemGroup.MATERIALS),
    PURCHASED_AND_SEMIFINISHED(WarehouseItemGroup.MATERIALS),
    FUEL(WarehouseItemGroup.MATERIALS),
    CONTAINERS_AND_PACKAGING(WarehouseItemGroup.MATERIALS),

    // FIXED ASSETS
    FA_BUILDINGS(WarehouseItemGroup.FIXED_ASSETS),
    FA_STRUCTURES(WarehouseItemGroup.FIXED_ASSETS),
    FA_TRANSMISSION_DEVICES(WarehouseItemGroup.FIXED_ASSETS),
    FA_MACHINERY_AND_EQUIPMENT(WarehouseItemGroup.FIXED_ASSETS),
    FA_TRANSPORT(WarehouseItemGroup.FIXED_ASSETS),
    FA_TOOLS(WarehouseItemGroup.FIXED_ASSETS),
    FA_INDUSTRIAL_AND_HOUSEHOLD_EQUIPMENT(WarehouseItemGroup.FIXED_ASSETS),
    // NCA
    NCA_PURCHASED(WarehouseItemGroup.NON_CURRENT_ASSETS);
    
    private final WarehouseItemGroup group;

    WarehouseItemSubgroup(WarehouseItemGroup group) {
        this.group = group;
    }

    public WarehouseItemGroup getGroup() {
        return group;
    }
}
