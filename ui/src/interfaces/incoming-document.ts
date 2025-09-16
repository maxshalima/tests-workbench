import { BaseDto } from './base-dto';

export interface IncomingDocument extends BaseDto {
    title: string;
    address: string|undefined;
    enabled: boolean;
}
