import { BaseDto } from './base-dto';

export interface WarehouseItemType extends BaseDto {
    title: string;
    units: string;
    gtin: string | undefined;
    group: string;
    subgroup: string | undefined;
}
