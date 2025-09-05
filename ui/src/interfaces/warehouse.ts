import { BaseDto } from './base-dto';

export interface Warehouse extends BaseDto {
    title: string;
    address: string|undefined;
    enabled: boolean;
}
