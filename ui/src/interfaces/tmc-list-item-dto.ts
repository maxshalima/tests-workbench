import { BaseDto } from './base-dto';

export interface TmcTableItemDto extends BaseDto {
    name: string;
    quantity: string;
    price: string;
    sum: string;
}
