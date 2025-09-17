import { BaseDto } from './base-dto';

export interface IncomingDocumentItemDto extends BaseDto {
    warehouseItemTypeId: string,
    warehouseItemTypeTitle: string,
    name: string;
    quantity: string;
    price: string;
    sum: string;
}
