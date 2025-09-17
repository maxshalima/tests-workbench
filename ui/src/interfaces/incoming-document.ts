import { BaseDto } from './base-dto';
import {SelectListItemDto} from "./select-list-item-dto";
import {IncomingDocumentItemDto} from "./tmc-list-item-dto";

export interface IncomingDocument extends BaseDto {
    documentDate: Date;
    documentNumber: number;
    contractor: SelectListItemDto;
    contract: SelectListItemDto;
    warehouse: SelectListItemDto;
    items: IncomingDocumentItemDto[];

    enabled: boolean;
}
