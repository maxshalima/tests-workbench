import { BaseDto } from './base-dto';

export interface Warehouse extends BaseDto {
    title: string|undefined;
    address: string|undefined;
    enabled: boolean|undefined;
}
